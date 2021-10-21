package org.example.interview.game.component;

import java.util.function.Consumer;
import java.util.function.Supplier;

/** A class which will act as a player. */
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

  /** THis will provide a new player whenever is called. */
  public static Supplier<Player> instance = Player::new;

  /** Unique id of the player */
  public Supplier<Integer> id = () -> _id;

  /** Dice is being assigned to play. */
  public Consumer<Dice> passDice = (dice) -> _dice = dice;

  /** Player rolled the dice. */
  public Supplier<Integer> roll =
      () -> {
        if (_dice != null) {
          _currentPosition = _currentPosition + _dice.nextNumber.get();
        }
        return _currentPosition;
      };

  /** Returns the current position of the player. */
  public Supplier<Integer> currentPosition = () -> _currentPosition;

  /** this will move the player to the given position. */
  public Consumer<Integer> moveTo = newPostion -> _currentPosition = newPostion;
}
