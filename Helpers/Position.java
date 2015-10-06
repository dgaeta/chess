/**
 * @author danielgaeta
 * @class Coords, data structure for coordinates.
 */
package Helpers;

/**
 * Helper class for coordinates.
 */

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}