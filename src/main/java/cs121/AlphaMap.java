package cs121;

import java.util.*;

public class AlphaMap {

    private final Map<String, String> alphaMap = new HashMap<>();

    /**
     * construct an alphaMap by iterating over all the set of all words in the dictionary.
     * and using alphabetized word letters as key to the value consisting of the most common
     * dictionary word with that set of letters.
     */
    public AlphaMap() {
        WordFreqMap wfm = new WordFreqMap("wordfreq.txt");
        for (String newWord: wfm.getWordSet()) {
            long newWordFreq = wfm.get(newWord);
            String alpha = alphabetize(newWord);
            String currentValue = alphaMap.get(alpha);
            if (currentValue == null) {
                alphaMap.put(alpha, newWord);
            } else {
                long currentFreq = wfm.get(currentValue);

                if (currentFreq < newWordFreq) {
                    alphaMap.put(alpha, newWord);
                }
            }
        }
        System.out.printf("Alpha Map, size=%,d\n", alphaMap.size());
    }

    public String getWord(String clue) {
        String alpha = alphabetize(clue);
        String value = alphaMap.getOrDefault(alpha, "");
        System.out.printf("clue=%s, alpha=%s, value=%s\n", clue, alpha, value);
        return value;
    }


    public static String alphabetize(String input) {
        char[] array = input.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public static void main(String[] args) {
        AlphaMap am = new AlphaMap();
        System.out.println(am.getWord("ehllo")); // Want "hello" not "holle"

        String[] input = {"tea", "ate", "holle", "hello"};
        String[] output= {"aet", "aet", "ehllo", "ehllo"};
        for (int i=0; i<input.length; i++) {
            String result = alphabetize(input[i]);
            System.out.printf("input:%s, expected:%s, got:%s %s\n",
                    input[i], output[i], result,
                    result.equals(output[i])?"Test Passed":"TEST FAILED");
        }

    }
}

