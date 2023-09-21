import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Arrays;
import java.io.*;

public class HumanPlayer {
  
  // Initializng variables.
  private String player_input;
  private int PlayerGoldOwned;
  private String[] Entries ={"HELLO","GOLD","PICKUP","LOOK","QUIT","MOVE S","MOVE N","MOVE E","MOVE W"};
  private String[] GivenMaps = new String[] {"Map1.txt","Map2.txt","Map3.txt"}; 
  
  private int x_pos;
  private int y_pos;
  private Random rand;
  
  public HumanPlayer(){

    PlayerGoldOwned = 0;
  }

// This method prompts the user to type a map from a list of given maps, or import their own map.
  
  
  protected String produceMap(){ //protected means nobody can change the map outside of this method

      System.out.println("Please enter the name of the map as a text file as shown below: "); // just printing out all of the map names
      for(int i=0; i< GivenMaps.length;i++) {
    	  System.out.println((i+1)+") " + GivenMaps[i]);
      
      }
      System.out.println((GivenMaps.length + 1) + ") Import your own map with the whole path directory"); // allow the user to choose their own map.
      try {
                  BufferedReader fileLocation = new BufferedReader(new InputStreamReader(System.in));
                  return fileLocation.readLine();
      }
      catch (IOException e){
          return null;
      }



  }
  

  
/* This method places a character in a random position on a given char array
   It also takes a 2D character array representing the map, and the height and length of the map as parameters.
*/ 
protected void randomPlace(char[][] charArray, int mapHeight, int mapLength){
  
  // Initialize a Random object to generate random coordinates.
  rand = new Random();

  // Ensure that the Random object was successfully initialized.
  if (rand == null) {
    return;
  }

  // Generating random 'x' and 'y' coordinates and check if they are valid.
  do {
    int randomX = rand.nextInt(mapLength); 
    int randomY = rand.nextInt(mapHeight);

    /*If the character at the randomly-generated coordinates is not 'G' or '#',
      set the x_pos and y_pos to the randomly-generated coordinates
      and return from the method
    */ 
    if(charArray[randomY][randomX] != 'G' && charArray[randomY][randomX] != '#'){ 
      x_pos = randomX;  
      y_pos = randomY;
      return;
    }
  } 
    
    // Keep looping until a valid random position is found.
    while (true);
}
  
// These methods return the x and y position coordinates of the player on the map.
protected int x_position(){
  return x_pos;
}
  
protected int y_position(){
    return y_pos;
  }
  
/* This method moves the player in the specified direction on the given map, 
   if the move is valid it returns "SUCCESS" if the move was successful, and "FAIL" otherwise.
   It takes a character representing the direction and a 2D character array representing the map as parameters. */
protected String moveplayer(char direction, char[][]map){
  String command ="FAIL";
  
// Calculate the new position of the player if they move in the specified direction.
int newY = y_pos;
int newX = x_pos;
    switch (direction) {
        case 'N':
            newY = y_pos - 1;
            break;
        case 'E':
            newX = x_pos + 1;
            break;
        case 'S':
            newY = y_pos + 1;
            break;
        case 'W':
            newX = x_pos - 1;
            break;
        default:
            break;
    }
  
  // Check if the next position is not a wall and within the boundaries of the map.
    if (newY >= 0 && newY < map.length && newX >= 0 && newX < map[newY].length && map[newY][newX] != '#') {
        command = "SUCCESS";
        y_pos = newY;
        x_pos= newX;
    }
  
   // Return the command indicating the result of the move.
    return command;
}


// This method reads the player's input and returns it if it is valid, or "INVALID" otherwise.
protected String nextTurn() {
   
  // Read the player's input from the standard input stream.
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        player_input = br.readLine().trim().toUpperCase();
    } 
    catch (IOException e) {
      
      // If an error occurred while reading the input, print the error message and exit the program.
        System.err.println(e.getMessage());
        System.exit(1);
        return null;
    }

    // checks whether the input is contained in the Entries array, and returns either the input or the string "INVALID"
    if (Arrays.asList(Entries).contains(player_input)) {
        return player_input;
    } else {
        return "INVALID";
    }
} 
  
// The 'capturedGold' methos returns the number of gold that the player has captured.
protected int capturedGold(){
  return PlayerGoldOwned;
}


// GoldScore method updates the player's gold score based on whether they caught gold on their turn. 
protected void goldScore (boolean caughtgold){
  
  // If the player caught gold, increment the player's gold score.
  PlayerGoldOwned += caughtgold ? 1 : 0;
}


  
}