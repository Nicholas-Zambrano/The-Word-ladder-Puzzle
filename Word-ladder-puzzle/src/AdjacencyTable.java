//import java.util.Queue;
import java.util.*;


public class AdjacencyTable {

    private GraphNode[] table;
    

    public AdjacencyTable(String[] nodes) {
	// add your constructor code here
//    	initialise table
    	table = new GraphNode[nodes.length * 2];
    	
//    	 for each graph node add it to the table 
    	for(int each_node = 0 ; each_node < nodes.length;each_node++) {
//    		lets calculate the hash value for each node
//    		table[find_hash_value(nodes[each_node])] = new GraphNode(nodes[each_node]);
//    		placing the calculated node to the hash table
//    		table[(nodes.hashCode() & 0x7fffffff) % table.length] = new GraphNode(nodes[each_node]);
    		int hash_code = nodes[each_node].hashCode();
    		int hash_value = (hash_code & 0x7fffffff) % table.length;
    		
    		if(table[hash_value] == null) {
    			table[hash_value] = new GraphNode(nodes[each_node]);
    		}

    	}
    	
    }

    public GraphNode[] getTable() {
        return table;
    }

    public boolean find(String s) {
	// add your table search code here
//	return false; // delete this line
    	int hash_code = s.hashCode();
		int hash_value = (hash_code & 0x7fffffff) % table.length;
		
//		checking if the hash value position is not empty
		if(table[hash_value] != null) {
//			if not empty, then check if node exists = string s
			if(table[hash_value].label.equals(s)) {
				return true;
			}
		}
		return false;
    }
		
		

    public GraphNode get(String s) {
	// add your table lookup code here
//	return null; // delete this line
    	int hash_code = s.hashCode();
		int hash_value = (hash_code & 0x7fffffff) % table.length;
//    	start from hash value and check until an empty slot or the key is found
		
		for(int i = hash_value; i< table.length;i++) {
			if(table[i]==null) {
//				empty slot so key not found
				return null;
			}
			else if(table[i].label.equals(s)) {
//				the key was found
				return table[i];
			}
		}
//		otherwise key not found
		return null;
    }

    public String getPath(String s, String t) {
	// add your code here
//	return ""; // delete this line
    	
//    	initialising the queue and adding the first node to it
    	Queue<String> queue = new LinkedList<>();
    	queue.add(s);
    	
//    	creating a map , to keep track of the path to each node
    	Map<String,String>path = new HashMap<>();
    	path.put(s, null);
    	
    	while(!queue.isEmpty()) {
    		String current_node = queue.poll();
    		if(current_node.equals(t)) {
    			StringBuilder path_string = new StringBuilder(t);
    			while(path.get(current_node) != null) {
    				path_string.insert(0, path.get(current_node) + "-");
    				current_node = path.get(current_node);
    			}
    			return path_string.toString();
    		}
    		for(int i = 0;i<get(current_node).neighbours.length;i++) {
    			String neighbour = get(current_node).neighbours[i];
    			if(neighbour != null && !path.containsKey(neighbour)) {
    				queue.add(neighbour);
    				path.put(neighbour, current_node);
    			}
    			
    		}
    		
    	}
    	return "There is no path from " + s + " to " + t;
    }

    public boolean existsPath(String s, String t) {
	// add your code here
//	return false; // delete this line
//    	created a queue andd the start node to it
    	Queue<String>queue = new LinkedList<>();
    	queue.add(s);
    	
    	Set<String> visited_node = new HashSet<>();
    	visited_node.add(s);
    	
    	
    	while(!queue.isEmpty()) {
    		String current_node = queue.poll();
//    		if the current node = target node, return true
    		if(current_node.equals(t)) {
    			return true;
    		}
//    		iterating over each neighbour of the current node
    		for(int i =0; i<get(current_node).neighbours.length;i++) {
    			String neighbour = get(current_node).neighbours[i];
//    			checking if current neighbour has been visited
    			if(neighbour != null && !visited_node.contains(neighbour)) {
//    				if neighbour has been visited,add it to visited list and add it to the queue
    				visited_node.add(neighbour);
    				queue.add(neighbour);
    				}
    		}
    	}
//    	if queue is empty and have not found target node
    	return false;
    	
    }

