import java.util.Scanner;
import java.lang.Math;
/**
 * A loan Project(Assignment 01) || CS 20j
 * By Jacob D. Burgess
 * For Steve J. Hodges
**/
class Loan {

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);

	System.out.println("Jacob D Burgess @ jburgess @ jacobdanielburgess@gmail.com");
	System.out.println("Due Date : Monday the 3rd at 7PM | Name of program : Loan | Assignment #1");
	System.out.println("============================================================================");
	System.out.println("Whats the Amount Borrowed?");
	//input
	double principle = sc.nextDouble();
	System.out.println("and Your Annual Rate? (as a decimal like so : 15% equals .15)");
	double interestRate = sc.nextDouble();
	System.out.println("Then the Amount of Payments per the Year? (as a number)");
	interestRate /= sc.nextInt();//this is the interest rate per the payment 
	System.out.println("And Finally the Price of the individual Payments?");
	double bill = sc.nextDouble();
	
	double amountPayments = calcPayments(interestRate, principle, bill);// Calling on the calcPayment function
	// and Printing the final result
	System.out.printf("%.1f payments to be made and a total of $%.2f to be repaid%n", amountPayments, amountPayments * bill);
    }

    // The function for the equation, Returns amount of pauments rounded to nearest whole number
    public static long calcPayments(double interestRate, double principle, double bill) {
	double amountPayments = Math.round(
                                         Math.log( 1 - interestRate * principle / bill )
                                         /
                                         Math.log(1 + interestRate)
                                         );
	return (long) Math.abs(amountPayments);
    }
}
