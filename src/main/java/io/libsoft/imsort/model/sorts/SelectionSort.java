package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class SelectionSort implements Sorter {

  private Pixel[] pixels;


  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    try {
      Thread t = new Thread(() -> {
        sort();
      });
      t.start();
      t.join();
    } catch (InterruptedException ignored) {
    }
  }


  private void sort() {
    Pixel x;
    int min;
    for (int i = 0; i < pixels.length - 1; i++) {
      min = i;
      for (int j = i + 1; j < pixels.length; j++) {
        if (pixels[j].compareTo(pixels[min]) < 0) {
          min = j;
        }
      }

      x = pixels[min];
      for (int j = min; j > i; j--) {
        pixels[j] = pixels[j - 1];
      }
      pixels[i] = x;
    }
  }

  @Override
  public void setPixels(Pixel[] pixels) {
    this.pixels = pixels;
  }

  @Override
  public String toString() {
    return "Selection Sort";
  }

}
