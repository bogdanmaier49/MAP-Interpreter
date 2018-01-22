package view;

import controller.Controller;

public class RunExample extends Command {

    private Controller ctr;

    public RunExample(String key, String desc, Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }

    @Override
    public void execute() {
        try {
            ctr.allStep();
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }
}
