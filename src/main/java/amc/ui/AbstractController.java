package amc.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class AbstractController {

private Parent root;
  
  public void loadMeIntoNewWindow() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(getFxml()));
    fxmlLoader.setController(this);
    Parent root = null;
    try {
      root = (Parent) fxmlLoader.load();
      setRoot(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(root);
    scene.setFill(Color.TRANSPARENT);
    scene.getStylesheets().add("/book-reader.css");
    
    Stage stage = new Stage();
    stage.initStyle(StageStyle.TRANSPARENT);
    stage.setScene(scene);
    stage.show();
  }
  
  protected abstract String getFxml();

  public Parent getRoot() {
    return root;
  }

  public void setRoot(Parent root) {
    this.root = root;
  }
}
