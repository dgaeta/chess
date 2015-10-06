/**
 * Tests for whether or not the King is correctly recognized as in check.
 */

package JUnit_Tests;

import junit.framework.TestCase;
import Game.Player;
import Game.SquareBoard;
import Helpers.Position;
import Pieces.King;
import Pieces.Queen;

public class JUnit_TestCheck extends TestCase {

  SquareBoard board = new SquareBoard(8, 8);

  // Getting checked by an enemy Queen.

  public void testCheckQueen() {
    board.createEmptyBoard();
    Player P0 = new Player(0, "jack", "white");

    King K0 = new King(0, board);
    board.setKing(0, K0);

    board.addPiece(K0, new Position(0, 0));

    Queen Q1 = new Queen(1, board);
    board.addPiece(Q1, new Position(2, 2));

    assertTrue(board.checkScan(P0));

  }

  // No check at all.

  public void testCheck1() {
    board.createEmptyBoard();
    Player P0 = new Player(0, "jack", "white");

    King K0 = new King(0, board);
    board.setKing(0, K0);

    board.addPiece(K0, new Position(0, 0));

    Queen Q1 = new Queen(0, board);
    board.addPiece(Q1, new Position(2, 2));

    assertFalse(board.checkScan(P0));

  }

}