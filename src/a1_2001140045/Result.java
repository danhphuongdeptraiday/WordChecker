package a1_2001140045;

import java.util.ArrayList;
import java.util.List;

public class Result implements Comparable<Result> {
    private Doc doc;
    private List<Match> matchList;

    public Result(Doc d, List<Match> matches) {
        this.doc = d;
        this.matchList = matches;
    }

    public List<Match> getMatches() {
        List<Match> newMatch = this.matchList;
        return newMatch;
    }

    public int getTotalFrequency() {
        int t = 0;
        int i = 0;
        while (i < matchList.size()) {
            Match match = matchList.get(i);
            int n = match.getFreq();
            t = t + n;
            i++;
        }
        return t;
    }

    public int getAverageFirstIndex() {
        int f = 0;
        int i = 0;
        while (i < matchList.size()) {
            Match match = matchList.get(i);
            int n = match.getFirstIndex();
            f += n;
            i++;
        }
        return f / matchList.size();
    }

    public String handleTitle() {
        List<Word> wordTitleList = new ArrayList<>(doc.getTitle());
        String titleLines = "";
        int check = 0;
        for (int i = 0; i < wordTitleList.size(); i++) {
            for (int j = 0; j < matchList.size(); j++) {
                if (matchList.get(j).getWord().equals(wordTitleList.get(i))) {
                    Word word2 = wordTitleList.get(i);
                    titleLines +=
                              word2.getPrefix()
                            + "<u>"
                            + word2.getText()
                            + "</u>"
                            + word2.getSuffix()
                            + " ";
                    check++;
                }
            }
            if(check > 0){
                check = 0;
                continue;
            }
            titleLines = titleLines + wordTitleList.get(i) + " ";
        }
        titleLines =
                "<h3>"
                + titleLines.trim() +
                "</h3>";
        return titleLines;
    }

    public String handleBody() {
        List<Word> wordBodyList = new ArrayList<>(doc.getBody());
        String bodyLines = "";
        int check = 0;
        for (int i = 0; i < wordBodyList.size(); i++) {
            for (int j = 0; j < matchList.size(); j++) {
                Word word1 = matchList.get(j).getWord();
                Word word2 = wordBodyList.get(i);
                if (word1.equals(word2)) {
                    bodyLines +=
                            word2.getPrefix()
                                    + "<b>"
                            + word2.getText()
                                    + "</b>"
                            + word2.getSuffix()
                                    + " ";
                    check++;
                }
            }
            if(check > 0){
                check = 0;
                continue;
            }
            bodyLines =
                    bodyLines
                    + wordBodyList.get(i)
                    + " ";
        }
        bodyLines =
                "<p>"
                + bodyLines.trim() +
                "</p>";
        return bodyLines;
    }

    public String htmlHighlight() {
        // Handle title
        String s1 = handleTitle();
        String s2 = handleBody();

        // Handle body
        String s = s1 + s2;
        return s;
    }

    public Doc getDoc() {
        return this.doc;
    }

    public int compareTo(Result o) {
        int a = this.getMatches().size();
        int aFreq = this.getTotalFrequency();
        int aFirstIn = this.getAverageFirstIndex();
        int b = o.getMatches().size();
        int bFreq = o.getTotalFrequency();
        int bFirstIn = o.getAverageFirstIndex();
        if (a < b) {
            return 1;
        } else if(a == b) {
            if (aFreq < bFreq) {
                return 1;
            } else if (aFreq == bFreq) {
                if (aFirstIn < bFirstIn) {
                    return 1;
                } else if (aFirstIn == bFirstIn) {
                    return 0;
                }
            }
        }

        return -1;
//        if (a <= b) {
//            if (a == b) {
//                if (aFreq <= bFreq) {
//                    if (aFreq < bFreq) {
//                        return 1;}
//                    else {
//                        if (aFreq != bFreq) {
//                            return 0;
//                        }
//                        if (aFirstIn > bFirstIn) {
//                            return -1;
//                        } else if (aFirstIn < bFirstIn) {
//                            return 1;
//                        }
//                    }
//                } else {
//                    return -1;
//                }
//            } else if (a < b) {
//                return 1;
//            }
//        }else {
//            return -1;
//        }
//        return 0;
    }
}
