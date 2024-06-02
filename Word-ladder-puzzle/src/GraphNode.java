 public class GraphNode {

    // A node holds a String label, and an array of its neighbours
    public String label;
    public String[] neighbours = new String[6]; // 6 neighbours to start with
//    ArrayList<String> neighbours;

    // You may add further fields and methods. 
     
    public GraphNode(String label) {
      this.label = label;
//      this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(String s) {
       // check if the array is full:
//    	using a counter to check number of elements in the array
    	int count_elements = 0;
    	for(int element =0 ; element<neighbours.length;element++) {
    		if(neighbours[element] != null) {
    			count_elements += 1;
    		}
    	}
    	

//    	if array is full
    	if(count_elements == neighbours.length) {
    		String [] resize_array = new String [neighbours.length * 2];
//    	    		now copy the elements to the array
    		for(int i = 0; i<neighbours.length; i++) {
    			resize_array[i] = neighbours[i];
    			}
//    	    		now working on the resized array 
    	    		neighbours = resize_array ;
    	    		
    	    	}
    	    	
//    	    	 now add array to the first empty slot
    	    	
    	    	for(int i = 0; i<neighbours.length;i++) {
    	    		if(neighbours[i] == null) {
    	    			neighbours[i] = s;
    	    			break;
    	    		}
    	    	}
    			
    			
    		
    	}
   }


