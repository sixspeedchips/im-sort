package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class BubbleSort implements Sorter {


  private Pixel[] pixels;

  public void setPixels(Pixel[] pixels) {
    this.pixels = pixels;
  }

  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    try {
      Thread t = new Thread(()->{
        int n = pixels.length;
        for (int i = 0; i < n-1; i++)
          for (int j = 0; j < n-i-1; j++)
            if (pixels[j].compareTo(pixels[j+1])> 0)
            {
              swap(pixels, j,j+1);
            }
      });
      t.start();
      t.join();
    } catch (InterruptedException ignored) {}
  }
  @Override
  public String toString() {
    return "Bubble Sort";
  }
}
