
import java.util.Scanner;

/**
*Input Machine
*By
*Jacob Bugress
*for and taught by
*Jeff Bergamini
**/

public class Assignment05 {

  public static void main(String[] args) {
    //initializing all deez values
    Scanner sc = new Scanner(System.in);
    int count = 0;
    double vals = 0;
    int max = 0;
    int min = Integer.MAX_VALUE;
    String date;
    String time;
    int val = 0;
    String maxDate = "na";
    String maxTime = "na";
    String minDate = "na";
    String minTime = "na";
    // Statment that reads input an undetermined amount of times
    while (sc.hasNext()) {
      //counting
      ++count;
      //getting input
      date = sc.next();
      time = sc.next();
      val = sc.nextInt();
      //the sum
      vals += val;
      //gets biggest values
      if (val > max) {
        max = val;
        maxDate = date;
        maxTime = time;
      }
      //gets smallest values
      if (val < min) {
        min = val;
        minDate = date;
        minTime = time;
      }
    }
    //calculating average
    double avg = vals / count;
    //Output...
    System.out.printf("Count: %d%n", count);
    System.out.printf("Minimum: %d @ %s %s%n", min, minDate, minTime);
    System.out.printf("Maximum: %d @ %s %s%n", max, maxDate, maxTime);
    System.out.printf("Average: %.2f%n", avg);
  }
}
