package engine;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private List<Word> titleStore = new ArrayList<>();
    private List<Word> bodyStore = new ArrayList<>();

    public Doc(String content) {
        String[] holeText = content.split("\n");
        String[] eachTitleWords = holeText[0].split(" ");
        for (int i = 0; i < eachTitleWords.length; i++) {
            titleStore.add(Word.createWord(eachTitleWords[i]));
        }

        String[] eachBodyWords = holeText[1].split(" ");
        for (int i = 0; i < eachBodyWords.length; i++) {
            bodyStore.add(Word.createWord(eachBodyWords[i]));
        }
    }

    public List<Word> getTitle() {
        return this.titleStore;
    }

    public List<Word> getBody() {
        return this.bodyStore;
    }

    public boolean equals(Object o) {
        Doc d = (Doc) o;
        boolean b1 = true;
        boolean b2 = true;
        for (int i = 0; i < titleStore.size(); i++) {
            if (this.titleStore.get(i).equals(d.getTitle().get(i))){
                b1 = true;
            } else {
                b1 = false;
            }
        }
        for (int i = 0; i < bodyStore.size(); i++) {
            if (this.bodyStore.get(i).equals(d.getBody().get(i))){
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
