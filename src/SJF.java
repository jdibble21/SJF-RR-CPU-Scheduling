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

    private Integer taskIndex;

    public SJF(List<Task> queue){
        this.queue = queue;
        numTasks = queue.size();
    }

    public void schedule(){
        System.out.println("Shortest Job First Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;

        Task currentTask;
        List<Task> tasks = getAvailableTasks();
        System.out.println("Available tasks: " + tasks);
        int shortestIndex = pickShortestTaskIndex(tasks);
        System.out.println("Shortest tasks burst time is: " + queue.get(shortestIndex).getBurst());

        /*
        while (!queue.isEmpty()) {


            currentTask = pickNextTask();

            //the waiting time for a process in FCFS is the time it is given the CPU minus its arrival time
            int wTime = 0;
            if (CPU.getCurrentTime() > currentTask.getArrivalTime()){
                wTime = CPU.getCurrentTime() - currentTask.getArrivalTime();
            }
            totalWaitingTime += wTime;

            CPU.run(currentTask, currentTask.getBurst());

            System.out.println(currentTask.getName() + " finished at time "+CPU.getCurrentTime() + ". Its waiting time is: " + wTime);

            // remove the completed process
            queue.remove(currentTask);
        }

         */
    }

    public Task pickNextTask() {
        return queue.get(taskIndex);
    }

    public Integer pickShortestTaskIndex(List<Task> taskList){
        int shortestIndex = 0;
        for(int i=0; i < taskList.size() - 1; i++){
            shortestIndex = i;
            if(queue.get(shortestIndex).getBurst() > queue.get(i+1).getBurst()){
                shortestIndex = i + 1;
            }
        }
        return shortestIndex;
    }
    public List<Task> getAvailableTasks() {
        List<Task> taskList = new ArrayList<>();
        int currentTime = CPU.getCurrentTime();
        for(int i=0; i < queue.size(); i++){
            if(queue.get(i).getArrivalTime() <= currentTime){
                taskList.add(queue.get(i));
            }
        }
        return taskList;
    }
}
