package dev.skyleaworlder.astparserdemo.ast;

import org.eclipse.jdt.core.dom.*;

public class ASTAnalyzer {
    public static void analyzeDeclaration(ASTNode node) {
        System.out.println(node.getClass());
        if (node instanceof TypeDeclaration) {
            String result = "";
            while (!(node instanceof CompilationUnit)) {
                if (node instanceof TypeDeclaration) {
                    TypeDeclaration td = (TypeDeclaration) node;
                    result = td.getName().toString() + "." + result;
                }
                node = node.getParent();
            }
            System.out.println("Type Name: " + result);
        }
    }


    public static void analyzeStmt(ASTNode node) {
        System.out.println(node.getClass());
        if (node instanceof VariableDeclarationStatement) {
            VariableDeclarationStatement vdstmt = (VariableDeclarationStatement) node;
            System.out.println("Class: " + node.getClass());
            System.out.println("Type: " + vdstmt.getType());
            vdstmt.fragments().forEach(f -> {
                ASTAnalyzer.analyzeFragment((ASTNode) f);
            });
        } else if (node instanceof ForStatement) {
            ForStatement fstmt = (ForStatement) node;
            // initializers and updaters are all Expression
            System.out.println("Init: " + fstmt.initializers());
            System.out.println("Continue Condition: " + fstmt.getExpression());
            System.out.println("Updaters: " + fstmt.updaters());
            // The type of Body in ForStatement is Statement,
            // but it is often jdt.core.dom.Block.
            System.out.println("Body: ");
            ASTAnalyzer.analyzeStmt(fstmt.getBody());
        } else if (node instanceof IfStatement) {
            IfStatement istmt = (IfStatement) node;
            System.out.println("Expression: " + istmt.getExpression());
            // The type of getThenStatement's return type is Statement,
            // but it is often jdt.core.dom.Block.
            System.out.println("ThenStatement: " + istmt.getThenStatement().getClass().getName());
            // The type of getElseStatement's return type is Statement,
            // but it is often jdt.core.dom.Block.
            System.out.println("ElseStatement: " + istmt.getElseStatement().getClass().getName());
        } else if (node instanceof WhileStatement) {

        } else {

        }
    }

    public static void analyzeFragment(ASTNode node) {
        if (node instanceof VariableDeclarationFragment) {
            VariableDeclarationFragment vdf = (VariableDeclarationFragment) node;
            System.out.print("Fragment Name: " + vdf.getName() + ", Expression: " + vdf.getInitializer());
            if (vdf.getInitializer() != null) {
                System.out.print(" (Initializer Type: " + vdf.getInitializer().getClass() + ")");
            }
        } else if (node instanceof SingleVariableDeclaration) {
            SingleVariableDeclaration svd = (SingleVariableDeclaration) node;
            System.out.print("Single Variable Declaration Class: " + node.getClass() + ", ");
            System.out.print("Type: " + svd.getType() + ", ");
            System.out.print("Name: " + svd.getName() + ", ");
            Expression expr = svd.getInitializer();
            System.out.print("Expression: " + expr);
        }
        System.out.println();
        System.out.println();
    }
}
