package com.liberty.neuro.network;

import java.util.Arrays;

public class Perceptron {

  double[] inputs;
  double out;
  double[] weights;

  double[][] patterns = {
      {0, 0, 0},
      {0, 1, 1},
      {1, 0, 1},
      {1, 1, 1}
  };

  public Perceptron() {
    inputs = new double[2];
    weights = new double[inputs.length];
    for (int i = 0; i < weights.length; i++) {
      weights[i] = Math.random() * 0.2 + 0.1;
    }
  }


  void evaluateOutput() {
    out = 0;
    for (int i = 0; i < inputs.length; i++) {
      out += inputs[i] * weights[i];
    }
    out = activation(out);
  }

  public void learn() {
    double globalError;
    int count = 0;
    do {
      count++;
      System.out.println("Trying to train network. Iteration #" + count);
      globalError = 0;
      for (double[] p : patterns) {
        inputs = Arrays.copyOf(p, p.length - 1);
        evaluateOutput();
        double error = p[p.length - 1] - out;
        globalError += Math.abs(error);
        for (int i = 0; i < inputs.length; i++) {
          weights[i] += 0.1 * error * inputs[i];
        }
      }

    } while (globalError != 0);
    System.out.println("Learned in " + count + " iterations");

  }

  private double activation(double out) {
    if (out >= 0.5) {
      return 1;
    }
    return 0;
  }

  public void test() {
    learn();
    for (double[] p : patterns) {
      inputs = Arrays.copyOf(p, p.length - 1);
      evaluateOutput();
      System.out.println(out);
    }
  }

  public static void main(String[] args) {
     new Perceptron().test();
  }
}
