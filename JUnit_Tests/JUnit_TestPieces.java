package JUnit_Tests;

import junit.framework.TestCase;
import Game.SquareBoard;
import Helpers.Position;
import Pieces.Bishop;
import Pieces.Wizard;
import Pieces.Jester;
import Pieces.Horse;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class JUnit_TestPieces extends TestCase {

  SquareBoard board = new SquareBoard(8, 8);

  public void testRook() {
    board.createEmptyBoard();

    Rook R = new Rook(0, board);
    board.addPiece(R, new Position(0, 0));

    Pawn P = new Pawn(1, board);
    board.addPiece(P, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 0));

    assertFalse(R.canCapture(new Position(0, 0), new Position(3, 3), board));
    assertTrue(R.canCapture(new Position(0, 0), new Position(3, 0), board));
    assertFalse(R.canCapture(new Position(0, 0), new Position(4, 4), board));
    assertFalse(R.canCapture(new Position(0, 0), new Position(4, 0), board));
  }

  public void testBishop() {
    board.createEmptyBoard();

    Bishop B = new Bishop(0, board);
    board.addPiece(B, new Position(0, 0));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 0));

    assertTrue(B.canCapture(new Position(0, 0), new Position(3, 3), board));
    assertFalse(B.canCapture(new Position(0, 0), new Position(3, 0), board));
    assertFalse(B.canCapture(new Position(0, 0), new Position(4, 4), board));
    assertFalse(B.canCapture(new Position(0, 0), new Position(4, 0), board));
  }

  public void testQueen() {
    board.createEmptyBoard();

    Queen Q = new Queen(0, board);
    board.addPiece(Q, new Position(0, 0));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 0));

    assertTrue(Q.canCapture(new Position(0, 0), new Position(3, 3), board));
    assertTrue(Q.canCapture(new Position(0, 0), new Position(3, 0), board));
    assertFalse(Q.canCapture(new Position(0, 0), new Position(3, 1), board));
    assertFalse(Q.canCapture(new Position(0, 0), new Position(7, 7), board));
  }

  public void testPawn() {
    board.createEmptyBoard();

    Pawn P = new Pawn(0, board);
    board.addPiece(P, new Position(4, 3));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 4));

    Pawn P3 = new Pawn(0, board);
    board.addPiece(P3, new Position(6, 0));

    assertTrue(P.canCapture(new Position(4, 3), new Position(3, 4), board));
    assertFalse(P.canCapture(new Position(4, 3), new Position(3, 3), board));
    assertTrue(P1.canCapture(new Position(3, 4), new Position(4, 3), board));
    assertTrue(P3.canMove(new Position(6, 0), new Position(4, 0), board));
  }

  public void testHorse() {
    board.createEmptyBoard();

    Horse H = new Horse(0, board);
    board.addPiece(H, new Position(4, 4));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(2, 5));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(2, 3));

    Pawn P3 = new Pawn(1, board);
    board.addPiece(P1, new Position(6, 3));

    Pawn P4 = new Pawn(1, board);
    board.addPiece(P2, new Position(5, 7));

    assertTrue(H.canCapture(new Position(4, 4), new Position(2, 5), board));
    assertTrue(H.canCapture(new Position(4, 4), new Position(2, 3), board));
    assertTrue(H.canCapture(new Position(4, 4), new Position(6, 3), board));
    assertFalse(H.canCapture(new Position(4, 4), new Position(5, 7), board));
  }

  public void testKing() {
    board.createEmptyBoard();

    King K = new King(0, board);
    board.addPiece(K, new Position(4, 4));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 5));

    Pawn P3 = new Pawn(1, board);
    board.addPiece(P1, new Position(7, 7));

    assertTrue(K.canCapture(new Position(4, 4), new Position(3, 3), board));
    assertTrue(K.canCapture(new Position(4, 4), new Position(3, 5), board));
    assertFalse(K.canCapture(new Position(4, 4), new Position(7, 7), board));
  }

  public void testGod() {
    board.createEmptyBoard();

    Jester G = new Jester(0, board);
    board.addPiece(G, new Position(4, 4));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(3, 3));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 5));

    Pawn P3 = new Pawn(1, board);
    board.addPiece(P1, new Position(7, 7));

    assertTrue(G.canCapture(new Position(4, 4), new Position(3, 3), board));
    assertTrue(G.canCapture(new Position(4, 4), new Position(3, 5), board));
    assertTrue(G.canCapture(new Position(4, 4), new Position(7, 7), board));
  }

  public void testCentaur() {
    board.createEmptyBoard();

    Wizard C = new Wizard(0, board);
    board.addPiece(C, new Position(4, 4));

    Pawn P1 = new Pawn(1, board);
    board.addPiece(P1, new Position(5, 6));

    Pawn P2 = new Pawn(1, board);
    board.addPiece(P2, new Position(3, 6));

    Pawn P3 = new Pawn(1, board);
    board.addPiece(P1, new Position(4, 7));

    assertTrue(C.canCapture(new Position(4, 4), new Position(5, 6), board));
    assertTrue(C.canCapture(new Position(4, 4), new Position(3, 6), board));
    assertTrue(C.canCapture(new Position(4, 4), new Position(4, 7), board));
  }
}

