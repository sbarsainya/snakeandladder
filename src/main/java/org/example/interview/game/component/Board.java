package org.example.interview.game.component;

import java.util.List;

/**
 * It is main class, which will start the configuration for the game.
 * */
public class Board {

  private static Board board;

  private List<Player> players;
  private Dice dice;
  private List<Element> elements;
  private Player winner;

  private Board() {}

  static Board getInstance() {
    if (board == null) {
      board = new Board();
    }
    return board;
  }
}
