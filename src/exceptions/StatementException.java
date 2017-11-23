package exceptions;

public class StatementException extends Throwable {
    private String msg;
    public StatementException (String msg) {
        this.msg = msg;
    }
    @Override
    public String toString () {
        return msg;
    }
}
