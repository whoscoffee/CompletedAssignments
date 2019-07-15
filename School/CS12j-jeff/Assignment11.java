import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment11 {

    public static void main(String[] args) throws Exception {

        ArrayList<Double> longList = new ArrayList<>();
        ArrayList<Double> latList = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<String> addList = new ArrayList<>();
        double dist = Double.parseDouble(args[0]);
        double shortest = 0d;
        int max = 0;
        int lastCount = 0;
        int count = 0;
        int p = 0;
        int u = 0;
        Scanner sc = new Scanner(new URL("http://jeff.cis.cabrillo.edu/datasets/santa-cruz-addresses.txt").openStream())
                .useDelimiter("\\t|\\n");
        while (sc.hasNext()) {
            longList.add(sc.nextDouble());
            latList.add(sc.nextDouble());
            addList.add(sc.next());
        }
        args[0] = "";
        String[] pieces = args[1].split(" ");
        for (int c = 0; c < pieces.length; c++) {
            char[] car = pieces[c].replaceAll("[^A-Za-z0-9 #]", "").toCharArray();
            for (int lk = 0; lk < car.length; lk++)
                car[lk] = Character.toUpperCase(car[lk]);
            pieces[c] = new String(car);
        }
        String str = String.join("", pieces);
        char[] arr = str.toCharArray();
        for (int j = 0; j < addList.size(); j++) {
            String str1 = addList.get(j);
            char[] ayy = str1.replace(" ", "").toCharArray();
            for (int k = 0; k < arr.length; k++) {

                if (ayy[k] == arr[k])
                    count++;
                else
                    k = arr.length;
            }

            if (count > lastCount) {
                lastCount = count;
                max = j;
            }
            if (count == arr.length)
                j = addList.size();
            count = 0;
        }

        double thisLat = latList.get(max) * Math.PI / 180;
        double thisLong = longList.get(max) * Math.PI / 180;
        double thatLat = 0f;
        double thatLong = 0f;
        for (int i = 0; i < addList.size(); i++) {
            thatLat = latList.get(i) * Math.PI / 180;
            thatLong = longList.get(i) * Math.PI / 180;
            double km = 2
                    * Math.asin(Math.sqrt(Math.pow(Math.sin((thisLat - thatLat) / 2), 2)
                            + Math.cos(thisLat) * Math.cos(thatLat) * Math.pow(Math.sin((thisLong - thatLong) / 2), 2)))
                    * ((180 * 60) / Math.PI) * 1852 / 1000;

            if (km <= dist) {
                distances.add(km);
                indexes.add(i);
            }
        }
        int n = distances.size();
        for (int o = 0; o < n; o++) {
            for (int q = 0; q < distances.size(); q++) {
                if (distances.get(q) < shortest || q == 0) {
                    shortest = distances.get(q);
                    p = indexes.get(q);
                    u = q;
                }
            }
            System.out.printf("%s    %.02f%n", addList.get(p), shortest);
            distances.remove(u);
            indexes.remove(u);
        }
        sc.close();
    }
}
