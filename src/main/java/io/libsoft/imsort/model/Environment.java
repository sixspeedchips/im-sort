package io.libsoft.imsort.model;

import io.libsoft.imsort.model.sorts.BubbleSort;
import io.libsoft.imsort.model.sorts.InsertionSort;
import io.libsoft.imsort.model.sorts.Sorter;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Environment {


  private Mat original;
  private ShuffleImage shuffler;

  public Environment(Mat raw, Size size) {
    original = Mat.zeros(size, CvType.CV_8SC3);
    Imgproc.resize(raw, original, size, 0, 0, Imgproc.INTER_AREA);
    shuffler = new ShuffleImage(original);
  }


  public boolean shuffle() {
    return shuffler.shuffle();
  }

  public void setSort(Sorter sort){
    this.shuffler.setSorter(sort);

  }

  public Mat getOriginal() {
    return original;
  }

  public Mat getShuffler() {
    return shuffler.getMat();
  }



  public void reset(){
    shuffler.reset();
  }

  public boolean sort() {
    shuffler.sort();
    return true;
  }
}