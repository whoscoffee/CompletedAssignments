// CS20j Final Exam - 3D Tic Tac Toe Game - Spring 2019
// (C) Steve J. Hodges, Computer Science, Cabrillo College
// as per academic policy:
//     DO NOT DUPLICATE, DO NOT DISTRIBUTE, DO NOT TRANSCRIBE
//     for your individual use only

// directions: 
// You are to write the code for readLogo() and findWinner()
// (those two functions are at the bottom of the file)
//             ONLY MODIFY THE CONTENTS OF THE TWO
//             ASSIGNED FUNCTIONS AND ADD YOUR NAME ETC
//             WHERE DIRECTED OR ADD IMPORT STATEMENTS
//             DO NOT MODIFY OTHER PARTS OF THE CODE 



// YOU NAME AND PENGO LOGIN HERE PLEASE
// 

// You are allowed to add additional import statements if needed
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class TicTacToe{
    // board    :64 positions as follows
    //
    // value: 0 is empty
    //        1 is player 1 (X)
    //        2 is player 2 (O)
    private int [] gameBoard;
    private final String [] SIGIL = {" . ", "(X)", "(O)"};
    private final int [] CCODE = {3, 1, 2};

    // you might find this helpful
    private final int [] [] WINNING_LINES =
    { {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15},
      {16, 17, 18, 19}, {20, 21, 22, 23}, {24, 25, 26, 27}, {28, 29, 30, 31},
      {32, 33, 34, 35}, {36, 37, 38, 39}, {40, 41, 42, 43}, {44, 45, 46, 47},
      {48, 49, 50, 51}, {52, 53, 54, 55}, {56, 57, 58, 59}, {60, 61, 62, 63},
      {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15},
      {16, 20, 24, 28}, {17, 21, 25, 29}, {18, 22, 26, 30}, {19, 23, 27, 31},
      {32, 36, 40, 44}, {33, 37, 41, 45}, {34, 38, 42, 46}, {35, 39, 43, 47},
      {48, 52, 56, 60}, {49, 53, 57, 61}, {50, 54, 58, 62}, {51, 55, 59, 63},
      {0, 5, 10, 15}, {16, 21, 26, 31}, {32, 37, 42, 47}, {48, 53, 58, 63},
      {3, 6, 9, 12}, {19, 22, 25, 28}, {35, 38, 41, 44}, {51, 54, 57, 60},
      {0, 16, 32, 48}, {1, 17, 33, 49}, {2, 18, 34, 50}, {3, 19, 35, 51},
      {4, 20, 36, 52}, {5, 21, 37, 53}, {6, 22, 38, 54}, {7, 23, 39, 55},
      {8, 24, 40, 56}, {9, 25, 41, 57}, {10, 26, 42, 58}, {11, 27, 43, 59},
      {12, 28, 44, 60}, {13, 29, 45, 61}, {14, 30, 46, 62}, {15, 31, 47, 63},
      {0, 17, 34, 51}, {4, 21, 38, 55}, {8, 25, 42, 59}, {12, 29, 46, 63},
      {3, 18, 33, 48}, {7, 22, 37, 52}, {11, 26, 41, 56}, {15, 30, 45, 60},
      {0, 20, 40, 60}, {1, 21, 41, 61}, {2, 22, 42, 62}, {3, 23, 43, 63},
      {12, 24, 36, 48}, {13, 25, 37, 49}, {14, 26, 38, 50}, {15, 27, 39, 51},
      {0, 21, 42, 63}, {15, 26, 37, 48}, {3, 22, 41, 60}, {12, 25, 38, 51} };


    private int [][] logo;
    private static final int LOGOH=5;
    private static final int LOGOW=60;

    private Scanner input;
    private final int SIZE = 4;
    private final int SIZE3D = SIZE*SIZE*SIZE;

    // convert board, row, col value in range 0-3 to 
    // a board array index
    // invalid values generate an index of -1
    private int calculateIndex(int board, int row, int col){
	if (board <0 || board >=SIZE ||
	    row <0   || row >=SIZE   ||
	    col <0   || col >=SIZE     ){
	    return -1; // invalid index
	}
	return board * (SIZE * SIZE ) + row * SIZE + col;
    }

    public TicTacToe(){
	gameBoard = new int[SIZE3D];
	logo = new int[LOGOH][LOGOW];
	for(int i=0; i<LOGOH; i++){
	    logo[i] = new int[LOGOW];
	}
	input = new Scanner(System.in);
    }

    public static void main(String args[]){
	TicTacToe game = new TicTacToe();
	game.playGame();
    }

    public void playGame(){
	int player = 1; // indicates current player

	readLogo();
	drawLogo();
	for(int i=0;i<SIZE3D;i++){
	    gameBoard[i] = 0; // empty
	}
	
	// main game loop
	while(gameNotOver()){
	    drawBoard();
	    playerTurn(player);
	    player = 3 - player; // other players turn
	}
	// show final board
	drawBoard();
	
	int winner = findWinner();
	if (winner == 1){
	    color(1);
	    System.out.print( "Player 1 (X) wins!");
	    color(0);
	    System.out.println();
	}
	if (winner == 2){
	    color(2);
	    System.out.print( "Player 2 (O) wins!");
	    color(0);
	    System.out.println();
	}
	if (winner == 0){
	    color(0);
	    System.out.println("Wow, Tie Game!");
	}
	System.out.println(findWinner()); // last line of output: 0, 1, 2	
    }


    // Changes text color of System.out using ANSI standard CSI escape codes
    private void color(int c){
	switch(c){
	case 1: // black bold on green
	    System.out.print('\u001B');System.out.print("[0;30;1;42m");break;  
	case 2: // white bold on green
	    System.out.print('\u001B');System.out.print("[0;37;1;42m");break;  
	case 3: // yellow on green
	    System.out.print('\u001B');System.out.print("[0;33;42m");break;  
	case 4: // bold green on black
	    System.out.print('\u001B');System.out.print("[0;32;1;40m");break;  
	case 5: // bold green on white
	    System.out.print('\u001B');System.out.print("[0;32;1;47m");break;  
	default:
	case 0:   // black on white (default)
	    System.out.print('\u001B');System.out.print("[0;30m");break;  
	}
    }

    // game ends when either player1 or player 2 wins
    private boolean gameNotOver(){
	return (findWinner() == 0);
    }

    private void drawLogo(){
	color(2);
	System.out.print( "Final Exam: 3D Tic Tac Toe - Jacob Burgess");
	color(0);
	System.out.println();
	for(int i=0;i<LOGOH;i++){
	    for(int j=0;j<LOGOW;j++){
		color(logo[i][j]+3);
		System.out.print(" ");
	    }
	    color(0);
	    System.out.println();
	}
	color(0);
    }
    
    private void drawBoard(){
	int index=0;
	color(0);  
	System.out.println();
	System.out.println( "   [0] top            [1] middle         [2] middle         [3] bottom ");
	System.out.println("   0  1  2  3         0  1  2  3         0  1  2  3         0  1  2  3 ");
	for (int row = 0; row < SIZE; row++){
	    color(0);
	    System.out.print(""+row+":");
	    for (int board = 0; board < SIZE; board++){
		for (int col = 0; col < SIZE; col++){
		    index = calculateIndex(board, row, col);
		    color( CCODE[gameBoard[index]] );
		    System.out.print(SIGIL[gameBoard[index]]);
		    color(0);
		} // col
		if (board != SIZE -1){
		    color(0);
		    System.out.print("     "); // space between boards
		    System.out.print(""+row+":");
		}
	    } // board
	    System.out.println(); // next row
	} // row
	color(0);
	System.out.println();
    }


    

    // player's turn
    // input move until a legal move is chosen
    // and then update the gameBoard
    private void playerTurn(int player){
	int board = -1; // init to invalid choices
	int col = -1;
	int row = -1;
	int index = -1;

	// repeat until valid move is made
	while( index == -1 || gameBoard[index] != 0){
	    color(player);
	    System.out.print("Player "+ player +" "+ SIGIL[player] + "  your move (b r c)? ");
	    color(0);
	    board = input.nextInt();
	    row = input.nextInt();
	    col = input.nextInt();
	    index = calculateIndex(board, row, col);
	}
	// add players move to the game board
	gameBoard[index] = player;
    }

    // returns value to indicate winner
    // 0 = no one has won yet
    // 1 or 2 for player 1 or 2 win
    // check all 76 ways to win
    private int findWinner(){
	// placeholder code:
	return 0; 

    } // findWinner   


    // this function should open the text file "logo-ttt.txt"
    // and store values '0' = 0 ,  '1' = 1 ,  '2' = 2
    // into the integer array named logo
    private void readLogo() {
	try {
	    Scanner sc = new Scanner(new File("logo-ttt.txt"));
	    for (int i = 0; i < 5; i++) {
	        String str = sc.nextLine();
	        for (int j = 0; j < str.length(); j++){
		    logo[i][j] = str.charAt(j) - 48;
		}
	    }
	    for (int i = 0; i < 5; i++ ){
		for (int j : logo[i])
		    System.out.print(j + ", " );
		System.out.println();
	    }
	}catch(Exception e){
	    System.out.println("Something went Wrong...missing file Probably");
	}
    } //readLogo
}


