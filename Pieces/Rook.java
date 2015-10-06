/**
 * @author danielgaeta
 * @see class Piece
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;

public class Rook extends Piece {

  public Rook(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Rook";
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    moveStraight.setBoard(chessBoard);

    return (moveStraight.isLegal(p1, p2));
  }
}