package io.libsoft.imsort.view;

import io.libsoft.imsort.model.sorts.BubbleSort;
import io.libsoft.imsort.model.sorts.InsertionSort;
import io.libsoft.imsort.model.sorts.MergeSort;
import io.libsoft.imsort.model.sorts.Sorter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class AlgorithmChooser extends ComboBox<Sorter> {

  public AlgorithmChooser() {

    setItems(FXCollections.observableArrayList(new BubbleSort(), new InsertionSort(), new MergeSort()));

    getSelectionModel().selectFirst();


  }

  public AlgorithmChooser(
      ObservableList<Sorter> items) {
    super(items);
  }
}
