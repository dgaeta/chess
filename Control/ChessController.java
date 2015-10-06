package Control;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Draw.View;
import Game.Game;
import Game.Player;
import Game.SquareBoard;
import Helpers.Position;

public class ChessController implements MouseListener, ActionListener {
  
  private Game chessGame; 
  
  private int squareSize; 
  private View view; 
    
  int whiteWins; 
  int blackWins; 
    
    
  public ChessController(JFrame frame, View view, int squareSize) { 
      this.squareSize = squareSize; 
      this.view = view; 
        
      this.whiteWins = 0; 
      this.blackWins = 0; 
  } 
    
  /** 
   *  Converts a location on the screen into the appropriate 2D array location 
   *  
   * @param p The position on the board 
   * @param squareSize The size of the squares on the board. 
   * @returns The position in the 2D array. 
   */
    
  public Position convertScreenPos(Position p, int squareSize) { 
      int arrX = p.getX()/squareSize; 
      int arrY = p.getY()/squareSize; 
        
      return new Position(arrY, arrX); 
  } 
    
  /** 
   * Adds a point to the current players color. 
   * @param player the player whose win count will be incremented. 
   */
    
  public void addWinnerCount(Player player) { 
      addWinnerCount(player.getColor()); 
  } 
    
  /** 
   * Adds a point for the white or black chess player 
   * @param color The color of the player to add the point to 
   */
    
  public void addWinnerCount(String color)  { 
      if (color == "white") 
          whiteWins++; 
      else if (color == "black") 
          blackWins++; 
  } 
    
  /** 
   * Displays the current white vs black win count. 
   */
    
  public void displayScoreboard() { 
      JOptionPane.showMessageDialog(null, "White has won " + whiteWins + " times!\n"
                                          + "Black has won " + blackWins + " times!"); 
  } 
    
    
  /** 
   * Determines if both players agree to forfeit the game. 
   * @return True if both players agree. False otherwise.  
   */
    
  public boolean forfeitDialog() { 
      String whitePlayerChoice = JOptionPane.showInputDialog("WHITE PLAYER: Do you agree to forfeit? Yes / No");  
      String blackPlayerChoice = JOptionPane.showInputDialog("BLACK PLAYER: Do you agree to forfeit? Yes / No");  
        
      System.out.println(whitePlayerChoice); 
      System.out.println(blackPlayerChoice); 
        
      if (whitePlayerChoice.equals("Yes") && blackPlayerChoice.equals("Yes")) 
          return true; 
                    
      return false; 
        
  } 
    
  /** 
   * Displays a dialog box to and allows the players to  
   * input their names. 
   */
    
  public void getPlayerNames() { 
      String whitePlayer = JOptionPane.showInputDialog("Enter white player name");  
      String blackPlayer = JOptionPane.showInputDialog("Enter black player name");  
        
      chessGame.setPlayer(whitePlayer, "white"); 
      chessGame.setPlayer(blackPlayer, "black"); 
  } 
    
  /** 
   *  Resets the board to it's last previous instance. 
   */
    
  public void undoMove() 
  { 
      SquareBoard board = chessGame.getBoard(); 
      board.undoLastMove(); 
        
      // puts Piece at end position back to where it came from
      view.drawPiece(board.pieceLastMoved(), board.lastMoveStart()); 
      view.erasePiece(board.lastMoveEnd()); 
        
      // Puts a piece back if it was captured
      if (board.pieceLastCaptured() != null) 
          view.drawPiece(board.pieceLastCaptured(), board.lastMoveEnd()); 
        
      chessGame.swapTurn(); 
  } 
        
  public void actionPerformed(ActionEvent e)  
  { 
      JMenuItem source = (JMenuItem)(e.getSource()); 
      String event = source.getText(); 
        
      if (event == "New Classic Game")  
      { 
          chessGame = new Game(); 
            
          view.drawGame("Classic"); 
        
          getPlayerNames(); 
      } 
      else if (event == "New Custom Game") 
      { 
          chessGame = new Game(); 
          chessGame.initCustomGame(); 
        
          view.drawGame("Custom"); 
        
          getPlayerNames(); 
      } 
      else if (event == "Undo Move") 
      { 
          if (chessGame == null) 
              return; 
            
          this.undoMove(); 
      } 
      else if (event == "Forfeit") 
      { 
          if (forfeitDialog())  
          { 
              System.out.println("Agreed to forfeit"); 
              view.eraseAllBorders(); 
              view.clearBoard(); 
              view.rePaintAll(); 
                
              Player winner = chessGame.getPlayerMoved(); 
              JOptionPane.showMessageDialog(null, winner.getName() + " Won!!!"); 
              addWinnerCount(winner); 
              displayScoreboard(); 
          } 
          else
          { 
              System.out.println("Agreed not to forfeit"); 
          } 
            
      } 
  } 
        

  public void mousePressed(MouseEvent e) { 
        
      if (chessGame == null) 
          return; 
        
      Position screenPos = new Position(e.getX(), e.getY()); 
      Position boardPos = convertScreenPos(screenPos, squareSize); 
        
      SquareBoard board = chessGame.getBoard(); 
        
      if (chessGame.legalStartMove(boardPos)) { 
          view.eraseAllBorders(); 
          view.drawBorder(boardPos, Color.green); 
      } 
      
      if (chessGame.moveChosen(boardPos)) {    
          Position start = chessGame.getStartMove(); 
          Position end = chessGame.getEndMove(); 
                
          if (chessGame.takeTurn(start, end)) {    
              chessGame.swapTurn(); 
                
              Player tookTurn = chessGame.getPlayerMoved(); 
              Player isTurn = chessGame.getPlayerTurn(); 
                
              view.moveImage(chessGame.getStartMove(), chessGame.getEndMove()); 
              view.drawBorder(end, Color.green); 
                
        if (board.checkScan(isTurn) == true) {
                  view.drawBorder(board.getKingLoc(isTurn), Color.red); 
                
                  if (chessGame.checkMate(isTurn)) 
                  { 
                      JOptionPane.showMessageDialog(null, tookTurn.getName() + " Won!!!"); 
                      addWinnerCount(tookTurn); 
                      displayScoreboard(); 
                  } 
              }    
          } 
            
          chessGame.clearMove(); 
      } 
  } 
    
  /** 
   * No implementation necessary. 
   */
  public void mouseReleased(MouseEvent e) {} 

    
  /** 
   * No implementation necessary. 
   */
  public void mouseEntered(MouseEvent e) {} 

  /** 
   * No implementation necessary. 
   */
  public void mouseExited(MouseEvent e) {} 

  /** 
   * No implementation necessary. 
   */
  public void mouseClicked(MouseEvent e) {} 

}
