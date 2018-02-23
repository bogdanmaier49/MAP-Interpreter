package Util;

import Controller.ProgramController;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ConstantExpression;
import Model.Expressions.ReadHeapExpression;
import Model.Expressions.VariableExpression;
import Model.Statements.*;
import Model.Program;
import Repository.ProgramRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramGenerator {

    public static List<ProgramController> generatePrograms() throws IOException {

        Program test1 = new Program(
            new CompStatement(
                new CompStatement(
                    new CompStatement(
                            new CompStatement(new NewVariableStatement("v1", new ConstantExpression(2)), new NewVariableStatement("v2", new ConstantExpression(3))),
                            new CompStatement(new NewVariableStatement("v3", new ConstantExpression(4)), new NewLatchStatement("cnt", new ReadHeapExpression("v2")))
                    ),

                    new CompStatement(
                        new CompStatement(
                                new CompStatement(
                                        new ForkStatement(
                                                        new WriteHeapStatement(
                                                                "v1",
                                                                new ArithmeticExpression(
                                                                        new ReadHeapExpression("v1"),
                                                                        '*',
                                                                        new ConstantExpression(10)
                                                                )
                                                        )
                                        ),
                                        new CompStatement(
                                                new PrintStatement(
                                                        new ReadHeapExpression("v1")
                                                ),
                                                new CountDownStatement("cnt")
                                        )
                                ),
                                new ForkStatement(
                                        new WriteHeapStatement("v2",
                                                new ArithmeticExpression(
                                                        new ReadHeapExpression("v2"),
                                                        '*',
                                                        new ConstantExpression(10)
                                                )
                                        )
                                )
                        ),
                        new CompStatement(
                               new PrintStatement(new ReadHeapExpression("v2")),
                               new CountDownStatement("cnt")
                        )
                    )
                ),

                new CompStatement(

                    new CompStatement(
                        new CompStatement(
                                new ForkStatement(
                                    new WriteHeapStatement("v2",
                                            new ArithmeticExpression(
                                                    new ReadHeapExpression("v3"),
                                                    '*',
                                                    new ConstantExpression(10)
                                            )
                                    )
                                ),
                                new PrintStatement(new ReadHeapExpression("v3"))
                        ),
                        new CompStatement(
                                new CountDownStatement("cnt"),
                                new AwaitStatement("cnt")
                        )
                    ),

                    new CompStatement(
                            new CompStatement(
                                    new PrintStatement(new ConstantExpression(100)),
                                    new CountDownStatement("cnt")
                            ),
                            new PrintStatement(new ConstantExpression(100))
                    )

                )
            )
        );

        ProgramRepository repoTest1 = new ProgramRepository(test1, "data/test1.txt");
        ProgramController ctrlTest1 = new ProgramController(repoTest1);



        Program p = new Program(new CompStatement(
                new AssignStatement("v", new ConstantExpression(100)),
                new PrintStatement(new VariableExpression("v"))
        ));
        ProgramRepository repoTest = new ProgramRepository(p, "data/prgTest.txt");
        ProgramController ctrlTest = new ProgramController(repoTest);


        /*

        a=1;b=2;
        c=a?100:200;
        print(c);
        c= (b-2)?100:200;
        print(c);

         */
        Program testB = new Program(
                new CompStatement(
                        new CompStatement(
                                new CompStatement(
                                    new AssignStatement("a", new ConstantExpression(1)),
                                    new AssignStatement("b", new ConstantExpression(2))
                                ),
                                new ConditionalAssignStatement("c", new VariableExpression("a"), new ConstantExpression(100), new ConstantExpression(200))
                        ),
                        new CompStatement(
                                new CompStatement(
                                        new PrintStatement(new VariableExpression("c")),
                                        new ConditionalAssignStatement("c", new ArithmeticExpression(new VariableExpression("b"), '-', new ConstantExpression(2)), new ConstantExpression(100), new ConstantExpression(200))
                                ),
                                new PrintStatement(new VariableExpression("c"))
                        )
                )
        );

        ProgramRepository repoB = new ProgramRepository(testB, "data/prgB.txt");
        ProgramController ctrlB = new ProgramController(repoB);


        List<ProgramController> generated = new ArrayList<ProgramController>();
        generated.add(ctrlTest1);
        generated.add(ctrlB);


        return generated;
    }
}



