package Scheduling;

import Kernel.Core;
import Kernel.Process;

/**
 * Created by Karlos on 2/27/2016.
 */

import static Kernel.ProcessQueue.processesqueue;
import static Kernel.Core.busy;

public class FirstInFirstServe implements Runnable {

    Core core= new Core();
    public static Thread t0;
    int conta;

    @Override
    public void run(){
        while(true){
            try {
                fcFs();
                Thread.sleep(1000*conta);
            }catch (Exception e){

            }
        }
    }

    public void zombieFication(){
        t0.stop();
    }

    public void activar(){
        t0= new Thread(new FirstInFirstServe());
        t0.start();
    }

    public void fcFs(){

        Process aux= processesqueue.get(0);

        if (!busy){
            conta= aux.getTicks();
           core.serve(aux);
            processesqueue.remove(aux);

        }
    }
}
