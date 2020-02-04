package io.libsoft.imsort.controller;

import io.libsoft.imsort.model.Environment;
import io.libsoft.imsort.view.AlgorithmChooser;
import io.libsoft.imsort.view.SortViewer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

public class DrawController {


  private static final long MS_SLEEP = 16;

  @FXML
  public Button shuffleButton;

  @FXML
  public Button sortButton;

  @FXML
  public Button reset;

  @FXML
  public SortViewer sortViewer;
  @FXML
  public AlgorithmChooser chooser;

  private int iterations = 1000;
  private boolean running;
  private Environment environment;
  private Updater updater;


  @FXML
  private void initialize() {
    initEnv();
    updater = new Updater();
  }

  private void initEnv() {
    environment = new Environment(
        Imgcodecs.imread("E:\\Projects\\JAVA\\ga-draw-java\\src\\main\\resources\\me2.jpg",
            Imgcodecs.CV_LOAD_IMAGE_COLOR),
        new Size(300, 300));
    environment.setSort(chooser.getSelectionModel().getSelectedItem());
    sortViewer.setEnv(environment);
    updateView();
  }


  private void updateView() {
    sortViewer.draw();
  }

  @FXML
  public void reset(ActionEvent actionEvent) {
    initEnv();
  }

  @FXML
  public void shuffle(ActionEvent actionEvent) {
    startShuffle();
    updateControls();
  }


  private void startShuffle() {
    running = true;
    updater.start();
    new ShufflerThread().start();
    updateControls();
  }

  public void sort(ActionEvent actionEvent) {
    startSort();
    updateControls();
  }

  private void startSort() {
    running = true;
    updater.start();
    new SorterThread().start();
    updateControls();
  }

  private void updateControls() {
    if (running) {
      reset.setDisable(true);
      shuffleButton.setDisable(true);
      chooser.setDisable(true);
      sortButton.setDisable(true);
    } else {
      chooser.setDisable(false);
      reset.setDisable(false);
      shuffleButton.setDisable(false);
      sortButton.setDisable(false);
      updateView();
    }
  }

  public void stop() {
    running = false;
    updater.stop();
    updateControls();
  }


  public void selectAlgorithm(ActionEvent actionEvent) {
    environment.setSort(chooser.getSelectionModel().getSelectedItem());
  }


  class ShufflerThread extends Thread {


    @Override
    public void run() {
      while (running) {
        for (int i = 0; i < iterations; i++) {
          if (environment.shuffle()) {
            DrawController.this.stop();
            environment.reset();
          }
        }
        try {
          Thread.sleep(MS_SLEEP);
        } catch (InterruptedException ignored) {
        }
      }


    }
  }

  class SorterThread extends Thread {

    @Override
    public void run() {
      environment.sort();
      DrawController.this.stop();
    }
  }


  private class Updater extends AnimationTimer {

    @Override
    public void handle(long now) {
      updateView();
    }

  }
}
