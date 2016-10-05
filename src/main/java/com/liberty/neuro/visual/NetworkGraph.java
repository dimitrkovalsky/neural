package com.liberty.neuro.visual;

import com.liberty.neuro.layer.NeuronLayer;
import com.liberty.neuro.network.NeuralNetwork;
import com.liberty.neuro.network.NeuralNetworkImpl;
import com.liberty.neuro.neuron.AbstractNeuron;
import com.liberty.neuro.neuron.InputNeuron;
import com.liberty.neuro.neuron.OutputNeuron;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.List;
import java.util.Map;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
public class NetworkGraph {

  public static void draw(NeuralNetworkImpl network) {
    System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
    Graph graph = new SingleGraph("Neural network");
    graph.addAttribute("ui.stylesheet",
        "url('file:///D:/github/neural/src/main/resources/styles.css')");
    List<InputNeuron> inputs = network.getInputLayer().getNeurons();
    addInputs(graph, inputs);
    addLayers(graph, network.getLayers());
    addOutput(graph, network.getOutputLayer().getNeurons());
    addConnections(graph, network);
    graph.display();
  }

  private static void addConnections(Graph graph, NeuralNetworkImpl network) {
    Map<Long, Map.Entry<Long, Long>> connections = network.getConnections();
    Map<Long, AbstractNeuron> neurons = network.getAllNeurons();
    connections.values()
        .forEach(e -> addConnection(graph, neurons.get(e.getKey()), neurons.get(e.getValue())));
  }

  private static void addConnection(Graph graph, AbstractNeuron from, AbstractNeuron to) {
    graph.addEdge(getEdgeId(from, to), getNodeId(from), getNodeId(to), true);
  }

  private static String getEdgeId(AbstractNeuron from, AbstractNeuron to) {
    return from.getId() + "-" + to.getId();
  }

  private static void addOutput(Graph graph, List<OutputNeuron> neurons) {
    neurons.forEach(n -> {
      Node node = graph.addNode(getNodeId(n));
      node.addAttribute("ui.class", "out");
      node.addAttribute("ui.label", "Out");
    });
  }

  private static void addLayers(Graph graph, List<NeuronLayer> layers) {
    layers.stream()
        .flatMap(l -> l.getNeurons().stream())
        .forEach(n -> {
          Node node = graph.addNode(getNodeId(n));
          node.addAttribute("ui.label", "#" + n.getId());
        });
  }

  private static void addInputs(Graph graph, List<InputNeuron> inputs) {
    inputs.forEach(i -> {
      Node node = graph.addNode(getNodeId(i));
      node.addAttribute("ui.class", "in");
      node.addAttribute("ui.label", "In");
    });
  }

  private static String getNodeId(AbstractNeuron i) {
    return i.getId().toString();
  }

  public static void draw(NeuralNetwork network) {
    draw((NeuralNetworkImpl) network);
  }
}
