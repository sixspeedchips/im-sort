package io.libsoft.imsort.model;

import io.libsoft.imsort.model.sorts.Sorter;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import org.opencv.core.Mat;

public class ShuffleImage {


  private Pixel[] pixels;
  private Mat mat;
  private Random random = new SecureRandom();
  private int count = 0;
  private Sorter sort;

  public  ShuffleImage(Mat original) {
    mat = new Mat(original.size(), original.type());
    pixels = new Pixel[(int) original.size().height * (int) original.size().width];
    int count = 0;
    for (int i = 0; i < original.rows(); i++) {
      for (int j = 0; j < original.cols(); j++) {
        pixels[count++] = new Pixel(original.get(i, j), i, j);
      }
    }

  }

  public void sort(){
    sort.start();
  }

  private void swap(int i, int j) {
    Pixel temp = pixels[i];
    pixels[i] = pixels[j];
    pixels[j] = temp;
  }

  public boolean shuffle() {

    if (count < pixels.length-1){
      int idx = random.nextInt(pixels.length-count)+ count;
      swap(count,idx);
      count++;
      return false;
    } else {
      return true;
    }
  }

  public Mat getMat() {
    int count = 0;
    for (int i = 0; i < mat.rows(); i++) {
      for (int j = 0; j < mat.cols(); j++) {
        mat.put(i, j, pixels[count++].getColors());
      }
    }
    return mat;
  }

  public void completeSort() {
    Arrays.sort(pixels);
  }

  public void setSorter(Sorter sort) {
    sort.setPixels(pixels);
    this.sort = sort;
  }

  public void reset() {
    count = 0;
  }


}
