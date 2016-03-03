package MultilevelQueue;

import Kernel.Core;
import Kernel.Process;

import static MultilevelQueue.MultiQueue.System;
import static MultilevelQueue.MultiQueue.InteractiveProcesses;
import static MultilevelQueue.MultiQueue.InteractiveEditProcesses;
import static MultilevelQueue.MultiQueue.BatchProcesses;
import static MultilevelQueue.MultiQueue.UserProcesses;
import static GUI.Controller.quantumTick;
import static Kernel.Core.busy;

/**
 * Created by Karlos on 3/3/2016.
 */
public class MultilevelQueueAghm implements Runnable{

    Core core = new Core();
    public static Thread t1;
    private int quantum;


    @Override
    public void run() {
        quantum = quantumTick;
        while (true) {
            if (!System.isEmpty()) {
                Process systemP = System.remove(0);
                while (busy){
                    java.lang.System.out.println("Esperar.....");
                }
                int tick = systemP.getTicks();
                int aux = tick - quantum;
                if (aux > 0 ){
                    systemP.setTicks(aux);
                    core.serve(systemP);
                    try {
                        Thread.sleep(1000*aux);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    java.lang.System.out.println(systemP.getTiempoLlegada() + "<<<<<<<");
                    System.add(systemP);
                }
            }
            if (!InteractiveProcesses.isEmpty()){
                Process process = InteractiveProcesses.remove(0);
                while (busy){
                    java.lang.System.out.println("Esperar.....");
                }
                int tick= process.getTicks();
                for (int i=1; i < InteractiveProcesses.size(); i++){
                    Process aux2= InteractiveProcesses.get(i);
                    int tick2= aux2.getTicks();

                    if(tick2 < tick){
                        process= aux2;
                        tick= process.getTicks();
                    }
                }
                core.serve(process);
                try {
                    Thread.sleep(1000*tick);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void zombification(){
        t1.stop();
    }

    public void start(){
        t1 = new Thread(new MultilevelQueueAghm());
        t1.start();
    }


}
