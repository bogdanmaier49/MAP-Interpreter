package view.GUI;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;


public class MainWindow extends Application {

    private List<Controller> programs;


    public void show () {
        launch();
    }


    public void setControllers (List<Controller> controller) {
        this.programs = controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindowView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Interpreter");

        Scene scene = new Scene(root, 616, 590 );
        MainWindowController controller = loader.getController();
        controller.setControllers(this.programs);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
