package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class QuickSort implements Sorter {


  private Pixel[] pixels;
  private int count;

  int partition(int low, int high) {
    Pixel pivot = pixels[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (pixels[j].compareTo(pivot) < 0) {
        i++;
        swap(pixels, i, j);
      }
    }
    swap(pixels, i + 1, high);

    return i + 1;
  }


  void sort(int low, int high) {
    if (low < high) {
      if(count % 20==0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException ignored) {}
      }
      count++;
      int pi = partition(low, high);
      sort(low, pi - 1);
      sort(pi + 1, high);
    }
  }

  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    count = 0;
    try {
      Thread t = new Thread(() -> {
        sort(0, pixels.length - 1);
      });
      t.start();
      t.join();
    } catch (InterruptedException e) {
    }
  }

  @Override
  public void setPixels(Pixel[] pixels) {
    this.pixels = pixels;
  }

  @Override
  public String toString() {
    return "QuickSort";
  }
}
