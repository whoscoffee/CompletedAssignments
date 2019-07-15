import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

class Animals {
    public static void main(String args[]) {
        System.out.println("do we drink its milk?");
        String[] in = new String[3];
        int[] values = new int[3];
        int[] weights = new int[3];
        String output = "null";
        Scanner sc = new Scanner(System.in);
        in[0] = sc.next();
        System.out.println("does it gobble?");
        in[1] = sc.next();
        System.out.println("does it bark?");
        in[2] = sc.next();
        for (int i = 0; i < in.length; i++) {
            if (in[i].length() == 1) {
                char s = in[i].charAt(0);
                if (s == 't')
                    values[i] = 1;
                else
                    values[i] = 0;
            } else {
                System.out.println("yous fucked shit up");
            }
        }
        weights[0] = 10;
        weights[1] = 20;
        weights[2] = 30;
        int j = (weights[0] * values[0]) + (weights[1] * values[1]) + (weights[2] * values[2]);
        if (j == 10)
            output = "its a cow";
        if (j == 20)
            output = "its a turkey or chicken";
        if (j == 30)
            output = "its a dogge";
        if (j == 0)
            output = "its a zero:(";
        System.out.println(output);
    }
}
