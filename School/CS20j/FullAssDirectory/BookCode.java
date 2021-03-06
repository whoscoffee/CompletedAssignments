import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
class BookCode {

    HashMap<Integer, String>hashBrowns = new HashMap<>();

    BookCode(String fileName) throws Exception {
	Scanner sc = new Scanner(new File(fileName));
	String value;
	while(sc.hasNextLine()){
	    value = sc.next();
	    hashBrowns.put(sc.nextInt(), value);
	}
	sc.close();
    } 
    String lookUp(int key) {
        return hashBrowns.get(key);
    } 
}
