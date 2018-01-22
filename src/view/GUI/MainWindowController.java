package view.GUI;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.List;

public class MainWindowController {

    private List<Controller> programs;
    private int currentProgramIndex = 0;

    public void setControllers (List<Controller> programs) {
        this.programs = programs;
    }

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private TableView<Pair<Integer, Integer>> heapTable;

    @FXML
    private ListView<Integer> outputList;

    @FXML
    private TableView<Pair<String, String>> fileTableTable;

    @FXML
    private TableView<Pair<String, Integer>> symbolTableTable;

    @FXML
    private ListView<String> programStatesIdentifierList;

    @FXML
    private ListView<String> executionStackList;

    @FXML
    private Button runOneStepButton;

    @FXML
    private Button listOfProgramsButton;




}
