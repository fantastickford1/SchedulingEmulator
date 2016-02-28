package Scheduling;

import Kernel.Core;
import Kernel.Process;

/**
 * Created by Karlos on 2/27/2016.
 */

import static Kernel.ProcessQueue.processqueue;
import static Kernel.Core.busy;

public class FirstInFirstServe implements Runnable {

    Core core= new Core();
    public static Thread t0;

    public void run(){
        while(true){
            try {
                fcFs();
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
    }

    public void zombieFication(){
        t0.interrupt();
    }

    public void activar(){
        t0= new Thread(new FirstInFirstServe());
        t0.start();
    }

    public void fcFs(){

        Process aux= processqueue.get(0);

        if (!busy){
           core.serve(aux);
            processqueue.remove(aux);

        }
    }
}
