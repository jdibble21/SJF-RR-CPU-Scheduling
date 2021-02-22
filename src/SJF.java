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

    private Integer shortestIndex;

    public SJF(List<Task> queue){
        this.queue = queue;
        numTasks = queue.size();
    }

    public void schedule(){
        System.out.println("Shortest Job First Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;
        Task currentTask;
        List<Task> availableTasks;
        while (!queue.isEmpty()) {

            availableTasks = getAvailableTasks();
            shortestIndex = pickShortestTaskIndex(availableTasks);

            currentTask = pickNextTask();

            int wTime;
            if (CPU.getCurrentTime() > currentTask.getArrivalTime()){
                wTime = CPU.getCurrentTime() - currentTask.getArrivalTime();
            }else{
                wTime = currentTask.getArrivalTime();
            }
            totalWaitingTime += wTime;



            CPU.run(currentTask, currentTask.getBurst());

            System.out.println(currentTask.getName() + " finished at time "+CPU.getCurrentTime() + ". Its waiting time is: " + wTime);

            // remove the completed process
            queue.remove(currentTask);
        }
        //need to cast either the numerator or the denominator to the double type
        //otherwise, when both are integers, their division result will always be rounded to integer
        double averageWaitingTime = totalWaitingTime / (double) numTasks;
        //use printf for formatted out (only show two digits after decimal points)
        System.out.printf("\nThe average waiting time is: %.2f\n", averageWaitingTime);


    }

    public Task pickNextTask() {
        return queue.get(shortestIndex);
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
        for (Task task : queue) {
            if (task.getArrivalTime() <= currentTime) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
