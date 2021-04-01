package practicalrefactorings.equationsolver;

public class NumberNode implements Evaluable {

    private int value;

    /**
     * Construct number node
     */
    public NumberNode(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public String representation() {
        return String.valueOf(value);
    }

}
