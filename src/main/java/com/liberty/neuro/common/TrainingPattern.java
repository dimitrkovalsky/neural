package com.liberty.neuro.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Dmytro_Kovalskyi.
 * @since 29.09.2016.
 */
@Data
@AllArgsConstructor
public class TrainingPattern {

  private List<Double> inputs;
  private Double out;
}
