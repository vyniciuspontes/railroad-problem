# Railroad Problem

## Instructions
At folder **railroad-problem/src/main/resources** there is a file called **input.txt** where it's possible to set the graph values.

Dependencies: 
 - Maven 
 - Java 1.8+

To execute the code, at the root folder type:

```
mvn package
java -cp target/railroad-1.0-SNAPSHOT.jar problem.railroad.App
```

## About the code

As the Railroad problem is similar to graph problems the key class of the code is **Graph**. The Graph class manages a data structure called **nodes** wich is a map containing the node, his neigbors and their weigh. As the class Graph holds the data structure all the logic and calculations done are concentrated inside of it (The Graph class).

The classe **Dijkstra** holds the algorithm that find shortest routes. As the algorithm has several data structures and their own properties I decided to keep it in a separated classe.

There is a class called **GraphFactory** wich is responsible for generating the graph based on a String or on a file.
