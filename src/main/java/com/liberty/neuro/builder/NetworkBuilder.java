package com.liberty.neuro.builder;

import com.liberty.neuro.network.NeuralNetwork;
import com.liberty.neuro.layer.NeuronLayer;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public interface NetworkBuilder {

  NetworkBuilder setInputAmount(int inputs);

  NetworkBuilder setOutputAmount(int outputs);

  NetworkBuilder addLayer(NeuronLayer layer);

  NeuralNetwork build();
}
