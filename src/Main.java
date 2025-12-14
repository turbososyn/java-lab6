import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Translator translator = new Translator();

        translator.addWordPair("hello", "привіт");
        translator.addWordPair("world", "світ");
        translator.addWordPair("i", "я");
        translator.addWordPair("love", "люблю");
        translator.addWordPair("java", "java");

        System.out.println("=== Консольний перекладач EN -> UA ===");
        System.out.print("Скільки пар слів хочеш ввести? (0 - пропустити): ");

        int n = readIntSafe(sc);
        for (int i = 0; i < n; i++) {
            System.out.print("Введи англійське слово: ");
            String en = sc.nextLine();
            System.out.print("Введи український переклад: ");
            String ua = sc.nextLine();
            translator.addWordPair(en, ua);
        }

        System.out.println();
        translator.printDictionary();
        System.out.println();

        System.out.print("Введи фразу англійською: ");
        String phrase = sc.nextLine();

        System.out.println("Переклад українською:");
        System.out.println(translator.translatePhrase(phrase));
    }

    private static int readIntSafe(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) return 0;
            try {
                int val = Integer.parseInt(line);
                return Math.max(val, 0);
            } catch (NumberFormatException e) {
                System.out.print("Будь ласка, введи ціле число: ");
            }
        }
    }
}
