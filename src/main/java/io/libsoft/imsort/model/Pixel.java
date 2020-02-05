package io.libsoft.imsort.model;

public class Pixel implements Comparable<Pixel> {

  private final double[] hls;
  private final double[] colors;
  private int row;
  private int col;
  private Point p;

  public Pixel(double[] rgb, double[] hsl, int row, int col) {
    this.colors = rgb;
    this.row = row;
    this.col = col;
    this.hls = hsl;
  }


    @Override
  public int compareTo(Pixel o) {
    if (row - o.row != 0) {
      return row - o.row;
    } else {
      return col - o.col;
    }
  }
//  @Override
//  public int compareTo(Pixel o) {
//    return Double.compare(colors[0],o.colors[0]);

  //    if (Double.compare(colors[0], o.colors[0]) != 0) {
//      return Double.compare(colors[0], o.colors[0]);
//    } else if (Double.compare(colors[1], o.colors[1]) != 0) {
//      return Double.compare(colors[1], o.colors[1]);
//    } else {
//      return Double.compare(colors[2], o.colors[2]);
//    }
//  }
//
//  @Override
//  public int compareTo(Pixel o) {
//    return (int) (Arrays.stream(colors).sum() / 3 - Arrays.stream(o.colors).sum() / 3);
//  }


//  @Override
//  public int compareTo(Pixel o) {
//    if (Math.abs(hls[1]- o.hls[1]) < .5){
//      return Double.compare(hls[0],o.hls[0]);
//    } else {
//      return Double.compare(hls[1],o.hls[1]);
//    }
//
//  }
//  @Override
//  public int compareTo(Pixel o) {
//    return p.compareTo(o.p);
//  }


  public double[] getColors() {
    return colors;
  }

  static class Point implements Comparable<Point> {

    private int x;
    private int y;

    private Point(int x, int y) {

      this.x = x;
      this.y = y;
    }

    public static Point of(int x, int y) {
      return new Point(x, y);
    }


    @Override
    public int compareTo(Point o) {
      double dis = Math.sqrt(x * x + y * y);
      double diso = Math.sqrt(o.x * o.x + o.y * o.y);
      return Double.compare(dis, diso);
    }
  }
}
