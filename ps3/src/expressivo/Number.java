/**
 * 
 */
package expressivo;

/**
 * 
 */
public class Number implements Expression {
    private final double value;
    private void checkRep() {
        assert Double.isFinite(value); // Ensures value is not NaN or Infinity
        assert 0 <= value && value <= Double.MAX_VALUE; // Allow Double.MAX_VALUE
    }

    public Number(double value) {
        this.value = value;
        checkRep();//called during construction
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
    	if (obj == this) {
            return true;
        }
        if (!(obj instanceof Number)) return false;
        Number other = (Number) obj;
        //can also be done using Math.abs() compare value error checking
        checkRep();
        return Double.compare(this.value, other.value) == 0;
    }
    
    @Override
    public Expression differentiate(String variable) {
        return new Number(0);
    }

    @Override
    public double evaluate(Environment env) {
        return value;
    }

    @Override
    public int hashCode() {//using prime number to generate hash v
    	double doubleValue = Double.parseDouble(this.toString());
        final int prime = 3;
        int result = 7;
        int numHash = (int) (result * doubleValue)+ (int) (prime * doubleValue); 
        checkRep();
        return numHash;
    }
}

