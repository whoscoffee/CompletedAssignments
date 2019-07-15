
import java.util.Scanner;
import java.util.stream.IntStream;

public class Assignment06 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // variables
        int count = 0;
        int sum = 0;
        // creating Arrays
        String[] deck = { "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen",
                "king", "ace" };
        String[] abreavs = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k", "a", "11" };
        int[] numbers = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
        int[] counter = new int[13];
        // using while statement for an optional amount of inputs
        while (sc.hasNext()) {
            String one = sc.next();
            // goes thro asking if input equals array values
            for (int i = 0; i < 13; i++) {
                // to lowercase
                one = one.substring(0).toLowerCase();
                if (one.equals(deck[i]) || one.equals(abreavs[i])) {
                    counter[count] = numbers[i];
                    count++;
                }
                // gets sum of all the usable input
                sum = IntStream.of(counter).sum();
                if (counter[i] == 11 && sum > 21)
                    counter[i] = 1;
            }
        }
        if (sum > 21)
            System.out.println("Bust: " + sum);

        if (sum == 21 && count == 2) {
            System.out.println("Blackjack!");
        } else if (sum <= 21)
            System.out.println(sum);
    }
}
