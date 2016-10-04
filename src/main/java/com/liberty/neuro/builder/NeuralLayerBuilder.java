package com.liberty.neuro.builder;

import com.liberty.neuro.Neuron;
import com.liberty.neuro.NeuronLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public class NeuralLayerBuilder implements LayerBuilder {

  private List<Neuron> neurons;
  private int neuronAmount;
  private Function<Double, Double> activationFunction;


  @Override
  public LayerBuilder setNeuronAmount(int neuronAmount) {
    neurons = new ArrayList<>();
    IntStream.range(0, neuronAmount).forEach(x -> neurons.add(createNeuron()));
    return this;
  }

  public LayerBuilder setActivationFunction(Function<Double, Double> activationFunction) {
    this.activationFunction = activationFunction;
    neurons.forEach(x -> x.setActivationFunction(activationFunction));
    return this;
  }

  private Neuron createNeuron() {
    return new Neuron(Counter.getNextNeuronId(), Counter.getNextLayerId());
  }


  @Override
  public NeuronLayer build() {
    return null;
  }
}
