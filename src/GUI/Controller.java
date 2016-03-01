package GUI;

import Kernel.Process;
import Kernel.ProcessGenerator;
import Scheduling.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//////////////////////////////////////
import static Kernel.Core.auxCalc;
/////////////////////////////////////////////

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    ChoiceBox choice;
    @FXML
    TextArea textArea;
    @FXML
    TextField quantum;

    public static int quantumTick;
////////////////////////
    String tr;

    //////////
    ProcessGenerator processGenerator = new ProcessGenerator();
    FirstInFirstServe fcfs = new FirstInFirstServe();
    Priority priority = new Priority();
    ShortestJobFirst shortestJobFirst = new ShortestJobFirst();
    Round_Robin roundRobin = new Round_Robin();
    MultilevelQueue multilevelQueue = new MultilevelQueue();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice.getItems().addAll(
                "First-In-First-Serve","Priority","ShortestJobFirst","Round Robin","Multilevel Queue");
    }

    @FXML private void startAll(){
        String selecction = (String) choice.getSelectionModel().getSelectedItem();
        switch (selecction){
            case "First-In-First-Serve":
                processGenerator.activateThread();
                fcfs.activar();
                break;
            case "Priority":
                processGenerator.activateThread();
                priority.activar();
                break;
            case "ShortestJobFirst":
                processGenerator.activateThread();
                shortestJobFirst.activar();
                break;
            case "Round Robin":
                processGenerator.activateThread();
                roundRobin.activar();
                break;
            case "Multilevel Queue":
                processGenerator.activateThread();
                multilevelQueue.start();
                break;
        }
    }

    @FXML private void stopAll(){
        String selecction = (String) choice.getSelectionModel().getSelectedItem();
        switch (selecction){
            case "First-In-First-Serve":
                processGenerator.zombification();
                fcfs.zombieFication();
                tr = "";
                for (Process p: auxCalc) {
                    tr += "Tiempo llegada -> " + p.getTiempoLlegada() + " : Tiempo espera real ->" + p.getTiempoEspera() + "\n";
                }
                textArea.appendText(tr);
                break;
            case "Priority":
                processGenerator.zombification();
                priority.zombieFication();
                tr = "";
                for (Process p: auxCalc) {
                    tr += "Tiempo llegada -> " + p.getTiempoLlegada() + " : Tiempo espera real ->" + p.getTiempoEspera() + "\n";
                }
                textArea.appendText(tr);
                break;
            case "ShortestJobFirst":
                processGenerator.zombification();
                shortestJobFirst.zombieFication();
                tr = "";
                for (Process p: auxCalc) {
                    tr += "Tiempo llegada -> " + p.getTiempoLlegada() + " : Tiempo espera real ->" + p.getTiempoEspera() + "\n";
                }
                textArea.appendText(tr);
                break;
            case "Round Robin":
                processGenerator.zombification();
                roundRobin.zombieFication();
                tr = "";
                for (Process p: auxCalc) {
                    tr += "Tiempo llegada -> " + p.getTiempoLlegada() + " : Tiempo espera real ->" + p.getTiempoEspera() + "\n";
                }
                textArea.appendText(tr);
                break;
            case "Multilevel Queue":
                processGenerator.zombification();
                multilevelQueue.zombification();
                tr = "";
                for (Process p: auxCalc) {
                    tr += "Tiempo llegada -> " + p.getTiempoLlegada() + " : Tiempo espera real ->" + p.getTiempoEspera() + "\n";
                }
                textArea.appendText(tr);
                break;
        }
    }

    @FXML private void getQuantum(){
        try {
            quantumTick = Integer.parseInt(quantum.getText());
        }catch (Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("It's not an integer type");
            alert.setContentText(ex.getMessage());
        }
    }

    private void data(){

    }
}
