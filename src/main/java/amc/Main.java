package amc;

import amc.ui.BookReaderUIController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    BookReaderUIController controller = new BookReaderUIController();
    controller.loadMeIntoNewWindow();
  }
  
  public static void main(String[] args) {
    launch(args);
  }
  
}
