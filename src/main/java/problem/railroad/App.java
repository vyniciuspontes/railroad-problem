package problem.railroad;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
    	String filePath = "src/main/resources/input.txt";
    	
    	String noRoutesMessage = "NO SUCH ROUTE";
    	
		Graph graph = GraphFactory.generateFromFile(filePath);

		System.out.println(graph.toString());
		
		String[] route1 = new String[] { "A", "B", "C" };
		String output1 = graph.calculateRoute(route1) == null ? noRoutesMessage : graph.calculateRoute(route1).toString();
		System.out.println("Output #1: " + output1);
		
		String[] route2 = new String[] { "A", "D" };
		String output2 = graph.calculateRoute(route2) == null ? noRoutesMessage : graph.calculateRoute(route2).toString();
		System.out.println("Output #2: " + output2);
		
		String[] route3 = new String[] { "A", "D", "C" };
		String output3 = graph.calculateRoute(route3) == null ? noRoutesMessage : graph.calculateRoute(route3).toString();
		System.out.println("Output #3: " + output3);
		
		String[] route4 = new String[] { "A", "E", "B", "C", "D" };
		String output4 = graph.calculateRoute(route4) == null ? noRoutesMessage : graph.calculateRoute(route4).toString();
		System.out.println("Output #4: " + output4);
		
		String[] route5 = new String[] { "A", "E", "D" };
		String output5 = graph.calculateRoute(route5) == null ? noRoutesMessage : graph.calculateRoute(route5).toString();
		System.out.println("Output #5: " + output5);
		
		Integer output6 = graph.calculatePossibleTrips("C", "C", 3);
		System.out.println("Output #6: " + output6);

		Integer output7 = graph.calculatePossibleTrips("A", "C", 4, true);
		System.out.println("Output #6: " + output7);
		
		
		Dijkstra algorithm = new Dijkstra(graph);
		
		algorithm.run("A");
		List<String> pathAtoC = algorithm.getPath("C");
		String output8 = pathAtoC != null ? graph.calculateRoute(pathAtoC.toArray(
				new String[pathAtoC.size()])).toString() : noRoutesMessage;
		System.out.println("Output #8: " + output8);
		
		algorithm.run("C");
		List<String> pathCtoC = algorithm.getPath("C");
		String output9 = pathCtoC != null ? graph.calculateRoute(pathCtoC.toArray(
				new String[pathCtoC.size()])).toString() : noRoutesMessage;
		System.out.println("Output #9: " + output9);
		
		String output10 = graph.calculateDiferentRoutes("C", "C", 30).toString();
		System.out.println("Output #10: " + output10);
    }
}
