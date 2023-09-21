import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map{

  // Initializing variables.
  private char [][] map;
  private int mapHeight;
  private int mapLength;
  private int goldNeeded;  
  private boolean validMap;
  
  

  public Map() {
    
     // The map has not yet been chosen.
    validMap=false;
  }
  
 
  // Method is called, when the map is successfully imported from a file.
  protected boolean mapFormed(){
    return validMap;
  }

  
  // This sets '.' at the specified position in the map array/
  protected void SetToDot(int y, int x){ 
    map[y][x]= '.';
  }

  
  // Return the number of gold required when reading the map.
  protected int gold_Objective() { 
    return goldNeeded; 
}
  
// This method creates a map from the given file.
  protected void createMap(String filename){
    try{
      
      // Open the map file for reading.
      BufferedReader importMap =new BufferedReader(new FileReader(filename)); 
      
      // Read the first line of the file and remove the "name " 
     importMap.readLine().replaceAll("name ", "");
      
     // We read the number of gold needed to win and store it in the 'goldNeeded' instance variable.
     goldNeeded= Integer.parseInt(Character.toString(importMap.readLine().charAt(4)));
     
      // Read the first line of the map and store its length int the variable 'mapLength'. 
     mapLength = importMap.readLine().length();
     mapHeight = 1;

      // Counting the number of lines in the file to determine the map height.
      while(importMap.readLine() != null){
        mapHeight++;
      }
      map= new char[mapHeight][mapLength];
      
      // We close to to ensure any changes made to the file are saved.
      importMap.close();

      // Reading the text files.
      BufferedReader buildingMap = new BufferedReader(new FileReader(filename)); 
      
      // Skips map name
      buildingMap.readLine(); 
      // Skips gold required
      buildingMap.readLine(); 
      
      // Read each line of the file and fill in the map array.
      for (int row = 0; row < mapHeight; row++) {
        String line = buildingMap.readLine();
        for (int col = 0; col < mapLength; col++) {
          map[row][col] = line.charAt(col);
          System.out.print(map[row][col]);
        }
        System.out.println(); 
      }
      buildingMap.close();
      
      // After finish creating the map - set it equal to true and inform the user the map was created.
      validMap = true; 
      System.out.println("Map Successfully imported, begin !!!");
      
    }
    catch (IOException e) {
      
      // This handles the erros.
      // Which occurs when the typed file is non existent
      System.err.println("Error generating map."); 
      return;
    }
  }
  
  
  // Returns height of map,  when map is successfully created
  protected int showHeight(){
    if(validMap == true){
      return mapHeight;
    }
    else{
      return 0;
    }
  }

  
 // Returns length of map.
  protected int showLength(){
    if(validMap==true){
      return mapLength;
    }
    else{
      return 0;
    }
  }
  
 
  // This method is returning the map.
  protected char[][] actualMap(){
  return map;
}   
 
}

