Instructions to play: Type "java GameLogic" 

To be able to choose a Map, the user must hard code the name to the following three given maps: "Map1.txt", "Map2.txt", "Map3.txt".
Note, these three maps must be in the same folder as the code to run.

If the user wants to import his own map then they have to specify the whole directory path in the command prompt.

If you are experiencing any issues with uploading any of the three given maps then just type the whole directory path of where they are saved.

In the HumanPlayer class, I implemented the principle of encapsulation by bundling methods like randomPlace and nextTurn within the class. This helped improve the code quality of my game because it protected these methods from outside interference and allowed the GameLogic class to access more methods from the HumanPlayer class, resulting in smoother code execution.
 
In the GameLogic class, I used abstraction to enhance the code quality of my game by separating the implementation details of the map and player. This allowed me to focus on the main concept of the code and the rules of playing and made it easier to understand and maintain the code.
