import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Assignment13 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new URL("http://jeff.cis.cabrillo.edu/datasets/homoglyphs_curated.txt").openStream()));
        HashMap<Integer, Character> homoglyphs = new HashMap<>();
        char car = 'A';
        String str = "";
        for (int i = 0; i < 2; i++) {
            if (i == 1)
                car = 'a';
            for (int j = 0; j < 26; j++) {
                str = reader.readLine();
                for (int code : str.codePoints().toArray())
                    homoglyphs.put(code, car);
                car++;
            }
        }
        reader = new BufferedReader(new InputStreamReader(System.in));
        for (String line; (line = reader.readLine()) != null;) {
            for (int codee : line.codePoints().toArray())
                if (homoglyphs.containsKey(codee))
                    System.out.print(String.valueOf(Character.toChars(homoglyphs.get(codee))));
                else
                    System.out.print(String.valueOf(Character.toChars(codee)));
            System.out.println();
        }
        reader.close();
    }
}