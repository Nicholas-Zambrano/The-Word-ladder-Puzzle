public class Tests {

    public static void main(String[] args) {
        testWeaverSolver();
        // Add more test cases as needed
    }

    private static void testWeaverSolver() {
        // Test Weaver Solver
        AdjacencyTable table = AdjacencyTable.weaver();

        // Test 1: Basic Path Existence
        System.out.println(table.existsPath("easy", "math"));  // Should print: true

////         Test 2: Basic Path Length
//        System.out.println(table.pathLength("easy", "math"));  // Should print: 4
////         Test 3: Basic Path
//        System.out.println(table.getPath("easy", "math"));  // Should print: easy-east-mast-mash-math
//
////        // Test 4: Non-Existent Path
//        System.out.println(table.existsPath("easy", "juicy"));  // Should print: false
////
////        // Test 5: Non-Existent Path Length
//        System.out.println(table.pathLength("easy", "juicy"));  // Should print: 0
////
////        // Test 6: Non-Existent Path Output
//        System.out.println(table.getPath("easy", "juicy"));  // Should print: There is no path from easy to juicy
////
////        // Test 7: Add Custom Edge
//        table.add_edge("easy", "fasy");
//        System.out.println(table.getPath("easy", "fasy"));  // Should print: easy-fasy
////
////        // Test 8: Duplicate Node
//        AdjacencyTable duplicateTable = new AdjacencyTable(new String[]{"A", "B", "C"});
//        duplicateTable.add_edge("A", "B");
//        duplicateTable.add_edge("A", "C");
//        System.out.println(duplicateTable.getPath("A", "C"));  // Should print: A-C
////
////        // Test 9: Invalid Word
//        AdjacencyTable invalidWordTable = new AdjacencyTable(new String[]{"A", "B", "C"});
//        System.out.println(invalidWordTable.is_valid_word("D"));  // Should print: false
////
////        // Test 10: Resize Neighbours Array
//        GraphNode node = new GraphNode("label");
//        for (int i = 0; i < 10; i++) {
//            node.addNeighbour("Neighbor" + i);
//        }
//        for (String neighbor : node.neighbours) {
//            System.out.println(neighbor);  // Should print: Neighbor0 Neighbor1 ... Neighbor9 null
//        }
    }
}

