package Dodatek;

public class Zamiana {
    public static void main(String[] args) {
        // Przykładowe testy
        char[] testChars = {'0', '5', '9', 'a', '.', ','};

        for (char c : testChars) {
            try {
                int result = parseCharToInt(c);
                System.out.println("Znak '" + c + "' zamieniony na liczbę całkowitą: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }

    public static int parseCharToInt(char c) {
        // Sprawdzenie, czy znak jest cyfrą
        if (Character.isDigit(c)) {
            // Zamiana znaku na liczbę całkowitą odejmując kod ASCII '0'
            return c - '0';
        } else {
            throw new IllegalArgumentException("Podano niedozwolony znak. Podaj tylko cyfrę.");
        }
    }
}

