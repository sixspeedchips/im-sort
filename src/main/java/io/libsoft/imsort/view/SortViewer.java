package io.libsoft.imsort.view;


import io.libsoft.imsort.model.Environment;
import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class SortViewer extends Pane {


  private ImageView original;
  private ImageView shuffled;
  private MatOfByte byteMat;
  private Environment environment;


  public SortViewer() {
    original = new ImageView();
    shuffled = new ImageView();
    byteMat = new MatOfByte();
    getChildren().addAll(new HBox(original, shuffled));

  }

  public void setEnv(Environment environment) {
    this.environment = environment;
  }

  public void draw() {
    original.setImage(getImage(environment.getOriginal()));
    shuffled.setImage(getImage(environment.getShuffler()));
  }


  private Image getImage(Mat mat) {
    Imgcodecs.imencode(".bmp", mat, byteMat);
    return new Image(new ByteArrayInputStream(byteMat.toArray()));
  }

}
