package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Engine {
    List<Doc> doc = new ArrayList<>();
    public int loadDocs(String dirname) {
        File dir = new File(dirname);
        File[] listOffFile = dir.listFiles();

        for (File file: listOffFile) {
            String t = "";

            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    t += sc.nextLine() + "\n";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            Doc d = new Doc(t);
            doc.add(d);
        }
        return listOffFile.length;
    }

    public Doc[] getDocs() {
        Doc[] docs = new Doc[this.doc.size()];
        return this.doc.toArray(docs);
    }

    public List<Result> search(Query q) {
        List<Result> r = new ArrayList<Result>();
        Doc[] doc = getDocs();
        for (int i = 0 ; i < doc.length; i++) {
            if (handleResult(doc[i], q) != null) {
                r.add(handleResult(doc[i], q));
            }
        }
        Collections.sort(r);
        return r;
    }

    public Result handleResult(Doc doc, Query q) {
        List<Match> match;
        Doc d = doc;
        match = q.matchAgainst(d);
        int m = match.size();
        if (m <= 0) {
            return null;
        }
        Result re = new Result(d, match);
        return re;
    }

    public String htmlResult(List<Result> results) {
        String s = "";
        int i = 0;
        while (i < results.size()){
            Result r = results.get(i);
            s += r.htmlHighlight();
            i++;
        }

        return s;
    }
}
