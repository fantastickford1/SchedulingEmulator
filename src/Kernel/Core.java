package Kernel;

import java.util.ArrayList;

/**
 * Created by Karlos on 2/27/2016.
 */
public class Core {

    public static boolean busy;
    public static ArrayList<Process> auxCalc = new ArrayList<>();
    int conta = 0;

    public void serve(Process process){
        this.busy = true;
        if (conta == 0)
            process.setTiempoEspera(0);
        else {
            int aux = conta - process.getTiempoLlegada();
            if (aux < 0){
                aux = 0;
            }
            process.setTiempoEspera(aux);
        }
        System.out.println(process.getName() + " : type ->" + process.getType() + " : ticks ->" + process.getTicks() + " : priority -> " + process.getPriority());
        conta+= process.getTicks();
        auxCalc.add(process);
        this.busy = false;
    }

}
