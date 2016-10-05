package com.liberty.neuro.builder;

import com.liberty.neuro.layer.NeuronLayer;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public interface LayerBuilder {

//  LayerBuilder addNeuron(Neuron neuron);
//
//  LayerBuilder addNeuronConnection(long fromId, long toId);

  LayerBuilder setNeuronAmount(int neuronAmount);

  NeuronLayer build();
}
