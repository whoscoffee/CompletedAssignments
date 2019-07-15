import java.util.ArrayList;
import java.util.Scanner;

class Decoder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNextLine()) {
            for (String str : sc.nextLine().split(" "))
                arr.add(str);
        }
        String str = arr.toString();
        System.out.println(str);
        System.out.println("here");
        char[] topTens = topTen(arr);
        for (char c : topTens)
            System.out.println(c);
        sc.close();
    }

    public static char[] topTen(ArrayList<String> arr) {
        ArrayList<Character> listoChars = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        //Counts the characters
        for (char c : arr.toString().toCharArray()){
            //if LTC already contains Letter
            if (listoChars.contains(c)) {
                // then Count
                count.set(listoChars.indexOf(c), count.get(listoChars.indexOf(c)) + 1);
            } else {//else then add letter and count
                listoChars.add(c);
                count.add(1);
            }

        }
        //Final List of the up to top ten most common characters
        ArrayList<Character> cars = new ArrayList<>();
        // up to 10 characters but only if has aviable characters
        for (int j = 0; j < 10 && j < count.size(); j++) {
                // comparor
                int biggest = 0;
                int index = 0;
                // Finds Biggest
                for (int i = 0; i < count.size(); i++) {
                    // gets count
                    int l = count.get(i);
                    // assigns biggest and indexOf
                    if (j > biggest && cars.contains(l) != true) {
                        biggest = l;
                        index = i;
                    }
                    
                }
                cars.add(listoChars.get(index));
        }
        return cars.toString().toCharArray();
    }
}