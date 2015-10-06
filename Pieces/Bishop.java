package Pieces;
import Game.SquareBoard;
import Helpers.Position;


/**
 * @author danielgaeta
 * @see class Piece
 */
public class Bishop extends Piece {
  public Bishop(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Bishop";
  }

  @Override
  public boolean moveLegal(Position c1, Position c2, SquareBoard squareBoard) {
    moveDiagonal.setBoard(squareBoard);

    return (moveDiagonal.isLegal(c1, c2));
  }
}