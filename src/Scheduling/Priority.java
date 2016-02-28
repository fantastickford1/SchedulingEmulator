package Scheduling;

/**
 * Created by Karlos on 2/27/2016.
 */

import Kernel.Core;
import Kernel.Process;

import static Kernel.ProcessQueue.processesqueue;
import static Kernel.Core.busy;

public class Priority implements Runnable {

    Core core= new Core();
    public static Thread t0;

    @Override
    public void run(){
        while(true){
            try {
                priority();
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
    }

    public void zombieFication(){
        t0.stop();
    }

    public void activar(){
        t0= new Thread(new Priority());
        t0.start();
    }



    public void priority(){
        Process aux= processesqueue.get(0);
        int priori= aux.getPriority();

        for (int i=1; i < processesqueue.size(); i++){
            Process aux2= processesqueue.get(i);
            int priori2= aux2.getPriority();

            if (priori2 < priori){
                aux= aux2;
                priori= aux.getPriority();
            }
        }

        if (!busy){
            core.serve(aux);
            processesqueue.remove(aux);
        }
    }

}
