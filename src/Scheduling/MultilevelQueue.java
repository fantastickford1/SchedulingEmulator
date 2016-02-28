package Scheduling;

import Kernel.Core;
import Kernel.Process;

import java.util.ArrayList;
import static Kernel.ProcessQueue.processesqueue;

/**
 * Created by Karlos on 2/27/2016.
 */
public class MultilevelQueue implements Runnable{
    ArrayList<Process> System = new ArrayList<>();
    ArrayList<Process> InteractiveProcesses = new ArrayList<>();
    ArrayList<Process> InteractiveEditProcesses = new ArrayList<>();
    ArrayList<Process> BatchProcesses = new ArrayList<>();
    ArrayList<Process> UserProcesses = new ArrayList<>();
    Core core = new Core();
    public static Thread t0;

    public void multilevel(){
        if (!processesqueue.isEmpty()){
            Process process = processesqueue.remove(0);
            int type = process.getType();
            if (type == 0)
                System.add(process);
            else if (type == 1)
                InteractiveProcesses.add(process);
            else if (type == 2)
                InteractiveEditProcesses.add(process);
            else if (type == 3)
                BatchProcesses.add(process);
            else if (type == 4)
                UserProcesses.add(process);
        }

        if (!System.isEmpty())
            core.serve(System.remove(0));
        else if (!InteractiveProcesses.isEmpty())
            core.serve(InteractiveProcesses.remove(0));
        else if (!InteractiveEditProcesses.isEmpty())
            core.serve(InteractiveEditProcesses.remove(0));
        else if (!BatchProcesses.isEmpty())
            core.serve(BatchProcesses.remove(0));
        else if (!UserProcesses.isEmpty())
            core.serve(UserProcesses.remove(0));
    }


    @Override
    public void run() {
        while (true){
            multilevel();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void zombification(){
        t0.stop();
    }

    public void start(){
        t0 = new Thread(new MultilevelQueue());
        t0.start();
    }
}
