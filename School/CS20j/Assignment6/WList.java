import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Jacob Burgess
 * jburgess
 * jacobdanielburgess@gmail.com
 * WList.java
 * Assignment 6 (Save the Wumpus)
 **/
public class WList{
    protected class LinkNode{
        protected LinkNode next;
        protected Wumpus data;
        public LinkNode() {
            next = null;
            data = null;
        }
    }
    private LinkNode first;
    private int population;
    // Constructor
    public WList(){
        first = null;
    }
    //inserts wumpus into Linkedlist in a proper manner
    public void wumpusSpotted(Wumpus w){
        boolean b = update(w);
        if (b == false)
            if (population >= 1)
                sort(w);
            else
                insertFront(w);
    }
    //updates duplicates else returns false
    private boolean update(Wumpus w){
        LinkNode temp = first;
        if (w == null || first == null ) return false;
        while (temp != null) {
            if (temp.data.equals(w)){
                double a, b;
                if (w.getX()<0 && temp.data.getX()>0 || w.getX()>0 && temp.data.getX()<0)
                    a = Math.abs(w.getX()) + Math.abs(temp.data.getX());
                else
                    a = Math.abs(Math.abs(w.getX()) - Math.abs(temp.data.getX()));
                if (w.getY()<0 && temp.data.getY()>0 || w.getY()>0 && temp.data.getY()<0)
                    b = Math.abs(w.getY()) + Math.abs(temp.data.getY());
                else
                    b = Math.abs(Math.abs(w.getY()) - Math.abs(temp.data.getY()));
                double pygrean = Math.sqrt((a*a)+(b*b));
                pygrean += temp.data.getDistTraveled();
                temp.data.setX(w.getX());
                temp.data.setY(w.getY());
                temp.data.setDistTraveled(pygrean);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
    //sorts alphabetically given wumpus to insert
    private void sort(Wumpus w){
        LinkNode temp = first;
        LinkNode prev = new LinkNode();
        boolean isFirst = true;
        String name = w.toString().toLowerCase(), tempName;
        int size;
        //each in list
        outerLoop:while(temp != null) {
            tempName = temp.data.toString().toLowerCase();
            size = 0;
            // sets size to smallest index
            if (name.length() > tempName.length())
                size = tempName.length();
            else
                size = name.length();
            //each letter comparison
            for (int i = 0; i < size;i++){
                if (tempName.charAt(i) > name.charAt(i))
                    if (isFirst == true){
                        insertFront(w);
                        break outerLoop;
                    }else {
                        insertAt(prev, w, temp);
                        break outerLoop;
                    }
                //else continue loop if isnt the last node in list
                else if (tempName.charAt(i) < name.charAt(i)){
                    isFirst = false;
                    prev = temp;
                    //if is last node in list then insert rear
                    if (temp.next == null) {
                        insertRear(w);
                        break outerLoop;
                    }
                    temp = temp.next;
                    continue outerLoop;
                }else if (i + 1 == size && tempName.charAt(i) == name.charAt(i)){
                    //ex: if name = jimmy, tempName = jim then instert jimmy after jim                                                                                                                                                      \

                    //if a                                                                                                                                                                                                                  \

                    if (name.length() > tempName.length() && temp.next != null){
                        insertAt(temp, w, temp.next);
                        break outerLoop;
                    }else if (name.length() > tempName.length() && temp.next == null && prev != null) {
                        insertRear(w);
                        break outerLoop;
                    }else if (name.length() > tempName.length() && prev == null){
                        insertFront(w);
                        break outerLoop;
                    }else {
                        insertAt(prev,w,temp);
                        break outerLoop;
                    }
                }
            }
        }
    }
    // insterts Wumpus on the left
    private void insertFront(Wumpus w){
        LinkNode temp = new LinkNode();
        temp.next = first;
        w.setIndex(++population);
        temp.data = w;
        first = temp;
    }
    //inserts Wumpus on the right
    private void insertRear(Wumpus w) {
        LinkNode temp = new LinkNode();
        temp.next = first;
        w.setIndex(++population);
        while (temp != null){
            if (temp.next == null) {
                temp.next = new LinkNode();
                temp.next.data = w;
                break;
            }
            temp = temp.next;
        }
    }
    //inserts given (w) after prev and before temp
    private void insertAt(LinkNode prev,Wumpus w, LinkNode temp){
        LinkNode knew = new LinkNode();
        w.setIndex(++population);
        knew.data = w;
        prev.next = knew;
        knew.next = temp;
    }
    //get/returns population
    public int getPopulation(){
        return population;
    }
    // Starts from left and prints until null
    public void printList(){
        LinkNode temp = first;
        while(temp != null){
            System.out.printf("#%02d %s ", temp.data.getIndex(), temp.data);
            System.out.printf("%.02f %.02f ", temp.data.getX(), temp.data.getY());
            System.out.printf("%.02f", temp.data.getDistTraveled());
            if (temp.next == null) break; else System.out.println();
            temp = temp.next;
        }
        System.out.println();
    }
    /*
     * Takes a list of (newly tagged)Wumpii,their names,and X and Y positions
     * And returns a list numbering the order they where spotted
     * their names, last known X,Y positions and distance traveled
     *
     * working/tested
     * yes compiles,no errors known
     */
    public static void main(String args[]) {
        System.out.println("Assignment 6 (Save the Wumpus)");
        System.out.println("by Jacob Burgess");
        WList list = new WList();
        Scanner sc = new Scanner(System.in);
        list.population = 0;
        try {
            if (args[0] != null)
                sc = new Scanner(new File(args[0]));
        }
        catch(ArrayIndexOutOfBoundsException e){
            //do nothing
        }
        catch(FileNotFoundException e) { e.printStackTrace(); }
        while (sc.hasNextLine()){
            if (sc.hasNext()) {
                String name = sc.next();
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                Wumpus w = new Wumpus(x, y, name);
                list.wumpusSpotted(w);
            }else break;
        }
        list.printList();
    }
}