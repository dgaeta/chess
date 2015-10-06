package Pieces;
import Game.SquareBoard;
import Helpers.Position;

/**
 * @author danielgaeta
 * @see class Piece
 */
public class Wizard extends Piece {

  public Wizard(int owner, SquareBoard board) {
    super(owner, board);
    this.name = "Wizard";
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    moveStraight.setBoard(chessBoard);
    moveL.setBoard(chessBoard);

    return (moveStraight.isLegal(p1, p2) || moveL.isLegal(p1, p2));
  }
}