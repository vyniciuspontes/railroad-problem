package problem.railroad.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import problem.railroad.exceptions.InvalidFileContentException;

public class GraphFactory {

	static private String regex = "([A-Z])([A-Z])(\\d)";
	static private Pattern pattern = Pattern.compile(regex);

	static public Graph generateFromString(String graphString) {

		if (graphString == null || graphString.isEmpty())
			return null;

		Graph graph = new Graph();

		Matcher matcher = pattern.matcher(graphString);

		while (matcher.find()) {
			String firstNodeName = matcher.group(1);
			String secondNodeName = matcher.group(2);
			String weight = matcher.group(3);

			graph.addNeighbor(firstNodeName, secondNodeName, Integer.parseInt(weight));
		}

		if (graph.getNodes().isEmpty()) {
			throw new IllegalArgumentException("Graph string is not valid");
		}
		
		return graph;
	}

	static public Graph generateFromFile(String filePath) {

		File file = new File(filePath);
		Scanner scanner;
		try {
			scanner = new Scanner(file);

			String fileContent;

			if (scanner.hasNextLine()) {
				fileContent = scanner.nextLine();
				scanner.close();

				return generateFromString(fileContent.trim());
			} else {

				scanner.close();
				throw new InvalidFileContentException("The file " + file.getName() + " has no valid content");
			}
		} catch (FileNotFoundException | InvalidFileContentException | IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
	}

}
