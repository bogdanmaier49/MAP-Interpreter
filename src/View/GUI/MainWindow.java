package View.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application {

    public void show(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent mainWindow = FXMLLoader.load(getClass().getResource("Views/MainWindowView.fxml"));

        primaryStage.setTitle("Interpreter");

        Scene scene = new Scene(mainWindow, 1048, 800 );

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
