package Draw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Control.ChessController;
import Helpers.Position;
import Pieces.Piece;

public class View extends JPanel {
  private List<List<JPanel>> panelBoard;

  private JPanel bottomPanel;
  private JFrame frame;

  private int rows;
  private int cols;
  private int squareSize;

  JMenuItem newCustomGame;
  JMenuItem newClassicGame;
  JMenuItem undoMove;
  JMenuItem forfeit;

  public View(int rows, int cols, int squareSize) {
    this.rows = rows;
    this.cols = cols;
    this.squareSize = squareSize;

    frame = new JFrame();

    panelBoard = new ArrayList<List<JPanel>>();

    this.setBoard();

    frame.setSize(495, 540);
  }

  public JPanel getBottomPanel() {
    return bottomPanel;
  }

  public JFrame getMainFrame() {
    return frame;
  }

  public JPanel getPanel(Position p) {
    return panelBoard.get(p.getX()).get(p.getY());
  }

  /**
   * Draws the game onto the chess board.
   * @param type The type of game, either "Custom" or "Classic"
   */

  public void drawGame(String type) {
    eraseAllBorders();
    clearBoard();
    setDefaultPieces();

    if (type == "Custom") setCustomPieces();

    rePaintAll();
  }

  /**
   * Clears the board of all current pieces, reseting the pieces to their default location.
   */

  public void clearBoard() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        JPanel temp = this.getPanel(new Position(i, j));

