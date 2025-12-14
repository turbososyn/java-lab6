import java.util.HashMap;
import java.util.Map;

public class Translator {
    private final HashMap<String, String> dictionary = new HashMap<>();

    public void addWordPair(String en, String ua) {
        if (en == null || ua == null) return;
        String key = normalize(en);
        String val = ua.trim();
        if (!key.isEmpty() && !val.isEmpty()) dictionary.put(key, val);
    }

    public String translatePhrase(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) return "";

        String[] tokens = phrase.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            String[] wp = splitWordAndTrailingPunct(token);
            String rawWord = wp[0];
            String punct = wp[1];

            String key = normalize(rawWord);
            String translated = dictionary.getOrDefault(key, rawWord);

            result.append(translated).append(punct);
            if (i < tokens.length - 1) result.append(" ");
        }

        return result.toString();
    }

    public void printDictionary() {
        if (dictionary.isEmpty()) {
            System.out.println("(Словник порожній)");
            return;
        }
        System.out.println("Поточний словник:");
        for (Map.Entry<String, String> e : dictionary.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }
    }

    private String normalize(String s) {
        return s.trim().toLowerCase();
    }

    private String[] splitWordAndTrailingPunct(String token) {
        int end = token.length();
        while (end > 0) {
            char c = token.charAt(end - 1);
            if (c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':') end--;
            else break;
        }
        String word = token.substring(0, end);
        String punct = token.substring(end);
        return new String[]{word, punct};
    }
}
