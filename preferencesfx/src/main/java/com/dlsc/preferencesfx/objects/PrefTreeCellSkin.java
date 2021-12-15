package com.dlsc.preferencesfx.objects;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.skin.TreeCellSkin;

public class PrefTreeCellSkin<T> extends TreeCellSkin<T> {

  public PrefTreeCellSkin(TreeCell<T> treeCell) {
    super(treeCell);
  }

  @Override
  protected void layoutChildren(double v, double v1, double v2, double v3) {
    setIndent(24);
    super.layoutChildren(v, v1, v2, v3);

    Node disclosureNode = getSkinnable().getDisclosureNode();
    if (disclosureNode != null) {
      positionInArea(disclosureNode, v2 - 30, v3 - 12, 30, 30, 0, HPos.CENTER, VPos.CENTER);
    }
  }

}