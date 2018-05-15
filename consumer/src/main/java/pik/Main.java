package pik;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Consumer consumer = new Consumer();
        while(true) {
            sleep();
            consumer.consume();
        }

    }

    private static void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
    }
}
