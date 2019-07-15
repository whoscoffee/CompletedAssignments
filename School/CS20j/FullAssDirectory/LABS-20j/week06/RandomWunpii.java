import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
class RandomWunpii{
    public static void main(String[] args)throws Exception {
	Scanner sc = new Scanner(new File("first-names.txt"));
	ArrayList<String> arr = new ArrayList<>();
	while(sc.hasNext())
	    arr.add(sc.next());
	Random rand = new Random();
	PrintWriter writer = new PrintWriter("newFile.txt", "UTF-8");
	for (int i = 0; i < 10000;i++ ) {
	    writer.printf("%s %.02f %.02f%n", arr.get(rand.nextInt(arr.size())), rand.nextDouble(), rand.nextDouble());
	     
	}
    }
}

