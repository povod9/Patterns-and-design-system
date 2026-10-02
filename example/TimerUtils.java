package org.example;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * DO NOT TOUCH THIS FILE!
 */
public class TimerUtils {

    public static <T> T measure(String operation, Supplier<T> action, Duration sleepTime) {
        final var start = System.nanoTime();
        T result;
        try {
            result = action.get();
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            final var elapsedMs = (System.nanoTime() - start) / 1_000_000;
            System.out.printf("[%s] took %d ms%n", operation, elapsedMs);
        }
        return result;
    }
}
