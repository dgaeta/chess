package Movement;
import Game.SquareBoard;
import Helpers.Position;

/**
 * @author danielgaeta
 * @class DiagonalMove Helper class to determine if two points are diagonal
 */
public class DiagonalMove extends LegalMove {

  public DiagonalMove(SquareBoard board) {
    super(board);
  }

  /**
   * Determines whether or not two positions are diagonal from each other.
   * @param p1 First position
   * @param p2 Second position
   * @return True if the positions are diagonal. False otherwise.
   */

  public boolean isDiagonal(Position p1, Position p2) {
    if (Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY())) return true;
    else return false;
  }

  /**
   * Criterion under which a move is "Diagonal & Legal" 1) Both locations are on the board. 2) The
   * locations are diagonal from each other. 3) There are no pieces in between the two points.
   * @returns True if all three conditions are satisfied. False otherwise.
   */

  @Override
  public boolean isLegal(Position p1, Position p2) {
    if (board.isOnBoard(p1) && board.isOnBoard(p2)) {
      if (isDiagonal(p1, p2) && pathClear(p1, p2)) {
        return true;
      }
    }

    return false;
  }
}