import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
//made by jake
class Example {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        // these next 5 ints basically make up the perceptron
        // a = input1, b = input2, diff == difference between output and expected output
        int a = 0, b = 0, diff = 0;
        // the weights for the corresponding inputs
        // in other words, these are tubes
        // 2 tubes, of which sizes can be different
        // these tubes will soon join , so the size
        // of these tubes effect the output.
        int weightA = 10, weightB = 10;
        // And this for loop is where the weight values above get trained
        for (int i = 0; i < 1000; i++) {
            // Random input test values
            a = random.nextInt(10);
            b = random.nextInt(10);
            // difference between output and expected output
            diff = (weightA * a + weightB * b) - (2 * a + 2 * b);
            // applying difference
            weightA -= 0.01 * diff * a;
            weightB -= 0.01 * diff * b;
        }
        // YOUR USER INPUT!
        a = sc.nextInt();
        b = sc.nextInt();
        // Answer
        int ans = weightA * a + weightB * b;
        System.out.printf("(%d * %d) + (%d * %d) = %d%n%n", weightA, a, weightB, b, ans);

        System.out.printf("its supposed to be (2 * input1) + (2 * input2) = Ans)%n%n"
                + "Altho because its a basic Ai whoms main goal is to learn%n"
                + "it doesnt always answer properly. u see i cant tell it the%n"
                + "equation, but i cant tell it if its right or wrong or in this%n case the difference between its"
                + " answer and the expected answer%n%n" + "that being said, it has 2 controls:%n"
                + "one is control over (weightA) number%n"
                + "two is control over (weightB) number%n%n Thanks for stoping by:]");
    }
}
