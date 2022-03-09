package demo.example;

public class WhileStmt implements Stmt {
    private static final int WHILE_STMT = 2;

    public int inner;

    WhileStmt(int inner) {
        this.inner = inner;
    }

    public int GetNumber() {
        return WHILE_STMT;
    }

    public void DoSomeAction(int a) {
        int b = 10;
        int c = 0;
        while (a < b) {
            a++;
            c = b + a;
        }
    }
}
