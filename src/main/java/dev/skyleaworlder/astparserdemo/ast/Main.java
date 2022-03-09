package dev.skyleaworlder.astparserdemo.ast;

import dev.skyleaworlder.astparserdemo.utils.ASTNodePlayer;
import dev.skyleaworlder.astparserdemo.utils.CUFactory;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = System.getProperty("user.dir")
                + File.separator + "demo"
                + File.separator + "example"
                + File.separator + "ast"
                + File.separator + "Main.java";
        CompilationUnit cu = CUFactory.make(sourceFilePath);
        BasicInfoPrinter.printInfo(cu);

        ASTNode node = ASTNodePlayer.getStmt(cu, 0, 0, 4);
        ASTAnalyzer.analyzeStmt(node);

        node = ASTNodePlayer.getType(cu, 0, 0, 1);
        ASTAnalyzer.analyzeDeclaration(node);
    }
}
