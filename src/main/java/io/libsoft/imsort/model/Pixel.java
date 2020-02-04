package io.libsoft.imsort.model;

public class Pixel implements Comparable<Pixel> {

  private int row, col;
  private double[] colors;

  public Pixel(double[] colors, int row, int col) {
    this.colors = colors;
    this.row = row;
    this.col = col;
  }

  @Override
  public int compareTo(Pixel o) {
    if (row - o.row != 0) {
      return row - o.row;
    } else {
      return col - o.col;
    }
  }


  public double[] getColors() {
    return colors;
  }
}
