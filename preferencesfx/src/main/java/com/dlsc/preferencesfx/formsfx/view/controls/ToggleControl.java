package com.dlsc.preferencesfx.formsfx.view.controls;

import com.dlsc.formsfx.model.structure.BooleanField;
import javafx.scene.control.Label;
import org.controlsfx.control.ToggleSwitch;

/**
 * Displays a control for boolean values with a toggle from ControlsFX.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public class ToggleControl extends SimpleControl<BooleanField, ToggleSwitch> {
  public static final double NEGATIVE_LABEL_INSETS = -17.3;

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeParts() {
    super.initializeParts();

    fieldLabel = new Label(field.labelProperty().getValue());

    node = new ToggleSwitch();
    node.getStyleClass().add("toggle-control");
    node.setSelected(field.getValue());
    updateText();
  }

  /**
   * Set the toggle text on state change.
   */
  public void updateText() {
    node.setText(field.getValue() ? "On" : "Off");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void layoutParts() {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupBindings() {
    super.setupBindings();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupValueChangedListeners() {
    super.setupValueChangedListeners();
    field.userInputProperty().addListener((observable, oldValue, newValue) -> {
      node.setSelected(Boolean.parseBoolean(field.getUserInput()));
      updateText();
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setupEventHandlers() {
    node.selectedProperty().addListener((observable, oldValue, newValue) -> {
      field.userInputProperty().setValue(String.valueOf(newValue));
    });
  }

}


