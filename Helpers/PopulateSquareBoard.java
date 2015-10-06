package Helpers;

import Game.SquareBoard;
import Pieces.Bishop;
import Pieces.Wizard;
import Pieces.Jester;
import Pieces.Horse;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class PopulateSquareBoard {

  private int rows;
  private int cols;
  private SquareBoard board;

  public PopulateSquareBoard(SquareBoard board) {
    this.rows = board.getRows();
    this.cols = board.getCols();
    this.board = board;
  };

  public void initRowOfPawns(int rowToAdd, int owner) {
    for (int i = 0; i < cols; i++) {
      board.addPiece(new Pawn(owner, board), new Position(rowToAdd, i));
    }
  }

  public void initNonPawns(int rowToAdd, int owner) {
    board.addPiece(new Horse(owner, board), new Position(rowToAdd, 1));
    board.addPiece(new Horse(owner, board), new Position(rowToAdd, 6));

    board.addPiece(new Rook(owner, board), new Position(rowToAdd, 0));
    board.addPiece(new Rook(owner, board), new Position(rowToAdd, 7));

    board.addPiece(new Bishop(owner, board), new Position(rowToAdd, 2));
    board.addPiece(new Bishop(owner, board), new Position(rowToAdd, 5));

    board.addPiece(new Queen(owner, board), new Position(rowToAdd, 3));

    King K = new King(owner, board);
    board.addPiece(K, new Position(rowToAdd, 4));
    board.setKing(owner, K);
  }

  public void initGodPieces(int rowToAdd, int owner) {
    board.addPiece(new Jester(owner, board), new Position(rowToAdd, 0));
    board.addPiece(new Jester(owner, board), new Position(rowToAdd, 7));
  }

  public void initGhostPieces(int rowToAdd, int owner) {
    board.addPiece(new Wizard(owner, board), new Position(rowToAdd, 1));
    board.addPiece(new Wizard(owner, board), new Position(rowToAdd, 6));
  }

  public void initPlayerCustom(int owner) {
    if (owner == 1) {
      initGhostPieces(2, owner);
      initGodPieces(2, owner);
    } else if (owner == 0) {
      initGhostPieces(5, owner);
      initGodPieces(5, owner);
    }
  }

  public void initPlayer(int owner) {
    if (owner == 1) {
      initRowOfPawns(1, owner);
      initNonPawns(0, owner);
    } else if (owner == 0) {
      initRowOfPawns(rows - 2, owner);
      initNonPawns(rows - 1, owner);
    }
  }
}