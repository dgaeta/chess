/**
 * @author danielgaeta
 * @see class Piece
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;

public class Horse extends Piece {
  public Horse(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Horse";
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    moveL.setBoard(chessBoard);

    return moveL.isLegal(p1, p2);
  }
}