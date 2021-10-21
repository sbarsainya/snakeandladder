package org.example.interview.game.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  @Test
  public void shouldCreateNewBoard() {
    assertNotNull(Board.instance.get());
  }

  @Test
  public void shouldNotCreateNewBoard() {
    Board board1 = Board.instance.get();
    Board board2 = Board.instance.get();
    assertEquals(board1, board2);
  }

  @Test
  public void shouldHaveFourPlayers() {
    Board board1 = Board.instance.get();
    assertDoesNotThrow(board1::play);
  }
}
