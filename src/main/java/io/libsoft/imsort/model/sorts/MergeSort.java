package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;
import io.libsoft.imsort.utils.Constants;

public class MergeSort implements Sorter {

  private int threads = 0;
  private Pixel[] pixels;
  private int count;

  void merge(int l, int m, int r) {

    int n1 = m - l + 1;
    int n2 = r - m;

    Pixel[] L = new Pixel[n1];
    Pixel[] R = new Pixel[n2];
    for (int i = 0; i < n1; ++i) {
      L[i] = pixels[l + i];
    }
    for (int j = 0; j < n2; ++j) {
      R[j] = pixels[m + 1 + j];
    }

    int i = 0, j = 0;
    int k = l;
    while (i < n1 && j < n2) {
      if (L[i].compareTo(R[j]) <= 0) {
        pixels[k] = L[i];
        i++;
      } else {
        pixels[k] = R[j];
        j++;
      }
      k++;


      if (count++ % Constants.ITERATIONS*200 == 0) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException ignored) {}
      }

    }
    while (i < n1) {
      pixels[k] = L[i];
      i++;
      k++;
    }
    while (j < n2) {
      pixels[k] = R[j];
      j++;
      k++;
    }
  }

  void sort(int l, int r) {

    if (l < r) {
      int m = l + (r - l) / 2;
      if (threads < Constants.MAX_THREADS){
        Thread t1 = new Thread(()->{sort(l, m);});
        Thread t2 = new Thread(()->{sort(m + 1, r);});
        threads+=2;
        t1.start();
        t2.start();
        try {
          t1.join();
          t2.join();
          threads-=2;
        } catch (InterruptedException ignored) {
        }
      }
      else {
        sort(l, m);
        sort(m + 1, r);
      }
      merge(l, m, r);
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
    } catch (InterruptedException ignored) {
    }
  }

  @Override
  public void setPixels(Pixel[] pixels) {
    this.pixels = pixels;
  }

  @Override
  public String toString() {
    return "Merge Sort";
  }
}
