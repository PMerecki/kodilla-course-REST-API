package Dodatek;

public class StringToIntegerConverter {

    public static void main(String[] args) {
        String numberAsString = "2040";

        try {
            int result = convertStringToInteger(numberAsString);
            System.out.println("Wartość liczby jako integer: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Błąd: Wprowadzono niepoprawną liczbę.");
        }
    }

    public static int convertStringToInteger(String str) throws NumberFormatException {
        // Usuwamy białe znaki z początku i końca łańcucha
        str = str.trim();

        // Sprawdzamy, czy łańcuch nie jest pusty
        if (str.isEmpty()) {
            throw new NumberFormatException("Pusty łańcuch");
        }

        // Sprawdzamy, czy łańcuch zawiera jedynie cyfry
        if (!str.matches("\\d+")) {
            throw new NumberFormatException("Niepoprawny format liczby");
        }

        // Konwertujemy łańcuch na liczbę całkowitą
        int result = Integer.parseInt(str);

        return result;
    }
}
