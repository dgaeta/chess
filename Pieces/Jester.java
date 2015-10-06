package Pieces;

import Game.SquareBoard;
import Helpers.Position;


public class Jester extends Piece {

  public Jester(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Jester";
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    moveDiagonal.setBoard(chessBoard);
    moveStraight.setBoard(chessBoard);
    moveL.setBoard(chessBoard);

    return (moveDiagonal.isLegal(p1, p2) || moveStraight.isLegal(p1, p2) || moveL.isLegal(p1, p2));
  }

}
