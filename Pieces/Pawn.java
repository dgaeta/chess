/**
 * @author danielgaeta
 * @see class Piece
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;
import Movement.DiagonalMove;
import Movement.StraightMove;

public class Pawn extends Piece {

  private StraightMove moveStr;
  private DiagonalMove moveDiag;
  private boolean hasMoved;

  public Pawn(int owner, SquareBoard board) {
    super(owner, board);
    hasMoved = false;
    this.name = "Pawn";
  }

  public int getMaxMove() {
    if (hasMoved == true) return 1;
    else return 2;
  }

  public boolean maxMove(Position p1, Position p2) {
    int max = getMaxMove();

    if (Math.abs(p1.getX() - p2.getX()) <= max) return true;

    return false;
  }

  public boolean movedAwayFromSide(Position p1, Position p2) {
    if (getOwner() == 0 && (p1.getX() > p2.getX())) return true;
    if (getOwner() == 1 && (p1.getX() < p2.getX())) return true;
    else {
      return false;
    }

  }

  public boolean pawnStraightMove(Position p1, Position p2, SquareBoard chessBoard) {
    boolean movePossible = false;
    moveStr = new StraightMove(chessBoard);

    if (moveStr.isLegal(p1, p2) && maxMove(p1, p2)) {
      if (movedAwayFromSide(p1, p2)) {
        movePossible = true;
      }
    }

    return movePossible;
  }

  public boolean pawnDiagonalMove(Position p1, Position p2, SquareBoard chessBoard) {
    boolean movePossible = false;
    moveDiag = new DiagonalMove(chessBoard);

    if (moveDiag.isLegal(p1, p2) && maxMove(p1, p2)) {
      if (movedAwayFromSide(p1, p2)) {
        movePossible = true;
      }
    }

    return movePossible;
  }

  @Override
  public boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard) {
    return (pawnDiagonalMove(p1, p2, chessBoard)) || pawnStraightMove(p1, p2, chessBoard);
  }

  @Override
  public boolean canMove(Position p1, Position p2, SquareBoard chessBoard) {
    if (pawnStraightMove(p1, p2, chessBoard) && chessBoard.getOccupied(p2) == false) {
      hasMoved = true; // Pawns can no longer jump 2 places if they have moved.

      return true;
    }

    return false;
  }

  @Override
  public boolean canCapture(Position p1, Position p2, SquareBoard chessBoard) {
    if (chessBoard.getOccupied(p2)) {
      if (pawnDiagonalMove(p1, p2, chessBoard) && chessBoard.ownersDiffer(p1, p2)) {
        hasMoved = true; // Pawns can no longer jump 2 places if they have captured

        return true;
      }
    }

    return false;
  }
}