package dev.skyleaworlder.astparserdemo.ast;

import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.List;

public class BasicInfoPrinter {
    public static void printInfo(CompilationUnit cu) {
        System.out.println("Basic Information for given CompilationUnit");
        BasicInfoPrinter.printPackageInfo(cu);
        BasicInfoPrinter.printTypeGeneralInfo(cu);
    }

    private static void printPackageInfo(CompilationUnit cu) {
        System.out.println("Package Name: " + cu.getPackage().getName());
        System.out.println();
    }

    private static void printTypeGeneralInfo(CompilationUnit cu) {
        List<TypeDeclaration> types = cu.types();
        types.forEach(t -> {
            System.out.println("Type Name: " + t.getName());
            List<MethodDeclaration> methods = Arrays.asList(t.getMethods());
            System.out.println("Methods Num: " + methods.size());
            methods.forEach(m -> {
                BasicInfoPrinter.printMethodDescriptorInfo(m);
                Block body = m.getBody();
                BasicInfoPrinter.printBody(body);
                System.out.println();
            });
            System.out.println();
        });
    }

    private static void printMethodDescriptorInfo(MethodDeclaration md) {
        System.out.println("Method ReturnType: " + md.getReturnType2());
        System.out.println("Method Name: " + md.getName());
        List<SingleVariableDeclaration> params = md.parameters();
        System.out.print("Method Parameters: ");
        params.forEach(p -> {
            System.out.print(p + "; ");
        });
        System.out.println();
    }

    private static void printBody(Block body) {
        List<Statement> stmts = body.statements();
        System.out.println("Stmt Num: " + stmts.size());
        stmts.forEach(stmt -> {
            BasicInfoPrinter.printStmt(stmt);
        });
    }

    private static void printStmt(Statement stmt) {
        System.out.print("Stmt Content: " + stmt);
    }
}
