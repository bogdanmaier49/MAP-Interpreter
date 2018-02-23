package View.GUI.Controllers;

import Controller.ProgramController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectProgramController implements Initializable{

    @FXML
    private ListView<String> programsView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button selectButton;

    private ProgramController selectedProgram;

    private List<ProgramController> programs;

    public SelectProgramController(){
        programs = new ArrayList<>();
        selectButton = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void selectButtonAction() {
        int index = programsView.getSelectionModel().getSelectedIndex();
        selectedProgram = programs.get(index);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setPrograms(List<ProgramController> programs) {
        this.programs = programs;
        programsView.setItems(FXCollections.observableArrayList(programs.stream().map(ProgramController::toString).collect(Collectors.toList())));
    }

    public ProgramController getSelectedProgram() {
        return selectedProgram;
    }
}