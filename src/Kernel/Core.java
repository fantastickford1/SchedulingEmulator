package Kernel;

import java.util.ArrayList;

/**
 * Created by Karlos on 2/27/2016.
 */
public class Core {

    public static boolean busy;
    public static ArrayList<Process> auxCalc = new ArrayList<>();
    public static int conta = 0;

    public void serve(Process process){
        busy = true;
        if (conta == 0)
            process.setTiempoEspera(0);
        else {
            int aux = conta - process.getTiempoLlegada();
            if (aux < 0){
                aux = 0;
            }
            process.setTiempoEspera(aux);
        }
        int tRespuesta = process.getTicks()+process.getTiempoEspera();
        process.settRespuesta(tRespuesta);
        double penalizacion = process.gettRespuesta()/process.getTicks();
        process.setPenalizacion(penalizacion);
        System.err.println("Contador de ticks:: "+ conta);
        System.out.println(process.getName() + " : type ->" + process.getType() + " : ticks ->" + process.getTicks() + " : priority -> " + process.getPriority() + "T llegada:" + process.getTiempoLlegada());
        conta+= process.getTicks();
        auxCalc.add(process);
        busy = false;
    }

}
