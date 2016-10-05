package com.liberty.neuro.builder;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dmytro_Kovalskyi.
 * @since 04.10.2016.
 */
public class Counter {

  private static AtomicLong neuronCounter = new AtomicLong(0);
  private static AtomicInteger neuronLayerCounter = new AtomicInteger(0);
  private static AtomicLong connectionCounter = new AtomicLong(0);

  public static long getNextNeuronId() {
    return neuronCounter.incrementAndGet();
  }

  public static long getConnectionId() {
    return connectionCounter.incrementAndGet();
  }

  public static int getNextLayerId() {
    return neuronLayerCounter.incrementAndGet();
  }
}
