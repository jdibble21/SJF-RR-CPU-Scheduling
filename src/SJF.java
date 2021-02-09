/*
 * SJF scheduling algorithm.
 *
 * Add your implementation inside the SJF class below.
 */

import java.util.*;

public class SJF implements Algorithm
{
    private final List<Task> queue;

    //the total number of processes to be scheduled
    private final int numTasks;

    public SJF(List<Task> queue){
        this.queue = queue;
        numTasks = queue.size();
    }

    public void schedule(){
        System.out.println("Shortest-Job First scheduling");
        int totalWaitingTime = 0;

        Task currentTask;

    }

    public Task pickNextTask() {
        return queue.get(0);
    }
}
