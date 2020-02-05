package io.libsoft.imsort.controller;

import io.libsoft.imsort.model.Environment;
import io.libsoft.imsort.utils.Constants;
import io.libsoft.imsort.view.AlgorithmChooser;
import io.libsoft.imsort.view.ChooseFile;
import io.libsoft.imsort.view.SortViewer;
import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

public class DrawController {


  private static final long MS_SLEEP = 5;

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
  @FXML
  public ChooseFile chooseFile;

  private int iterations = 1000;
  private boolean running;
  private Environment environment;
  private Updater updater;
  private File file;
  private File lastDirectory;
  private Size size;

  @FXML
  private void initialize() {
    file = new File("E:\\Projects\\JAVA\\im-sort\\src\\main\\resources\\lonamisa.jpg");
    size = new Size(Constants.SIZE,Constants.SIZE);
    initEnv();
    updater = new Updater();
  }

  private void initEnv() {
    environment = new Environment(
        Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.CV_LOAD_IMAGE_COLOR),
        size);
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
      chooseFile.setDisable(true);
    } else {
      chooser.setDisable(false);
      reset.setDisable(false);
      shuffleButton.setDisable(false);
      sortButton.setDisable(false);
      chooseFile.setDisable(false);
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


  public void chooser(ActionEvent actionEvent) {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("images (*.png/jpg)",
        "*.png", "*.jpg");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialDirectory(lastDirectory == null ? new File(".") : lastDirectory);
    if ((file = fileChooser.showOpenDialog(new Stage()))!= null){
      lastDirectory = file.getParentFile();
      initEnv();
    }
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
