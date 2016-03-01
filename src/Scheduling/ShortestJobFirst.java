package Scheduling;

import Kernel.Core;
import Kernel.Process;


/**
 * Created by Karlos on 2/27/2016.
 */


import static Kernel.ProcessQueue.processesqueue;
import static Kernel.Core.busy;

public class ShortestJobFirst implements Runnable{

    Core core= new Core();
    public static Thread t0;
    int tick=0;

    @Override
    public void run(){

        while(true){
            try{
                sjf();
                Thread.sleep(1000*tick);

            }catch(Exception e){

            }
        }

    }

    public void zombieFication(){
        t0.stop();
    }

    public void activar(){
        t0= new Thread(new ShortestJobFirst());
        t0.start();
    }


    public void sjf(){

        Process aux= processesqueue.get(0);
        tick= aux.getTicks();

        for (int i=1; i < processesqueue.size(); i++){
            Process aux2= processesqueue.get(i);
            int tick2= aux2.getTicks();

            if(tick2 < tick){
                aux= aux2;
                tick= aux.getTicks();
            }
        }

        if (!busy){
            core.serve(aux);
            processesqueue.remove(aux);
        }

    }




}
