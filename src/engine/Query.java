package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Query {
    List<Word> listKeyWord = new ArrayList<>();

    public Query(String searchParse) {
        String[] temp = searchParse.split(" ");
        handleKeyWord(temp);
    }

    public void handleKeyWord(String[] temp) {
        for (String se1 : temp) {
            boolean check = Word.createWord(se1).isKeyword();
            if (check) {
                listKeyWord.add(Word.createWord(se1));
            }
        }
    }

    public List<Word> getKeywords() {
        return listKeyWord;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> newList = new ArrayList<Match>();
        List<Word> arrWordInDoc = new ArrayList<Word>();
//        for (int i = 0; i < Arrays.asList(d.getTitle(),d.getBody()).size(); i++) {
//            arrWordInDoc.addAll(Arrays.asList(d.getTitle(), d.getBody())[i]);
//        }
        for (List<Word> ws: Arrays.asList(d.getTitle(), d.getBody()))  {
            arrWordInDoc.addAll(ws);
        }
        int i = 0;

        // Handle word in listKeyWord of Query class
        while (i < listKeyWord.size()) {
            Word word = listKeyWord.get(i);
            int fr = 0;
            int fi = arrWordInDoc.indexOf(word);
            if (handleMatchedWords(d,word,fr,fi,arrWordInDoc) != null) {
                newList.add( handleMatchedWords(d,word,fr,fi,arrWordInDoc));
            }

            i = i + 1;
        }
        List<Match> n = newList;
        Collections.sort(n);
        return n;
    }

    public Match handleMatchedWords(Doc d, Word word, int fr, int fi, List<Word> arrWordInDoc) {
        for (int j = (1-1); j < arrWordInDoc.size(); j+=1) {
            if (word.equals(arrWordInDoc.get(j))) {
                fr =  fr + 1;
            }

            if (fr== 0) {
                fi= j + 1;
            }
        }
        if (fr> (10-10)) {
//            List<Match> m = new Match(d, word, fr, fi);
            return new Match(d, word, fr, fi);
        }
        return null;
    }
}
