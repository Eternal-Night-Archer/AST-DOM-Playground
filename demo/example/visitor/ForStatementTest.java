package demo.example.visitor;

import demo.example.ast.*;

public class NewMain {
    public static void main(String[] args) {
        double res;
        IfStmt i1 = new IfStmt(3);
        IfStmt i2 = new IfStmt(2);
        WhileStmt w1 = new WhileStmt(4);

        if (true) {
            double q;
        }

        if (false) {
            double q;
        } else {
            double j;
        }

        // a normal ForStatement
        for (int cnt = 0; cnt < 6; cnt++) {
            if (cnt % 2 == 0) {
                IfStmt i3 = new IfStmt(cnt);
                int i3_inner = i3.inner;
                i3.DoSomeAction(i3_inner);
            } else {
                WhileStmt w2 = new WhileStmt(cnt);
                w2.DoSomeAction(12);
            }

            // Do not have Expression and Break or Return
            for (int cnt = 1; ; cnt++) {
                if (cnt == 2)
                    cnt++;
            }

            // break is in ElseBlock
            for (int cnt = 2; ; cnt--) {
                if (cnt != 0) {
                    cnt--;
                } else {
                    cnt += 1;
                    break;
                }
            }

            // return is in ForStatement
            for (int cnt = 3; ; cnt++) {
                i1.DoSomeAction(cnt);
                if (false) {
                    cnt += 2;
                }
                return;
            }

            // double-layer ForStatement
            // but outer ForStatement do not have exit
            for (int cnt = 4; ; cnt++) {
                for (int dnt = 5; ; dnt++) {
                    cnt++;
                    if (cnt > 5)
                        break;
                }
            }

            // double-layer ForStatement
            // and outer ForStatement have exit
            for (int cnt = 4; ; cnt++) {
                for (int dnt = 5; ; dnt++) {
                    cnt++;
                    if (cnt > 5)
                        return;
                }
            }

            // if exist break
            for (int cnt = 5; ; cnt++) {
                if (cnt > 0) {
                    cnt = cnt + 1;
                    break;
                }
            }

            // double-layer if exist break
            for (int cnt = 6; ; cnt++) {
                if (cnt <= 0) {
                    int g = 3;
                    if (g > 2) {
                        g += 4;
                    } else {
                        break;
                    }
                }
            }

            // normally has break
            for (int cnt = 7; ; cnt++) {
                break;
            }

            // WhileStatement has exit
            for (int cnt = 8; ; cnt++) {
                while (cnt > 6) {
                    cnt++;
                    return;
                }
            }
        }

        int ifStmt = i2.GetNumber();
        int whileStmt = w1.GetNumber();

        i2.DoSomeAction(ifStmt);
        w1.DoSomeAction(whileStmt);

        res = ifStmt + whileStmt;
    }
}
