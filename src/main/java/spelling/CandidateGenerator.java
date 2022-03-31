package spelling;


import java.util.*;

import java.util.function.Consumer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import comprehension.*;
import cs121.WordFreqMap;
import producerConsumer.*;

public class CandidateGenerator extends Generator<String>{
    private static final List<Character> letters = (new CharRange('a', 'z')).stream().toList();
    private final List<Slice> slices;
    public CandidateGenerator(String str) {
        slices = (new Range(str.length()+1)).map(index->new Slice(str.substring(0,index), str.substring(index))).toList();
    }
    @Override
    public void produce(Consumer<String> consumer) {
        for (Slice slice: slices) {
            Set<String> insertionSet = Sets.set(letters, letter->slice.head() + letter + slice.tail());
            insertionSet.forEach(item->consumer.accept(item));
            if (!slice.tail().isEmpty()) {
                Set<String> substitutionSet = Sets.set(letters, letter->slice.head() + letter + slice.tail().substring(1));
                substitutionSet.forEach(item->consumer.accept(item));
                // deletion
                consumer.accept(slice.head() + slice.tail().substring(1));
                if(!slice.head().isEmpty()) {
                    // interchange
                    consumer.accept(slice.head().substring(0, slice.head().length()-1)
                            + slice.tail().charAt(0)
                            + slice.head().charAt(slice.head().length()-1)
                            + slice.tail().substring(1));
                }
            }
        }
    }


    private record Slice(String head, String tail) {}

    public static void main(String[] args) {
        cs121.WordFreqMap wfm = new WordFreqMap("wordfreq.txt");
        Set<String> dictionarySet = wfm.getWordSet();
        CandidateGenerator cg = new CandidateGenerator("acress");
        Stream<String> candidateStream = cg.filter(str->dictionarySet.contains(str));
        Set<String> candidateSet = candidateStream.collect(Collectors.toSet());
        System.out.println("candidateSet.size()=" + candidateSet.size());
        for (String s: new TreeSet<>(candidateSet)) {
            System.out.printf("%8s %,16d\n", s, wfm.get(s));
        }
    }
}
