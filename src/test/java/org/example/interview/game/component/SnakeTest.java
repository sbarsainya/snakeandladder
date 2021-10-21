package org.example.interview.game.component;

import org.example.interview.game.common.ElementBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
  @Test
  public void shouldCreateANewSnake() {
    Element snake = Snake.instance.apply(() -> 5, () -> 2);
    assertNotNull(snake);
  }

  @Test
  public void shouldHaveADownBehavior() {
    assertEquals(Snake.behavior.get(), ElementBehavior.SNAKE);
  }

  @Test
  public void shouldNotReturnSameSnake() {
    Element snake1 = Snake.instance.apply(() -> 5, () -> 2);
    Element snake2 = Snake.instance.apply(() -> 5, () -> 2);
    assertNotEquals(snake1, snake2);
  }

  @Test
  public void shouldReturnStartAndEnd() {
    Snake snake1 = Snake.instance.apply(() -> 5, () -> 2);
    assertNotEquals(snake1.start, 5);
    assertNotEquals(snake1.end, 2);
  }

  @Test
  public void shouldNotCreateSnakeWhenStartAndEndPositionsAreNotCorrect() {
    assertThrows(RuntimeException.class, () -> Snake.instance.apply(() -> null, () -> null));
    assertThrows(RuntimeException.class, () -> Snake.instance.apply(() -> 2, () -> null));
    assertThrows(RuntimeException.class, () -> Snake.instance.apply(() -> null, () -> 3));
    assertThrows(RuntimeException.class, () -> Snake.instance.apply(() -> 1, () -> 10));
    assertThrows(RuntimeException.class, () -> Snake.instance.apply(() -> 8, () -> 100));
  }
}
