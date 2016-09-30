package com.liberty.neuro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmytro_Kovalskyi.
 * @since 29.09.2016.
 */
@Slf4j
public class Neuron {

  private static final double CORRECTION_COEFFICIENT = 0.01;
  private List<Double> inputs;
  @Getter
  private List<Double> weights;
  private Function<Double, Double> activationFunction;
  private int inputAmount;

  public Neuron(int inputAmount, Function<Double, Double> activationFunction) {
    inputs = new ArrayList<>(inputAmount);
    weights = new ArrayList<>(inputAmount);
    this.inputAmount = inputAmount;
    this.activationFunction = activationFunction;
    initWeights();
  }

  private void initWeights() {
    for (int i = 0; i < inputAmount; i++) {
      weights.add(Math.random() * 0.2 + 0.1);
    }
  }

  public void train(List<TrainingPattern> patterns) {
    double globalError;
    int count = 0;
    do {
      count++;
      log.info("Trying to train network. Iteration #" + count);
      globalError = 0;
      for (TrainingPattern p : patterns) {
        validateInputs(p);
        inputs = p.getInputs();
        double out = evaluateOutput();
        double error = p.getOut() - out;
        globalError += Math.abs(error);
        for (int i = 0; i < inputs.size(); i++) {
          weights.set(i, weights.get(i) + CORRECTION_COEFFICIENT * error * inputs.get(i));
        }
      }

    } while (globalError != 0);
    log.info("Learned in " + count + " iterations");

  }

  private void validateInputs(TrainingPattern p) {
    if (p.getInputs() == null || p.getInputs().size() != inputAmount) {
      throw new IllegalArgumentException("Training set size is incorrect : " +
          p.getInputs().size());
    }
  }

  public double evaluate(List<Double> inputs) {
    this.inputs = inputs;
    return evaluateOutput();
  }

  public double evaluate(Double... inputs) {
    if (inputs.length != inputAmount) {
      throw new IllegalArgumentException("Can not evaluate wrong parameter amount. Should be : " +
          inputAmount);
    }
    this.inputs = Arrays.asList(inputs);
    return evaluateOutput();
  }

  private double evaluateOutput() {
    double out = 0;
    for (int i = 0; i < inputs.size(); i++) {
      out += inputs.get(i) * weights.get(i);
    }
    out = activation(out);
    return out;
  }

  private double activation(double out) {
    return activationFunction.apply(out);
  }
}
