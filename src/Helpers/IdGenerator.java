package Helpers;

public class IdGenerator {
    private static long idCounter = 0;

    public static synchronized String generateId() {
        return String.valueOf(++idCounter);
    }
}
