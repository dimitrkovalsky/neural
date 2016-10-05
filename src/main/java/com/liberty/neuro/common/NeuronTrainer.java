package com.liberty.neuro.common;

import com.liberty.neuro.neuron.Neuron;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Dmytro_Kovalskyi.
 * @since 30.09.2016.
 */
@AllArgsConstructor
@Slf4j
public class NeuronTrainer {

  private String fileName;
  private static String TRAINING_SET_DIR = "D:\\github\\neural\\training";

  public void train(Neuron neuron) {
    log.info("Started training...");
    List<TrainingPattern> patterns = readTrainingSet();
    neuron.train(patterns);
  }

  private List<TrainingPattern> readTrainingSet() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(TRAINING_SET_DIR, fileName));
      return lines.stream().map(s -> {
        String[] split = s.split(":");
        double out = Double.parseDouble(split[1]);
        List<Double> input = Arrays.stream(split[0].split(","))
            .map(Double::parseDouble)
            .collect(Collectors.toList());
        return new TrainingPattern(input, out);
      }).collect(Collectors.toList());

    } catch (Exception e) {
      log.error(e.getMessage());
      throw new IllegalArgumentException(e);
    }
  }
}
