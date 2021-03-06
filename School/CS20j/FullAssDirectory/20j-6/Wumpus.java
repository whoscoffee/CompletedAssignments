/**
  * Jacob Burgess
  * jburgess
  * jacobdanielburgess@gmail.com
  * WList.java
  * Assignment 6 (Save the Wumpus)
  **/
public class Wumpus {
    private int index;
    private double x,y,distTraveled;
    private String name;
    //constructor
    public Wumpus( double x, double y, String name ){
	this.index = 00;
        this.x = x;
	this.y = y;
	this.name = name;
	this.distTraveled = 0;
    }
    //basically asks if the names of 
    //this wumpus and another are the same
    public boolean equals(Wumpus w){
	if (name.equals(w.name)) return true;
	return false;
    }
    //Getters
    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getDistTraveled(){ return distTraveled; }
    public int getIndex(){ return index;}
    //Setters
    public void setX(double d){ x = d; }
    public void setY(double d){ y = d; }
    public void setDistTraveled(double d){ distTraveled = d; }
    public void setIndex(int i) {index = i;}
    //returns the name of wumpus
    public String toString(){ return name; }
}
