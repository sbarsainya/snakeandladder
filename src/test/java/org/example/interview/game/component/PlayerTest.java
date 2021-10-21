package org.example.interview.game.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  @Test
  public void shouldCreateAPlayer() {
    assertNotNull(Player.instance.get());
  }

  @Test
  public void shouldReturnANewPlayerEveryTime() {
    Player player1 = Player.instance.get();
    Player player2 = Player.instance.get();
    assertNotEquals(player1, player2);
  }

  @Test
  public void shouldHaveAUniqueId() {
    Player player1 = Player.instance.get();
    Player player2 = Player.instance.get();
    assertNotEquals(player1.id.get(), player2.id.get());
  }

  @Test
  public void shouldRollADiceWhenDiceIsAssigned() {
    Dice dice = Dice.instance.get();
    Player player1 = Player.instance.get();
    player1.passDice.accept(dice);
    assertTrue(player1.roll.get() > 0);
  }

  @Test
  public void shouldNotBeAbleToPlayWhenNoDiceIsAssigned() {
    Player player1 = Player.instance.get();
    assertEquals(0, (int) player1.roll.get());
  }
}
