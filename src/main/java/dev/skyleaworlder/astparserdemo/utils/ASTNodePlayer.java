package dev.skyleaworlder.astparserdemo.utils;

import org.eclipse.jdt.core.dom.*;

public class ASTNodePlayer {
    public static ASTNode getType(CompilationUnit root, int...typeNo) {
        int i = 0;
        TypeDeclaration td = (TypeDeclaration) root.types().get(typeNo[i]);
        if (typeNo.length == 1) {
            return td;
        }

        for (i = 1; i < typeNo.length; i++) {
            td = td.getTypes()[typeNo[i]];
        }
        return td;
    }

    public static ASTNode getMethod(CompilationUnit root, int typeNo, int methodNo) {
        TypeDeclaration td = (TypeDeclaration) getType(root, typeNo);
        return td.getMethods()[methodNo];
    }

    public static ASTNode getStmt(CompilationUnit root, int typeNo, int methodNo, int stmtNo) {
        MethodDeclaration md = (MethodDeclaration) getMethod(root, typeNo, methodNo);
        return (ASTNode) md.getBody().statements().get(stmtNo);
    }
}
