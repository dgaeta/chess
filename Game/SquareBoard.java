package Game;

import java.util.ArrayList;
import java.util.List;

import Helpers.Position;
import Pieces.King;
import Pieces.Piece;

/**
 * A SquareBoard is an 8x8 Board.
 */

public class SquareBoard extends Board {

  private int rows;
  private int cols;

  private Position lastMoveStart;
  private Position lastMoveEnd;

  private King KingPlayerZero;
  private King KingPlayerOne;

  private Piece lastCaptured;
  private Piece lastMoved;

  private List<List<BoardUnit>> chessBoard;

  public SquareBoard(int rows, int cols) {
    chessBoard = new ArrayList<List<BoardUnit>>();
    this.rows = rows;
    this.cols = cols;

    lastCaptured = null;
    lastMoved = null;
    lastMoveStart = null;
    lastMoveEnd = null;
  }

  /* Getters & Setters */

  public int getPlayer(Position p) {
    if (getPiece(p) != null) return getPiece(p).getOwner();
    else return 99;
  }

  public boolean getOccupied(Position p) {
    return getUnit(p).getOccupied();
  }

  public Piece getPiece(Position p) {
    return getUnit(p).getPiece();
  }

  public Piece pieceLastMoved() {
    return lastMoved;
  }

  public Piece pieceLastCaptured() {
    return lastCaptured;
  }

  public BoardUnit getUnit(Position p) {
    return chessBoard.get(p.getX()).get(p.getY());
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public Position lastMoveStart() {
    return lastMoveStart;
  }

  public Position lastMoveEnd() {
    return lastMoveEnd;
  }

  public void setKing(int owner, King K) {
    if (owner == 0) KingPlayerZero = K;
    else KingPlayerOne = K;
  }

  /* End Getters & Setters */

  /**
   * Adds a piece onto the board.
   * @param chessPiece the piece to add.
   * @param p the position to add the piece.
   */

  public void addPiece(Piece chessPiece, Position p) {
    this.getUnit(p).setPiece(chessPiece);
    this.getUnit(p).setOccupied(true);
    chessPiece.setLoc(p);
  }

  /**
   * Removes a piece from the board.
   * @param p the position of the piece.
   */

  public void removePiece(Position p) {
    this.getUnit(p).setPiece(null);
    this.getUnit(p).setOccupied(false);
  }

  public void undoLastMove() {
    if (lastMoveStart == null || lastMoveEnd == null) return;
    else {
      System.out.println("Moving piece at position X: " + lastMoveEnd.getX() + "and Y: "
          + lastMoveEnd.getY());
      System.out.println("TO -> X: " + lastMoveStart.getX() + "and Y: " + lastMoveStart.getY());

      addPiece(getPiece(lastMoveEnd), lastMoveStart);

      if (lastCaptured != null) addPiece(lastCaptured, lastMoveEnd);
      else removePiece(lastMoveEnd);

    }
  }

  public void undoLastMove(Position p1, Position p2) {
    addPiece(getPiece(p2), p1);

    if (lastCaptured != null) addPiece(lastCaptured, p2);
    else removePiece(p2);
  }

  /**
   * Moves a piece from position p1, to position p2.
   * @param p1 The starting position.
   * @param p2 The ending position.
   */

  @Override
  public void movePiece(Position p1, Position p2) {
    if (this.getOccupied(p2)) lastCaptured = this.getPiece(p2);
    else lastCaptured = null;

    lastMoved = this.getPiece(p1);

    lastMoveStart = p1;
    lastMoveEnd = p2;

    this.addPiece(this.getUnit(p1).getPiece(), p2);
    this.removePiece(p1);
  }

  /**
   * Determines whether two spots on the board have a different "owner"
   * @return True if the owner of the piece is different. False otherwise.
   */

  public boolean ownersDiffer(Position p1, Position p2) {
    return (!(getPlayer(p1) == getPlayer(p2)));
  }

  /**
   * Creates an empty board without any pieces.
   */

  public void createEmptyBoard() {
    for (int i = 0; i < rows; i++) {
      List<BoardUnit> row = new ArrayList<BoardUnit>();

      for (int j = 0; j < cols; j++) {
        Position unitLoc = new Position(i, j);
        BoardUnit emptySquare = new BoardUnit(false, null, unitLoc);
        row.add(emptySquare);
      }

      chessBoard.add(row);
    }
  }

  /**
   * Determines whether a given position is on the board
   * @param p1 The position to check
   * @return True if on the board. False otherwise.
   */

  public boolean isOnBoard(Position p1) {
    if (p1.getX() >= 0 && p1.getY() >= 0) // Check for negative positions, they are not legal.
    {
      if (p1.getX() < rows && p1.getY() < cols) return true;
    }

    return false;
  }

  /**
   * Retrieves the location of the King for a given player.
   * @param p The player whose king we are looking for
   * @return The position of that players King.
   */

  public Position getKingLoc(Player p) {
    Position kingLoc;

    if (p.getID() == 0) kingLoc = KingPlayerZero.getLoc();
    else kingLoc = KingPlayerOne.getLoc();

    return kingLoc;
  }

  /**
   * Determines if the King is in check for a given player
   * @param p The player to check.
   * @returns True if the player is in check. False otherwise.
   */

  public boolean checkScan(Player p) {
    Position kingLoc = getKingLoc(p);
    boolean inCheck = false;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Position curr = new Position(i, j);

        if (getOccupied(curr)) {
          if (getPiece(curr).canCapture(curr, kingLoc, this)) // If any enemy piece can capture the
                                                              // King
          {
            inCheck = true;
          }
        }
      }
    }

    return inCheck;
  }

}