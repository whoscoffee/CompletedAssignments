
//so it can get input from user.
import java.util.Scanner;

/**
 * Ye Olde Navada Driver License Teacher: jeff Bergamini for CS 12J By Jacob
 * Burgess
 */
// so it can get input from user.
public class Assignment03 {
    public static void main(String[] args) {
        // Creating the scanner object.
        Scanner sc = new Scanner(System.in);

        // Asking for input from user (First name).
        System.out.println("Enter your first name.");
        // Using Scanner object sc and next() method to get a string input.
        String fn = sc.next();
        // Asking for input from user (Middle name).
        System.out.println("Enter your middle name.");
        // using next() method to get a string input.
        String mn = sc.next();
        // Asking for input from user (Last name).
        System.out.println("Enter your last name.");
        // using next() method to get a string input.
        String ln = sc.next();
        // Asking for input from user (Navada's dl number(before 1998)).
        System.out.println("And finally your drivers lisence number(Before 98(in navada)).");
        // using nextLong() method to get a Long var input.
        long lisenceNum = sc.nextLong();
        String lsn = String.valueOf(lisenceNum);
        String ans = lsn.substring(lsn.length() - 2, lsn.length());
        // Making sure all the words are Proberly cased.
        fn = fn.substring(0, 1).toUpperCase() + fn.substring(1).toLowerCase();
        mn = mn.substring(0, 1).toUpperCase() + mn.substring(1).toLowerCase();
        ln = ln.substring(0, 1).toUpperCase() + ln.substring(1).toLowerCase();
        // calculating lase two digits(last two digits of birth year) and ssn.

        long ssn = (lisenceNum / 100 - 2600000001L) / 2;
        // printing the name out properly.
        System.out.printf("%s, %s %s%n", ln, fn, mn);
        // Printing out ssn with dashes.
        System.out.printf("%03d-%02d-%04d%n", ssn / 1000000, ssn / 10000 % 100, ssn % 10000);
        // printing last two digits(year of birth)and adding a 19.
        System.out.println("19" + ans);
        sc.close();
    }
}