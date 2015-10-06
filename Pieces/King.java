/**
 * @author danielgaeta
 * @see class Piece
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;

public class King extends Piece {

  public King(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "King";
  }

  public boolean maxMove(Position p1, Position p2) {
    if (Math.abs(p1.getX() - p2.getX()) == 1) return true;
    else if (Math.abs(p1.getY() - p2.getY()) == 1) return true;
    else return false;
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {

    boolean movePossible = false;

    moveDiagonal.setBoard(chessBoard);
    moveStraight.setBoard(chessBoard);

    if (moveStraight.isLegal(p1, p2) || moveDiagonal.isLegal(p1, p2)) {
      if (maxMove(p1, p2)) {
        movePossible = true;
      }
    }

    return movePossible;
  }

}