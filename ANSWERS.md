# Assignment Questions

## Instructions
Answer all 4 questions with detailed explanations. Each answer should be **3-5 sentences minimum** and demonstrate your understanding of the concepts.

---

## Question 1: Thread vs Process

**Question**: Explain the difference between a **thread** and a **process**. Why did we use threads in this assignment instead of creating separate processes?

**Your Answer:**

[A process is a stand-alone program that runs independently and has its own resources, memory, and system state. Unless specific protocols (like IPC) are employed, each process operates independently and does not share memory with other processes. In contrast, a process's memory and resources are shared by several threads, each of which is a smaller unit of operation ,The primary distinctions are that threads are lighter and easier to manage, whereas processes are heavier (more memory and slower to produce). While processes need more sophisticated communication techniques, threads transfer data with ease. Because threads provide quicker context switching and effective shared memory communication, we employed them in this assignment rather than processes. They are therefore better suited for modeling scheduling algorithms such as Round-Robin..]

---

## Question 2: Ready Queue Behavior

**Question**: In Round-Robin scheduling, what happens when a process doesn't finish within its time quantum? Explain using an example from your program output.

**Your Answer:**

[A process is moved to the end of the ready queue in Round-Robin scheduling if it does not complete within the allotted time quantum. Because each process has a chance to run in turn, this guarantees fairness. After other processes have finished, the process will wait till its turn comes again..]

Example from my output:
```
[P1 is now in operation.
P1's time quantum expired
P1 was relocated to the back of the line.
P2 is currently in operation.]
```

**Explanation of example:**
[In this instance, Process P1 begins to run but does not finish within the allotted time. It is consequently put on hold and at the back of the ready queue. Process P2 then starts to run. When P1 eventually makes it back to the front of the line, it will have another opportunity to run. This cycle keeps going till every process is finished.]

---

## Question 3: Thread States

**Question**: A thread can be in different states: **New**, **Runnable**, **Running**, **Waiting**, **Terminated**. Walk through these states for one process (P1) from your simulation.

**Your Answer:**
[ NEW: When P1 is formed in the program, it is in the New state and has not yet begun to execute.
Runnable : P1 enters the Runnable state—ready to run and awaiting CPU scheduling—after invoking the start() method.
Running : When P1 is chosen by the CPU scheduler and starts carrying out its instructions, it enters the Running state.
Waiting : If P1 is paused—for instance, when its time quantum ends or while it waits for another resource or thread—it may go into the Waiting state.
Terminated :When P1 has completed its execution and is no longer in need of CPU time, it enters the Terminated state. ]

5. **Terminated**: [When is P1 Terminated?]

---

## Question 4: Real-World Applications

**Question**: Give **TWO** real-world examples where Round-Robin scheduling with threads would be useful. Explain why this scheduling algorithm works well for those scenarios.

**Your Answer:**

### Example 1: 
[Requests for Web Server Handling]

**Description**: 
[When loading webpages or responding to API calls, a web server manages several client requests concurrently.]

**Why Round-Robin works well here**: 
[Every request is guaranteed CPU time without starvation thanks to round-robin scheduling. It enhances responsiveness, particularly when a large number of people are using the server at once. Multiple requests can be efficiently handled inside the same process thanks to threads.]

### Example 2: [Operating System Task Scheduling]

**Description**: 
[Operating systems use scheduling algorithms to manage multiple running applications like browsers, music players, and background services.]

**Why Round-Robin works well here**: 
[By allocating a predetermined time slice to each activity, Round-Robin assures fairness. This keeps the system responsive by preventing any one software from controlling the CPU. In time-sharing systems where several users or apps operate concurrently, it is very helpful.]

---

## Summary

**Key concepts I understood through these questions:**
1. The distinction between processes and threads and the reasons why threads are more effective
2.How a ready queue is used by Round-Robin scheduling to handle operations
3.A thread's life cycle and state transitions

**Concepts I need to study more:**
1. Advanced thread synchronization with semaphores and locks
2. How to avoid deadlocks
