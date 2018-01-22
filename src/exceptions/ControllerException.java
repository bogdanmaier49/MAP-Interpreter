package exceptions;

public class ControllerException extends Exception {
    private String s;
    public ControllerException(String s) {
        this.s = s;
    }

    @Override
    public String toString () {
        return s.toString();
    }

}
