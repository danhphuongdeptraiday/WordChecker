package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Query {
    List<Word> listKeyWord = new ArrayList<>();

    public Query(String searchParse) {
        String[] temp = searchParse.split(" ");
        for (int i = 0; i < temp.length; i++) {
            if (Word.createWord(temp[i]).isKeyword()) {
                listKeyWord.add(Word.createWord(temp[i]));
            }
        }
    }

    public List<Word> getKeywords() {
        return listKeyWord;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> newList = new ArrayList<Match>();
        List<Word> arrWordInDoc = new ArrayList<Word>();
        for (List<Word> ws: Arrays.asList(d.getTitle(), d.getBody()))  {
            arrWordInDoc.addAll(ws);
        }
        int i = 0;

        // Handle word in listKeyWord of Query class
        while (i < listKeyWord.size()) {
            Word word = listKeyWord.get(i);
            int freq = 0;
            int firstFound = arrWordInDoc.indexOf(word);
            for (int j = 0; j < arrWordInDoc.size(); j++) {
                if (word.equals(arrWordInDoc.get(j))) {
                    freq =  freq + 1;
                }

                if (freq == 0) {
                    firstFound = j + 1;
                }
            }
            if (freq > 0) {
                newList.add(new Match(d, word, freq, firstFound));
            }
            i++;
        }
        Collections.sort(newList);
        return newList;
    }
}
