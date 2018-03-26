package hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hello.MyClass;

class Tests {

    @Test
    void twoPlusTwoIsFour() {
        MyClass tester = new MyClass();

        assertEquals(4, tester.multiply(2, 2), "2 plus 2 is 4");
        assertEquals(6, tester.multiply(2, 3), "2 times 3 is 6");
    }
}
