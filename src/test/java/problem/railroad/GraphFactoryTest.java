package problem.railroad;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import problem.railroad.Graph;
import problem.railroad.GraphFactory;

public class GraphFactoryTest {

	@Test
	public void testGenerateGraphFromFile() {

		String filePath = "src/main/resources/input.txt";
		Graph graph = GraphFactory.generateFromFile(filePath);
		System.out.println(graph.toString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testGraphStringNotValid() {
		Graph graph = GraphFactory.generateFromString("not working string");
		assertNull(graph);
	}
}
