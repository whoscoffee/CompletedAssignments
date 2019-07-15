import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

//this is an ai, for a word guessing game
// by jake
class Five {

    Dictionary dictionary;
    // exactly what it sounds like
    // misc
    int numguesses;
    boolean cheated;
    // secret word being guessed for
    String secret, cheatcode = "xxxxx";
    // secret word in a char array
    ArrayList<Character> car = new ArrayList<>();

    // constructor
    public Five() {
        start("Five-words.txt");
    }

    // constructor 2
    public Five(String filename) {
        start(filename);
    }

    // sends file to Dictionary
    // sets the secret word
    public void start(String filename) {
        dictionary = new Dictionary(filename);
        secret = dictionary.getLegalSecretWord();
        // sets an array of the secret word
        for (char c : secret.toCharArray())
            car.add(c);
        play();
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Five!");
        boolean istrue = true;
        String guess = "";
        finishline: while (istrue) {
            System.out.print("Your Guess? ");

            System.out.println(guess);
            if (guess.equals(cheatcode)) {
                System.out.println("Secret Word is: " + secret);
                cheated = true;
            } else if (dictionary.validWord(guess)) {
                if (guess.equals(secret)) {
                    istrue = false;
                    break finishline;
                }
                // else
                System.out.println("Matching: " + countMatchingLetters(guess));
                System.out.println("In-Place: " + countInPlaceLetters(guess));
            } else
                System.out.println("I don't know that word.");
            numguesses++;
        }
        // when that mess is done, u will be here where u get bitcxhes
        if (cheated != true)
            System.out.printf("Correct! You got it in %d guesses.", ++numguesses);
        else {
            System.out.printf("Correct! You got it in %d guesses,%nbut only by cheating.%n", ++numguesses);
        }

    }

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
        try {
            new Five(args[0]);
        } catch (Exception e) {
            new Five();
        }
    }
}