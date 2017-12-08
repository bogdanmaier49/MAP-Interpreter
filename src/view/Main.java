package view;

import controller.Controller;
import model.*;
import model.expressions.ArithmeticExpression;
import model.expressions.ConstantExpression;
import model.expressions.Expression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.utils.*;
import repository.ProgramStateRepository;

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

        Statement openFile = new OpenRFileStatement("test2.in", "file");
        Statement readFile = new ReadFileStatement(new VariableExpression("file"), "var_c");
        Statement closeFile = new CloseFileStatement(new VariableExpression("file"));
        Statement then = new CompStatement(readFile, new PrintStatement(new VariableExpression("var_c")));
        Statement ifst = new IfStatement(
                new VariableExpression("var_c"),
                then,
                new PrintStatement(new ConstantExpression(0))
        );

        Statement openAndRead = new CompStatement(openFile, readFile);
        Statement openReadPrint = new CompStatement(openAndRead, new PrintStatement(new VariableExpression("var_c")));
        Statement openReadPrintIf = new CompStatement(openReadPrint, ifst);

        return new CompStatement(openReadPrintIf, closeFile);
    }


    public static Statement testProgram3 () {
        /*
            new(a,20);
            new(a,10);
            wH(a,20);
            print(a);
         */

        Statement first = new CompStatement(
                new AllocateStatement("a", new ConstantExpression(20)),
                new AllocateStatement("a", new ConstantExpression(10)));
        Statement second = new CompStatement(
                new WriteHeapStatement("a", new ConstantExpression(20)),
                new PrintStatement(new VariableExpression("a"))
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


    public static ProgramState createProgramState () {
        Dictionary<String, Integer> symbolTable = new SymbolTable();
        Stack<Statement> execStack = new ExecutionStack();
        List<Integer> out = new OutputList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        Dictionary<Integer, Integer> heap = new Heap();

        return new ProgramState(symbolTable, execStack, out, fileTable, heap);
    }


    public static void main (String[] args) {


        ProgramState program1 = createProgramState();
        program1.getExecutionStack().push(testProgram1());
        Controller controller1 = new Controller(new ProgramStateRepository("log1.txt"));
        controller1.getRepository().add(program1);


        ProgramState program2 = createProgramState();
        program2.getExecutionStack().push(testProgram2());
        Controller controller2 = new Controller(new ProgramStateRepository("log2.txt"));
        controller2.getRepository().add(program2);

        ProgramState program3 = createProgramState();
        program3.getExecutionStack().push(testProgram3());
        Controller controller3 = new Controller(new ProgramStateRepository("log3.txt"));
        controller3.getRepository().add(program3);

        ProgramState program4 = createProgramState();
        program4.getExecutionStack().push(testProgram4());
        Controller controller4 = new Controller(new ProgramStateRepository("log4.txt"));
        controller4.getRepository().add(program4);

/*
        try {
            controller1.executeAll();
        } catch (StatementException e) {
            e.printStackTrace();
        }

*/
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", testProgram1().toString(), controller1));
        menu.addCommand(new RunExample("2", testProgram2().toString(), controller2));
        menu.addCommand(new RunExample("3", testProgram3().toString(), controller3));
        menu.addCommand(new RunExample("4", testProgram4().toString(), controller4));


        menu.show();


    }

}
