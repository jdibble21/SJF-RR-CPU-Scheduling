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
        chooseShortestTask();
    }

    public Task pickNextTask() {
        return queue.get(taskIndex);
    }

    public void chooseShortestTask(){
        // get current queue burst lengths and indices
        HashMap<Integer,Integer> burstVals = new HashMap<>();
        for(int i=0; i < queue.size(); i++){
            burstVals.put(i, queue.get(i).getBurst());
        }
        int minValue = 0;
        for(int i=0; i < burstVals.size() - 1; i++){
            minValue = burstVals.get(i);
            if(burstVals.get(i+1) < minValue){
                minValue = burstVals.get(i+1);
            }
        }
        System.out.println(minValue);
    }


}
