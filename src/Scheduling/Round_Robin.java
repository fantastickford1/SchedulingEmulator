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

    @Override
    public void run(){
        while (true){
            quantum = quantumTick;
            Process process = null;
            if (!processesqueue.isEmpty()){
                process = processesqueue.remove(0);
            }
            while (busy){
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
}
