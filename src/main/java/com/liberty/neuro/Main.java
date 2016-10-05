package com.liberty.neuro;

import com.liberty.neuro.builder.NeuralLayerBuilder;
import com.liberty.neuro.builder.NeuralNetworkBuilder;
import com.liberty.neuro.network.NeuralNetwork;
import com.liberty.neuro.visual.NetworkGraph;

/**
 * @author Dmytro_Kovalskyi.
 * @since 05.10.2016.
 */
public class Main {

  public static void main(String[] args) {
    NeuralNetwork network = new NeuralNetworkBuilder()
        .setInputAmount(2)
        .setOutputAmount(1)
        .addLayer(new NeuralLayerBuilder().setNeuronAmount(2).build())
        .addLayer(new NeuralLayerBuilder().setNeuronAmount(2).build())
        .build();

    NetworkGraph.draw(network);
    //network.evaluateOutput()
  }
}
