package dev.skyleaworlder.astparserdemo.breakloop;

import dev.skyleaworlder.astparserdemo.utils.CUFactory;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class BreakLoopCheckerTest {

    @Test
    public void testAcceptVisitorForCheckIfBreakLoop() throws IOException {
        String sourceFilePath = System.getProperty("user.dir")
                + File.separator + "demo"
                + File.separator + "example"
                + File.separator + "visitor"
                + File.separator + "ForStatementTest.java";
        CompilationUnit cu = CUFactory.make(sourceFilePath);
        cu.accept(new BreakLoopChecker());
    }
}
