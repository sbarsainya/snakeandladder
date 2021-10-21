package org.example.interview.game.component;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Player {

  private int _currentPosition;
  private int _id;
  private Dice _dice;
  private static int _count = 1;

  private Player() {
    _currentPosition = 0;
    _id = _count;
    _count++;
  }

  public static Supplier<Player> instance = Player::new;

  public Supplier<Integer> id = () -> _id;

  public Consumer<Dice> passDice = (dice) -> _dice = dice;

  public Supplier<Integer> roll =
      () -> {
        if (_dice != null) {
          _currentPosition = _currentPosition + _dice.nextNumber.get();
        }
        return _currentPosition;
      };
}
