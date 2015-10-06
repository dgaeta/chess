/*
 * @class LMove Helper class to determine if two coordinates are an "L" distance from each other.
 * Definition of L: An "L" move is defined as two steps in one direction, followed by one step 90
 * degrees to that direction.
 */

package Movement;

import Game.SquareBoard;
import Helpers.Position;

public class LMove extends LegalMove {

  public LMove(SquareBoard board) {
    super(board);
  }

  /**
   * Determines if two positions are an L distance from each other.
   * @param p1 Starting location
   * @param p2 Ending location
   * @see "Definition of L" at top
   */

  public boolean isLShape(Position p1, Position p2) {
    boolean LShaped = false;

    if (Math.abs(p1.getX() - p2.getX()) == 1 && Math.abs(p1.getY() - p2.getY()) == 2) LShaped = true;
    else if (Math.abs(p1.getX() - p2.getX()) == 2 && Math.abs(p1.getY() - p2.getY()) == 1) LShaped = true;

    return LShaped;

  }

  /**
   * This function will determine whether or not two coordinates are "L"-shaped, and if they are on
   * the chessBoard
   * @param p1 Starting location
   * @param p2 Ending location
   * @returns True if the positions are L-shaped. False otherwise.
   */

  @Override
  public boolean isLegal(Position p1, Position p2) {
    if (board.isOnBoard(p1) && board.isOnBoard(p2)) {
      if (isLShape(p1, p2)) {
        return true;
      }
    }

    return false;
  }
}
