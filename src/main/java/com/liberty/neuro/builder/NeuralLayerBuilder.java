package com.liberty.neuro.builder;

import com.liberty.neuro.neuron.Neuron;
import com.liberty.neuro.layer.NeuronLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public class NeuralLayerBuilder implements LayerBuilder {

  private int neuronAmount;
  private Function<Double, Double> activationFunction;


  @Override
  public LayerBuilder setNeuronAmount(int neuronAmount) {
    this.neuronAmount = neuronAmount;
    return this;
  }

  public LayerBuilder setActivationFunction(Function<Double, Double> activationFunction) {
    this.activationFunction = activationFunction;
    return this;
  }

  private Neuron createNeuron(int layerId) {
    Neuron neuron = new Neuron(Counter.getNextNeuronId(), layerId);
    neuron.setActivationFunction(activationFunction);
    return neuron;
  }

  @Override
  public NeuronLayer build() {
    int layerId = Counter.getNextLayerId();
    List<Neuron> neurons = new ArrayList<>(neuronAmount);
    IntStream.range(0, neuronAmount).forEach(x -> neurons.add(createNeuron(layerId)));
    NeuronLayer layer = new NeuronLayer();
    layer.setId(layerId);
    layer.setNeurons(neurons);
    return layer;
  }
}
