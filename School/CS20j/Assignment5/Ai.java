
class Ai {
    Dictionary dictionary;
    ArrayList<Character> alphabet = new ArrayList<>();
    ArrayList<String> guessedWords = new ArrayList<>();
    ArrayList<ArrayList<Character>> knownWords = new ArrayList<>();
    ArrayList<Integer[]> knownValues = new ArrayList<>();
    ArrayList<Character> knownLetters = new ArrayList<>();
    String guess;

    public Ai(Dictionary dictionary) {
        this.dictionary = dictionary;
        char c = 'a';
        for (int i = 0; i < 26; i++)
            alphabet.add(c++);
    }

    public void Recalc() {
        boolean hasGuessed = false;
        // while ai hasnt made guess
        outer: while (hasGuessed == false) {
            // makes random guess
            guess = dictionary.getLegalSecretWord();
            // makes sure alphabet has all the letters in guess else guess again
            for (char d : guess.toCharArray()) {
                if (alphabet.contains(d) == false)
                    continue outer;
                if (alphabet.contains(d) == false && knownLetters.size() < new Random().nextInt(4))
                    continue outer;
            }
            // to make sure it doesnt guess a word twice
            if (guessedWords.contains(guess) == false)
                hasGuessed = true;

        }
        guessedWords.add(guess);
        // sets known word
        ArrayList<Character> ahh = new ArrayList<>();
        for (char e : guess.toCharArray())
            ahh.add(e);
        knownWords.add(ahh);
        // adds known values
        Integer[] ayy = { countMatchingLetters(guess), countInPlaceLetters(guess) };
    }

    public void resortAlphabet() {
        if (countMatchingLetters(guess) == 0 && countInPlaceLetters(guess) == 0) {
            for (char d : guess.toCharArray())
                if (alphabet.contains(d)) {
                    alphabet.remove(alphabet.indexOf(d));
                    for (int i = 0; i < knownWords.size(); i++)
                        if (knownWords.get(i).size() > 1)
                            for (int j = 0; j < alphabet.size(); j++) {
                                for (int k = 0; k < knownWords.get(i).size(); k++)
                                    if (alphabet.contains(knownWords.get(i).get(k)) == false && knownWords.size() > 1)
                                        knownWords.get(i).remove(alphabet.get(j));
                            }
                        else if (knownWords.get(i).size() == 1) {
                            knownLetters.add(knownWords.get(i).get(0));
                        }
                }
        }
    }

    public String nextGuess() {
        Recalc();
        return guess;
    }

    public void feedback(Integer[] ayy) {
        knownValues.add(ayy);
    }
}