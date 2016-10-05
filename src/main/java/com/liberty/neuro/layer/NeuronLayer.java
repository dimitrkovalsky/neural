package com.liberty.neuro.layer;

import com.liberty.neuro.neuron.Neuron;

import java.util.List;

import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 03.10.2016.
 */
@Data
public class NeuronLayer {

  private long id;
  private List<Neuron> neurons;
}
