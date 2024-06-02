# The Word-ladder Puzzle

For this project I gained experience of implementing three important data structures - hash tables, resizing arrays and directed graphs - in a popular programming language (Java), and using them to implement a graph search algorithm. I also got some practice at thinking about the operation and performance of some of the data structures and algorithms I studied, using theoretical tools (Master theorem) as well as directly working with them. 

## Context of the game

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install fooba
The Word-ladder Puzzle

The word-ladder puzzle asks you to transform one four-letter word into another, changing one letter at a time. Each intermediate step should be a valid word. So for example we could transform "easy" into "math" as follows:

easy-east-mast-mash-math

A path such as

easy-masy-maty-math

would not be allowed because "masy" is not a word, and the path

easy-math

would not be allowed because we tried to change three letters at once. 

Of course the challenge is not only to find a path but to find the shortest possible path. 

There is a playable version of this game online at Weaver - A daily word puzzle.

In this assignment you will implement a solver for this game. 

Shortest Path Search

We will solve the puzzle by treating it as a search for a shortest path in a graph. The graph has one node for each legal word, and an edge joins two words if they differ by one letter. We will create a representation of this graph and solve the puzzle by performing breadth-first search. 

Graph Representation

We will represent the graph using an adjacency list. For each word, we will store an array of the words that can be reached from it in one step: these are its neighbours. We will need a data structure in which to store the collection of all these arrays, in such a way that we can look up a node's neighbours easily. We will therefore store them in a hash table, with the keys being the words. 

You are asked to implement two Java classes:

GraphNode.java stores a word together with the array of its neighbours
AdjacencyTable.java implements the hash table to store a collection of GraphNode objects that can be retrieved by searching for their label in O(1) average time. It also includes methods for the breadth-first search, and a static method which creates the graph corresponding to the word-ladder game. 
Graph Nodes

Complete the Java class GraphNode.java (link to template file). The code should work as follows:

The constructor GraphNode(String s) creates a new graph node object with the given label.

The field neighbours stores an array of strings which are the labels of the node's neighbours in the graph. Neighbours are added to this array by calling the addNeighbour method. Use a resizing array for this: initially the array has 6 slots, all holding null entries. The array should always have at least one empty slot; if it becomes full, replace the existing array by one that is double the size, as studied in lectures. (In this exercise there is no need to implement deletion, so there is no need to handle size reduction.)

You may add further fields and methods to this class if you need to. 

Adjacency List as a hash-table of nodes

Complete the code for AdjacencyTable.java (link to template file). The code should work as follows:

The constructor AdjacencyTable(String[] nodes) takes an array of Strings and creates a fresh hash table containing one GraphNode object labelled with each string in the array. You may assume all the strings are distinct. Initially each node has no neighbours. 

The load factor of the table you have created should be 0.5, so e.g. if the array nodes has two entries, the hash table should have 4 slots.

Graph nodes should be stored in the hash table in the slot indicated by the hash code of their label. The hash code for a string should be computed as follows:

  int hashcode = myString.hashCode();
  int hashValue = (hashcode & 0x7fffffff) % capacity;
where capacity is the number of slots available in the hash table. Collisions should be resolved by open addressing with linear probing. Using the right hash value is important for the automated tests that will run so please do not alter this – the bit mask is there to change negative values to positive, so please leave that there too! Empty slots in the table should hold a null pointer.

The method getTable() allows the automated tests to inspect your hash table directly; please do not alter this. 

Implement the followings hash-table operations:
boolean find(String s) tells whether a node labelled s exists in the table
GraphNode get(String s) returns the GraphNode object labelled with s, or a null pointer if s does not exist in the table
There is no need to implement resizing or deletion: once created, our hashtable will remain unchanged.

Finding paths

Once we have a hash-table full of GraphNode objects, we can represent a graph by adding neighbours to the nodes. For example the code

String[] nodes = {"A","B","C","D","E"};
AdjacencyTable t = new AdjacencyTable(nodes);
GraphNode n = t.get("A");
n.addNeighbour("B");
would create a table storing five nodes, and add an edge from A to B.  

Given such a graph, we can search for paths within it. Implement the following methods to perform breadth-first search on a graph represented by an AdjacencyTable of GraphNode objects. 
boolean existsPath(String s, String t) tells us whether or not there is a path from node labelled s to node labelled t in the graph represented by this table. This is the most straightforward path-finding code to implement so I suggest you attempt this first. 
int pathLength(String s, String t) returns the length of a shortest path from s to t, counting the nodes visited. So the shortest path from a node to itself has length 1. If there is no path from s to t, this method should return 0.
String getPath(String s, String t) returns a string representing the path from string s to string t. For example, a path from a node labelled A to a node labelled E via a node labelled D would return the string
A-D-E
If there is no path from s to t, this method should return the string

There is no path from s to t
You may make use of Java library classes such as ArrayList or LinkedList for any auxiliary data structures that you use to implement these algorithms. 

Implementing the word-ladder game Weaver

In order to solve word-ladder puzzles using our code, we need to build a representation of the word-ladder game. 

public static AdjacencyTable weaver() should return an AdjacencyTable object representing the word ladder game Weaver. The list of words is given in a provided support file. You can download WeaverWords.java here for your use but you do not need to upload it when submitting your code. WeaverWords.words will give you an array of strings containing all the legal words in the game. Your weaver() method should use this list to populate an AdjacencyTable. It should then add edges to the table for each valid step in the game. Try to do this as efficiently as possible: don't forget that it is quick to check whether a string is a valid word of the dictionary, by looking it up in the hash table. (Hint: if you need help generating the possible neighbours of a word in the graph, look at examples of computing Caesar Ciphers online.)

Once this is done, you should be able to solve a Weaver puzzle with code such as

   AdjacencyTable table = AdjacencyTable.weaver();
   System.out.println(table.getPath("easy","math")); 
```bash
```
