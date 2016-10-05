package com.liberty.neuro.neuron;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
@Data
public class OutputNeuron implements AbstractNeuron {

  private Long id;
  private Double output = 0.0;

  public OutputNeuron(long id) {
    this.id = id;
  }

  @Override
  public NeuronType getNeuronType() {
    return NeuronType.OUTPUT_NEURON;
  }
}
