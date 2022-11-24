package engine;

public class Match implements Comparable<Match>{
    private Doc document;
    private Word word;
    private int fre;
    public int firstIn;

    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.document =  d;
        this.word = w;
        this.fre = freq;
        this.firstIn = firstIndex;
    }

    public Word getWord() {
        return this.word;
    }

    public int getFreq() {
        return this.fre;
    }

    public int getFirstIndex()  {
        return this.firstIn;
    }

    public int compareTo(Match o) {
        int a = getFirstIndex();
        int b = o.getFirstIndex();
        if (a < b) {
            return -1;
        } else if (a > o.getFirstIndex()) {
            return 1;
        }
        return 0;
    }
}
