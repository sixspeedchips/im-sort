package io.libsoft.imsort.model.sorts;

import io.libsoft.imsort.model.Pixel;

public interface Sorter {

  void start() throws NullPointerException;
  void setPixels(Pixel[] pixels);

  default void swap(Pixel[] pixels, int i, int j) {
    Pixel temp = pixels[i];
    pixels[i] = pixels[j];
    pixels[j] = temp;
  }
}
