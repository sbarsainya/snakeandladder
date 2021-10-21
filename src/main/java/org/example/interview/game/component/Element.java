package org.example.interview.game.component;

import org.example.interview.game.common.ElementBehavior;

/** This is interface for Snake and Ladder. */
public interface Element {
  /**
   * This returns Behavior.
   *
   * @return ElementBehavior
   */
  ElementBehavior getBehavior();

  /**
   * Returns Start Point
   *
   * @return Start Point
   */
  int getStart();

  /**
   * Return the end point
   *
   * @return End point
   */
  int getEnd();
}
