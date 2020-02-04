package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class MergeSort implements Sorter {

  private Pixel[] pixels;

  public void mergeSort() {
    if (pixels == null) {
      return;
    }

    if (pixels.length > 1) {
      int mid = pixels.length / 2;

      Pixel[] left = new Pixel[mid];
      for (int i = 0; i < mid; i++) {
        left[i] = pixels[i];
      }

      Pixel[] right = new Pixel[pixels.length - mid];
      for (int i = mid; i < pixels.length; i++) {
        right[i - mid] = pixels[i];
      }
//      mergeSort(left);
//      mergeSort(right);

      int i = 0;
      int j = 0;
      int k = 0;

      // Merge left and right arrays
      while (i < left.length && j < right.length) {
        if (pixels[i].compareTo(pixels[j]) <= 0) {
          pixels[k] = left[i];
          i++;
        } else {
          pixels[k] = right[j];
          j++;
        }
        k++;
      }
      // Collect remaining elements
      while (i < left.length) {
        pixels[k] = left[i];
        i++;
        k++;
      }
      while (j < right.length) {
        pixels[k] = right[j];
        j++;
        k++;
      }
    }
  }

  @Override
  public void start() throws NullPointerException {
    if (pixels == null) {
      throw new NullPointerException("The pixels must be set.");
    }
    try {
      Thread t = new Thread(() -> {
        mergeSort();
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
