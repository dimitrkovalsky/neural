package com.liberty.neuro.layer;

import com.liberty.neuro.builder.Counter;
import com.liberty.neuro.neuron.OutputNeuron;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
@Data
public class OutputLayer {

  private int id;
  private List<OutputNeuron> neurons;

  public OutputLayer(int id, int outputs) {
    this.id = id;
    neurons = Stream.generate(() -> new OutputNeuron(Counter.getNextNeuronId()))
        .limit(outputs)
        .collect(Collectors.toList());
  }
}
