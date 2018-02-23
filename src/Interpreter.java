import Controller.ProgramController;
import Model.Commands.ExitCommand;
import Model.Commands.RunExample;

import Util.ProgramGenerator;
import View.CLI.TextMenu;
import View.GUI.MainWindow;

import java.util.List;

public class Interpreter {

    public static void main(String[] args) {

        //startCLI();
        startGUI();

    }

    private static void startGUI(){
        MainWindow gui = new MainWindow();
        gui.show();
    }

    private static void startCLI(){
        try{
            List<ProgramController> controllers = ProgramGenerator.generatePrograms();

            TextMenu textMenu = new TextMenu();
            textMenu.addCommand(new ExitCommand("exit", "exit the interpreter" )) ;

            for (Integer i = 1; i < controllers.size(); i++){
                textMenu.addCommand(new RunExample(i.toString(), controllers.get(i).toString(), controllers.get(i)));
            }

            textMenu.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
