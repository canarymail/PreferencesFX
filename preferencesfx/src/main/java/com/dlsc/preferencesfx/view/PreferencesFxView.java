package com.dlsc.preferencesfx.view;

import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.PreferencesFxModel;
import javafx.geometry.Side;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.MasterDetailPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the master view, which is used to show all view parts in {@link PreferencesFxDialog}.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public class PreferencesFxView extends BorderPane implements View {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(PreferencesFxView.class.getName());

  CategoryController categoryController;
  public MasterDetailPane preferencesPane;
  public VBox contentBox;
  private PreferencesFxModel model;
  public NavigationView navigationView;
  public BreadCrumbView breadCrumbView;

  /**
   * Displays all of the view parts, representing the master view.
   *
   * @param model              the model of PreferencesFX
   * @param navigationView     left part of the view
   * @param breadCrumbView     breadcrumb bar on the top of the {@code categoryController}
   * @param categoryController shows the {@link CategoryView} of the
   *                           currently selected {@link Category}
   */
  public PreferencesFxView(
      PreferencesFxModel model,
      NavigationView navigationView,
      BreadCrumbView breadCrumbView,
      CategoryController categoryController
  ) {
    this.breadCrumbView = breadCrumbView;
    this.navigationView = navigationView;
    this.model = model;
    this.categoryController = categoryController;
    init();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeSelf() {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeParts() {
    preferencesPane = new MasterDetailPane();
    contentBox = new VBox();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void layoutParts() {
    // but always add the categoryController
    contentBox.getChildren().addAll(breadCrumbView, categoryController);
    VBox.setVgrow(categoryController, Priority.ALWAYS);

    if (!model.isOneCategoryLayout()) {
      preferencesPane.setDetailSide(Side.LEFT);
      preferencesPane.setDetailNode(navigationView);
      preferencesPane.setMasterNode(contentBox);
      setCenter(preferencesPane);
    } else {
      setCenter(new StackPane(contentBox));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void bindFieldsToModel() {

  }
}
