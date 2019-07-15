import java.io.File;
import java.util.Scanner;

/**
 * Assignment 08 for Jeff By Jacob Daniel Burgess
 **/
public class Assignment08 {
    // main String creates elementArr obj array with a contructor class and a
    // Scanner

    public static void main(String[] args) throws Exception {
        // creates yhr obj arr
        Assignment08C[] elementArr = new Assignment08C[119];
        // inputs values of periodic table text to obj arr
        reader(elementArr);
        // reads input, outputs molar mass
        action(elementArr, args);
    }

    /**
     * Reads the periodic table text doc
     **/
    public static void reader(Assignment08C[] elements) throws Exception {
        // creates new scanner input being elements the file.
        Scanner data = new Scanner(new File("/srv/datasets/elements"));
        // while theres still elements
        while (data.hasNext()) {
            // values
            int atomicNumber = data.nextInt();
            String abrv = data.next();
            String name = data.next();
            double atomicMass = data.nextDouble();
            int period = data.nextInt();
            int group = data.nextInt();
            // creating the object per indevidual
            elements[atomicNumber] = new Assignment08C(name, abrv, atomicNumber, atomicMass, group, period);
        }
        // i dont know what this is for lol
        data.close();
    }

    /**
     * This is what takes said input and turn it into an anwer i guess
     **/
    public static void action(Assignment08C[] element, String[] str) {
        // values
        double sum = 0d;
        int power = 1;
        // This is for the commands, does one loop every string(args) seperated by
        // whitespace
        for (int j = 0; j < str.length; j++) {
            // i did it this way
            String[] piece = { "1", "1" };
            // with this because jeff
            if (str[j].contains("_"))
                // dis seperates O_2 to an arr called piece to 2 values, where piece[0] = 2 and
                // piece[1]= 2
                piece = str[j].split("_");
            else
                piece[0] = str[j];
            // For going thro all 119 elements of the periodic table
            for (int i = 1; i < 119; i++) {
                power = Integer.parseInt(piece[1]);
                // IF INPUT = ABREVIATIONS & IF POWER > 1
                if (piece[0].compareTo(element[i].abrv) == 0 && power > 1)
                    sum += element[i].atomicMass * power;
                // IF INPUT = ABREVIATIONS & THERE ISNT A POWER
                if (piece[0].compareTo(element[i].abrv) == 0 && power == 1)
                    sum += element[i].atomicMass;
            }
        }
        System.out.printf("%.6f%n", sum);
    }
}
