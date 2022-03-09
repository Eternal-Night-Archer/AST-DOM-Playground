package demo.example;

public class IfStmt implements Stmt {
    private static final int IF_STMT = 1;

    public int inner;

    IfStmt(int inner) {
        this.inner = inner;
    }

    public int GetNumber() {
        return IF_STMT;
    }

    public void DoSomeAction(int a) {
        int b = 1, c = 0;
        if (a < 0) {
            b = 3;
            b = (int) add(a, b);
        } else {
            c = 2;
        }
        int d = b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }
}