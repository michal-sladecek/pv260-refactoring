package practicalrefactorings.equationsolver;

public class SymbolNode implements Evaluable {
    private Evaluable left;
    private Evaluable right;
    private char operator;

    public SymbolNode(char symbol) {
        this.operator = symbol;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public void setLeft(Evaluable left) {
        this.left = left;
    }

    public void setRight(Evaluable right) {
        this.right = right;
    }

    @Override
    public int evaluate() {
        switch (operator) {
            case '+':
                return left.evaluate() + right.evaluate();
            case '-':
                return left.evaluate() - right.evaluate();
            case '*':
                return left.evaluate() * right.evaluate();
            case '/':
                return left.evaluate() / right.evaluate();
            default:
                throw new IllegalStateException("Unknown operator: " + operator);
        }
    }

    @Override
    public String representation() {
        return "(" + left.representation() + " " + operator + " " + right.representation() + ")";
    }
}