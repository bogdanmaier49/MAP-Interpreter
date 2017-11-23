package exceptions;

public class ProgramStateException extends Throwable {

    private String msg;
    public ProgramStateException (String msg) {
        this.msg = msg;
    }
    @Override
    public String toString () {
        return msg;
    }

}
