/**
 * @author danielgaeta
 * @see class Piece
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;

public class Queen extends Piece {

  public Queen(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Queen";
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    moveStraight.setBoard(chessBoard);
    moveDiagonal.setBoard(chessBoard);
    moveL.setBoard(chessBoard);

    return (moveDiagonal.isLegal(p1, p2) || moveStraight.isLegal(p1, p2));
  }

}