/*
 *
 * Round-robin scheduling algorithm.
 *
 * Add your implementation inside the RR class below.
 *
 */

import java.util.*;

public class RR implements Algorithm
{
    private final List<Task> queue;

    //the total number of processes to be scheduled
    private final int numTasks;
    private final int timeQuantum = 5;
    private int queueIndex;
    private List<Task> availableTasks;

    public RR(List<Task> queue){
        this.queue = queue;
        numTasks = queue.size();
        queueIndex = 0;
    }

    public void schedule(){

        System.out.println("Round Robin Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;

        Task currentTask;
        while (!queue.isEmpty()) {

            int wTime = 0;

            int timeSlice;
            currentTask = pickNextTask();
            timeSlice = timeQuantum;
            if(currentTask.getBurst() < timeQuantum){
                timeSlice = currentTask.getBurst();
            }



            CPU.run(currentTask, timeSlice);
            System.out.println(currentTask.getName() + " finished at time "+CPU.getCurrentTime() + ". Its waiting time is: " + wTime);

            // deduct quantum time from burst length
            queue.get(queueIndex).setBurst(currentTask.getBurst() - timeQuantum);

            // point queue index to choose next task
            queueIndex++;

            // check if task is finished, and remove if so, and backtrack index to account for it
            if(currentTask.getBurst() <= 0){
                queue.remove(currentTask);
                queueIndex--;
            }
            if(queueIndex >= queue.size()){
                queueIndex = 0;
            }
        }
    }

    public Task pickNextTask(){
        return queue.get(queueIndex);
    }
}
