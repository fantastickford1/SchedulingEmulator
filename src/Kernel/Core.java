package Kernel;

import java.util.ArrayList;

/**
 * Created by Karlos on 2/27/2016.
 */
public class Core {

    public static boolean busy;
    public static ArrayList<Process> auxCalc = new ArrayList<>();
    //public Process pcss;
    int conta=0;
    public void serve(Process process){
        this.busy = true;
        conta= process.getTicks();
        System.out.println(process.getName() + " : type ->" + process.getType() + " : ticks ->" + process.getTicks() + " : priority -> " + process.getPriority());
        auxCalc.add(process);
        this.busy = false;
    }

}
