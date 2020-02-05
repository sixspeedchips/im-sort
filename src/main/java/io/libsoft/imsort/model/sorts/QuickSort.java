package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;
import io.libsoft.imsort.utils.Constants;
import java.security.SecureRandom;
import java.util.Random;

public class QuickSort implements Sorter {


  private Pixel[] pixels;
  private int count;
  private int threads;
  private Random random = new SecureRandom();

  int partition(int low, int high) {
    Pixel pivot = pixels[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if(count++ % (Constants.ITERATIONS*2) ==0){
        try {
          Thread.sleep(1);
        } catch (InterruptedException ignored) {}
      }
      if (pixels[j].compareTo(pivot) < 0) {
        i++;
        swap(pixels, i, j);
      }
    }
    swap(pixels, i + 1, high);

    return i + 1;
  }
  private int partitionRandom(int low, int hi){
    int r = random.nextInt(hi - low) + low;
    swap(pixels, r, low);
    return partition(low, hi);

  }

  void sort(int low, int high) {

    if (low < high) {
//      sort(low, pi - 1);
//      sort(pi + 1, high);
      int pi = partitionRandom(low, high);
      if (threads < Constants.MAX_THREADS){
        Thread t1 = new Thread(()->{sort(low, pi - 1);});
        Thread t2 = new Thread(()->{sort(pi + 1, high);});
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
        sort(low, pi - 1);
        sort(pi + 1, high);
      }

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
