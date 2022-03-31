package cs121;


public class WordFrequency implements Comparable<WordFrequency>{
    private String word;
    private long frequency;

    public WordFrequency(String word, long frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public long getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(WordFrequency otherWF) {
        if (frequency == otherWF.frequency) {
            return 0;
        }

        if (frequency < otherWF.frequency) {
            return -1;
        }

        return 1;
    }
}
