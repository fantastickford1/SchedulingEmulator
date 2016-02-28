package Kernel;

import java.util.Random;

/**
 * Created by Karlos on 2/27/2016.
 */
public class ProcessGenerator implements Runnable{

    double probability = 0.9503;
    public static Thread t0;

    public void generateProcess(){
        Random random = new Random();
        double doICreate = random.nextDouble();
        int tick = random.nextInt();
        double type = random.nextInt();
        int prior = random.nextInt();
        int count = 0;

        if (doICreate < probability){
            if (type < 0.2){
                String name = "System:" + count;
                Process sistema = new Process(0,tick,prior,name);
            }else if (type < 0.25){
                String name = "Interactive:" + count;
                Process interactive = new Process(1,tick,prior,name);
            }else if (type < 0.33){
                String name = "Edit:" + count;
                Process edit = new Process(2,tick,prior,name);
            }else if (type < 0.5){
                String name = "Batch:" + count;
                Process batch = new Process(3,tick,prior,name);
            }else if (type < 1){
                String name = "User:" + count;
                Process user = new Process(4,tick,prior,name);
            }
            count++;
        }
    }

    @Override
    public void run() {
        while (true){
            generateProcess();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void activateThread(){
        t0 = new Thread(new ProcessGenerator());
        t0.start();
    }

    public void zombification(){
        t0.interrupt();
    }
}
