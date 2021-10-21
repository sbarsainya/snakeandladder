package org.example.interview.game.component;

import java.util.Random;
import java.util.function.Supplier;

public class Dice {

  private static Dice dice;

  private static final int UPPER_BOUND = 6;
  private static final int LOWER_BOUND = 1;
  private Random randomizer;

  private Dice() {
    this.randomizer = new Random();
  }

  public Supplier<Integer> nextNumber =
      () -> randomizer.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;

  public static Supplier<Dice> instance =
      () -> {
        if (dice == null) {
          dice = new Dice();
        }
        return dice;
      };
}
