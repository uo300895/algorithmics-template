package s4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class greedy_times {
	
	public static void main(String[] args) {
		JSONParser parser;
		long timeElapsed = 0;
		for (int i = 8; i < 65537; i*=2) {
			parser = new JSONParser();
			try (FileReader reader = new FileReader("sol/g"+i+".json")) {
				JSONObject jsonObject = (JSONObject) parser.parse(reader);
				@SuppressWarnings("unchecked")
				Map<String, List<Long>> graph = (Map<String, List<Long>>) jsonObject.get("graph");
				
				long start = System.nanoTime();
				Map<String, String> solution;
				for (int j = 0; j<10; j++)
					solution = GraphColouring.greedy(graph);
				long end = System.nanoTime();
				timeElapsed = (end - start) / 1_000_000;
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			System.out.print("n= "+i+", time= "+ timeElapsed+"\n");
		}
	}
}
