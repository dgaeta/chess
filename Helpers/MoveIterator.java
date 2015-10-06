package Helpers;

public class MoveIterator {
  public static int incrementTowardsBlock(int x, int x2) {
    if (x > x2) return x - 1;
    if (x < x2) return x + 1;
    else return x;
  }

}