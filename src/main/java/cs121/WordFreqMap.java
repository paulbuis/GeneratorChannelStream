package cs121;

import producerConsumer.ScannerGenerator;
import java.util.Map;
import java.util.Set;
import java.io.File;

import java.util.stream.Collectors;



public final class WordFreqMap {
    private final Map<String, Long> wordFreq;// = new TreeMap<>();

    public record WordFreqPair(String word, long freq) { }

    public WordFreqMap(String fileName) {
        ScannerGenerator ss = new ScannerGenerator(new File(fileName));
        wordFreq = ss.map(line->{
            String[] split = line.split("\t");
            return new WordFreqPair(split[0],Long.parseLong(split[1]) );
        })
                .filter(wfPair-> wfPair.freq()>50000)
                .collect(Collectors.toMap(WordFreqPair::word, WordFreqPair::freq));

        System.out.printf("Word Frequency Map, size=%,d\n", wordFreq.size());
    }

    public Set<String> getWordSet() {
        return wordFreq.keySet();
    }

    public long get(String word) {
        return wordFreq.getOrDefault(word, 0L);
    }

    public void increment(String word) {
        wordFreq.merge(word, 1L, (oldValue, newValue)->oldValue+newValue);
    }



    public static void main(String[] args) {
        WordFreqMap wfm = new WordFreqMap("wordFreq.txt");
        System.out.println("the " + wfm.get("the"));
        System.out.println("bob " + wfm.get("bob"));

        System.out.println("holle " + wfm.wordFreq.get("holle"));
        System.out.println("hello " + wfm.wordFreq.get("hello"));
        System.out.println("ohell " + wfm.get("ohell"));

        long freq1 = wfm.get("ohell");
        long freq2 = wfm.get("holle");
        if (freq1 > freq2) {
            System.out.println("'ohell' more common than 'holle'");
        }
        else {
            System.out.println("'holle' more common than 'ohell'");
        }

        System.out.println("holle " + wfm.wordFreq.get("holle"));
        wfm.increment("holle");
        System.out.println("holle " + wfm.wordFreq.get("holle"));
    }
}

