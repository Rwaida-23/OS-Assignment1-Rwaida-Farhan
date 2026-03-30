# Reflection Questions

## Instructions
Answer the following questions about your learning experience. Each answer should be **at least 5-7 sentences** and show your understanding.

---

## Question 1: What did you learn about multithreading?

**Your Answer:**

[I discovered that a program may carry out several tasks concurrently within a single process thanks to multithreading. Compared to processes, threads communicate more quickly since they share memory. I also comprehended how a thread moves between its various states during execution, including New, Runnable, Running, Waiting, and Terminated. How the CPU scheduler chooses which thread to execute at any given moment is one key idea I learned. I also witnessed the process of context switching that occurs when several threads vie for CPU time. I was really aback by both how effective threads are and how, if improperly handled, they may result in problems like race situations. In general, I now have a better grasp of how concurrency functions in actual systems.]

---

## Question 2: What was the most challenging part of this assignment?

**Your Answer:**

[Understanding how the Round-Robin scheduling method operates in practice—particularly when it is done using threads—was the most difficult aspect of this project. Visualizing how processes enter and exit the ready queue while they are running was challenging. Monitoring each thread's behavior and making sure they adhered to the proper scheduling order was another difficulty. Because thread execution is not always predictable, debugging the program was equally difficult. Concurrent execution occasionally caused the output to display in an unexpected order. It was necessary to closely examine the program output in order to comprehend how time quantum impacts execution. Overall, it was a conceptual and practical challenge that combined theory and application.]

---

## Question 3: How did you overcome the challenges you faced?

**Your Answer:**

[By dividing the issue into manageable chunks and concentrating on one idea at a time, I was able to overcome these obstacles. To have a deeper understanding of Round-Robin scheduling, I first went over course notes and internet materials. After closely examining the code, I added print statements to monitor thread activity in detail. I was able to see how processes progressed through the ready queue thanks to this. In order to see various actions and verify my comprehension, I also tried the application several times. I used debugging strategies including examining program flow and variable values when I ran into problems. I also talked about ideas with students and looked up examples online. These techniques enabled me to solve the issues and progressively gain confidence.]

---

## Question 4: How can you apply multithreading concepts in real-world applications?

**Your Answer:**

[In practical applications, multithreading is frequently employed to enhance responsiveness and performance. Web browsers, for instance, use several threads to load webpages, execute scripts, and manage user interactions all at once. Threads are used in mobile applications to carry out background operations, such as data downloads, while maintaining a responsive user interface. Multithreading is also used in games to manage user input, physics computations, and graphics rendering simultaneously. Multithreading enables servers to effectively handle numerous client requests. These systems immediately benefit from the ideas I learnt in this project, such as thread states and scheduling. Designing programs that are quicker and more effective requires an understanding of multithreading.]

---

## Additional Reflections (Optional)

### What would you like to learn more about?

[I'm interested in learning more about thread synchronization methods like monitors, semaphores, and mutexes. Additionally, I'm curious about deadlocks and how operating systems identify and avoid them.]

---

### How confident do you feel about multithreading concepts now?


[In the middle ,I have faith in the fundamental ideas of thread creation, scheduling, and lifespan. I still need more experience, though, with more complex subjects like avoiding concurrency problems and synchronization.]

---

### Feedback on the assignment

[Understanding how scheduling functions in actual systems was greatly aided by the assignment. It offered an excellent balance between theory and real-world application. However, it might be simpler to comprehend intricate behaviors like thread scheduling if there were more illustrations or diagrams.]
