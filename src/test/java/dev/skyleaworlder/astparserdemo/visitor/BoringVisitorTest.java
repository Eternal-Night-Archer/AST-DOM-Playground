package dev.skyleaworlder.astparserdemo.visitor;

import dev.skyleaworlder.astparserdemo.utils.CUFactory;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class BoringVisitorTest {
    @Test
    public void testAcceptBoringVisitor() throws IOException {
        String sourceFilePath = System.getProperty("user.dir")
                + File.separator + "demo"
                + File.separator + "example"
                + File.separator + "visitor"
                + File.separator + "NewMain.java";

        // class NewMain doesn't access to IfStmt and WhileStmt,
        // but AST can also be generated,
        // because AST is concerned "Grammatically",
        // while "access" is concerned "Semantically".
        CompilationUnit cu = CUFactory.make(sourceFilePath);

        // NewMain is almost identical to BoringVisitorTest in demo.example.ast;
        // although NewMain's fields, methods and type definitions
        // are disordered.
        // "Order" matters to jdt.core.dom, different order leads to
        // the difference of AST structure.
        ASTVisitor visitor = new BoringVisitor();
        cu.accept(visitor);
    }
}
