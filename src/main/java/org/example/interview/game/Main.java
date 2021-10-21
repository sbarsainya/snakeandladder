package org.example.interview.game;

import org.example.interview.game.component.Board;

/**
 * Main method to start the game.
 * */
public class Main {
  public static void main(String[] args) {
    Board board = Board.instance.get();
    board.play();
  }
}