    public int pathLength(String s, String t) {
	// add your code here
//	return 111; // delete this line
    	
    	Queue<String>queue = new LinkedList<>();
//    	this stores the shortest distance
    	Map<String,Integer>distance_map = new HashMap<>();
    	queue.add(s);
//    	setting the initial distance from the starting node to itself as 1 
    	distance_map.put(s, 1);
    	
    	while(!queue.isEmpty()) {
    		String current_word = queue.poll();
//    		if current word = target word, then return the distance
    		if(current_word.equals(t)) {
    			return distance_map.get(current_word);
    		}
    		for(int i = 0; i<get(current_word).neighbours.length;i++) {
    			String neighbour = get(current_word).neighbours[i];
//    			checking if neighbour is not null
    			if(neighbour != null) {
//    				checking if neighbour has not been visited yet
    				if(!distance_map.containsKey(neighbour)) {
    					queue.add(neighbour);
//    					getting shortest distance from starting node to neighbour
    					distance_map.put(neighbour, distance_map.get(current_word)+1);
    				}
    			}
    		}
    	}
    	return 0;
    	
    }
    
    public static String[] generate_neighbours(String word){
    	String[] neighbours = new String[26];
    	char[] word_chars = word.toCharArray();
    	for(int i = 0;i< 26;i++) {
    		for (int j=0;j<word_chars.length;j++) {
    			word_chars[j] =(char)('a'+i);
    			neighbours[i] = new String(word_chars);
    		}
    	}
    	return neighbours;
    }
    
    public boolean is_valid_word(String word) {
    	String[] valid_words = WeaverWords.words;
    	for(int i=0 ; i < valid_words.length; i++) {
    		if(word.equals(valid_words[i])) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void add_edge(String node1, String node2) {
    	GraphNode graph_node1 = get(node1);
    	GraphNode graph_node2 = get(node2);
    	
//    	if either node does not exist
//    	if not then those nodes don't exist in the graph
//    	creating gradnodes objects
    	if(graph_node1 == null || graph_node2== null ) {
    		if(graph_node1 == null) {
    			graph_node1 = new GraphNode(node1);
    			int hash_code = node1.hashCode();
    			int hash_value = (hash_code & 0x7fffffff) % table.length;
    			table[hash_value] = graph_node1;
    		}
    		if(graph_node2 == null) {
    			graph_node2 = new GraphNode(node2);
    			int hash_code = node2.hashCode();
    			int hash_value = (hash_code & 0x7fffffff) % table.length;
    			table[hash_value] = graph_node2;
    		}
    	}
//    	graph_node1.add(graph_node2.label);
    	   // Add the second node to the neighbours of the first node
//    	graph_node1.neighbours = Arrays.copyOf(graph_node1.neighbours, graph_node1.neighbours.length + 1);
//    	graph_node1.neighbours[graph_node1.neighbours.length - 1] = graph_node2.label;
//
//    	   // Add the first node to the neighbours of the second node
//    	graph_node2.neighbours = Arrays.copyOf(graph_node2.neighbours, graph_node2.neighbours.length + 1);
//    	graph_node2.neighbours[graph_node2.neighbours.length - 1] = graph_node1.label;
    	graph_node1.addNeighbour(graph_node2.label);

    	
    }

    public static AdjacencyTable weaver() {
	//    	getting the words from the weaver words class
    	String[] words = WeaverWords.words;
//    	created adjacency table with the words
    	AdjacencyTable adjacencyTable = new AdjacencyTable(words);
    	
//    	add edge for each neigbour
    	for(int i = 0; i<words.length;i++) {
    		String[] neighbours = generate_neighbours(words[i]);
    		for(int j = 0; j<neighbours.length;j++) {
    			if(adjacencyTable.is_valid_word(neighbours[j])) {
    				adjacencyTable.add_edge(words[i],neighbours[j]);
    			}
    		}
    	}
    	return adjacencyTable;
    }
}
