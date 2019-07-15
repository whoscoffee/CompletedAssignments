
/**
 * Name: jake Burgess Class: cs12j Details: this program is to tell the people
 * how much light has been traveling in 4 different units of measurments, You
 * know if you ever need to like pull up facts.
 */
public class Assignment02 {
    public static void main(String[] args) {
        double millis = System.currentTimeMillis(),
                // Calculating millis so i we can calculate secs.
                secs = millis / 1000,
                // Calculating seconds for reasons.
                km = secs * 299792458,
                // For calculations.
                km1 = secs * 299792.458,
                // For displaying kilometers correctly.
                au = km / 149597870700.0,
                // Declaring stuff for astronomical units.
                ly = km / 9460730472580800.0,
                // Declaring stuff for light years(in units).
                pc = km / 3.085677581e16;
        // Declaring stuff for parsecs.
        String sentence = "Since the Unix epoch, light has traveled...";
        // Because its neat, plus i cant go past 100 characters in a line.
        System.out.printf("%s%n%.3f km%n%.3f au%n%.3f ly%n%.3f pc%n", sentence, km1, au, ly, pc);
        // Finalized output.
    }
}
