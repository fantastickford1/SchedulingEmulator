package MultilevelQueue;

import Kernel.Process;

import java.util.ArrayList;

/**
 * Created by Karlos on 3/3/2016.
 */
public class MultiQueue {

    public static ArrayList<Process> System = new ArrayList<>();
    public static ArrayList<Process> InteractiveProcesses = new ArrayList<>();
    public static ArrayList<Process> InteractiveEditProcesses = new ArrayList<>();
    public static ArrayList<Process> BatchProcesses = new ArrayList<>();
    public static ArrayList<Process> UserProcesses = new ArrayList<>();

}
