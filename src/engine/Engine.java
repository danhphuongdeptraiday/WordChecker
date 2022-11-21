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

        int count = 0;
        for (File file: listOffFile) {
            String t = "";

            try {
                Scanner sc = new Scanner(file);

                while (sc.hasNextLine()) {
                    t = t + sc.nextLine() + "\n";
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
            List<Match> match;
            match = q.matchAgainst(doc[i]);
            if (match.size() <= 0) {
                break;
            }
            r.add(new Result(doc[i], match));
        }
      Collections.sort(r);
        return r;
    }

    public String htmlResult(List<Result> results) {
        String s = "";
        for (int i = 0; i < results.size(); i++){
            s = s + results.get(i).htmlHighlight();
        }

        return s;
    }
}
