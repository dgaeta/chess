package Game;
import Helpers.Position;

/**
 * @author danielgaeta
 * @class Board - Mother-class of all shaped boards.
 */

public abstract class Board {
  public abstract void createEmptyBoard();

  public abstract boolean getOccupied(Position c1);

  public abstract void movePiece(Position c1, Position c2);

}
