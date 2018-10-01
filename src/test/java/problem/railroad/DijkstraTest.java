package problem.railroad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import problem.railroad.Dijkstra;
import problem.railroad.Graph;
import problem.railroad.GraphFactory;

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
    	
    	this.algorithm.run("A");
    	
    	List<String> paths = algorithm.getPath("C");
    	
    	assertEquals(Integer.valueOf(9), graph.calculateRoute(paths.toArray(new String[paths.size()])));
	}
	
	@Test
	public void testBtoB() {
		    	
    	this.algorithm.run("B");
    	
    	List<String> paths = algorithm.getPath("B");
    	
    	assertEquals(Integer.valueOf(9), graph.calculateRoute(paths.toArray(new String[paths.size()])));
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
		    	
    	this.algorithm.run("B");
    	
    	List<String> paths = algorithm.getPath("D");
    	
    	assertEquals(Integer.valueOf(12), graph.calculateRoute(paths.toArray(new String[paths.size()])));
	}
	
	@Test
	public void testAtoA() {
		    	
    	this.algorithm.run("A");
    	
    	List<String> paths = algorithm.getPath("A");
    	
    	assertNull(paths);
	}
}
