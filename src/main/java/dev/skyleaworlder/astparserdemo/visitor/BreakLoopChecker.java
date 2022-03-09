package dev.skyleaworlder.astparserdemo.visitor;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * visit ForStatement, try to find some explicit non-stop loop.
 *
 * 1. if for-expression is not null, assume that it wouldn't stop;
 * 2. if ForStatement has a BreakStatement in its body
 *      or in some IfStatements that are located in ForStatement,
 *      assume that it will stop.
 * 3. if ForStatement has a ReturnStatement in its body
 *      or even in the body of some ForStatements and WhileStatements,
 *      assume that it will stop.
 *
 * example input is available in demo/example/visitor
 */
public class BreakLoopChecker extends ASTVisitor {
    @Override
    public boolean visit(ForStatement node) {
        // check ForStatement's expression
        Expression expr = node.getExpression();
        boolean hasForStatementExpression = expressionValid(expr);

        List<Statement> stmts = getStmtFromBody(node.getBody());
        boolean allIfStmtDoNotExistExit = true;
        boolean hasBreakOrReturnStmt = false;
        for (Statement stmt : stmts) {
            if (stmt instanceof IfStatement
                    && (ifStmtExistBreak((IfStatement) stmt) || ifStmtExistReturn((IfStatement) stmt))
            ) {
                // if stmt's body exist break or return stmt
                allIfStmtDoNotExistExit = false;
            } else if (stmt instanceof BreakStatement || stmt instanceof ReturnStatement) {
                // break stmt and return stmt means exit for-loop
                hasBreakOrReturnStmt = true;
            } else if ((stmt instanceof ForStatement || stmt instanceof WhileStatement)
                    && isInnerLoopExistReturn(stmt)
            ) {
                // return stmt in inner ForStatement or WhileStatement
                hasBreakOrReturnStmt = true;
            }
        }
        if (hasForStatementExpression || hasBreakOrReturnStmt) {
            return true;
        }
        if (allIfStmtDoNotExistExit) {
            System.out.println("ForStatement might loop forever, its content is:\n" + node);
        }
        return true;
    }

    /**
     * expression is valid only if expression is not null
     * @param expr
     * @return
     */
    private boolean expressionValid(Expression expr) {
        return expr != null;
    }

    /**
     * if an IfStatement has BreakStatement,
     * break can end one-layer loop
     * @param istmt
     * @return
     */
    private boolean ifStmtExistBreak(IfStatement istmt) {
        List<Statement> allStmts = new ArrayList<>();
        allStmts.addAll(getStmtFromBody(istmt.getThenStatement()));
        allStmts.addAll(getStmtFromBody(istmt.getElseStatement()));

        for (Statement stmt : allStmts) {
            if (stmt instanceof IfStatement && ifStmtExistBreak((IfStatement) stmt)) {
                return true;
            } else if (stmt instanceof BreakStatement) {
                return true;
            }
        }
        return false;
    }

    /**
     * if an IfStatement has ReturnStatement,
     * return can end multi-layers loop
     * @param istmt
     * @return
     */
    private boolean ifStmtExistReturn(IfStatement istmt) {
        List<Statement> allStmts = new ArrayList<>();
        allStmts.addAll(getStmtFromBody(istmt.getThenStatement()));
        allStmts.addAll(getStmtFromBody(istmt.getElseStatement()));

        for (Statement stmt : allStmts) {
            if (stmt instanceof IfStatement && ifStmtExistReturn((IfStatement) stmt)) {
                return true;
            } else if (stmt instanceof ReturnStatement) {
                return true;
            } else if ((stmt instanceof ForStatement || stmt instanceof WhileStatement)
                    && isInnerLoopExistReturn(stmt)
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * if inner ForStatement or WhileStatement had ReturnStatement,
     * outer ForStatement will exit.
     * @param fwstmt must be ForStatement or WhileStatement
     * @return
     */
    private boolean isInnerLoopExistReturn(Statement fwstmt) {
        assert (fwstmt instanceof ForStatement || fwstmt instanceof WhileStatement);
        Statement body;
        if (fwstmt instanceof ForStatement) {
            body = ((ForStatement) fwstmt).getBody();
        } else {
            body = ((WhileStatement) fwstmt).getBody();
        }

        List<Statement> stmts = getStmtFromBody(body);
        for (Statement stmt : stmts) {
            if (stmt instanceof ReturnStatement) {
                return true;
            } else if (stmt instanceof IfStatement && ifStmtExistReturn((IfStatement) stmt)) {
                return true;
            } else if (stmt instanceof ForStatement || stmt instanceof WhileStatement) {
                // ReturnStatement can always exit a loop,
                // so check whether inner-inner loop exists ReturnStatement.
                return isInnerLoopExistReturn(stmt);
            }
        }
        return false;
    }

    /**
     * Tool method to get statements in body.
     * usage: getStmtFromBody(stmt.getBody())
     * @param body
     * @return
     */
    private List<Statement> getStmtFromBody(Statement body) {
        if (body instanceof Block) {
            Block block = (Block) body;
            return new ArrayList<>(block.statements());
        } else if (body != null) {
            return Collections.singletonList(body);
        } else {
            return Collections.emptyList();
        }
    }
}
