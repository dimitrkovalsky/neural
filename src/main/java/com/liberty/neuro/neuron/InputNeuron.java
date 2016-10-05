package com.liberty.neuro.neuron;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
@Data
public class InputNeuron implements AbstractNeuron {

  private Long id;
  private Double input = 0.0;

  public InputNeuron(long id) {
    this.id = id;
  }

  @Override
  public NeuronType getNeuronType() {
    return NeuronType.INPUT_NEURON;
  }
}
