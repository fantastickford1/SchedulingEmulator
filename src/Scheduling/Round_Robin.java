package Scheduling;

/**
 * Created by Karlos on 2/27/2016.
 */
import Kernel.Core;
import Kernel.Process;

import static Kernel.ProcessQueue.processesqueue;
import static GUI.Controller.quantumTick;
import static Kernel.Core.busy;

public class Round_Robin implements Runnable{

    private int quantum;
    public static Thread t0;
    private Core core = new Core();
    int currentTicks=0;

    @Override
    public void run(){
        while (true){
            roundRobin();
            try {
                Thread.sleep(1000 *currentTicks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void zombieFication(){
        t0.stop();
    }

    public void activar(){
        t0 = new Thread(new Round_Robin());
        t0.start();
    }

    public void roundRobin(){
        quantum = quantumTick;
        Process process = processesqueue.get(0);
        if (!busy){
            core.serve(process);
        }
        currentTicks = process.getTicks();
        currentTicks -= quantum;
        if (currentTicks > 0){
            process.setTicks(currentTicks);
            processesqueue.add(process);
        }else
            processesqueue.remove(process);

    }

}
