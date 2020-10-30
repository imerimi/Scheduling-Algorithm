# schedulingalgo
Scheduling Algorithm written in Java.

*Round Robin Scheduling with Priority:*

Round Robin (RR) scheduling is the preemptive version of FCFS algorithm that selects the process that has
been in the ready queue for the longest period of time. (i.e) the process is selected in round- robin
manner. FCFS is used to select the process, after the expiry of specified time quantum. However, if a
higher priority process comes into the FCFS queue, it goes to the head of the queue.

-----------------------------------------------------------------------------------------------------------------------------------

*Shortest Remaining Time Next (SRTN) Scheduling with Priority:*

This scheduling algorithm is preemptive version of Shortest Job First (SJF) algorithm. The next process to
be removed from the ready queue is the one with the shortest CPU burst time. If two or more processes
are having the smallest burst time, the one with the higher priority is selected.
When a new process arrives at the ready queue, the scheduler will compare the remaining CPU time
required for the currently running process with the next CPU burst time required for the newly arrived
process.
If the next CPU burst time required for the newly arrived process is shorter, it will preempt the currently
running process and it will be placed in the ready queue and newly arrived process will be given the
control of CPU. But if the remaining time required for the currently running process is shorter, it will be
allowed to continue.

-----------------------------------------------------------------------------------------------------------------------------------

Note:

1.The range of process = 3-10 only
2.User need to enter the range, quantum and arrival time, burst time, priority of each respective process.
