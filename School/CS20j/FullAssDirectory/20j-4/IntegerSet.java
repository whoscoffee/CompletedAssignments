/**
* Jacob Daniel Burgess
* jburgess
* jacobdanielburgess
* IntegerSet.java
* Assignment 4(Integer Sets)
* Status: Working/tested
**/
public class IntegerSet {
    private static final int MAXSETVALUE = 1000;
    private static final int MINSETVALUE = 0;
    private int[] get;

    //contructs with given array and or values
    public IntegerSet(int... i) {
        get = i;
    }
    
    //returns max set value
    public int getMaxSetValue() {
        return MAXSETVALUE;
    }

    //returns true if contains element
    public boolean hasElement(int element) {
        if (get != null)
            for (int j : get)
                if (element == j)
                    return true;
        return false;
    }

    // inserts element if within MIN and MAX SETVALUES and this set doesnt already
    // contain element
    public void insertElement(int element) {
        // if within min and max and doesnt haveElement then add the int
        if (element < MAXSETVALUE && element > MINSETVALUE && hasElement(element) != true && get != null) {
            // replacement array
            int[] arr = new int[get.length + 1];
            // copying values up to last
            for (int j = 0; j < get.length; j++)
                arr[j] = get[j];
            // inserts element
            arr[get.length] = element;
            get = arr;
            // avoiding errors
        } else if (get == null) {
            get = new int[1];
            get[0] = element;
        }

    }

    // deletes given element from set
    public void deleteElement(int element) {
        // duplicates array
        int[] getdouble = get;
        // clears this
        get = new int[0];
        // reinserts everything but given element
        for (int i : getdouble)
            if (i != element)
                insertElement(i);
    }

    //returns true if set1 is equal to set2
    public boolean equals(IntegerSet set1, IntegerSet set2) {
        if (set1.get.length == set2.get.length) {
            if (set1.toString() == set2.toString())
                return true;
        }
        return false;
    }

    //returns string of array like so : { 1, 2, 3, 4}
    public String toString() {
	//sorts before returning because its proper
        sort();
        String str = "{";

        for (int i = 0; i < get.length; i++) {
            if (i != get.length - 1)
                str += " " + get[i] + ",";
            else
                str += " " + get[i] + "}";
        }
        if (get.length == 0) {
            return null;
        }
        return str;

    }

    // sorts this array
    public void sort() {
        // dupe
        int[] arr = get;
        int count = 0, index = 0;
        get = new int[0];
        int smallest = MAXSETVALUE;
        // finds smallest value and its index
        // inserts that into this array
        // and deletes smallest from dupe(arr)
        // one by one until properly sorted
        for (int i = 0; i < arr.length; i++) {
            for (int j : arr) {
                if (j < smallest) {
                    smallest = j;
                    index = count;
                }
                count++;
            }

            if (smallest < MAXSETVALUE)
                insertElement(smallest);
            arr[index] = MAXSETVALUE + 1;
            smallest = MAXSETVALUE;
            count = 0;
        }
    }

    // changes this set to a set of shared values between set1 and set2
    public void intersectionOf(IntegerSet set1, IntegerSet set2) {
        get = new int[0];
        for (int i : set1.get)
            if (set2.hasElement(i))
                insertElement(i);
    }

    // changes this set to set1 + set2
    public void unionOf(IntegerSet set1, IntegerSet set2) {
        get = new int[0];
        for (int i : set1.get)
            insertElement(i);
        for (int i : set2.get)
            insertElement(i);
    }
}
