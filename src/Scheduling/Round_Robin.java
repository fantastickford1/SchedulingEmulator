package Scheduling;

/**
 * Created by Karlos on 2/27/2016.
 */
import Kernel.Core;
import Kernel.Process;

import static Kernel.ProcessQueue.processesqueue;
import static GUI.Controller.quantumTick;
import static Kernel.Core.busy;
import static GUI.Controller.exterminate;
import static GUI.Controller.terminar;
import static Kernel.Core.conta;

public class Round_Robin implements Runnable{

    private int quantum;
    public static Thread t0;
    private Core core = new Core();

    @Override
    public void run(){
        while (true){
            quantum = quantumTick;
            if (!processesqueue.isEmpty()){
                exterminate = false;
                while (busy){
                    System.out.println("Esperar.....");
                }

                Process process = processesqueue.remove(0);
                System.err.println("El tockki es: " + process.getTicks());
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
                        System.out.println("Esperar.....");
                    }
                    System.err.println("Conta: " + conta);
                    process.setTiempoLlegada(conta);
                    processesqueue.add(process);
                }
            }else if (processesqueue.isEmpty()){
                if (terminar){
                    System.err.println("ELSEEEEE <<<<<::" + exterminate);
                    exterminate = true;
                    return;
                }
            }
            /*while (busy){
                System.out.println("Esperar.....");
            }
            int tick = process.getTicks();
            int aux = tick - quantum;
            if (aux > 0 ){
                process.setTicks(aux);
                core.serve(process);
                try {
                    Thread.sleep(1000*aux);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(process.getTiempoLlegada() + "<<<<<<<");
                processesqueue.add(process);
            }*/
        }
    }

    public void zombieFication(){
        t0.stop();
    }

    public void activar(){
        t0 = new Thread(new Round_Robin());
        t0.start();
    }
}
