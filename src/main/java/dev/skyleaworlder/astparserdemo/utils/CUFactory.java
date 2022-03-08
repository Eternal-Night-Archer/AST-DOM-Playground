package dev.skyleaworlder.astparserdemo.utils;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class CUFactory {
    public static CompilationUnit make(String javaSourceFilePath) throws IOException {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        // set ASTParser compiler options
        Map options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        parser.setCompilerOptions(options);

        FileInputStream fin = new FileInputStream(javaSourceFilePath);
        BufferedInputStream buf = new BufferedInputStream(fin);
        byte[] input = new byte[fin.available()];
        buf.read(input);
        buf.close();

        parser.setSource(new String(input).toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        return cu;
    }
}
