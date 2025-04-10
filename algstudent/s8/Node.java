package s8;

import java.util.ArrayList;
import java.util.HashMap;

public class Node implements Comparable<Node> {
    protected int depth; //Number of moves made so far (is equal to the number of nodes developed) on this branch
    protected int parentID; //Parent ID for node tracking
    protected int ID; //ID for the node
    protected int heuristicValue; //Value of the calculated heuristic

    /**
     * Constructor for Node objects
     */
	public Node(int id) {
		ID = id;
    	parentID = -1; //It does not have parent unless we say another thing
    }
	
	/**
	 * Getter for depth
	 * @return The depth variable
	 */
    public int getDepth() {return depth;}
	  
    /**
     * Getter for heuristicValue
     * @return The heuristicValue variable
     */
	public int getHeuristicValue() { return heuristicValue; }
	
	/**
	 * Compares whether two nodes are equal using the ToString method
	 * @param n Another node to be compared with
	 * @return True if there are equal. False otherwise
	 */
    public boolean equals(Node n) {
		return (n.toString().equals(toString()));
	}
    
    /**
     * Getter for parentID
     * @return The parentID variable
     */
    public int getParentID() {
    	return parentID;
    }
    
    /**
     * Gets the ID of the node
     * @return ID of the node
     */
    public int getID() {
    	return ID;
    }
    
    /**
     * We can have extra information about the problem to prune all the nodes
     * above a specific heuristicValue. By default we know nothing, so we 
     * do not prune anything
     * @return Value of the initial prune limit 
     */
	public int initialValuePruneLimit(int size, int threshold) {
		return size * threshold; //Implementation by default
	}
    
	@Override
	public int compareTo(Node node) { //BRANCHING METHOD
		int totalValue = Math.abs(heuristicValue);
		int totalValueToBeCompared = Math.abs(node.getHeuristicValue());
		
		if (totalValue > totalValueToBeCompared) return 1; //this has less priority (is bigger)
		else if (totalValue == totalValueToBeCompared) return 0; //The same priority
		else return -1; //this has more priority (is smaller)
	}
    
	
	
	public ArrayList<Node> expand(HashMap usedNodes, ArrayList<Node> nodeList){
		ArrayList<Node> expandedList = new ArrayList<Node>();
		for(Node node: nodeList) {
			if(!usedNodes.containsValue(node))
				expandedList.add(node);	
		}
		return expandedList;
	}
	
	public boolean isSolution(int max, int value, int threshold) {
		if(this.getDepth() + 1 < max)
			return false;
		
		return value <= threshold;
	}
}
