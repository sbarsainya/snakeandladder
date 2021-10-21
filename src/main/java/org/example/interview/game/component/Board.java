package org.example.interview.game.component;

import org.example.interview.game.common.ElementBehavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** It is main class, which will start the configuration for the game. */
public class Board {

  private static Board board;

  private static final int LOWER_BOUND = 1;
  private static final int UPPER_BOUND = 99;

  private List<Player> _players;
  private Dice _dice;
  private List<Element> _elements;
  private Player _winner;
  private Map<Integer, Integer> elementMap = new HashMap<>();
  private Map<Integer, Element> snakeAndLadderMap = new HashMap<>();
  private Random random = new Random();

  private Board() {
    _players = players.get();
    _dice = Dice.instance.get();
    _elements = elements.get();
  }

  /** To provide the singleton support. */
  public static Supplier<Board> instance =
      () -> {
        if (board == null) {
          board = new Board();
        }
        return board;
      };

  private final Supplier<List<Player>> players =
      () -> {
        if (_players != null && _players.size() > 0) {
          return _players;
        } else {
          return IntStream.range(0, 4)
              .mapToObj(value -> Player.instance.get())
              .collect(Collectors.toList());
        }
      };

  private final Function<ElementBehavior, Element> element =
      (behavior) -> {
        int startPoint = random.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
        while (elementMap.containsKey(startPoint)) {
          startPoint = random.nextInt(99) + 2;
        }
        int finalStartPoint = startPoint;
        if (ElementBehavior.SNAKE.equals(behavior)) {
          int endPoint = random.nextInt(finalStartPoint) + 2;
          while (elementMap.containsValue(endPoint) && finalStartPoint == endPoint) {
            endPoint = random.nextInt(finalStartPoint);
            if (endPoint == 0) endPoint += 2;
          }
          int finalEndPoint = endPoint;
          elementMap.put(finalStartPoint, finalEndPoint);
          Element snake = Snake.instance.apply(() -> finalStartPoint, () -> finalEndPoint);
          snakeAndLadderMap.put(finalStartPoint, snake);
          return snake;
        } else if (ElementBehavior.LADDER.equals(behavior)) {
          int endPoint =
              random.nextInt(LOWER_BOUND + UPPER_BOUND - finalStartPoint) + finalStartPoint;
          while (elementMap.containsValue(endPoint) && finalStartPoint == endPoint) {
            endPoint =
                random.nextInt(LOWER_BOUND + UPPER_BOUND - finalStartPoint) + finalStartPoint;
          }
          int finalEndPoint = endPoint;
          elementMap.put(finalStartPoint, finalEndPoint);
          Element ladder = Ladder.instance.apply(() -> finalStartPoint, () -> finalEndPoint);
          snakeAndLadderMap.put(finalStartPoint, ladder);
          return ladder;
        }
        return null;
      };

  private final Supplier<List<Element>> elements =
      () -> {
        Random random = new Random();
        return IntStream.range(0, 6)
            .mapToObj(
                value -> {
                  if (random.nextInt() % 2 == 0) {
                    return element.apply(Snake.behavior.get());
                  } else {
                    return element.apply(Ladder.behavior.get());
                  }
                })
            .collect(Collectors.toList());
      };

  /** A method which will star the play. */
  public void play() {
    System.out.println(String.format("Total number of players %s", _players.size()));
    snakeAndLadderMap
        .values()
        .forEach(
            e ->
                System.out.println(
                    String.format(
                        "%s present starts from %s and ends at %s",
                        e.getBehavior(), e.getStart(), e.getEnd())));
    int currentPointer = 0;
    while (_winner == null) {
      Player currentPlayer = _players.get(currentPointer);
      System.out.println(
          String.format(
              "Current player is %s and current is at %s",
              currentPlayer.id.get(), currentPlayer.currentPosition.get()));
      _players.stream()
          .filter(player -> player != currentPlayer)
          .forEach(player -> player.passDice.accept(null));
      currentPlayer.passDice.accept(_dice);
      System.out.println(String.format("Dice passed to Player %s", currentPlayer.id.get()));
      int newPosition = currentPlayer.roll.get();
      Element element = snakeAndLadderMap.get(newPosition);
      if (element != null) {
        if (element instanceof Snake) {
          currentPlayer.moveTo.accept(((Snake) element).end.get());
          System.out.println(
              String.format(
                  "Player %s encountered a Snake which will put the player %s to %s",
                  currentPlayer.id.get(), currentPlayer.id.get(), ((Snake) element).end.get()));
        } else if (element instanceof Ladder) {
          currentPlayer.moveTo.accept(((Ladder) element).end.get());
          System.out.println(
              String.format(
                  "Player %s encountered a Ladder which will put the player %s to %s",
                  currentPlayer.id.get(), currentPlayer.id.get(), ((Ladder) element).end.get()));
        }
      }
      System.out.println(
          String.format(
              "Player %s rolled a dice and got to position %s",
              currentPlayer.id.get(), newPosition));
      currentPointer = (currentPointer + 1) % _players.size();
      if (newPosition >= 100) {
        _winner = currentPlayer;
        System.out.println(String.format("Player %s has won the game", currentPlayer.id.get()));
      }
    }
  }
}
