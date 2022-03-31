package cs121;

import producerConsumer.Generator;

import java.util.function.Consumer;


public class PermutationGenerator extends Generator<String> {

    private final char[] letters;

    public PermutationGenerator(String word) {
        letters = word.toCharArray();
    }
    @Override
    public void produce(Consumer<String> consumer) {
        int wordLength = letters.length;
        int[] indexes = new int[wordLength];
        for (int i = 0; i < wordLength; i++) {
            indexes[i] = 0;
        }
        consumer.accept(new String(letters));
        int i = 0;
        while (i < wordLength) {
            if (indexes[i] < i) {
                int j = i % 2 == 0 ?  0: indexes[i];
                char temp = letters[i];
                letters[i] = letters[j];
                letters[j] = temp;
                consumer.accept(new String(letters));
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        PermutationGenerator pg = new PermutationGenerator("abc");
        pg.stream().forEach(str -> System.out.println(str));
    }
}
