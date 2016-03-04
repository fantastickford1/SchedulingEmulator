package Scheduling;

/**
 * Created by Karlos on 2/27/2016.
 */

import Kernel.Core;
import Kernel.Process;

import static GUI.Controller.exterminate;
import static GUI.Controller.terminar;
import static Kernel.ProcessQueue.processesqueue;
import static Kernel.Core.busy;

public class Priority implements Runnable {

    Core core= new Core();
    public static Thread t0;
    int conta;

    @Override
    public void run(){
        while(true){
            try {
                priority();
                Thread.sleep(1000*conta);
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

        if (!processesqueue.isEmpty()) {
            exterminate = false;
            while (busy) {
                System.out.println("Esperar.....");
            }

            Process aux = processesqueue.get(0);
            int priori = aux.getPriority();


            for (int i = 1; i < processesqueue.size(); i++) {
                Process aux2 = processesqueue.get(i);
                int priori2 = aux2.getPriority();

                if (priori2 < priori) {
                    aux = aux2;
                    priori = aux.getPriority();
                }
            }

             conta = aux.getTicks();
             core.serve(aux);
             processesqueue.remove(aux);
        }else if (processesqueue.isEmpty()){
            if (terminar){
                System.err.println("ELSEEEEE <<<<<::" + exterminate);
                exterminate = true;
                return;
            }
        }
    }

}
