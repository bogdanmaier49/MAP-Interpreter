package exceptions;

public class ExpressionException extends Throwable {
    private String msg;
    public ExpressionException (String msg) {
        this.msg = msg;
    }
    @Override
    public String toString () {
        return msg;
    }
}
