package dev.skyleaworlder.astparserdemo.visitor;

import dev.skyleaworlder.astparserdemo.utils.CUFactory;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.IOException;

public class BreakLoopCheckerTest {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = System.getProperty("user.dir")
                + File.separator + "demo"
                + File.separator + "example"
                + File.separator + "visitor"
                + File.separator + "ForStatementTest.java";
        CompilationUnit cu = CUFactory.make(sourceFilePath);
        cu.accept(new BreakLoopChecker());
    }
}
