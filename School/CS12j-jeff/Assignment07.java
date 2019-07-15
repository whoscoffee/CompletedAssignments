/*
* Array Statistics 
* By 
* Jacob Burgess
* For Jeffrey Bergamini
*/
public class Assignment07 {
    // The Mean Method(average of all numbers)
    public static double mean(int[] data) {
        int ans = 0;

        for (int i = 0; i < data.length; i++) {
            ans += data[i];
        }
        ans = ans / data.length;
        return ans;
    }

    // The Median Method(or middle number(if there are 2 then the mean of those
    // two))
    public static double median(int[] data) {
        double ans = 0d;

        if (data.length / (data.length / 2) == 2)
            ans = ((double) data[data.length / 2] + (double) data[data.length / 2 - 1]) / 2;
        else
            ans = (double) data[data.length / 2];
        return ans;
    }

    // The Mode Method(or most common number)
    public static int mode(int[] data) {
        int count = 0;
        int num = 0;
        int big = 0;
        int ans = 0;

        for (int i = 0; i < data.length; i++) {
            for (int a = 0; a < data.length; a++) {
                if (i != a && data[i] == data[a])
                    count++;
                if (count > big) {
                    big = count;
                    ans = data[i];
                }
                count = 0;
            }
        }
        return ans;
    }

    /*
     * The Population Standard Deviation Method or the square root of the mean of
     * the squared differences between each value and the mean value
     */
    public static double pstddev(int[] data) {
        double mean = mean(data);
        double ans = 0d;
        double[] arr = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            arr[i] = (double) data[i] - mean;
            arr[i] = arr[i] * arr[i];
        }
        for (int i = 0; i < data.length; i++) {
            ans += arr[i];
        }
        ans = ans / data.length;
        return Math.sqrt(ans);
    }
}