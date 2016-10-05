package com.liberty.neuro.builder;

import com.liberty.neuro.layer.InputLayer;
import com.liberty.neuro.layer.NeuronLayer;
import com.liberty.neuro.layer.OutputLayer;
import com.liberty.neuro.network.NeuralNetwork;
import com.liberty.neuro.network.NeuralNetworkImpl;
import com.liberty.neuro.neuron.AbstractNeuron;
import com.liberty.neuro.neuron.InputNeuron;
import com.liberty.neuro.neuron.Neuron;
import com.liberty.neuro.neuron.OutputNeuron;

import org.apache.commons.collections4.CollectionUtils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public class NeuralNetworkBuilder implements NetworkBuilder {

  private int inputs;
  private int outputs;
  private List<NeuronLayer> layers = new ArrayList<>();
  Map<Long, Map.Entry<Long, Long>> connections = new HashMap<>();


  @Override
  public NetworkBuilder setInputAmount(int inputs) {
    this.inputs = inputs;
    return this;
  }

  @Override
  public NetworkBuilder setOutputAmount(int outputs) {
    this.outputs = outputs;
    return this;
  }

  @Override
  public NetworkBuilder addLayer(NeuronLayer layer) {
    layers.add(layer);
    return this;
  }

  @Override
  public NeuralNetwork build() {
    validate();
    NeuralNetworkImpl network = new NeuralNetworkImpl();
    network.setInputLayer(new InputLayer(Counter.getNextLayerId(), inputs));
    network.setOutputLayer(new OutputLayer(Counter.getNextLayerId(), outputs));
    network.setLayers(layers);
    network.setConnections(createConnections(network));

    network.setAllNeurons(getAllNeurons(network));
    return network;
  }

  private Map<Long, AbstractNeuron> getAllNeurons(NeuralNetworkImpl network) {
    Map<Long, AbstractNeuron> allNeurons = new HashMap<>();
    network.getInputLayer().getNeurons()
        .forEach(n -> allNeurons.put(n.getId(), n));
    network.getOutputLayer().getNeurons()
        .forEach(n -> allNeurons.put(n.getId(), n));
    network.getLayers().stream()
        .flatMap(l -> l.getNeurons().stream())
        .forEach(n -> allNeurons.put(n.getId(), n));
    return allNeurons;
  }

  private void validate() {
    if (CollectionUtils.isEmpty(layers)) {
      throw new IllegalArgumentException("Neural network can't contains less than one layer");
    }
    if (inputs <= 0) {
      throw new IllegalArgumentException("Neural network can't contains " + inputs + " inputs");
    }
    if (outputs <= 0) {
      throw new IllegalArgumentException("Neural network can't contains " + outputs + " outputs");
    }
  }

  private Map<Long, Map.Entry<Long, Long>> createConnections(NeuralNetworkImpl network) {

    List<InputNeuron> inputNeurons = network.getInputLayer().getNeurons();
    List<NeuronLayer> layers = network.getLayers();
    int currentLayerId = 0;
    List<Neuron> lastLayer = layers.get(currentLayerId).getNeurons();
    connectInput(inputNeurons, lastLayer);

    for (int i = 1; i < layers.size(); i++) {
      List<Neuron> currentLayer = layers.get(i).getNeurons();
      connectLayers(lastLayer, currentLayer);
      lastLayer = currentLayer;
    }

    connectOutput(lastLayer, network.getOutputLayer().getNeurons());
    return connections;
  }

  private void connectLayers(List<Neuron> lastLayer, List<Neuron> currentLayer) {
    lastLayer.forEach(l -> {
      currentLayer.forEach(c -> createConnection(l, c));
    });
  }

  private void connectOutput(List<Neuron> lastLayer, List<OutputNeuron> outputLayer) {
    lastLayer.forEach(l -> {
      outputLayer.forEach(o -> createConnection(l, o));
    });
  }

  private void connectInput(List<InputNeuron> inputNeurons, List<Neuron> lastLayer) {
    inputNeurons.forEach(i -> {
      lastLayer.forEach(f -> createConnection(i, f));
    });
  }

  private void createConnection(AbstractNeuron n1, AbstractNeuron n2) {
    connections.put(Counter.getConnectionId(),
        new AbstractMap.SimpleEntry<>(n1.getId(), n2.getId()));
  }
}
