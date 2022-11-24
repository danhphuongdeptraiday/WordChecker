package a1_2001140045;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    public static Set<String> stopWords = new LinkedHashSet<>();
    private String word;
    private Word(String rawText) {
        this.word = rawText;
    }

    public static Word createWord(String rawText) {
        return new Word(rawText);
    }
// Word w = new Word("ranh phuong")
    //Word w = Word.createWord("ranh phuong")

    public boolean checkValidWord() {

        if (this.word.length() <= 0) {
            return false;
        }
        int hyphen = 0;
        for (int i = 0; i < this.word.length(); i++) {
            if (Character.isDigit(this.word.charAt(i))) {
                return false;
            }
            if (String.valueOf(this.word.charAt(i)).matches(".*[\'\"].*")) {
               continue;
            }
            if (Character.isAlphabetic(this.word.charAt(i))) {
                continue;
            }

            if (this.word.charAt(i) == '-') {
                // hyphen should be surrounded
                // by letters
                if (i - 1 < 0 || !Character.isAlphabetic(this.word.charAt(i - 1)) ||
                        i + 1 >= this.word.length() || !Character.isAlphabetic(this.word.charAt(i + 1))) {
                    return false;
                }
            }

            // Punctuation must be at the
            // end of the word
            else if (i != this.word.length()- 1
                    && ((this.word.charAt(i) == '!'
                    || this.word.charAt(i) == ','
                    || this.word.charAt(i) == ';'
                    || this.word.charAt(i) == '.'
                    || this.word.charAt(i) == '?'
                    || this.word.charAt(i) == '-'
                    || this.word.charAt(i) == '\''
                    || this.word.charAt(i) == '\"'
                    || this.word.charAt(i) == ':'
                    || this.word.charAt(i) == ' '
                    ))) {
                return false;
            }
        }
        return true;
    }


    public boolean isKeyword() {
        if (stopWords.contains(this.word.toLowerCase())) {
            return false;
        } else {
            if (checkValidWord()) {
                return true;
            }
            return false;
        }
    }

    public String getPrefix() {
        if (checkValidWord()) {
            return this.word.substring(0, this.word.indexOf(getText()));
//            return String.valueOf(this.word.charAt(0));
        }
        return "";
    }

    public String getSuffix() {
        if (checkValidWord()) {
           int p = getPrefix().length();
           for(int i = p; i< this.word.length();i++){
               if(!Character.isLetterOrDigit(this.word.charAt(i))){
                   if(this.word.charAt(i) == '-'){
                       continue;
                   }
                   return this.word.substring(i);
               }
           }
        }
        return "";
    }

    public String getText() {
        String newWord = this.word.replaceAll("('s)[\\W]*$", "");
        Pattern pattern = Pattern.compile("([-|']*[a-zA-Z]+[-|']*)");
        Matcher matcher = pattern.matcher(newWord);

        String t = "";
        while (matcher.find()) {
            t += matcher.group();
        }
        int length = t.length();
        return checkValidWord() && length != 0 ? t : this.word;
    }

    public static boolean loadStopWords(String fileName) {
        File file = new File(fileName);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String eachLine = sc.nextLine();
                stopWords.add(eachLine);
            }
            sc.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.word;
    }

    public boolean equals(Object o) {
        Word word = (Word)o;
        return word.getText().toLowerCase().equals(getText().toLowerCase());
    }
}
