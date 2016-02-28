package Kernel;

/**
 * Created by Karlos on 2/27/2016.
 */
public class Process {
    /*
    >>>>>>>>>>>Type<<<<<<<<<
    System processes -> 0
    Interactive Processes -> 1
    Interactive Edit Processes -> 2
    Batch Processes -> 3
    User Processes -> 4
    >>>>>>>>>>>Priority<<<<<<<<<
    Highest -> 0
    High -> 1
    Middle -> 2
    Low -> 3
    Lowest -> 4
     */

    public Integer type = 0;
    public static Integer ticks = 0;
    public Integer priority = 0;
    public String name = "";
    public int cont;

    public Process(Integer tp, Integer tick, Integer priority, String name){
        this.type = tp;
        ticks = tick;
        this.priority = priority;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getTicks() {
        return ticks;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setTicks(Integer tick) {
        ticks = tick;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCont(int cont){
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }
}
