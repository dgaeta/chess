/**
 * @author danielgaeta
 */

package Game;

public class Player {

  private String name;
  private int ID;

  private String color;
  private boolean isTurn;
  private boolean inCheck;

  public Player(int ID, String name, String color) {
    this.name = name;
    this.color = color;
    this.ID = ID;
    this.inCheck = false;

    if (color == "white") this.isTurn = true;
    else this.isTurn = false;
  }

  public String getName() {
    return name;
  }

  public int getID() {
    return ID;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColor() {
    return this.color;
  }

  public boolean isTurn() {
    return this.isTurn;
  }

  public void setTurn(boolean turn) {
    this.isTurn = turn;
  }

  public void setCheck(boolean check) {
    this.inCheck = check;
  }

  public boolean getCheck() {
    return inCheck;
  }

}