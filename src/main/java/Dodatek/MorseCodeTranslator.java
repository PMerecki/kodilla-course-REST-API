package Dodatek;

import java.util.HashMap;

public class MorseCodeTranslator {
    private static final HashMap<String, Character> morseToLatinMap = new HashMap<>();

    static {
        morseToLatinMap.put(".-", 'A');
        morseToLatinMap.put("-...", 'B');
        morseToLatinMap.put("-.-.", 'C');
        morseToLatinMap.put("-..", 'D');
        morseToLatinMap.put(".", 'E');
        morseToLatinMap.put("..-.", 'F');
        morseToLatinMap.put("--.", 'G');
        morseToLatinMap.put("....", 'H');
        morseToLatinMap.put("..", 'I');
        morseToLatinMap.put(".---", 'J');
        morseToLatinMap.put("-.-", 'K');
        morseToLatinMap.put(".-..", 'L');
        morseToLatinMap.put("--", 'M');
        morseToLatinMap.put("-.", 'N');
        morseToLatinMap.put("---", 'O');
        morseToLatinMap.put(".--.", 'P');
        morseToLatinMap.put("--.-", 'Q');
        morseToLatinMap.put(".-.", 'R');
        morseToLatinMap.put("...", 'S');
        morseToLatinMap.put("-", 'T');
        morseToLatinMap.put("..-", 'U');
        morseToLatinMap.put("...-", 'V');
        morseToLatinMap.put(".--", 'W');
        morseToLatinMap.put("-..-", 'X');
        morseToLatinMap.put("-.--", 'Y');
        morseToLatinMap.put("--..", 'Z');
        morseToLatinMap.put("-----", '0');
        morseToLatinMap.put(".----", '1');
        morseToLatinMap.put("..---", '2');
        morseToLatinMap.put("...--", '3');
        morseToLatinMap.put("....-", '4');
        morseToLatinMap.put(".....", '5');
        morseToLatinMap.put("-....", '6');
        morseToLatinMap.put("--...", '7');
        morseToLatinMap.put("---..", '8');
        morseToLatinMap.put("----.", '9');
    }

    public static String morseToLatin(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split(" ");

        for (String word : words) {
            result.append(morseToLatinMap.getOrDefault(word, ' '));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Przykłady użycia
        System.out.println(morseToLatin(".- -.. .- --"));  // Wynik: ADAM
        System.out.println(morseToLatin("-.- --- -.. .. .-.. .-.. .-"));  // Wynik: KODILLA
        System.out.println(morseToLatin("... --- ..."));  // Wynik: SOS
    }
}

