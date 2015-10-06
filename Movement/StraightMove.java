package Movement;

import Game.SquareBoard;
import Helpers.Position;

public class StraightMove extends LegalMove {

  public StraightMove(SquareBoard board) {
    super(board);
  }

  /**
   * Determines whether or not two points are horizontal or vertical from each other.
   * @param p1 First position
   * @param p2 Second position
   * @return True if the positions are horizontal or vertical. False otherwise.
   */

  public boolean isStraight(Position p1, Position p2) {
    if (p1.getX() == p2.getX() || p1.getY() == p2.getY()) return true;
    else return false;
  }

  /**
   * Criterion under which a move is "Straight & Legal" 1) Both locations are on the board. 2) The
   * locations are vertical or horizontal from each other. 3) There are no pieces in between the two
   * points.
   * @returns True if all three conditions satisfied. False otherwise.
   */

  @Override
  public boolean isLegal(Position p1, Position p2) {
    if (board.isOnBoard(p1) && board.isOnBoard(p2)) {
      if (isStraight(p1, p2) && pathClear(p1, p2)) {
        return true;
      }
    }

    return false;
  }
}