package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class InsertionSort implements Sorter {


  private Pixel[] pixels;

  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    try {
      Thread t = new Thread(() -> {
        int n = pixels.length;
        for (int i = 1; i < n; ++i) {
          Pixel key = pixels[i];
          int j = i - 1;
          while (j >= 0 && pixels[j].compareTo(key) > 0) {
            pixels[j + 1] = pixels[j];
            j = j - 1;
          }
          pixels[j + 1] = key;
        }
      });
      t.start();
      t.join();
    } catch (InterruptedException ignored) {}

  }

  @Override
  public void setPixels(Pixel[] pixels) {
    this.pixels = pixels;
  }

  @Override
  public String toString() {
    return "Insertion Sort";
  }
}
