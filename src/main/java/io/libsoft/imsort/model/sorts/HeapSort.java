package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;
import io.libsoft.imsort.utils.Constants;

public class HeapSort implements Sorter {


  private Pixel[] pixels;
  private long count;

  public void sort() {
    int n = pixels.length;
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(n, i);
    }
    for (int i = n - 1; i >= 0; i--) {
      if ((++count) % (Constants.ITERATIONS/2) == 0) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException ignored) {}
      }
      swap(pixels, 0, i);
      heapify(i, 0);
    }
  }

  void heapify(int n, int i) {

    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l < n && pixels[l].compareTo(pixels[largest]) > 0) {
      largest = l;
    }

    if (r < n && pixels[r].compareTo(pixels[largest]) > 0) {
      largest = r;
    }

    if (largest != i) {
      swap(pixels, i, largest);
      heapify(n, largest);
    }
  }


  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    try {
      Thread t = new Thread(this::sort);
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
    return "Heap sort";
  }
}
