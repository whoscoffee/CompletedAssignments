import java.util.Random;
import java.util.Scanner;

class Animals2 {
    public static void main(String[] args) {
        Fixednetwork();
    }

    // below should be a working neural network with fixed weights to insure a
    // correct answer,
    // So yea, its mainly to use as a reference
    private static void Fixednetwork() {
        double[] input = getInput();// 4 input neurons
        double[] weights = new double[16];
        // Theres no hidden layer
        weights[0] = 1;// Connects input[0] to output[0]
        weights[1] = 0;// Connects input[1] to output[0]
        weights[2] = 0;// Connects input[2] to output[0]
        weights[3] = 0;// Connects input[3] to output[0]

        weights[4] = 0;// Connects input[0] to output[1]
        weights[5] = 1;// Connects input[1] to output[1]
        weights[6] = 0;// Connects input[2] to output[1]
        weights[7] = 0;// Connects input[3] to output[1]

        weights[8] = 0;// Connects input[0] to output[2]
        weights[9] = 0;// Connects input[1] to output[2]
        weights[10] = 1;// Connects input[2] to output[2]
        weights[11] = 0;// Connects input[3] to output[2]

        weights[12] = 0;// Connects input[0] to output[3]
        weights[13] = 0;// Connects input[1] to output[3]
        weights[14] = 0;// Connects input[2] to output[3]
        weights[15] = 1;// Connects input[3] to output[3]

        System.out.println(getAnimal(input, weights));
    }

    // Returns animal name using an input[] length of 4, and weights[] length of 12
    private static String getAnimal(double[] input, double[] weights) {
        double[] arr = new double[4];
        double d = 0;
        int sum = 0, count = 0;
        // Assigns arr with the probabilities
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, count++)
                sum += input[j] * weights[count];
            arr[i] = sum;
            sum = 0;
        }
        count = 0;
        // Finds most Probable of arr(or the sum of inputs * weights for each)
        for (int i = 0; i < 4; i++) {
            if (arr[i] > d) {
                d = arr[i];
                count = i;
            }
        }
        // String to return, Returns String name
        String str = "error";
        if (count == 0)
            str = "its a Mountain Lion";
        if (count == 1)
            str = "its a Dogge";
        if (count == 2)
            str = "its a Cat";
        if (count == 3)
            str = "its a Mouse";

        return str;
    }

    // returns user input, using template
    private static double[] getInput() {

        System.out.println("does it chase dogs?");
        Scanner sc = new Scanner(System.in);
        char[] arr = new char[4];
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
        if (c == 'f' || c == 't')
            arr[3] = c;
        else
            inputError();
        sc.close();
        double[] result = new double[4];
        for (int i = 0; i < 4; i++) {
            if (arr[i] == 't')
                result[i] = 1;
            else
                result[i] = 0;
        }
        return result;
    }

    // Prints error and ends program
    private static void inputError() {
        System.out.println("-----------------------");
        System.out.println("input error(enter a t or f)");
        System.out.println("-----------------------");
        System.exit(0);
    }
}
