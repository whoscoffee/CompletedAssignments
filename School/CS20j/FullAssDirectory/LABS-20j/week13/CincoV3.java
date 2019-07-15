import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

class CincoV3 {
    Dictionary dictionary;
    ArrayList<Character> alphabet = new ArrayList<>();
    int numguesses;
    boolean cheated;
    String secret, cheatcode = "xxxxx";
    ArrayList<Character> car = new ArrayList<>();
    ArrayList<String> guessedWords = new ArrayList<>();
    ArrayList<ArrayList<Character>> knownWords = new ArrayList<>();
    ArrayList<Integer[]> knownValues = new ArrayList<>();
    ArrayList<Character> knownLetters = new ArrayList<>();

    public CincoV3() {
    }

    public CincoV3(String filename) {
        dictionary = new Dictionary(filename);
        secret = dictionary.getLegalSecretWord();
        for (char c : secret.toCharArray())
            car.add(c);
        play();
    }

    public void play() {
        char c = 'a';
        for (int i = 0; i < 26; i++)
            alphabet.add(c++);
        Scanner sc = new Scanner(System.in);
        System.out.println("CincoV3!");
        System.out.println("By Jacob Burgess");
        System.out.println("For Steve J. Hodges");
        System.out.println("I'm thinking of a five letter word...");
        boolean istrue = true;
        String guess = "";

        finishline: while (istrue) {
            System.out.print("Your Guess? ");
            boolean hasGuessed = false;
            outer: while (hasGuessed == false) {
                guess = dictionary.getLegalSecretWord();
                for (char d : guess.toCharArray()) {
                    if (alphabet.contains(d) == false)
                        continue outer;
                    if (alphabet.contains(d) == false && knownLetters.size() < new Random().nextInt(4))
                        continue outer;
                }
                if (guessedWords.contains(guess) == false)
                    hasGuessed = true;
            }
            guessedWords.add(guess);
            ArrayList<Character> ahh = new ArrayList<>();
            for (char e : guess.toCharArray())
                ahh.add(e);
            knownWords.add(ahh);
            Integer[] ayy = { countMatchingLetters(guess), countInPlaceLetters(guess) };
            knownValues.add(ayy);
            System.out.println(guess);
            if (guess.equals(cheatcode)) {
                System.out.println("Secret Word is: " + secret);
                cheated = true;
            } else if (dictionary.validWord(guess)) {

                if (guess.equals(secret)) {
                    istrue = false;
                    break finishline;
                }
                System.out.println("Matching: " + countMatchingLetters(guess));
                System.out.println("In-Place: " + countInPlaceLetters(guess));
            } else
                System.out.println("I don't know that word.");
            numguesses++;
            if (countMatchingLetters(guess) == 0 && countInPlaceLetters(guess) == 0) {
                for (char d : guess.toCharArray())
                    if (alphabet.contains(d)) {
                        alphabet.remove(alphabet.indexOf(d));
                        for (int i = 0; i < knownWords.size(); i++)
                            if (knownWords.get(i).size() > 1)
                                for (int j = 0; j < alphabet.size(); j++) {
                                    for (int k = 0; k < knownWords.get(i).size(); k++)
                                        if (alphabet.contains(knownWords.get(i).get(k)) == false
                                                && knownWords.size() > 1)
                                            knownWords.get(i).remove(alphabet.get(j));
                                }
                            else if (knownWords.get(i).size() == 1) {
                                knownLetters.add(knownWords.get(i).get(0));
                            }
                    }
            }
        }
        if (cheated != true)
            System.out.printf("Correct! You got it in %d guesses.", ++numguesses);
        else {
            System.out.printf("Correct! You got it in %d guesses,%nbut only by cheating.%n", ++numguesses);
        }

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
        new CincoV3(args[0]);
    }
}
