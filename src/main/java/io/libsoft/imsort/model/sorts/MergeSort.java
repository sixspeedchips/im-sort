package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public class MergeSort implements Sorter {

  private Pixel[] pixels;

  public static void mergeSort(Pixel[] array) {
    if (array == null) {
      return;
    }

    if (array.length > 1) {
      int mid = array.length / 2;

      Pixel[] left = new Pixel[mid];
      for (int i = 0; i < mid; i++) {
        left[i] = array[i];
      }

      Pixel[] right = new Pixel[array.length - mid];
      for (int i = mid; i < array.length; i++) {
        right[i - mid] = array[i];
      }
      mergeSort(left);
      mergeSort(right);

      int i = 0;
      int j = 0;
      int k = 0;

      // Merge left and right arrays
      while (i < left.length && j < right.length) {
        if (array[i].compareTo(array[j]) <= 0) {
          array[k] = left[i];
          i++;
        } else {
          array[k] = right[j];
          j++;
        }
        k++;
      }
      // Collect remaining elements
      while (i < left.length) {
        array[k] = left[i];
        i++;
        k++;
      }
      while (j < right.length) {
        array[k] = right[j];
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
        mergeSort(pixels);
        System.out.println("MergeSort.start");
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
