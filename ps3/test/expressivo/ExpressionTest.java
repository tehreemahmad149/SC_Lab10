/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testNumber() {
        Number n1 = new Number(5.0);
        Number n2 = new Number(5.0);
        Number n3 = new Number(3.14159);

        // toString
        assertEquals("5.0", n1.toString());
        
        // equals and hashCode
        assertEquals(n1, n2);
        assertEquals(n1.hashCode(), n2.hashCode());
        assertNotEquals(n1, n3);
    }
    
    @Test(expected = AssertionError.class)
    public void testNumberInvalidValue() {
        new Number(Double.POSITIVE_INFINITY); // infinity is invalid according to checkRep();
    }

    @Test
    public void testVariable() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Variable x2 = new Variable("x");

        // toString
        assertEquals("x", x.toString());
        assertEquals("y", y.toString());

        // equals and hashCode
        assertEquals(x, x2);
        assertEquals(x.hashCode(), x2.hashCode());
        assertNotEquals(x, y);
    }
    
    @Test(expected = AssertionError.class)
    public void testVariableInvalidName() {
        new Variable(""); // Should fail checkRep
    }
    
    @Test
    public void testSum() {
        Expression n1 = new Number(1.0);
        Expression n2 = new Number(2.0);
        Sum sum = new Sum(n1, n2);

        // toString
        assertEquals("1.0 + 2.0", sum.toString());

        // equals and hashCode
        Sum sameSum = new Sum(n1, n2);
        assertEquals(sum, sameSum);
        assertEquals(sum.hashCode(), sameSum.hashCode());

        Sum differentSum = new Sum(n2, n1);
        assertEquals(sum, differentSum);
    }
    
    
    @Test(expected = AssertionError.class)
    public void testSumInvalid() {
        new Sum(null, new Number(1.0)); // Should fail checkRep
    }
    
    
    @Test
    public void testProduct() {
        Expression x = new Variable("x");
        Expression y = new Variable("y");
        Product product = new Product(x, y);

        // toString
        assertEquals("x * y", product.toString());

        // equals and hashCode
        Product sameProduct = new Product(x, y);
        assertEquals(product, sameProduct);
        assertEquals(product.hashCode(), sameProduct.hashCode());

        Product differentProduct = new Product(y, x);
        assertNotEquals(product, differentProduct);
    }
    
    
    @Test(expected = AssertionError.class)
    public void testProductInvalid() {
        new Product(null, new Variable("x")); // Should fail checkRep
    }
    
    
    
    

    
}
