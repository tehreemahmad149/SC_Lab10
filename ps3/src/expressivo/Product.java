package expressivo;

public class Product implements Expression {
    private final Expression left;
    private final Expression right;
    
    private void checkRep() {
        assert left != null;
        assert right != null;
    }
    
    public Product(Expression left, Expression right) {
        this.left = left;
        this.right = right;
        checkRep();
    }
    //expression evaluation concrete functions
    
    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
    	//no additional syntax defined
    	checkRep();
        return left.toString() + " * " + right.toString();//no parenthesis to be used
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) return false;
        if (obj == this) {
            return true;
        }
        Product other = (Product) obj;
        return this.left.equals(other.left) && this.right.equals(other.right);
    }
    
    @Override
    public Expression differentiate(String variable) {
        return new Sum(
            new Product(left.differentiate(variable), right),
            new Product(left, right.differentiate(variable))
        );
    }

    @Override
    public double evaluate(Environment env) {
        return left.evaluate(env) * right.evaluate(env);
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
