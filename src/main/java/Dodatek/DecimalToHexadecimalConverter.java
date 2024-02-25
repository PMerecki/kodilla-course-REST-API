package Dodatek;

public class DecimalToHexadecimalConverter {

    public static String convertToHexadecimal(int decimalNumber) {
        if (decimalNumber == 0) {
            return "0"; // Jeśli liczba dziesiętna wynosi 0, to zwracamy string "0" w systemie szesnastkowym
        }

        StringBuilder hexadecimal = new StringBuilder();
        while (decimalNumber > 0) {
            int remainder = decimalNumber % 16; // Obliczamy resztę z dzielenia przez 16
            hexadecimal.insert(0, getHexDigit(remainder)); // Wstawiamy odpowiadającą reszcie cyfrę szesnastkową na początek wynikowego stringa
            decimalNumber /= 16; // Dzielimy liczbę całkowitą przez 16, aby uzyskać kolejną cyfrę szesnastkową
        }

        return hexadecimal.toString();
    }

    private static char getHexDigit(int value) {
        if (value >= 0 && value <= 9) {
            return (char) ('0' + value); // Cyfry od 0 do 9 w systemie szesnastkowym są takie same jak w systemie dziesiętnym
        } else {
            return (char) ('A' + value - 10); // Litery od A do F odpowiadają wartościom od 10 do 15 w systemie dziesiętnym
        }
    }

    public static void main(String[] args) {
        int decimalNumber = 1000; // Przykładowa liczba dziesiętna do konwersji
        String hexadecimal = convertToHexadecimal(decimalNumber);
        System.out.println("Liczba " + decimalNumber + " w systemie szesnastkowym: " + hexadecimal);
    }
}

