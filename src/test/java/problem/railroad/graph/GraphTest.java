package problem.railroad.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import problem.railroad.graph.Graph;
import problem.railroad.graph.GraphFactory;

public class GraphTest {

	private static final String graphString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	private static Graph graph;

	@BeforeClass
	public static void setup() {
		graph = GraphFactory.generateFromString(graphString);
		System.out.println(graph.toString());
	}
	
	@Test
	public void countRoute1() {
		
		Integer result = graph.calculateDiferentRoutes("C", "C", 30);
		assertEquals(Integer.valueOf(7), result);
	}
	
	@Test
	public void countRoute2() {
		
		Integer result = graph.calculateDiferentRoutes("A", "A", 30);
		assertEquals(Integer.valueOf(0), result);
	}
	
	@Test
	public void countRoute3() {
		
		Integer result = graph.calculateDiferentRoutes("A", "C", 14);
		assertEquals(Integer.valueOf(2), result);
	}
	
	@Test
	public void countRoute4() {
		
		Integer result = graph.calculateDiferentRoutes("A", "C", 15);
		assertEquals(Integer.valueOf(3), result);
	}
	
	@Test
	public void countTripCtoC() { 
		Integer result = graph.calculatePossibleTrips("C", "C", 3);
		assertEquals(Integer.valueOf(2), result);
	}
	
	@Test
	public void countTripAtoC() { 
		Integer result = graph.calculatePossibleTrips("A", "C", 4, true);
		assertEquals(Integer.valueOf(3), result);
	}


	@Test
	public void calculateRoute1() {
		String[] route1 = new String[] { "A", "B", "C" };

		Integer result = graph.calculateRoute(route1);
		assertEquals(Integer.valueOf(9), result);
	}

	@Test
	public void calculateRoute2() {
		String[] route2 = new String[] { "A", "D" };

		Integer result = graph.calculateRoute(route2);
		assertEquals(Integer.valueOf(5), result);
	}

	@Test
	public void calculateRoute3() {
		String[] route3 = new String[] { "A", "D", "C" };

		Integer result = graph.calculateRoute(route3);
		assertEquals(Integer.valueOf(13), result);
	}

	@Test
	public void calculateRoute4() {
		String[] route4 = new String[] { "A", "E", "B", "C", "D" };

		Integer result = graph.calculateRoute(route4);
		assertEquals(Integer.valueOf(22), result);
	}

	@Test
	public void calculateRoute5() {
		String[] route5 = new String[] { "A", "E", "D" };

		Integer result = graph.calculateRoute(route5);
		assertNull(result);
	}
}
