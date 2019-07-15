import java.util.Scanner;

public class Assignment04 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // using said Scanner to Scan Said Input
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();

        // ChineseZodiac Formula
        int chineseZodiac = year % 12;

        // If Input equals April 19th - May 13th.
        if (month == 4 && day >= 19 || month == 5 && day <= 13)
            // Printing words and unique unicoded
            System.out.println("Constellation: Aries \u2648");

        // If Input equals May 14 - June 19.
        if (month == 5 && day >= 14 || month == 6 && day <= 19)
            // Printing words and unique unicoded
            System.out.println("Constellation: Taurus \u2649");

        // If Input equals June 20th - July 20th.
        if (month == 6 && day >= 20 || month == 7 && day <= 20)
            // Printing words and unique unicoded
            System.out.println("Constellation: Gemini \u264A");

        // If Input equals July 21st - August 9th.
        if (month == 7 && day >= 21 || month == 8 && day <= 9)
            // Printing words and unique unicoded
            System.out.println("Constellation: Cancer \u264B");

        // If Input equals August 10th - September 15th.
        if (month == 8 && day >= 10 || month == 9 && day <= 15)
            // Printing words and unique unicoded
            System.out.println("Constellation: Leo \u264C");

        // If Input equals September 16th - October 30th.
        if (month == 9 && day >= 16 || month == 10 && day <= 30)
            // Printing words and unique unicoded
            System.out.println("Constellation: Virgo \u264D");

        // If Input equals October 16th - November 22nd.
        if (month == 10 && day >= 31 || month == 11 && day <= 22)
            // Printing words and unique unicoded
            System.out.println("Constellation: Libra \u264E");

        // If Input equals November 23 - November 29th.
        if (month == 11 && day >= 23 && month == 11 && day <= 29)
            // Printing words and unique unicoded
            System.out.println("Constellation: Scorpius \u264F");

        // If Input equals November 30th - 17 December.
        if (month == 11 && day >= 30 || month == 12 && day <= 17)
            // Printing words and unique unicoded
            System.out.println("Constellation: Ophiucus \u26CE");

        // If Input equals December 18th - January 18.
        if (month == 12 && day >= 18 || month == 1 && day <= 18)
            // Printing words and unique unicoded
            System.out.println("Constellation: Sagittarius \u2650");

        // If Input equals January 19th - February 15.
        if (month == 1 && day >= 19 || month == 2 && day <= 15)
            // Printing words and unique unicoded
            System.out.println("Constellation: Capicorn \u2651");

        // If Input equals February 16th - March 11.
        if (month == 2 && day >= 16 || month == 3 && day <= 11)
            // Printing words and unique unicoded
            System.out.println("Constellation: Aquarius \u2652");

        // If Input equals March 12 - April 18th.
        if (month == 3 && day >= 12 || month == 4 && day <= 18)
            // Printing words and unique unicoded
            System.out.println("Constellation: Pisces \u2653");

        // The Switch Statement, it simplifies multiple if Statments
        switch (chineseZodiac) {
        case 0:
            System.out.println("Zodiac: Monkey \u7334");
            break;
        case 1:
            System.out.println("Zodiac: Rooster \u96DE");
            break;
        case 2:
            System.out.println("Zodiac: Dog \u72D7");
            break;
        case 3:
            System.out.println("Zodiac: Pig \u732A");
            break;
        case 4:
            System.out.println("Zodiac: Rat \u9F20");
            break;
        case 5:
            System.out.println("Zodiac: Ox \u725B");
            break;
        case 6:
            System.out.println("Zodiac: Tiger \u864E");
            break;
        case 7:
            System.out.println("Zodiac: Rabbit \u5154");
            break;
        case 8:
            System.out.println("Zodiac: Dragon \u9F8D");
            break;
        case 9:
            System.out.println("Zodiac: Snake \u86C7");
            break;
        case 10:
            System.out.println("Zodiac: Horse \u99AC");
            break;
        case 11:
            System.out.println("Zodiac: Goat \u7F8a");
            break;
        default:
            System.out.println("I dont know how youo did this");
        }
    }
}