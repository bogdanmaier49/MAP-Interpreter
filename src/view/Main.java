package view;

import controller.Controller;
import model.*;
import model.expressions.ConstantExpression;
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
        menu.show();


    }

}
