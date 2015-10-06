package Game;
import Helpers.PopulateSquareBoard;
import Helpers.Position;

/**
 * 
 * @author danielgaeta
 * {@docRoot} This class is responsible for determining if a player has taken a a legal turn and if the game is over. 
 */

public class Game {

  private SquareBoard board;
  
    Player P0;
  Player P1;

  private Position startMove;
  private Position endMove;

  public Game() {
    initGame();
  }

  public SquareBoard getBoard() {
    return board;
  }

  public void setBoard(SquareBoard board) {
    this.board = board;
  }

  public Position getStartMove() {
    return startMove;
  }

  public Position getEndMove() {
    return endMove;
  }

  public Player getPlayerTurn() {
    if (P0.isTurn()) return P0;
    else return P1;
  }

  public Player getPlayerMoved() {
    if (P0.isTurn()) return P1;
    else return P0;
  }

  public void clearMove() {
    startMove = null;
    endMove = null;
  }

  public void setStartMove(Position p) {
    startMove = p;
  }

  /**
   * Sets pieces in the correct configuration for a classic game of chess.
   */

  public void initGame() {
    board = new SquareBoard(8, 8);
    board.createEmptyBoard();

    PopulateSquareBoard defaultGame = new PopulateSquareBoard(board);
    defaultGame.initPlayer(0);
    defaultGame.initPlayer(1);

    startMove = null;
    endMove = null;
  }

  /**
   * Sets pieces in the correct place for a Custom game of chess.
   */

  public void initCustomGame() {
    initGame();

    PopulateSquareBoard defaultGame = new PopulateSquareBoard(board);

    defaultGame.initPlayerCustom(0);
    defaultGame.initPlayerCustom(1);
  }

  /**
   * Sets the players names for Chess.
   * @param name The name of the player.
   * @param color The color of the player.
   */

  public void setPlayer(String name, String color) {
    if (color == "white") P0 = new Player(0, name, color);
    else if (color == "black") P1 = new Player(1, name, color);
  }

  /**
   * Determines if a position is occupied by the piece that belongs to the player whose turn it is
   * @return True if the piece does belong to the player. False otherwise.
   */

  public boolean legalStartMove(Position pos) {
    Player isTurn = getPlayerTurn();

    if (this.getBoard().getPlayer(pos) == isTurn.getID()) return true;

    return false;
  }

  /**
   * Swaps the turns of the players, setting a players turn to True if it is False and False if it
   * is True.
   */

  public void swapTurn() {
    boolean temp = P1.isTurn();
    P1.setTurn(P0.isTurn());
    P0.setTurn(temp);
  }

  /**
   * This function will return true once it has been called twice with two move locations.
   * @param p The new move location to add
   * @return True if two move locations exist. False otherwise.
   */

  public boolean moveChosen(Position p) {
    if (startMove == null) {
      startMove = p;
      return false;
    } else {
      endMove = p;
      return true;
    }
  }
  
    /**
   * Allows a player to take a turn, IF that turn is legal. The criteria for a legal turn is the
   * following: 1) The player did not move into check. 2) The player did not move illegally, or try
   * to move another piece.
   * @param p1 The current location of the piece they want to move
   * @param p2 The ending location of the piece.
   * @param player The player to move.
   * @return True if the turn was legal. False otherwise.
   */

  public boolean takeTurn(Position p1, Position p2) {
    if (legalTurn(p1, p2, this.getPlayerTurn())) {
      board.movePiece(p1, p2);
      return true;
    }

    return false;

  }

  /**
   * Determines if a player has taken a 'legal' turn. The criteria for a "legal" turn is as such: 1)
   * One cannot move in a manner that places oneself in check. 2) One must follow all the rules in
   * place for how different pieces can and cannot move.
   * @param p1 The starting position of the piece.
   * @param p2 The ending position of the piece.
   * @param player The player whose turn it is.
   * @return True if the turn was legal. False otherwise.
   */

  public boolean legalTurn(Position p1, Position p2, Player player) {
    boolean legal = false;

    if (board.getPlayer(p1) == player.getID()) // If we're not moving the other players piece.
    {
      boolean pieceCapture = board.getPiece(p1).canCapture(p1, p2, board);
      boolean pieceMove = board.getPiece(p1).canMove(p1, p2, board);

      if (pieceCapture || pieceMove) {
        board.movePiece(p1, p2);

        if (board.checkScan(player) == false) // Make sure the player did not move into check.
        legal = true;

        board.undoLastMove(p1, p2);
      }
    }

    return legal;
  }

  /**
   * Determines if a given player is in Check mate.
   * @param p The player to check.
   * @returns True if the player is in check. False otherwise.
   */

  public boolean checkMate(Player player) {
    boolean checkMate = true;

    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getCols(); j++) {
        Position curr = new Position(i, j);

        if (board.getPlayer(curr) == player.getID()) // If this is one of our pieces;
        {
          if (checkMateHelper(player, curr) == false) // Proceed to move that piece to every
          { // possible location.
            checkMate = false;
          }
        }
      }
    }

    return checkMate;
  }
      
    /**
   * This function moves a piece at a given position to every possible location on the board.
   * @param player The player who owns the piece.
   * @param curr The location of the piece
   * @return True if the player was able to take a turn (i.e, is no longer in check). False
   *         otherwise.
   */
      
  public boolean checkMateHelper(Player player, Position curr) {
    boolean checkMate = true;

    for (int i = 0; i < board.getRows(); i++) {
      for (int j = 0; j < board.getCols(); j++) {
        Position moveLoc = new Position(i, j);

        if (this.takeTurn(curr, moveLoc)) {
          checkMate = false;
          board.undoLastMove(curr, moveLoc);
        }
      }
    }

    return checkMate;
  }
}
