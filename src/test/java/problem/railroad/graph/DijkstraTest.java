package problem.railroad.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import problem.railroad.graph.Dijkstra;
import problem.railroad.graph.Graph;
import problem.railroad.graph.GraphFactory;

public class DijkstraTest {
	
	private static final String graphString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	private static Graph graph;
	
	private Dijkstra algorithm;
	
	@BeforeClass
	public static void setup() {
		graph = GraphFactory.generateFromString(graphString);
    	System.out.println(graph.toString());
	}
	
	@Before
	public void beforeAlgorithm() {
		this.algorithm = new Dijkstra(graph);
	}
	
	@Test
	public void testAtoC() {
		
		Integer distance = graph.getShortDistance("A", "C");
    	assertEquals(Integer.valueOf(9), distance);
	}
	
	@Test
	public void testBtoB() {
		    	
		Integer distance = graph.getShortDistance("B", "B");
    	
    	assertEquals(Integer.valueOf(9), distance);
	}
	
	@Test
	public void testCtoC() {
		    	
    	this.algorithm.run("C");
    	
    	List<String> paths = algorithm.getPath("C");
    	
    	assertEquals(Integer.valueOf(9), graph.calculateRoute(paths.toArray(new String[paths.size()])));
	}
	
	@Test
	public void testBtoE() {
		    	
    	this.algorithm.run("B");
    	
    	List<String> paths = algorithm.getPath("E");
    	
    	assertEquals(Integer.valueOf(6), graph.calculateRoute(paths.toArray(new String[paths.size()])));
	}
	
	@Test
	public void testBtoD() {
		    	
		Integer distance = graph.getShortDistance("B", "D");
    	
    	assertEquals(Integer.valueOf(12), distance);
	}
	
	@Test
	public void testAtoA() {
		    	
		Integer distance = graph.getShortDistance("A", "A");
    	
    	assertNull(distance);
	}
}
