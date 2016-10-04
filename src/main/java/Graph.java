import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomEuclideanGenerator;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * @author Dmytro_Kovalskyi.
 * @since 30.09.2016.
 */
public class Graph {
  public static void main(String args[]) {
    SingleGraph graph = new SingleGraph("random euclidean");
    Generator gen = new RandomEuclideanGenerator();
    gen.addSink(graph);
    gen.begin();
    for(int i=0; i<1000; i++) {
      gen.nextEvents();
    }
    gen.end();
    graph.display(false);
  }

  public static void main2(String args[]) {
    SingleGraph graph = new SingleGraph("Tutorial 1");

    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addEdge("AB", "A", "B");
    graph.addEdge("BC", "B", "C");
    graph.addEdge("CA", "C", "A");

    graph.display();
  }
}
