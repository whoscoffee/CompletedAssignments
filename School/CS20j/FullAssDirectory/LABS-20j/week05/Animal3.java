import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class Animal3 {
    public static void main(String[] args) {
        double input[] = new double[4];
        for (int i = 0; i < 10000; i++) {
            input = getRandomInput();
            prompt(input);
        }
        input = getInput();
        prompt(input);
    }

    private static void prompt(double input[]) {

        double difference[] = new double[4];
        double curve = 0.01;
        double weights[] = new double[16];
        Random rand = new Random();
        for (int i = 0; i < 16; i++)
            weights[i] = rand.nextDouble();
        double output[] = new double[4];
        int count = 0;
        // calculates output probabilities
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++, count++)
                output[i] += input[j] + weights[count];

        double biggest = 0;
        for (int i = 0; i < 4; i++)
            if (output[i] > biggest) {
                biggest = output[i];
                count = i;
            }
        String str = "error";
        if (count == 0)
            str = "its a Mountain Lion";
        if (count == 1)
            str = "its a Dogge";
        if (count == 2)
            str = "its a Cat";
        if (count == 3)
            str = "its a Mouse";
        System.out.println(str);
        // gets difference
        for (int i = 0; i < 4; i++)
            if (output[i] != 0 && input[i] == 0)
                difference[i] = -output[i];
        count = 0;
        // applys difference
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++, count++)
                weights[count] += difference[j] * curve;
    }

    private static double[] getRandomInput() {
        Random rand = new Random();
        double input[] = new double[4];
        Boolean b;
        Boolean isTrue = false;
        for (int i = 0; i < 4; i++) {
            b = rand.nextBoolean();
            if (b == true && isTrue == false) {
                isTrue = true;
                input[i] = 1.0;
            } else
                input[i] = 0.0;
            if (b == false && isTrue == false && i == 3)
                input[i] = 1.0;
        }
        return input;
    }

    private static double[] getInput() {
        Scanner sc = new Scanner(System.in);
        char[] arr = new char[4];
        System.out.println("does it chase dogs?");
        char c = sc.next().toLowerCase().charAt(0);
        if (c == 'f' || c == 't')
            arr[0] = c;
        else
            inputError();
        System.out.println("does it chase cats?");
        c = sc.next().toLowerCase().charAt(0);
        if (c == 'f' || c == 't')
            arr[1] = c;
        else
            inputError();
        System.out.println("does it chase mice?");
        c = sc.next().toLowerCase().charAt(0);
        if (c == 'f' || c == 't')
            arr[2] = c;
        else
            inputError();
        System.out.println("does it squeek?");
        c = sc.next().toLowerCase().charAt(0);
        sc.close();
        if (c == 'f' || c == 't')
            arr[3] = c;
        else
            inputError();

        double[] arrr = new double[4];
        // Activating arrr
        for (int i = 0; i < 4; i++)
            if (arr[i] == 't')
                arrr[i] = 1.0;
            else
                arrr[i] = 0.0f;
        return arrr;
    }

    private static void displayProbabilities(double[] output) {
        for (double d : output)
            System.out.println(d);
    }

    // Prints error and ends program
    private static void inputError() {
        System.out.println("-----------------------");
        System.out.println("input error(enter a t or f)");
        System.out.println("-----------------------");
        System.exit(0);
    }
}