        if (temp.getComponentCount() > 0) erasePiece(new Position(i, j));
      }
    }
  }

  /**
   * Initializes the array which will hold all JPanels.
   */

  private void initPanelArr() {
    for (int i = 0; i < rows; i++) {
      List<JPanel> row = new ArrayList<JPanel>();

      for (int j = 0; j < cols; j++) {
        JPanel emptyPanel = new JPanel();
        row.add(emptyPanel);
      }

      panelBoard.add(row);
    }
  }

  /**
   * Adds all listeners for the controller.
   */

  public void addListeners(ChessController c) {
    bottomPanel.addMouseListener(c);
    newCustomGame.addActionListener(c);
    newClassicGame.addActionListener(c);
    undoMove.addActionListener(c);
    forfeit.addActionListener(c);
  }

  /**
   * Sets the JPanels for the entire board.
   */

  public void setBoard() {
    this.initPanelArr();

    bottomPanel = new JPanel();
    bottomPanel.setLayout(null);

    for (int i = 0; i < cols; i++)
      setRow(i, (i + 1) % 2);
  }

  /**
   * Sets a row of JPanels
   * @param row The row to add the JPanels
   * @param startColor The color of the JPanels.
   */
  public void setRow(int row, int startColor) {
    for (int j = 0; j < cols; j++) {
      int color = (j + startColor) % 2;
      JPanel toDraw = panelBoard.get(row).get(j);

      setSquare(toDraw, new Position(j * squareSize, row * squareSize), color);
    }
  }

  /**
   * Sets a single JPanel
   * @param panel The Panel to add.
   * @param loc The location of the panel on the screen.
   * @param color The color of the JPanel.
   */

  public void setSquare(JPanel panel, Position loc, int color) {
    if (color == 0) panel.setBackground(Color.gray);
    else panel.setBackground(Color.white);

    panel.setSize(squareSize, squareSize);
    panel.setLocation(loc.getX(), loc.getY());

    bottomPanel.add(panel);
  }

  /**
   * Adds a row of pawns to the given row.
   * @param row The row to add the pawns
   * @param color The color which corresponds to the Filename of the pieces. "white" and "black" are
   *          currently acceptable.
   */

  public void addPawnRow(int row, String color) {
    String fileName = "chessPieces/" + color + "Pawn.png";

    for (int j = 0; j < cols; j++) {
      JLabel piece = null;

      piece = new JLabel(new ImageIcon(fileName));

      panelBoard.get(row).get(j).add(piece);
    }
  }

  /**
   * Adds a row of non-pawns to the given row.
   * @param row The row to add the non-pawns (King, Bishop Rook, etc)
   * @param color The color which corresponds to the Filename of the pieces. "white" and "black" are
   *          currently acceptable.
   */

  public void addNonPawns(int row, String color) {
    String rookFileName = "chessPieces/" + color + "Rook.png";
    String horseFileName = "chessPieces/" + color + "Knight.png";
    String bishopFileName = "chessPieces/" + color + "Bishop.png";
    String kingFileName = "chessPieces/" + color + "King.png";
    String queenFileName = "chessPieces/" + color + "Queen.png";

    JLabel pieceR1 = new JLabel(new ImageIcon(rookFileName));
    JLabel pieceR2 = new JLabel(new ImageIcon(rookFileName));

    JLabel pieceH1 = new JLabel(new ImageIcon(horseFileName));
    JLabel pieceH2 = new JLabel(new ImageIcon(horseFileName));

    JLabel pieceB1 = new JLabel(new ImageIcon(bishopFileName));
    JLabel pieceB2 = new JLabel(new ImageIcon(bishopFileName));

    JLabel pieceK = new JLabel(new ImageIcon(kingFileName));
    JLabel pieceQ = new JLabel(new ImageIcon(queenFileName));

    panelBoard.get(row).get(0).add(pieceR1);
    panelBoard.get(row).get(7).add(pieceR2);

    panelBoard.get(row).get(1).add(pieceH1);
    panelBoard.get(row).get(6).add(pieceH2);

    panelBoard.get(row).get(2).add(pieceB1);
    panelBoard.get(row).get(5).add(pieceB2);

    panelBoard.get(row).get(4).add(pieceK);
    panelBoard.get(row).get(3).add(pieceQ);
  }

  /**
   * Adds the custom pieces to the chess game in the "default" location
   */

  public void setCustomPieces() {
    addCustomPieces(2, "black");
    addCustomPieces(5, "white");
  }

  /**
   * Adds the custom pieces to the chess game
   * @param row The row to add the pieces.
   * @param color The color of the pieces.
   */

  public void addCustomPieces(int row, String color) {
    String jesterFileName = "chessPieces/" + color + "Jester.png";
    String wizardFileName = "chessPieces/" + color + "Wizard.png";

    JLabel pieceJ1 = new JLabel(new ImageIcon(jesterFileName));
    JLabel pieceJ2 = new JLabel(new ImageIcon(jesterFileName));

    JLabel pieceW1 = new JLabel(new ImageIcon(wizardFileName));
    JLabel pieceW2 = new JLabel(new ImageIcon(wizardFileName));

    panelBoard.get(row).get(0).add(pieceJ1);
    panelBoard.get(row).get(7).add(pieceJ2);

    panelBoard.get(row).get(1).add(pieceW1);
    panelBoard.get(row).get(6).add(pieceW2);
  }

  /**
   * Erases all borders that are currently belong to JPanels on the screen.
   */

  public void eraseAllBorders() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        Position pos = new Position(i, j);

        JPanel panel = getPanel(pos);
        panel.setBorder(null);
        rePaint(pos);
      }
    }
  }

  /**
   * Draws a border on the panel at the specified position.
   * @param pos The position of the panel.
   * @param color The color of the border.
   */

  public void drawBorder(Position pos, Color C) {
    Border b = BorderFactory.createLineBorder(C, 3);

    getPanel(pos).setBorder(b);

    rePaint(pos);
  }

  /**
   * Erases a piece at the specified location.
   * @param pos The position where the piece is drawn.
   */

  public void erasePiece(Position pos) {
    JPanel panel = this.getPanel(pos);

    JLabel toRemove = (JLabel) panel.getComponent(0);
    panel.remove(toRemove);

    rePaint(pos);
  }

  /**
   * Draws a piece at the specified location.
   * @param piece The piece to draw.
   * @param pos The position to draw the piece at.
   */

  public void drawPiece(Piece piece, Position pos) {
    int x = pos.getX();
    int y = pos.getY();

    String fileName = piece.getFileName();

    System.out.println("Got filename: " + piece.getFileName());

    JLabel newPiece = new JLabel(new ImageIcon(fileName));

    panelBoard.get(x).get(y).add(newPiece);

    rePaint(pos);
  }

  /**
   * Moves the image at the first position to the second position. If the second position currently
   * has an image, it will be removed.
   * @param p1 The starting position
   * @param p2 The ending position
   */

  public void moveImage(Position p1, Position p2) {
    JPanel currPanel = getPanel(p1);
    JLabel currLabel = (JLabel) currPanel.getComponent(0);

    JPanel movePanel = getPanel(p2);

    if (movePanel.getComponentCount() > 0) {
      JLabel moveLabel = (JLabel) movePanel.getComponent(0);
      movePanel.remove((JLabel) moveLabel);
    }

    movePanel.add(currLabel);
    currPanel.remove((JLabel) currLabel);

    rePaint(p1);
    rePaint(p2);
  }

  /**
   * Validates and repaints all JLabels currently on the board.
   */

  public void rePaintAll() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++)
        rePaint(new Position(i, j));
    }
  }

  /**
   * Validates and repaints the JLabel at the current position.
   * @param x The position to refresh.
   */

  public void rePaint(Position pos) {
    int x = pos.getX();
    int y = pos.getY();

    panelBoard.get(x).get(y).validate();
    panelBoard.get(x).get(y).repaint();
  }

  /**
   * The default piece setup places the pieces according to the traditional chess rules.
   */

  public void setDefaultPieces() {
    addPawnRow(1, "black");
    addNonPawns(0, "black");

    addPawnRow(6, "white");
    addNonPawns(7, "white");
  }

  /**
   * For setting up the JFrame and displaying the chess board.
   */

  public void displayBoard(ChessController c) {
    JFrame.setDefaultLookAndFeelDecorated(true);

    frame.setContentPane(getBottomPanel());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    setUpMenu(frame);
    this.addListeners(c);
  }

  /**
   * Sets the menu for the game.
   * @param window The bottom-most JFrame
   * @param c
   */

  private void setUpMenu(JFrame window) {
    JMenuBar menuBar = new JMenuBar();

    JMenu menu = new JMenu("File");

    newCustomGame = new JMenuItem("New Custom Game");
    newClassicGame = new JMenuItem("New Classic Game");
    undoMove = new JMenuItem("Undo Move");
    forfeit = new JMenuItem("Forfeit");

    menu.add(newCustomGame);
    menu.add(newClassicGame);
    menu.add(undoMove);
    menu.add(forfeit);

    menuBar.add(menu);

    window.setJMenuBar(menuBar);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = null;
        View V = null;

        V = new View(8, 8, 60);
        V.displayBoard(new ChessController(frame, V, 60));
      }
    });
  }
}