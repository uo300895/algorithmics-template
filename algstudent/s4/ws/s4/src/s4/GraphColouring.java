package s4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphColouring {
	
	private static String colours[] = {"red", "blue", "green", "yellow", 
			"orange", "purple", "cyan", "magenta", "lime"};
	
	public static Map<String, String> greedy(Map<String, List<Long>> graph) {
		Map<String, String> sol = new HashMap<String, String>(graph.size());
		for (String node : graph.keySet()) {
			List<Long> nodeLinks = graph.get(node);
			List<String> usedColors = new ArrayList<>();
			for(Long link: nodeLinks) {
				if (sol.containsKey(String.valueOf(link))) {
					usedColors.add(sol.get(String.valueOf(link)));
				}
			}
			String chosenColour = null;
			for(int i = 0; i < colours.length; i++) {
				if (!usedColors.contains(colours[i])) {
					chosenColour = colours[i];
					break;
				}
			}
			if (chosenColour !=null) {
				sol.put(node, chosenColour);
			}
		}
		
		return sol;
	}
	

}
