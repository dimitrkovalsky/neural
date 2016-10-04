package com.liberty.neuro.builder;

import com.liberty.neuro.NeuralNetwork;
import com.liberty.neuro.NeuronLayer;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public interface NetworkBuilder {

  NetworkBuilder addLayer(NeuronLayer layer);

  NeuralNetwork build();
}
