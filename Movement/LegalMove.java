package Movement;

import Game.SquareBoard;
import Helpers.MoveIterator;
import Helpers.Position;

public abstract class LegalMove {

  protected SquareBoard board;

  public LegalMove(SquareBoard board) {
    this.board = board;
  }

  public void setBoard(SquareBoard board) {
    this.board = board;
  }

  /**
   * This function checks if two given locations are the same or different.
   * @param p1 First position
   * @param p2 Second position
   * @returns True if the positions are different. False otherwise.
   */

  public boolean isMove(Position p1, Position p2) {
    if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) return false;
    else return true;
  }

  /**
   * This function checks to see if the closest path between two points on the board is clear,
   * meaning no pieces exist between these two points, excluding the points themselves.
   * @param p1 First point
   * @param p2 Second point
   * @return True if the path is clear. False otherwise.
   */

  public boolean pathClear(Position p1, Position p2) {
    int x = p1.getX();
    int y = p1.getY();

    boolean pathClear = true;

    while (isMove(new Position(x, y), p2)) {
      x = MoveIterator.incrementTowardsBlock(x, p2.getX());
      y = MoveIterator.incrementTowardsBlock(y, p2.getY());

      if (board.getOccupied(new Position(x, y)) && isMove(new Position(x, y), p2)) pathClear = false;
    }

    return pathClear;
  }

  /**
   * Special criterion under which different styles of moves are legal.
   */

  public abstract boolean isLegal(Position p1, Position p2);

}
