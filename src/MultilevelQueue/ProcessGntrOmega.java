package MultilevelQueue;

import Kernel.Process;

import java.util.Random;

import static MultilevelQueue.MultiQueue.System;
import static MultilevelQueue.MultiQueue.InteractiveProcesses;
import static MultilevelQueue.MultiQueue.InteractiveEditProcesses;
import static MultilevelQueue.MultiQueue.BatchProcesses;
import static MultilevelQueue.MultiQueue.UserProcesses;

/**
 * Created by Karlos on 3/3/2016.
 */
public class ProcessGntrOmega implements Runnable{

    double probability = 0.9503;
    public static Thread t0;
    public static int count = 0;

    public void generateProcess(){
        Random random = new Random();
        double doICreate = random.nextDouble();
        int tick = random.nextInt(20)+1;
        double type = random.nextDouble();
        int prior = random.nextInt(6);

        if (doICreate < probability){
            if (type < 0.2){
                String name = "System:" + count;
                Process sistema = new Process(0,tick,prior,name);
                sistema.setTiempoLlegada(count);
                System.add(sistema);
            }else if (type < 0.25){
                String name = "Interactive:" + count;
                Process interactive = new Process(1,tick,prior,name);
                interactive.setTiempoLlegada(count);
                InteractiveProcesses.add(interactive);
            }else if (type < 0.33){
                String name = "Edit:" + count;
                Process edit = new Process(2,tick,prior,name);
                edit.setTiempoLlegada(count);
                InteractiveEditProcesses.add(edit);
            }else if (type < 0.5){
                String name = "Batch:" + count;
                Process batch = new Process(3,tick,prior,name);
                batch.setTiempoLlegada(count);
                BatchProcesses.add(batch);
            }else if (type < 1){
                String name = "User:" + count;
                Process user = new Process(4,tick,prior,name);
                user.setTiempoLlegada(count);
                UserProcesses.add(user);
            }
            count++;
        }
    }

    @Override
    public void run() {
        while (true){
            generateProcess();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void activateThread(){
        t0 = new Thread(new ProcessGntrOmega());
        t0.start();
    }

    public void zombification(){
        t0.stop();
    }
}
