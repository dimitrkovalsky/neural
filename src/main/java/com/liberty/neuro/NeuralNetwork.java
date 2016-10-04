package com.liberty.neuro;

import java.util.List;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public interface NeuralNetwork {

  List<Long> evaluateOutput(List<Long> input);

  long train(List<TrainingPattern> patterns);

}
