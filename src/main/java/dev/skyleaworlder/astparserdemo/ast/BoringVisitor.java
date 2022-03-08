package dev.skyleaworlder.astparserdemo.ast;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class BoringVisitor extends ASTVisitor {
    @Override
    public boolean visit(TypeDeclaration node) {
        System.out.println(" ==================== Type Declaration:");
        System.out.println("  - Fields Num: " + node.getFields().length);
        System.out.println("  - Methods Num: " + node.getMethods().length);
        System.out.println("  - Type Num: " + node.getTypes().length);
        return true;
    }

    @Override
    public boolean visit(SimpleType node) {
        System.out.println(" ==================== Simple Type:");
        System.out.println("  - Name: " + node.getName() + " | " + node.getName().getFullyQualifiedName());
        System.out.println("  - Parent Type: " + node.getParent().getClass().getName());
        return true;
    }

    @Override
    public boolean visit(ArrayType node) {
        System.out.println("Array Type: " + node.getElementType());
        return true;
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        System.out.println(" ==================== field declaration:");
        System.out.println("  - Type: " + node.getType());
        System.out.println("  - Modifier: " + node.getModifiers());
        System.out.print("  - Fragment: ");
        List<VariableDeclarationFragment> fragments = node.fragments();
        fragments.forEach(f -> {
            System.out.print(f.getName() + "; ");
        });
        System.out.println();
        System.out.println("  - Parent Type: " + node.getParent().getClass().getName());
        return true;
    }

    @Override
    public boolean visit(SingleVariableDeclaration node) {
        System.out.println(" ==================== Single variable declaration:");
        System.out.println("  - Type: " + node.getType());
        System.out.println("  - Name: " + node.getName());
        System.out.println("  - Modifier: " + node.getModifiers());
        System.out.println("  - Parent Type: " + node.getParent().getClass().getName());
        return true;
    }

    @Override
    public boolean visit(VariableDeclarationStatement node) {
        System.out.println(" ==================== Variable Declaration Statement:");
        System.out.println("  - Type: " + node.getType());
        System.out.println("  - Modifier: " + node.modifiers());
        List<VariableDeclarationFragment> fragments = node.fragments();
        System.out.print("  - Name: ");
        fragments.forEach(f -> {
            System.out.print(f.getName() + "; ");
        });
        System.out.println();
        System.out.println("  - Parent Type: " + node.getParent().getClass().getName());
        return true;
    }

    @Override
    public boolean visit(VariableDeclarationFragment node) {
        System.out.println(" ==================== Variable Declaration Fragment:");
        System.out.println("  - Name: " + node.getName());
        System.out.println("  - Parent Type: " + node.getParent().getClass().getName());
        return true;
    }
}
