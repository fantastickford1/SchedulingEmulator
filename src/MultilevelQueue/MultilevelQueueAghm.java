package MultilevelQueue;

import Kernel.Core;
import Kernel.Process;

import static GUI.Controller.exterminate;
import static GUI.Controller.terminar;
import static MultilevelQueue.MultiQueue.System;
import static MultilevelQueue.MultiQueue.InteractiveProcesses;
import static MultilevelQueue.MultiQueue.InteractiveEditProcesses;
import static MultilevelQueue.MultiQueue.BatchProcesses;
import static MultilevelQueue.MultiQueue.UserProcesses;
import static GUI.Controller.quantumTick;
import static Kernel.Core.busy;
import static Kernel.Core.conta;

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
                exterminate = false;
                while (busy){
                    java.lang.System.err.println("Esperar.....");
                }

                Process process = System.remove(0);
                java.lang.System.err.println("Tick original del proceso: " + process.getTicks());
                if ((process.getTicks() - quantum) > 0){
                    int tickki = process.getTicks();
                    process.setTicks(quantum);
                    core.serve(process);
                    process.setTicks(tickki-quantum);
                    try {
                        Thread.sleep(1000*quantum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (busy){
                        java.lang.System.err.println("Esperar.....");
                    }
                    java.lang.System.err.println("Conta: " + conta);
                    process.setTiempoLlegada(conta);
                    System.add(process);
                }
            }else if (!InteractiveProcesses.isEmpty()){
                exterminate = false;
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
            }else if (!InteractiveEditProcesses.isEmpty()){
                exterminate = false;
                Process processE = InteractiveEditProcesses.remove(0);
                while (busy){
                    java.lang.System.out.println("Esperar.....");
                }
                int tick = processE.getTicks();
                for (int i=1; i < InteractiveEditProcesses.size(); i++){
                    Process aux2= InteractiveEditProcesses.get(i);
                    int tick2= aux2.getTicks();

                    if(tick2 < tick){
                        processE= aux2;
                        tick= processE.getTicks();
                    }
                }
                core.serve(processE);
                try {
                    Thread.sleep(1000*tick);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (!BatchProcesses.isEmpty()){
                exterminate = false;
                Process prcs = BatchProcesses.remove(0);
                while (busy){
                    java.lang.System.out.println("Esperar.....");
                }
                core.serve(prcs);
            }else if (!UserProcesses.isEmpty()){
                exterminate = false;
                Process userprocess = UserProcesses.remove(0);
                while (busy){
                    java.lang.System.out.println("Esperar.....");
                }
                core.serve(userprocess);
            }else if (System.isEmpty()&& InteractiveEditProcesses.isEmpty() && InteractiveProcesses.isEmpty() && BatchProcesses.isEmpty() && UserProcesses.isEmpty()){
                if (terminar){
                    java.lang.System.err.println("ELSEEEEE <<<<<::" + exterminate);
                    exterminate = true;
                    return;
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
