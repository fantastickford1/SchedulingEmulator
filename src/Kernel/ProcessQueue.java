package Kernel;

import java.util.ArrayList;

/**
 * Created by Karlos on 2/28/2016.
 */
public class ProcessQueue {

    public static ArrayList<Process> processesqueue = new ArrayList<>();

    public void addToArray(Process process){
        processesqueue.add(process);
    }

    public Process removeProcess(int position){
        return processesqueue.remove(position);
    }

}
