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
    private HashMap<String,Integer> progressMap;

    //the total number of processes to be scheduled
    private final int numTasks;
    private final int timeQuantum = 5;

    public RR(List<Task> queue){
        this.queue = queue;
        numTasks = queue.size();
    }

    public void schedule(){
        // map process name to burst values for tracking
        for (int i=0; i < queue.size(); i++){

        }
        System.out.println("Round Robin Scheduling \n");

        //to keep track of the total waiting time
        int totalWaitingTime = 0;

        Task currentTask;
        while (!queue.isEmpty()) {
            int processTime = 0;
            currentTask = pickNextTask();




            CPU.run(currentTask, timeQuantum);
            // remove the completed process
            queue.remove(currentTask);
        }
    }

    public Task pickNextTask(){
        return queue.get(0);
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
