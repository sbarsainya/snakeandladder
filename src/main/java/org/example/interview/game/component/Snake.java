package org.example.interview.game.component;

import org.example.interview.game.common.ElementBehavior;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/** A class to represent a Snake which will take the user to lower position. */
public class Snake implements Element {

  private static final ElementBehavior _BEHAVIOR = ElementBehavior.SNAKE;

  private int _id;
  private int _startPosition;
  private int _endPosition;
  private static int _count = 1;

  private Snake(int start, int end) {
    _id = _count;
    _startPosition = start;
    _endPosition = end;
    _count++;
  }

  private static final BiFunction<Integer, Integer, Boolean> validation =
      (start, end) -> start > 1 && end > 1 && start < 100 && end < 100 && start > end;

  public static Supplier<ElementBehavior> behavior = () -> _BEHAVIOR;

  public static BiFunction<Supplier<Integer>, Supplier<Integer>, Snake> instance =
      (start, end) -> {
        if (validation.apply(start.get(), end.get())) return new Snake(start.get(), end.get());
        else
          throw new RuntimeException(
              "Can't instantiate Game because Snake is not behaving properly");
      };

  public Supplier<Integer> start = () -> _startPosition;
  public Supplier<Integer> end = () -> _endPosition;
  public Supplier<Integer> id = () -> _id;

  /** @return */
  @Override
  public ElementBehavior getBehavior() {
    return behavior.get();
  }

  /** @return */
  @Override
  public int getStart() {
    return start.get();
  }

  /** @return */
  @Override
  public int getEnd() {
    return end.get();
  }
}
