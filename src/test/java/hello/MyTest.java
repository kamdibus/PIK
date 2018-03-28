package hello;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MyTest {

    @Test
    public void twoPlusTwoIsFour() {
        MyClass tester = new MyClass();

        assertEquals(4, tester.multiply(2, 2));
        assertEquals(6, tester.multiply(2, 3));
    }
}
