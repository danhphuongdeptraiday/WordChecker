package a1_2001140045;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> titleStore = new ArrayList<>();
    private List<Word> bodyStore = new ArrayList<>();

    public Doc(String content) {
        String[] holeText = content.split("\n");
        handleWords(holeText[0].split(" "), 0);
        handleWords(holeText[1].split(" "), 1);

//        String[] eachBodyWords = holeText[1].split(" ");
//        for (int i = 0; i < eachBodyWords.length; i++) {
//            bodyStore.add(Word.createWord(eachBodyWords[i]));
//        }
    }

    public void handleWords(String[] holeText, int index) {
        String[] eachWords = holeText;
        if (index == 0) {
            for (int i = 0; i < eachWords.length; i++) {
                titleStore.add(Word.createWord(eachWords[i]));
            }
        } else if (index == 1) {
            for (int i = 0; i < eachWords.length; i++) {
                bodyStore.add(Word.createWord(eachWords[i]));
            }
        }

    }


    public List<Word> getTitle() {
        return titleStore;
    }

    public List<Word> getBody() {
        return bodyStore;
    }

    public boolean equals(Object o) {
        Doc d = (Doc) o;
        boolean b1 = true;
        boolean b2 = true;
        int j = 0;
        while (j < titleStore.size()) {
            Word w1 = titleStore.get(j);
            Word w2 = d.titleStore.get(j);
            if (w1.equals(w2)){
                b1 = true;
            } else {
                b1 = false;
            }
            j++;
        }
        for (int i = 0; i < bodyStore.size(); i++) {
            Word w2 = d.getBody().get(i);
            if (bodyStore.get(i).equals(w2)){
                b2 = true;
            } else {
                b2 = false;
            }
        }

        if (!(b1 && b2)) {
            return false;
        }
        return true;
    }
}
