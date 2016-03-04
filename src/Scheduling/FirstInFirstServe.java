package Scheduling;

import Kernel.Core;
import Kernel.Process;

/**
 * Created by Karlos on 2/27/2016.
 */

import static Kernel.ProcessQueue.processesqueue;
import static Kernel.Core.busy;
import static GUI.Controller.exterminate;
import static GUI.Controller.terminar;

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
        if (!processesqueue.isEmpty()) {
            exterminate = false;
            while (busy) {
                System.out.println("Esperar.....");
            }

            Process aux= processesqueue.get(0);

            conta= aux.getTicks();
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
