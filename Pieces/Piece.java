/**
 * @author danielgaeta
 */
package Pieces;

import Game.SquareBoard;
import Helpers.Position;
import Movement.DiagonalMove;
import Movement.LMove;
import Movement.StraightMove;

public abstract class Piece {

  private int owner;
  private Position loc;
  protected String name;
  private String color;

  SquareBoard board;
  DiagonalMove moveDiagonal;
  StraightMove moveStraight;
  LMove moveL;

  public Piece(int owner, SquareBoard board) {
    this.owner = owner;
    this.board = board;
    this.setColor();

    moveDiagonal = new DiagonalMove(board);
    moveStraight = new StraightMove(board);
    moveL = new LMove(board);

  }

  // Getters and Setters

  public int getOwner() {
    return owner;
  }

  public Position getLoc() {
    return loc;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public String getFileName() {
    return "chessPieces/" + color + name + ".png";
  }

  public void setLoc(Position loc) {
    this.loc = loc;
  }

  // End Getters and Setters

  /**
   * Sets the color for the piece. Player 0 is defined as White, Player 1 is defined as Black.
   */

  private void setColor() {
    if (this.owner == 0) color = "white";
    else color = "black";
  }

  /**
   * Determines if a Piece can capture another Piece at a given position.
   * @param p1 The location of the first piece
   * @param p2 The location of the second piece
   * @param chessBoard The board the game is being played on
   * @returns True if capturing is legal. False otherwise
   */

  public boolean canCapture(Position p1, Position p2, SquareBoard chessBoard) {
    boolean capture = false;

    if (chessBoard.getOccupied(p2)) {
      if (moveLegal(p1, p2, chessBoard) && chessBoard.ownersDiffer(p1, p2)) capture = true;
    }

    return capture;
  }

  /**
   * Determines if a Piece can move to a given Position.
   * @param p1 The initial location of the piece.
   * @param p2 The destination of the piece.
   * @param chessBoard The board the game is being played on
   * @returns True if moving is legal. False otherwise.
   */

  public boolean canMove(Position p1, Position p2, SquareBoard chessBoard) {
    return (moveLegal(p1, p2, chessBoard) && chessBoard.getOccupied(p2) == false);
  }

  /**
   * Determines whether or not a Piece has followed the rules of its movement.
   * @assumption This function does not check if the position the piece is moving to is occupied.
   * @param p1 The initial location of the piece.
   * @param p2 The destination of the piece.
   * @param chessBoard the board the game is being played on.
   * @returns True if moving is legal. False otherwise.
   */

  public abstract boolean moveLegal(Position p1, Position p2, SquareBoard chessBoard);

}