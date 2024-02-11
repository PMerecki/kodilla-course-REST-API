package Dodatek;

public class SortowanieBąbelkowe {

    public static void sortuj(int[] tablica) {
        int n = tablica.length;
        boolean zamiana;

        do {
            zamiana = false;
            for (int i = 0; i < n - 1; i++) {
                if (tablica[i] > tablica[i + 1]) {
                    int temp = tablica[i];
                    tablica[i] = tablica[i + 1];
                    tablica[i + 1] = temp;
                    zamiana = true;
                }
            }
            n--;
        } while (zamiana);
    }

    public static void main(String[] args) {
        int[] ciag = {2, 1, 8, 5, 4, 2, 4, 1};

        System.out.println("Przed sortowaniem:");
        for (int liczba : ciag) {
            System.out.print(liczba + " ");
        }
        System.out.println();

        sortuj(ciag);

        System.out.println("Po sortowaniu:");
        for (int liczba : ciag) {
            System.out.print(liczba + " ");
        }
        System.out.println();
    }
}
