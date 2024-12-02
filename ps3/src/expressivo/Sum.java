package expressivo;

public class Sum implements Expression {
    private final Expression left;
    private final Expression right;
    private void checkRep() {
        assert left != null;
        assert right != null;
    }
    public Sum(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();//no parenthesis to be used
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Sum)) return false;
        if (obj == this) {
            return true;
        }
        Sum other = (Sum) obj;
        return (this.left.equals(other.left) && this.right.equals(other.right)) || 
               (this.left.equals(other.right) && this.right.equals(other.left)); // Check commutativity
    }
    
    @Override
    public Expression differentiate(String variable) {
        return new Sum(left.differentiate(variable), right.differentiate(variable));
    }

    @Override
    public double evaluate(Environment env) {
        return left.evaluate(env) + right.evaluate(env);
    }

    @Override
    public int hashCode() { //generate hash code using prime numbers
    	final int prime = 19;
        int result = 7;
        
        result = prime*result + left.hashCode();
        result = prime*result + right.hashCode();
    	checkRep();
        return result;//hashed values
    }
}

