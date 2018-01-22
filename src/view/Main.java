package view;

import controller.Controller;
import model.*;
import model.expressions.*;
import model.statements.*;
import model.utils.*;
import repository.ProgramStateRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Statement testProgram1 () {

        /*

                openFile ("test.in", file);
                readFile (file, var_c);

                if (var_c)
                    print(var_c);
                else
                    print(-1);

                closeFile(file);
         */

        Statement openFile = new OpenRFileStatement("test.in", "file");
        Statement readFile = new ReadFileStatement(new VariableExpression("file"), "var_c");
        Statement closeFile = new CloseFileStatement(new VariableExpression("file"));
        Statement openAndRead = new CompStatement(openFile, readFile);
        Statement ifst = new IfStatement(
                new VariableExpression("var_c"),
                new PrintStatement(new VariableExpression("var_c")),
                new PrintStatement(new ConstantExpression(-1))
        );
        Statement readAndIf = new CompStatement(openAndRead, ifst);

        return new CompStatement(readAndIf, closeFile);
    }

    public static Statement testProgram2 () {

        /*
                openRFile(var_f,"test.in");
                readFile(var_f+2,var_c);

                print(var_c);

                if var_c then
                    readFile(var_f,var_c);
                    print(var_c)
                else
                    print(0));

                closeRFile(var_f)
         */


        Statement openFile = new OpenRFileStatement("test.in", "f");
        Statement readFile = new ReadFileStatement(new VariableExpression("f"), "var");
        Statement print = new PrintStatement(new VariableExpression("var"));
        return new CompStatement(new CompStatement(openFile, readFile), print);
    }

    public static Statement testProgram3 () {
        /*
            new(a,20);
            new(a,10);
            wH(a,20);
            print(rH(a));
         */

        Statement first = new CompStatement(
                new AllocateStatement("a", new ConstantExpression(20)),
                new AllocateStatement("a", new ConstantExpression(10)));
        Statement second = new CompStatement(
                new WriteHeapStatement("a", new ConstantExpression(20)),
                new PrintStatement(new ReadHeapExpression("a"))
        );

        return new CompStatement(first, second);
    }

    public static Statement testProgram4 () {
        /*
            a = 5
            while(a > 0) {
                print (a);
                a = a - 1;
            }

         */

        Statement a = new AssignStatement("a", new ConstantExpression(5));
        Statement dinWhile = new CompStatement(
                new PrintStatement(new VariableExpression("a")),
                new AssignStatement("a", new ArithmeticExpression(new VariableExpression("a"), '-', new ConstantExpression(1)))
        );
        Statement whileSt = new WhileStatement(new VariableExpression("a"),dinWhile);

        return new CompStatement(a, whileSt);
    }

    public static Statement testProgram5 () {
        /*

             v=10;
             new(a,22);

             fork(wH(a,30); v=32; print(v); print(rH(a)); );

             print(v);
             print(rH(a))

         */

        Statement v = new AssignStatement("v", new ConstantExpression(10));
        Statement newA = new AllocateStatement("a", new ConstantExpression(30));
        Statement first2 = new CompStatement(v, newA);

        Statement fork = new ForkStatement(new CompStatement(
                new CompStatement(
                        new WriteHeapStatement("a", new ConstantExpression(30)),
                        new AssignStatement("v", new ConstantExpression(32))
                ),
                new CompStatement(
                        new PrintStatement(new VariableExpression("v")),
                        new PrintStatement(new ReadHeapExpression("a"))
                )
        ));

        Statement last2 = new CompStatement(
                new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new ReadHeapExpression("a"))
        );

        Statement first2AndFork = new CompStatement(first2, fork);

        return new CompStatement(first2AndFork, last2);
    }

    public static List<Controller> programList () {

        ProgramState program1 = new ProgramState(
                new ExecutionStack(),
                new OutputList<>(),
                new SymbolTable<>(),
                new FileTable<>(),
                new Heap()
        );
        program1.getExecutionStack().push(testProgram1());
        Controller controller1 = new Controller(new ProgramStateRepository("log1.txt"));
        controller1.getRepository().add(program1);

        ProgramState program2 = new ProgramState(
                new ExecutionStack(),
                new OutputList<>(),
                new SymbolTable<>(),
                new FileTable<>(),
                new Heap()
        );
        program2.getExecutionStack().push(testProgram2());
        Controller controller2 = new Controller(new ProgramStateRepository("log2.txt"));
        controller2.getRepository().add(program2);

        ProgramState program3 = new ProgramState(
                new ExecutionStack(),
                new OutputList<>(),
                new SymbolTable<>(),
                new FileTable<>(),
                new Heap()
        );        program3.getExecutionStack().push(testProgram3());
        Controller controller3 = new Controller(new ProgramStateRepository("log3.txt"));
        controller3.getRepository().add(program3);

        ProgramState program4 = new ProgramState(
                new ExecutionStack(),
                new OutputList<>(),
                new SymbolTable<>(),
                new FileTable<>(),
                new Heap()
        );
        program4.getExecutionStack().push(testProgram4());
        Controller controller4 = new Controller(new ProgramStateRepository("log4.txt"));
        controller4.getRepository().add(program4);

        ProgramState program5 = new ProgramState(
                new ExecutionStack(),
                new OutputList<>(),
                new SymbolTable<>(),
                new FileTable<>(),
                new Heap()
        );

        program5.getExecutionStack().push(testProgram5());
        Controller controller5 = new Controller(new ProgramStateRepository("log5.txt"));
        controller5.getRepository().add(program5);


        List<Controller> res = new ArrayList<Controller>();

        res.add(controller1);
        res.add(controller2);
        res.add(controller3);
        res.add(controller4);
        res.add(controller5);

        return res;
    }

    public static void ConsoleApp () {

        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", testProgram1().toString(), programList().get(0)));
        menu.addCommand(new RunExample("2", testProgram2().toString(), programList().get(1)));
        menu.addCommand(new RunExample("3", testProgram3().toString(), programList().get(2)));
        menu.addCommand(new RunExample("4", testProgram4().toString(), programList().get(3)));
        menu.addCommand(new RunExample("5", testProgram5().toString(), programList().get(4)));

        menu.show();


    }

    public static void main (String[] args){
        /*
        MainWindow window = new MainWindow();
        window.setControllers(programList());
        window.show();
        */

        ConsoleApp();
    }

}
