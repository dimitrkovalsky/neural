package com.liberty.neuro.layer;

import com.liberty.neuro.builder.Counter;
import com.liberty.neuro.neuron.InputNeuron;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
@Data
public class InputLayer {

  private int id;
  private List<InputNeuron> neurons;

  public InputLayer(int id, int inputs) {
    this.id = id;
    neurons = Stream.generate(() -> new InputNeuron(Counter.getNextNeuronId()))
        .limit(inputs)
        .collect(Collectors.toList());
  }

}
