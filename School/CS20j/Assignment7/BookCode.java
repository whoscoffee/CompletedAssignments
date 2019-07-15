import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

class BookCode {
    HashMap<Integer, String> hashBrowns = new HashMap<>();
    Scanner sc;

    String lookUp(int key) {
        if (hashBrowns.containsKey(key))
            return hashBrowns.get(key);
        else
            return "";
    }

    BookCode() { stash("codebreaker-words.txt");}

    BookCode(String fileName) { stash(fileName);}

    private void stash(String fileName) {
        try {
            sc = new Scanner(new File(fileName));
            String value;
            int key;
            while (sc.hasNextLine()) {
                value = sc.next();
                key = sc.nextInt();
                hashBrowns.put(key, value);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
