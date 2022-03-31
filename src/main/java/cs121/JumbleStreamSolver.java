package cs121;

import java.util.Set;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class JumbleStreamSolver {
    WordFreqMap wfMap = new WordFreqMap("wordfreq.txt");
    Set<String> dictionaryWords = wfMap.getWordSet();

    public void solve(String clue) {
        PermutationGenerator pg = new PermutationGenerator((clue));
        Predicate<String> inDictionary = word -> dictionaryWords.contains(word);
        List<String> wordList = pg.filter(inDictionary)
            .distinct().toList();
        for (String word : wordList) {
            System.out.println(word);
        }
        /*

        List<String> list = distinctWords.toList();
        System.out.println(list);
         */
    }

    public static void main(String[] args) {
        JumbleStreamSolver jss = new JumbleStreamSolver();
        jss.solve("interwize");
    }
}
