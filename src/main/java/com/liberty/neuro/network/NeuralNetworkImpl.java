package com.liberty.neuro.network;

import com.liberty.neuro.common.TrainingPattern;
import com.liberty.neuro.layer.InputLayer;
import com.liberty.neuro.layer.NeuronLayer;
import com.liberty.neuro.layer.OutputLayer;
import com.liberty.neuro.neuron.AbstractNeuron;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
@Data
public class NeuralNetworkImpl implements NeuralNetwork {

  private InputLayer inputLayer;
  private OutputLayer outputLayer;
  private List<NeuronLayer> layers;
  private Map<Long, Map.Entry<Long, Long>> connections;
  private Map<Long, AbstractNeuron> allNeurons;

  @Override
  public List<Long> evaluateOutput(List<Long> input) {
    return null;
  }

  @Override
  public long train(List<TrainingPattern> patterns) {
    return 0;
  }
}
