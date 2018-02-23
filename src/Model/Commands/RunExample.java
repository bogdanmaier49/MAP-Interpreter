package Model.Commands;

import Controller.ProgramController;

public class RunExample extends Command {
    private ProgramController controller;

    public RunExample(String key, String description, ProgramController controller){
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            System.out.println(controller.toString());
            controller.allStep();
            System.out.println(controller.getOutput());
        }
        catch(Exception e){
            System.out.println("from RunExample" + e.toString());
        }
    }
}
