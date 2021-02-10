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
        System.out.println("Shortest-Job First scheduling");
        int totalWaitingTime = 0;

        Task currentTask;
        while (!queue.isEmpty()) {

            currentTask = pickNextTask();
            int wTime = 0;
            if (CPU.getCurrentTime() > currentTask.getArrivalTime()){
                wTime = CPU.getCurrentTime() - currentTask.getArrivalTime();
            }
            totalWaitingTime += wTime;

            CPU.run(currentTask, currentTask.getBurst());

            System.out.println(currentTask.getName() + " finished at time "+CPU.getCurrentTime() + ". Its waiting time is: " + wTime);
            queue.remove(currentTask);
        }
        //need to cast either the numerator or the denominator to the double type
        //otherwise, when both are integers, their division result will always be rounded to integer
        double averageWaitingTime = totalWaitingTime / (double) numTasks;
        //use printf for formatted out (only show two digits after decimal points)
        System.out.printf("\nThe average waiting time is: %.2f\n", averageWaitingTime);
    }

    public Task pickNextTask() {
        return queue.get(taskIndex);
    }

    public Task chooseShortestTask(ArrayList<Task> tasks){
        Task shortestTask = tasks.get(0);
        ArrayList<Integer> burstList = new ArrayList<>();
        for(int i=1; i<queue.size(); i++){
            if(tasks.get(i).getBurst() < shortestTask.getBurst()){
                shortestTask = tasks.get(i);
            }
        }
        return shortestTask;
    }

    public ArrayList<Task> getAvailableJobs(){
        ArrayList<Task> validTasks = new ArrayList<>();
        for (Task task : queue) {
            if (CPU.getCurrentTime() <= task.getArrivalTime()) {
                validTasks.add(task);
            }
        }
        return validTasks;
    }
}
