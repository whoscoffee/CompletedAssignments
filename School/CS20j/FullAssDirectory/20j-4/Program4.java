import java.util.Random;

class Program4 {
    // main function containing a test routine
    // this is not an exhaustive test routine so
    // please do conduct your own testing in addition!
    // this goes in your Program4.java file:
    public static void main(String args[]) {
        Random rng = new Random();
        rng.setSeed(0);
        IntegerSet is1, is2, is3;
        is1 = new IntegerSet();
        is2 = new IntegerSet(1, 2, 5);
        is3 = new IntegerSet();

        System.out.println("CS20j Integer Sets");
        System.out.println("jacob daniel burgess @Gmail.com");

        is1.insertElement(2);//
        is1.insertElement(4);//
        is1.insertElement(2);
        is1.insertElement(3);
        is1.insertElement(5);//
        is1.insertElement(7);

        is1.deleteElement(3);
        is1.deleteElement(7);
        is1.deleteElement(9);

        for (int i = 0; i < (is3.getMaxSetValue() * 0.10); i++)
            is3.insertElement((int) Math.round(rng.nextDouble() * is3.getMaxSetValue()));

        System.out.print("is3 (random): ");
        System.out.println(is3.toString());

        is3.unionOf(is1, is2);
        System.out.print("is3 (union): ");
        System.out.println(is3.toString());

        is3.intersectionOf(is1, is2);
        System.out.print("is3 (intersection): ");
        System.out.println(is3.toString());

        if (is3.equals(is3))
            System.out.println("1: is3 == is3");
        else
            System.out.println("1: is3 != is3");

        if (is3.equals(is2))
            System.out.println("2: is3 == is2");
        else
            System.out.println("2: is3 != is2");

        if (is1.hasElement(5)) {
            System.out.println("3: is1 has 5");
        } else {
            System.out.println("3: is1 does not have 5");
        }
        if (is1.hasElement(7)) {
            System.out.println("4: is1 has 7");
        } else {
            System.out.println("4: is1 does not have 7");
        }
    }
}
