package demo.example.visitor;

public class NewMain {
    private static final double PI = 3.14159;

    NewMain(int HANA) {
        this.HAHA = HANA;
    }

    public int HAHA;

    public static void main(String[] args) {
        double res;
        IfStmt i1 = new IfStmt(3);
        IfStmt i2 = new IfStmt(2);
        WhileStmt w1 = new WhileStmt(4);

        for (int cnt = 0; cnt < 6; cnt++) {
            if (cnt % 2 == 0) {
                IfStmt i3 = new IfStmt(cnt);
                int i3_inner = i3.inner;
                i3.DoSomeAction(i3_inner);
            } else {
                WhileStmt w2 = new WhileStmt(cnt);
                w2.DoSomeAction(12);
            }
        }

        int ifStmt = i2.GetNumber();
        int whileStmt = w1.GetNumber();

        i2.DoSomeAction(ifStmt);
        w1.DoSomeAction(whileStmt);

        res = ifStmt + whileStmt;
    }

    protected String MAIN_STRING = "so sad", MAIN_STRING_V2 = "sob sob";

    class InnerMain {
        class InnerInnerClass1 {
        }

        class InnerInnerClass2 {
            protected char prop = 'i';
        }
        public void main(String[] args) {
            System.out.println("Soot tutorial disordered my life");
        }
    }

    @Deprecated
    public int calculate(int a, int b) {
        return a + b;
    }
}
