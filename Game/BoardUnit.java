package Game;
import Helpers.Position;
import Pieces.Piece;

/**
 * @author danielgaeta
 * @BoardSpace A container for a piece.
 */
public class BoardUnit {

  private boolean occupied;
  private Piece chessPiece;
  private Position unitLoc;

  public BoardUnit(boolean occupied, Piece chessPiece, Position unitLoc) {
    this.occupied = occupied;
    this.chessPiece = chessPiece;
    this.unitLoc = unitLoc;
  }

  public boolean getOccupied() {
    return occupied;
  }

  public Position getLocation() {
    return unitLoc;
  }

  public Piece getPiece() {
    return chessPiece;
  }

  public void setPiece(Piece chessPiece) {
    this.chessPiece = chessPiece;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }
}
