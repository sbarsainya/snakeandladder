package org.example.interview.game.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

  @Test
  public void shouldReturnDice() {
    assertNotNull(Dice.instance.get());
  }

  @Test
  public void shouldReturnSingletonDice() {
    Dice dice1 = Dice.instance.get();
    Dice dice2 = Dice.instance.get();
    assertEquals(dice1, dice2);
  }

  @Test
  public void shouldGenerateNewNumber() {
    Dice dice = Dice.instance.get();
    assertTrue(dice.nextNumber.get() <= 6);
    assertTrue(dice.nextNumber.get() >= 1);
  }
}
