// CS20j:Final Exam - Java Programming
// as per academic policy:
//     DO NOT DUPLICATE, DO NOT DISTRIBUTE, DO NOT TRANSCRIBE
//     for your individual use only

// Linked List exercise
// exma filename: LinkedListF.java
//   generic link list inherited to special-case list
//   to simplify your code, you may directly use inserted data.
// Instructions: modify *ONLY* the two indicated functions
//               see handout for further details
// YOUR NAME and EMAIL HERE

class LinkedList<T>{
    class LinkNode<T>{
	private LinkNode<T> next;
	private T data;
	LinkNode<T> getNext(){return next;}
	void setNext(LinkNode<T> n){ next = n;}
	T getData(){ return data;}
	void setData(T nd){ data = nd;}
	public LinkNode(){ next = null; data = null; }
    }
	 
    protected LinkNode<T> first;
	 
    public LinkedList(){ first = null;}

    void insert( T d ){
	LinkNode<T> temp = new LinkNode<T>();
	temp.data = d; // NOTE: privacy leak here
	temp.next = first;
	first = temp;
    }
	 
    void print(){
	LinkNode<T> temp = first;
	while( temp != null){
	    System.out.println(temp.data);
	    temp = temp.next;
	}
    }
}

// -------------------

class GroceryItem{
    private String name;    // name of the item
    private int count;      // count of items purchased
    private double cost;     // item price
    private boolean deposit;  // has an exta $1.00 per item charge?
    
    public GroceryItem(){
	name = "default";
	count = 0;
	cost = 0.0;
	deposit = false;
    }
    
    public GroceryItem(String s, int cnt, double ip, boolean dep){
	name = s; count = cnt; cost = ip; deposit = dep;
    }

    //copy constructor
    public GroceryItem(GroceryItem source){
	name = source.name;
	count = source.count;
	cost = source.cost;
	deposit = source.deposit;
    }
    
    public String toString(){
	String s;
	s = name + "( " + count+ " @  $"+cost+ " ea.";
	if (deposit)
	    s+= " with $1.00 deposit";
	s+=")";
	return s;
    }

    public boolean equals(Object s){
	if (s == null){return false;}
	if (! (s instanceof GroceryItem)){ return false; }
	GroceryItem temp = (GroceryItem) s;
	// items are equal if they have the same name
	return name.equalsIgnoreCase(temp.name);
    }
    
    public int getItemCount(){ return count; }
    public String getItemName(){ return name; }
    public double getItemCost(){ return cost; }
    public boolean itemHasDeposit(){ return deposit; }
}

// -------------------

class CartList extends LinkedList<GroceryItem>{
    public void addItemFront(GroceryItem item){
	// use copy constructor to prevent privacy leak
	GroceryItem safeCopy = new GroceryItem(item);
	insert(safeCopy);
 }
    
    // *****  code this function
    // it should add a new node at the end of the list
    public void addItemRear(GroceryItem sale){
	if (first == null){
	    insert(sale);
	    return;
	}
	LinkNode prev = first;
	LinkNode temp = first.getNext();
	
	while(temp != null) {
	    prev = temp;
	    temp = temp.getNext();
	}    
	temp = new LinkNode();
	temp.setData(sale); 
	prev.setNext(temp);
    }
	 
    // *****  code this function
    // this function should return the total dollar value
    // of all of the items with a particular name
    //   (the ones where the names match)
    //    including the deposit, if applicable
    // if the parameter is blank, 
    //  return the total for all items in the list
    public double cartTotal(String name){
	return 0.0;
    }
    
}


// -------------------------------------------------------------------



public class LinkedListF{

    public static void main(String [] args){
	CartList myCart = new CartList();
	GroceryItem item;
	
	item = new GroceryItem("Tofu", 4, 15.00, false);
	myCart.addItemFront(item);
	item = new GroceryItem("Orange Juice", 2, 8.00, false);
	myCart.addItemRear(item);
	item = new GroceryItem("Craft Beer", 2, 200.00, true);
	myCart.addItemFront(item);
	item = new GroceryItem("Tofu", 2, 15.00, false);
	myCart.addItemRear(item);
	item = new GroceryItem("Flipper", 6, 50.00, false);
	myCart.addItemRear(item);
	item = new GroceryItem("Printer Ink", 6, 320.00, false);
	myCart.addItemRear(item);
	item = new GroceryItem("Craft Beer", 4, 200.00, true);
	myCart.addItemFront(item);
	item = new GroceryItem("Craft Beer", 4, 200.00, true);
	myCart.addItemFront(item);
	
	System.out.println("CS20j Final Exam - Linked Lists");
	System.out.println("YOUR NAME AND EMAIL HERE");
	
	// testing - print whole list to verify all added
	/* should print in this order:

	   Craft Beer( 4 @  $200.0 ea. with $1.00 deposit)
	   Craft Beer( 4 @  $200.0 ea. with $1.00 deposit)
	   Craft Beer( 2 @  $200.0 ea. with $1.00 deposit)
	   Tofu( 4 @  $15.0 ea.)
	   Orange Juice( 2 @  $8.0 ea.)
	   Tofu( 2 @  $15.0 ea.)
	   Flipper( 6 @  $50.0 ea.)
	   Printer Ink( 6 @  $320.0 ea.)

	*/

	myCart.print();
	
	// total value should be $90.0
	System.out.print("\"Tofu\" sales are: $");
	System.out.println( myCart.cartTotal("Tofu"));
	
	// total value should be $0.0
	System.out.print("\"Milk\" sales are: $");
	System.out.println(myCart.cartTotal("Milk"));
	
	// total value should be $2010.0
	System.out.print("\"Craft Beer\" sales are: $");
	System.out.println(myCart.cartTotal("Craft Beer"));
		  
	// total value of items should be $4336.0
	System.out.print("All items are: $");
	System.out.println(myCart.cartTotal(""));
		  
    }
}

