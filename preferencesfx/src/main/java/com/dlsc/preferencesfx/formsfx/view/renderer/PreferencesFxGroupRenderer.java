package com.dlsc.preferencesfx.formsfx.view.renderer;

import com.dlsc.formsfx.model.structure.Element;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.NodeElement;
import com.dlsc.preferencesfx.formsfx.view.controls.SimpleControl;
import com.dlsc.preferencesfx.util.PreferencesFxUtils;
import java.util.List;
import java.util.stream.Collectors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * This class renders a group for a PreferencesFx form.
 *
 * @author Sacha Schmid
 * @author Rinesch Murugathas
 * @author François Martin
 * @author Marco Sanfratello
 */
public class PreferencesFxGroupRenderer {

  /**
   * Add the controls in the GridPane in a 12-column layout. If a control
   * takes up too much horizontal space, wrap it to the next row.
   */
  private static final int COLUMN_COUNT = 12;
  public static final int GRID_MARGIN = 10;
  public Label titleLabel;
  public GridPane grid;
  public PreferencesFxGroup preferencesGroup;

  /**
   * This is the constructor to pass over data.
   *
   * @param preferencesGroup The PreferencesGroup which gets rendered.
   */
  public PreferencesFxGroupRenderer(PreferencesFxGroup preferencesGroup, GridPane grid) {
    this.preferencesGroup = preferencesGroup;
    this.grid = grid;
    preferencesGroup.setRenderer(this);
    init();
  }

  /**
   * Calls all the other methods for easier initialization.
   */
  public void init() {
    this.initializeParts();
    this.layoutParts();
    this.setupBindings();
  }

  /**
   * Initializes all parts of the rendered group.
   */
  public void initializeParts() {
    titleLabel = new Label();
  }

  /**
   * Defines the layout of the rendered group.
   */
  public void layoutParts() {
    StringBuilder styleClass = new StringBuilder("group");

    // if there are no rows yet, getRowCount returns -1, in this case the next row is 0
    int nextRow = PreferencesFxUtils.getRowCount(grid) + 1;

    // Only when the preferencesGroup has a title
    if (preferencesGroup.getTitle() != null) {
      grid.add(titleLabel, 0, nextRow++, 2, 1);
      styleClass.append("-title");
      titleLabel.getStyleClass().add("group-title");
    }

    List<Element> elements = preferencesGroup.getElements().stream()
        .map(Element.class::cast)
        .collect(Collectors.toList());
    styleClass.append("-setting");

    int currRow = nextRow;
    for (int i = 0; i < elements.size(); i++) {
      // add to GridPane
      Element element = elements.get(i);
      VBox box = new VBox();
      if (element instanceof Field) {
        SimpleControl c = (SimpleControl) ((Field)element).getRenderer();
        c.setField((Field)element);
        c.getFieldLabel().getStyleClass().add(styleClass.toString() + "-label");
        c.getNode().getStyleClass().add(styleClass.toString() + "-node");
        box.getChildren().addAll(c.getFieldLabel(), c.getNode());
      } else if (element instanceof NodeElement) {
        box.getChildren().addAll(((NodeElement<?>) element).getNode());
      }

      grid.add(box, 0, currRow++, 1, 1);

      // Styling
      box.setAlignment(Pos.CENTER_LEFT);
      box.setSpacing(PreferencesFxFormRenderer.V_GAP);
      GridPane.setHgrow(box, Priority.SOMETIMES);
      GridPane.setValignment(box, VPos.CENTER);

      // additional styling for the last setting
      if (i == elements.size() - 1) {
        styleClass.append("-last");
        GridPane.setMargin(
            box,
            new Insets(0, 0, PreferencesFxFormRenderer.GROUP_SPACING * 4, 0)
        );
      }
    }
  }

  /**
   * Sets up bindings of the rendered group.
   */
  public void setupBindings() {
    titleLabel.textProperty().bind(preferencesGroup.titleProperty());
  }

  /**
   * Adds a style class to the group.
   *
   * @param name of the style class to be added to the group
   */
  public void addStyleClass(String name) {
    titleLabel.getStyleClass().add(name);
  }

  /**
   * Removes a style class from the group.
   *
   * @param name of the class to be removed from the group
   */
  public void removeStyleClass(String name) {
    titleLabel.getStyleClass().remove(name);
  }

  public Label getTitleLabel() {
    return titleLabel;
  }
}
