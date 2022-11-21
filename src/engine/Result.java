package engine;

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
        return this.matchList;
    }

    public int getTotalFrequency() {
        int t = 0;
        int i = 0;
        while (i < matchList.size()) {
            t = t + matchList.get(i).getFreq();
            i++;
        }
        return t;
    }

    public int getAverageFirstIndex() {
        int f = 0;
        int i = 0;
        while (i < matchList.size()) {
            f = f + matchList.get(i).getFirstIndex();
            i++;
        }
        return f / matchList.size();
    }

    public String htmlHighlight() {
        // title
        List<Word> wordTitleList = new ArrayList<>(doc.getTitle());
        String titleLines = "";
        int check = 0;
        for (int i = 0; i < wordTitleList.size(); i++) {
            for (int j = 0; j < matchList.size(); j++) {
                if (matchList.get(j).getWord().equals(wordTitleList.get(i))) {
                    titleLines = titleLines + wordTitleList.get(i).getPrefix() + "<u>" +
                            wordTitleList.get(i).getText() + "</u>" + wordTitleList.get(i).getSuffix() +" ";
                    check++;
                }
            }
            if(check > 0){
                check = 0;
               continue;
            }
            titleLines = titleLines + wordTitleList.get(i) + " ";
        }
        titleLines = "<h3>" + titleLines.trim() + "</h3>";

        // body
        List<Word> wordBodyList = new ArrayList<>(doc.getBody());
        String bodyLines = "";
        for (int i = 0; i < wordBodyList.size(); i++) {
            for (int j = 0; j < matchList.size(); j++) {
                if (matchList.get(j).getWord().equals(wordBodyList.get(i))) {
                    bodyLines = bodyLines + wordBodyList.get(i).getPrefix() + "<b>" +
                            wordBodyList.get(i).getText() + "</b>" + wordBodyList.get(i).getSuffix() +" ";
                    check++;
                }
            }
            if(check > 0){
                check =0;
                continue;
            }
            bodyLines = bodyLines + wordBodyList.get(i) + " ";
        }
        bodyLines = "<p>" + bodyLines.trim()  + "</p>";
        String s = titleLines + bodyLines;
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
