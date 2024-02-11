package Dodatek;

public class PrimeNumberChecker {

    public static void main(String[] args) {
        int number = 7;

        boolean isPrime = isPrimeNumber(number);

        if (isPrime) {
            System.out.println(number + " jest liczbą pierwszą.");
        } else {
            System.out.println(number + " nie jest liczbą pierwszą.");
        }
    }

    private static boolean isPrimeNumber(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
