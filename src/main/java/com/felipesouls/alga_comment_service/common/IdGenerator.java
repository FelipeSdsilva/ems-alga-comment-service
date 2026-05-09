package com.felipesouls.alga_comment_service.common;

import java.util.UUID;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochRandomGenerator;

public class IdGenerator {

  private static final TimeBasedEpochRandomGenerator TIME_BASED_EPOCH_RANDOM_GENERATOR = Generators.timeBasedEpochRandomGenerator();

  public IdGenerator() {
  }

  public static UUID generateTimeBasedUUID() {
    return TIME_BASED_EPOCH_RANDOM_GENERATOR.generate();
  }
}
