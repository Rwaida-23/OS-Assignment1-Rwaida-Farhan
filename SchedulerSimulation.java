import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList; // Feature 3
import java.util.List;      // Feature 3

// ANSI Color Codes for enhanced terminal output
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_GREEN = "\u001B[42m";
    public static final String WHITE = "\u001B[37m";
    public static final String BRIGHT_WHITE = "\u001B[97m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

// Class representing a process that implements Runnable to be run by a thread
class Process implements Runnable {
    private String name;
    private int burstTime;
    private int timeQuantum;
    private int remainingTime;

    // Feature 1
    private int priority;

    // Feature 3: tracking times
    private long creationTime;
    private long finishTime;
    private long waitingTime;

    public Process(String name, int burstTime, int timeQuantum) {
        this.name = name;
        this.burstTime = burstTime;
        this.timeQuantum = timeQuantum;
        this.remainingTime = burstTime;

        this.priority = (int)(Math.random() * 5) + 1; // Feature 1

        this.creationTime = System.currentTimeMillis(); // Feature 3
    }

    @Override
    public void run() {
        int runTime = Math.min(timeQuantum, remainingTime);

        String quantumBar = createProgressBar(0, 15);
        System.out.println(Colors.BRIGHT_GREEN + "  ▶️ " + Colors.BOLD + Colors.CYAN + name +
                          Colors.RESET + Colors.GREEN + " executing quantum" + Colors.RESET +
                          " [" + runTime + "ms] ");

        try {
            int steps = 5;
            int stepTime = runTime / steps;

            for (int i = 1; i <= steps; i++) {
                Thread.sleep(stepTime);
                int quantumProgress = (i * 100) / steps;
                quantumBar = createProgressBar(quantumProgress, 15);

                System.out.print("\r  " + Colors.YELLOW + "⚡" + Colors.RESET +
                                " Quantum progress: " + quantumBar);
            }
            System.out.println();

        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "\n  ✗ " + name + " was interrupted." + Colors.RESET);
        }

        remainingTime -= runTime;
        int overallProgress = (int) (((double)(burstTime - remainingTime) / burstTime) * 100);
        String overallProgressBar = createProgressBar(overallProgress, 20);

        System.out.println(Colors.YELLOW + "  ⏸ " + Colors.CYAN + name + Colors.RESET +
                          " completed quantum " + Colors.BRIGHT_YELLOW + runTime + "ms" + Colors.RESET +
                          " │ Overall progress: " + overallProgressBar);
        System.out.println(Colors.MAGENTA + "     Remaining time: " + remainingTime + "ms" + Colors.RESET);

        if (remainingTime > 0) {
            System.out.println(Colors.BLUE + "  ↻ " + Colors.CYAN + name + Colors.RESET +
                              " yields CPU for context switch" + Colors.RESET);
        } else {
            // Feature 3: calculate waiting time when process finishes
            finishTime = System.currentTimeMillis();
            waitingTime = (finishTime - creationTime) - burstTime;

            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" +
                              Colors.RESET);
        }
        System.out.println();
    }

    private String createProgressBar(int progress, int width) {
        int filled = (progress * width) / 100;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < width; i++) {
            if (i < filled) {
                bar.append(Colors.GREEN + "█" + Colors.RESET);
            } else {
                bar.append(Colors.WHITE + "░" + Colors.RESET);
            }
        }
        bar.append("] ").append(progress).append("%");
        return bar.toString();
    }

    public void runToCompletion() {
        try {
            System.out.println(Colors.BRIGHT_CYAN + "  ⚡ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_CYAN + " is the last process, running to completion" +
                              Colors.RESET + " [" + remainingTime + "ms]");
            Thread.sleep(remainingTime);
            remainingTime = 0;

            // Feature 3
            finishTime = System.currentTimeMillis();
            waitingTime = (finishTime - creationTime) - burstTime;

            System.out.println(Colors.BRIGHT_GREEN + "  ✓ " + Colors.BOLD + Colors.CYAN + name +
                              Colors.RESET + Colors.BRIGHT_GREEN + " finished execution!" + Colors.RESET);
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println(Colors.RED + "  ✗ " + name + " was interrupted." + Colors.RESET);
        }
    }

    public String getName() { return name; }
    public int getBurstTime() { return burstTime; }
    public int getRemainingTime() { return remainingTime; }
    public int getPriority() { return priority; }

    // Feature 3
    public long getWaitingTime() { return waitingTime; }

    public boolean isFinished() {
        return remainingTime <= 0;
    }
}

public class SchedulerSimulation {
    static int contextSwitches = 0; // Feature 2

    // Feature 3
    static List<Process> allProcesses = new ArrayList<>();

    public static void main(String[] args) {

        int studentID = 445052062;
        Random random = new Random(studentID);

        int timeQuantum = 2000 + random.nextInt(4) * 1000;
        int numProcesses = 10 + random.nextInt(11);

        Queue<Thread> processQueue = new LinkedList<>();
        Map<Thread, Process> processMap = new HashMap<>();

        for (int i = 1; i <= numProcesses; i++) {
            int burstTime = timeQuantum/2 + random.nextInt(2 * timeQuantum + 1);

            Process process = new Process("P" + i, burstTime, timeQuantum);

            // Feature 3
            allProcesses.add(process);

            addProcessToQueue(process, processQueue, processMap);
        }

        while (!processQueue.isEmpty()) {
            Thread currentThread = processQueue.poll();
            contextSwitches++; // Feature 2

            currentThread.start();

            try {
                currentThread.join();
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted.");
            }

            Process process = processMap.get(currentThread);

            if (!process.isFinished()) {
                if (!processQueue.isEmpty()) {
                    addProcessToQueue(process, processQueue, processMap);
                } else {
                    process.runToCompletion();
                }
            }
        }

        System.out.println("Total context switches: " + contextSwitches);

        // Feature 3: summary table
        System.out.println("\n=== Process Summary ===");
        System.out.printf("%-10s %-15s %-15s\n", "Process", "Burst Time", "Waiting Time");

        for (Process p : allProcesses) {
            System.out.printf("%-10s %-15d %-15d\n",
                    p.getName(),
                    p.getBurstTime(),
                    p.getWaitingTime());
        }
    }

    public static void addProcessToQueue(Process process, Queue<Thread> processQueue,
                                         Map<Thread, Process> processMap) {

        Thread thread = new Thread(process);
        processQueue.add(thread);
        processMap.put(thread, process);

        System.out.println(Colors.BLUE + "  ➕ " + Colors.BOLD + Colors.CYAN + process.getName() +
                          Colors.RESET + Colors.BLUE + " (Priority: " + process.getPriority() + ") enters the ready queue" + Colors.RESET +
                          " │ Burst time: " + Colors.YELLOW + process.getBurstTime() + "ms" +
                          Colors.RESET);
    }
}