
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * DnaSequence By Jacob D Burgess For Jeffrey Bergamini
 **/
public class DnaSequence {
    // field
    private char[] dna;

    /**
     * Constructs a new DNA sequence from the contents of a char[].
     **/
    public DnaSequence(char[] dna) {
        this.dna = new String(dna).replaceAll("[^ATCG]", "").toCharArray();
    }

    /**
     * Constructs a new DNA sequence using text in the given file.
     **/
    public DnaSequence(File file) throws Exception {
        Scanner sc = new Scanner(file);
        StringBuffer sb = new StringBuffer("");
        while (sc.hasNextLine()) {
            sb.append(new String(sc.nextLine().replaceAll("[^ATCG]", "")));
        }
        this.dna = new String(sb).toCharArray();
        sc.close();
    }

    /**
     * Constructs a new DNA sequence from the contents of a String.
     **/
    public DnaSequence(String dna) {
        this.dna = dna.replaceAll("[^ATCG]", "").toCharArray();
    }

    /**
     * Returns the nucleotide base char value at the specified index.
     **/
    public char baseAt(int index) {
        return this.dna[index];
    }

    /**
     * Mutator method that flips one base of this sequence to its complement.
     **/
    public void complement(int index) {
        switch (this.dna[index]) {
        case 'A':
            this.dna[index] = 'T';
            break;
        case 'C':
            this.dna[index] = 'G';
            break;
        case 'G':
            this.dna[index] = 'C';
            break;
        case 'T':
            this.dna[index] = 'A';
            break;
        default:
            break;
        }
    }

    /**
     * Compares this sequence to another.
     **/
    public boolean equals(DnaSequence that) {
        return Arrays.equals(this.dna, that.dna);
    }

    /**
     * Calculates and returns the GC-content of this DNA sequence.
     **/

    public double gcContent() {
        int content = 0;
        int count = 0;
        for (int i = 0; i < this.dna.length; i++) {
            if (this.dna[i] == 'C' || this.dna[i] == 'G')
                content++;
            count++;
        }
        return (double) content / count;
    }

    /**
     * Calculates and returns the Hamming distance between this DNA sequence and
     * another.
     **/
    public int hammingDistance(DnaSequence that) {
        int count = 0;
        for (int i = 0; i < this.dna.length || i < that.dna.length; i++) {
            if (this.dna.length != that.dna.length) {
                count = -1;
                break;
            }
            if (this.dna[i] != that.dna[i])
                count++;
        }
        return count;
    }

    /**
     * Returns the length of this DNA sequence.
     **/
    public int length() {
        return this.dna.length;
    }

    /**
     * Calculates and returns where two DNA sequences of equal lengths differ.
     **/
    public boolean[] mutationPoints(DnaSequence that) {
        boolean[] b = new boolean[this.dna.length];
        if (this.dna.length == that.dna.length) {
            for (int i = 0; i < this.dna.length; i++) {
                if (this.dna[i] == that.dna[i])
                    b[i] = false;
                else
                    b[i] = true;
            }
        } else
            return null;
        return b;
    }

    /**
     * Calculates and returns the number of times each type of nucleotide occurs in
     * this DNA sequence.
     **/
    public int[] nucleotideCounts() {
        int[] count = new int[4];
        char[] c = "ACGT".toCharArray();
        for (int i = 0; i < this.dna.length; i++) {
            if (this.dna[i] == c[0])
                count[0]++;
            if (this.dna[i] == c[1])
                count[1]++;
            if (this.dna[i] == c[2])
                count[2]++;
            if (this.dna[i] == c[3])
                count[3]++;
        }
        return count;
    }

    /**
     * Calculates and returns the reverse complement of this DNA sequence as a new
     * DnaSequence.
     **/
    public DnaSequence reverseComplement() {
        char[] car = new char[this.dna.length];
        int count = this.dna.length - 1;
        for (int j = 0; j < this.dna.length; j++) {
            car[j] = this.dna[count];
            count--;
            switch (car[j]) {
            case 'A':
                car[j] = 'T';
                break;
            case 'C':
                car[j] = 'G';
                break;
            case 'G':
                car[j] = 'C';
                break;
            case 'T':
                car[j] = 'A';
                break;
            default:
                break;
            }
        }
        return new DnaSequence(car);
    }

    /**
     * Returns a string representation of this sequence (for example,
     * "ATCCGTGGACT").
     **/
    public String toString() {
        return new String(dna);
    }
}
