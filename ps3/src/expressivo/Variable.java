package expressivo;

public class Variable implements Expression {
    private final String name;
    
    private void checkRep() {//variable specs
        assert name != null && name != "";
        assert name.length() > 0;
        assert name.matches("[a-zA-Z]+");
    }
    
    
    public Variable(String name) {
        this.name = name;
        checkRep();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    @Override
    public double evaluate(Environment env) {
        if (!env.containsVariable(name)) {
            throw new IllegalArgumentException("Variable " + name + " is not defined.");
        }
        return env.getValue(name);
    }
    @Override
    public Expression differentiate(String variable) {
        return name.equals(variable) ? new Number(1) : new Number(0);
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Variable)) return false;
        if (obj == this) {
            return true;
        }
        Variable other = (Variable) obj;
        checkRep();
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
