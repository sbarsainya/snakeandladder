package org.example.interview.game.component;

import org.example.interview.game.common.ElementBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadderTest {
  @Test
  public void shouldCreateANewLadder() {
    Element ladder = Ladder.instance.apply(() -> 2, () -> 5);
    assertNotNull(ladder);
  }

  @Test
  public void shouldHaveADownBehavior() {
    assertEquals(Ladder.behavior.get(), ElementBehavior.LADDER);
  }

  @Test
  public void shouldNotReturnSameLadder() {
    Element ladder1 = Ladder.instance.apply(() -> 2, () -> 5);
    Element ladder2 = Ladder.instance.apply(() -> 2, () -> 5);
    assertNotEquals(ladder1, ladder2);
  }

  @Test
  public void shouldReturnStartAndEnd() {
    Ladder ladder = Ladder.instance.apply(() -> 2, () -> 5);
    assertNotEquals(ladder.start, 2);
    assertNotEquals(ladder.end, 5);
  }

  @Test
  public void shouldNotCreateLadderWhenStartAndEndPositionsAreNotCorrect() {
    assertThrows(RuntimeException.class, () -> Ladder.instance.apply(() -> null, () -> null));
    assertThrows(RuntimeException.class, () -> Ladder.instance.apply(() -> 2, () -> null));
    assertThrows(RuntimeException.class, () -> Ladder.instance.apply(() -> null, () -> 3));
    assertThrows(RuntimeException.class, () -> Ladder.instance.apply(() -> 1, () -> 10));
    assertThrows(RuntimeException.class, () -> Ladder.instance.apply(() -> 98, () -> 2));
  }
}
