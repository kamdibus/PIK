import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hello.MyClass;

class Tests {

    @Test
    void twoPlusTwoIsFour() {
        MyClass tester = new MyClass();

        assertEquals(4, tester.multiply(2, 2), "2 plus 2 is 4");
    }
}
