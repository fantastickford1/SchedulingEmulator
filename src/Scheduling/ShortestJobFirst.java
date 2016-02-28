package Scheduling;

import Kernel.Core;
import Kernel.Process;


/**
 * Created by Karlos on 2/27/2016.
 */


import static Kernel.ProcessQueue.processqueue;
import static Kernel.Core.busy;

public class ShortestJobFirst implements Runnable{

    Core core= new Core();
    public static Thread t0;


    public void run(){

        while(true){
            try{
                sjf();
                Thread.sleep(1000);

            }catch(Exception e){

            }
        }

    }

    public void zombieFication(){
        t0.interrupt();
    }

    public void activar(){
        t0= new Thread(new ShortestJobFirst());
        t0.start();
    }


    public void sjf(){

        Process aux= processqueue.get(0);
        int tick= aux.getTicks();

        for (int i=1; i < processqueue.size(); i++){
            Process aux2= processqueue.get(i);
            int tick2= aux2.getTicks();

            if(tick2 < tick){
                aux= aux2;
                tick= aux.getTicks();
            }
        }

        if (!busy){
            core.serve(aux);
            processqueue.remove(aux);
        }

    }




}