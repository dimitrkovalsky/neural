package com.liberty.neuro;

import java.util.function.Function;

/**
 * @author Dmytro_Kovalskyi.
 * @since 29.09.2016.
 */
public interface ActivationFunctions {

  double DEFAULT_HEAVISIDE_THRESHOLD = 0.5;
  double DEFAULT_SIGMOID_ALPHA = 0.5;

  static Function<Double, Double> heavisideFunction(double threshold) {
    return x -> x >= threshold ? 1d : 0d;
  }

  static Function<Double, Double> heavisideFunction() {
    return heavisideFunction(DEFAULT_HEAVISIDE_THRESHOLD);
  }

  static Function<Double, Double> linearFunction() {
    return x -> {
      double out;
      if (x <= 0) {
        out = 0;
      } else if (x >= 1) {
        out = 1;
      } else {
        out = x;
      }
      return out;
    };
  }

  static Function<Double, Double> sigmoidFunction(double alpha) {
    return x -> 1 / (1 + Math.exp(-alpha * x));
  }

  static Function<Double, Double> sigmoidFunction() {
    return sigmoidFunction(DEFAULT_SIGMOID_ALPHA);
  }
}
