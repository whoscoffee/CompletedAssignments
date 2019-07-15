import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

class Cinco {
    Dictionary dictionary;
    int numguesses;
    boolean cheated;
    String secret, cheatcode = "xxxxx";
    ArrayList<Character> car = new ArrayList<>();

    public Cinco() {
    }

    public Cinco(String filename) {
        dictionary = new Dictionary(filename);
        secret = dictionary.getLegalSecretWord();
        // car = credo
        for (char c : secret.toCharArray())
            car.add(c);
        play();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cinco!");
        System.out.println("By Jacob Burgess");
        System.out.println("For Steve J. Hodges");
        System.out.println("I'm thinking of a five letter word...");
        boolean istrue = true;
        String guess = "";

        finishline: while (istrue) {
            System.out.print("Your Guess? ");
            guess = sc.next();
            if (guess.equals(cheatcode)){
                System.out.println("Secret Word is: " + secret);
		cheated = true;
	    }
            else if (dictionary.validWord(guess)) {

                if (guess.equals(secret)) {
                    istrue = false;
                    break finishline;
                }
                System.out.println("Matching: " + countMatchingLetters(guess));
                System.out.println("In-Place: " + countInPlaceLetters(guess));
            } else
                System.out.println("I don't know that word.");
            numguesses++;
        }
	if (cheated != true)
        System.out.printf("Correct! You got it in %d guesses.", ++numguesses);
	else
        System.out.printf("Correct! You got it in %d guesses,%nbut only by cheating.%n", ++numguesses);

    } // contains main game loop and console i/o

    // return # of matching letters secret/guess
    private int countMatchingLetters(String guess) {
        int count = 0;
        ArrayList<Character> dont = new ArrayList<>();
        for (char c : guess.toCharArray())
            if (car.contains(c) && dont.contains(c) != true) {
                dont.add(c);
                count++;
            }
        return count;
    }

    // return # of in-place letters secret/guess
    private int countInPlaceLetters(String guess) {
        int count = 0;
        for (int i = 0; i < guess.length(); i++)
            if (secret.charAt(i) == guess.charAt(i))
                count++;
        return count;
    }

    // main program, required, minimal code
    public static void main(String[] args) {
        new Cinco(args[0]);
    }
}
