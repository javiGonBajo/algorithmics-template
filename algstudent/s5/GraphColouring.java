package algstudent.s4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//
public class GraphColouring {
	private static String[] colours = { "red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta",
			"lime" };

	public static Map<String, String> greedy(Map<String, List<Long>> graph) {
		Map<String, String> solution = new HashMap<String, String>();
		List<Long> edges;
		HashSet<String> usedColours;
		
		//Order the nodes for a better solution
		List<String> orderedNodes = new ArrayList<String>();
		for (String node : graph.keySet()) {
			orderedNodes.add(node);
		}
		
		Comparator<String> comparator = new Comparator<>() {
		    @Override
		    public int compare(String o1, String o2) {      
		        return ((Integer)graph.get(o2).size()).compareTo((Integer)graph.get(o1).size());
		    }
		};
		Collections.sort(orderedNodes, comparator);
		
		
		//Starts the greedy algorithm
		for (String node : orderedNodes) {
			edges = graph.get(node);
			usedColours = new HashSet<String>();

			for (Long neighbor : edges) {
				String neighborString = String.valueOf(neighbor);
				if (solution.containsKey(neighborString))
					usedColours.add(solution.get(neighborString));
			}

			for (String colour : colours) {
				if (!usedColours.contains(colour)) {
					solution.put(node, colour);
					break;
				}
			}
		}
		return solution;
	}
}
