
/**
 * this is the Y2K38 Countdown timer for cs12jf18
 *
 * By Jacob Burgess, jacobdanielburgess@gmail.com
 */
public class Assignment01 {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();// Current time in milliseconds
        long f = (long) Math.pow(2, 31) - time / 1000;// Time left unity y2k38 in seconds
        long[] t = { // Array because i think it looks nicer
                (long) Math.pow(2, 31) - time / 1000, // Seconds left
                f % 60, // remanding seconds left
                f / 60 % 60, // remanding minutes left
                f / 3600 % 24, // remanding hours left
                f / 86400, // days left
        };// hours, mins, days, years left
        System.out.printf("%d days%n%d hours%n%d minutes%n%d seconds%n", t[4], t[3], t[2], t[1]);// ^^^Printing
                                                                                                 // Variables and Line
                                                                                                 // Drops or however its
                                                                                                 // said
    }
}
