import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
  * AlgorithmTimer by jacob burgess
  * 
  * Current algorithms:
  * Bubble Sort
  * SelectionSort
  * InsertionSort
  *
  * note:(this program need a file filled with doubles)
  **/
class AlgorithmTimer {
    static Double[] bubble(Double[] arr) {
	boolean sorted = false;
	Double temp;
	while (!sorted) {
	    for (int i = 0; i < arr.length;i++) {
		if (i+1 < arr.length)
		    if (arr[i] > arr[i+1]) {
			temp = arr[i];
			arr[i] = arr[i+1];
			arr[i+1] = temp;
			sorted = true;
		    }
	    }
	    if (sorted == true)
		sorted = false;
	    else
		sorted = true;
	}
	return arr;
    }
    static Double[] selection(Double[] arr) {
	Double temp;
	for(int i = 0; i < arr.length - 1; i++)
	    for(int j = i + 1; j < arr.length;j++)
		if(arr[i] > arr[j]) {
		    temp = arr[i];
		    arr[i] = arr[j];
		    arr[j] = temp;
		    continue; 
		}
	return arr;
    }
    static Double[] insertion(Double[] arr) {
	Double temp;
	for (int i = 1; i < arr.length;i++)
	    for (int j = i-1; j > 0; j--)
		if (arr[j] > arr[i]) {
		    temp = arr[i];
		    arr[i] = arr[j];
		    arr[j] = temp;
		}
	return arr;
    }
    /*static double[] merge(double[] arr) {
      
    }
    static double[] quick(double[] arr) {
    
    }*/

    public static void main(String[] args) throws Exception {
	Scanner sc = new Scanner(new File(args[0]));
	ArrayList<Double> ayy = new ArrayList<>();
	
	while(sc.hasNextLine())
	    if (sc.hasNext())
		ayy.add(sc.nextDouble());
        Double[] arr = ayy.toArray(new Double[ayy.size()]);
	//Bubble
	double d = System.currentTimeMillis();
	arr = selection(arr);
	d = System.currentTimeMillis() - d;
	System.out.printf("BubbleSort took: %.06f%n", d);
	//Selection
	d = System.currentTimeMillis();
	arr = selection(arr);
	d = System.currentTimeMillis() - d;
	System.out.printf("SelectionSort took: %.06f%n", d);
	//Insertion
	d = System.currentTimeMillis();
        arr = insertion(arr);
        d = System.currentTimeMillis() - d;
        System.out.printf("InsertionSort took: %.06f%n", d);
    }
}
