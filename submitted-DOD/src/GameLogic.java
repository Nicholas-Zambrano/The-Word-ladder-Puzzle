public class GameLogic{
  
  // Initializing variables.
  private Map map;
  private boolean flag;
  private String command;
  private HumanPlayer human;
  
  
  public GameLogic(){ 

  // Creating new objects.
  // Initially set the flag to true.
    map = new Map();
    human = new HumanPlayer();
    flag = true;    
  }
  
  
  // We initially set the flag to true, to represent that the game is running.
  protected boolean operating_Game(){
    return flag;
  }
  
  
 /* This method will continue looping until the map has been succesfully created.
     Hence the player will be asked to choose the map,
     that chosen map will be created .
  */
  protected void setup(){
    while(map.mapFormed()==false){
      String mapChosen=human.produceMap();
      map.createMap(mapChosen);
      
    }
    
    // Once the map has been created.
    // We place the player on the map at a random location.
human.randomPlace(map.actualMap(),map.showHeight(),map.showLength());
    
    
    // This will continue taking the player's moves until the operating_Game() method returns false. 
    while(operating_Game()==true){
      
      // We call the 'nextTurn' method to get the player's input and call 'handleEntries' to execute the specific move.
      handleEntries(human.nextTurn());
    }  
  }
  

  // This hello function will return the number of gold needed to win.
  protected String hello() { 
    int goldRequired = map.gold_Objective();
    String message = "Gold to win: " + goldRequired;
    return message;
  }

  
   /*
  This method is called when the player tries to pick up an item in the game.
  */
  protected String pickup(){
  
    // This check if the player is currently on a GOLD tile.
    if(map.actualMap()[human.y_position()][human.x_position()] == 'G'){
  
      /* If this is true, then we increment the amount of gold that the player has
         We set that tile to a dot '.' and return 'SUCCESS', to suggest that the gold has been picked up.
      */
      human.goldScore(true);
      map.SetToDot(human.y_position(),human.x_position());
        return "SUCCESS";
      }
    
    // If character was not a GOLD then we return 'FAIL'.
    else{
      return "FAIL";
    }
  }

  
  // Gold function will return the amount of gold the player owns.
  protected String gold(){
    int goldOwned = human.capturedGold();
      String message = "Gold owned: " + goldOwned;
      return message; 
  }
  
  
  /*
    This method takes in a character representing a direction ('N','E','S', or 'W')  
    and returns a string representing the outcome of moving the player in that       direction.
  */
  protected String moving_the_player(char direction){
    switch(direction){
      
      /* If the direction is 'N' (north)  
       move the  player north on the map.
      */
      case 'N': 
        command = human.moveplayer('N', map.actualMap()); 
        break;
      case 'E':
        command = human.moveplayer('E',map.actualMap());
        break;
      case'S':
        command= human.moveplayer('S',map.actualMap());
        break;
      case 'W':
        command = human.moveplayer('W',map.actualMap());
        break;      
    }
  
    // We return the outcome of the player's movement.
    return command; 
  }
  

    /* 
      The look method returns a string  of a 5x5 grid centereed on the player's current position of the map.
    */
  protected String look(){
    
    // We initialized an empty string which will contain the grid and player.
    String grid=""; 
    
    // We are getting the player's current 'x' and 'y' coordinates.
    int x = human.x_position();
    int y =human.y_position();
    
    // Iterating over a 5x5 grid.
    for(int i = y-2; i <= y+2; i++){
      for(int j = x-2; j <= x+2; j++){
        
        /* If the current position is the player's position.
         then we add the player onto the grid */
        if(i == y && j==x){    
          grid += "P"; 
        }
          
          /* otherwise we get the character at the current position 
          if successful we add the character at the current position
          or if the current position is out of bounds on the map, then we add '#'.
          */
        else{
          try{ 
            grid += Character.toString(map.actualMap()[i][j]); 
          }
          catch(ArrayIndexOutOfBoundsException e){
            grid += "#";
          }
          
        }
      }
      
      /* If we are not at the end of the grid
         then we start a new line on the grid.
      */
      if(i != y+2){
        grid += "\n"; 
      }
    }
    
    // We return the completed grid string.
    return grid; 
  }

  
protected void handleEntries(String output){
   
  // Use a switch statement to determine the appropriate action based on the value of output.
  switch(output){
  
     // If output is "HELLO", we call the hello() method and set the command to its return value.
    case "HELLO":
      command=hello();
      break;
    case "GOLD":
      command=gold();
      break;
    case "PICKUP":
      command=pickup();
      break;
    case "LOOK":
      command=look();
      break;
    case "QUIT":
      quitGame();
      break;
    
    /*If output is "MOVE N" 
      we call the moving_the_player() method with the appropriate direction 
      and set the command to its return value.
    */ 
    case "MOVE N":
      command = moving_the_player(output.charAt(5));
      break;
     case "MOVE E":
      command = moving_the_player(output.charAt(5));
      break;
     case "MOVE S":
      command = moving_the_player(output.charAt(5));
      break;
     case "MOVE W":
      command = moving_the_player(output.charAt(5));
      break;
      case "INVALID":
      command = "INVALID";
      break;
  
 }
  
  // If the command is not null, we then print command.
  if(command != null){
    System.out.println(command);
  }
} 

  
  // quitGame method is called when the player tries to quit the game.
  protected void quitGame(){
    
    // Checks if the player is currently on the exit tile ('E') and has captured the required amount of gold.
    String result = (map.actualMap()[human.y_position()][human.x_position()] == 'E' && map.gold_Objective()== human.capturedGold()) ? "WIN":"LOSE"; 
  
  // Depending on the  result of the game, we print 'WIN'/'LOSE'.
  // We then set the flag to 'false', suggesting that the game has ended.
  System.out.println(result);
  flag=false;
  
  }

  
    // We made this class be the main.
  public static void main(String[] args) {
        
    // I created a new instance of the GameLogic class.
        GameLogic logic = new GameLogic();
    
    // Set up the game using the logic object.
        logic.setup();
    }
  
}