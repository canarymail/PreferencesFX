package com.dlsc.preferencesfx.objects;

import com.dlsc.preferencesfx.model.Category;
import javafx.scene.control.Skin;
import javafx.scene.control.TreeCell;

public class PrefTreeCell extends TreeCell<Category> {

  @Override
  protected void updateItem(Category category, boolean empty) {
    super.updateItem(category, empty);
    textProperty().unbind();
    if (empty || category == null) {
      setText(null);
      setGraphic(null);
    } else {
      textProperty().bind(category.descriptionProperty());
      setGraphic(category.getItemIcon());
    }
  }

  @Override
  public void updateSelected(boolean b) {
    if (getItem() != null && getItem().displayView) {
      super.updateSelected(b);
    }
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new PrefTreeCellSkin<Category>(this);
  }

  public void setCellPressed(boolean pressed) {
    this.setPressed(false);
  }
}
