package practice;

public abstract class Practice implements Testable {
    private final String printAnswerFormat = getClass().getSimpleName() + " 정답:\n";

    protected void printAnswer (Object result) {
        println(printAnswerFormat + result);
    }

    protected void println(Object numb) {
        System.out.println(numb);
    }

    protected void print(Object txt) {
        System.out.print(txt);
    }

    protected void printf(String format, Object ... args) {
        System.out.printf(format, args);
    }
}
