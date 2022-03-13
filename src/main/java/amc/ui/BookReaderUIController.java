package amc.ui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import amc.BookReader;
import amc.WordStatistics;
import amc.splitter.WordSplitterFactory;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BookReaderUIController extends AbstractController {

  @FXML private AnchorPane backgroundPane;
  
  @FXML private TextField filePath;
  
  @FXML private Button fileSelectButton, closeButton;
  
  @FXML private TextField regex;
  
  @FXML private Button scanButton;
  
  @FXML private AnchorPane chartPane;
  
  @FXML private TextField wordCount, avgWordLength;
  
  @FXML private Label popularWordLength;
    
  @FXML private BarChart<String, Integer> resultChart;
  
  @FXML private ComboBox<String> wordSplitterImpl;
  
  @FXML private Label errorText;
  
  @FXML private AnchorPane dragPane;
  
  private XYChart.Series<String, Integer> barDataSeries;
  
  private Map<Integer, XYChart.Data<String, Integer>> dataPoints;
  
  private BookReader reader;
  
  private static double xOffset = 0;
  private static double yOffset = 0;
  
  @FXML
  public void initialize() {
    backgroundPane.setBackground(new Background(new BackgroundFill(new Color(0.05, 0.05, 0.2, 0.7), CornerRadii.EMPTY, Insets.EMPTY)));
    
    dragPane.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        xOffset = ((Stage) dragPane.getScene().getWindow()).getX() - event.getScreenX();
        yOffset = ((Stage) dragPane.getScene().getWindow()).getY() - event.getScreenY();
      }
    });

    dragPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        ((Stage) dragPane.getScene().getWindow()).setX(event.getScreenX() + xOffset);
        ((Stage) dragPane.getScene().getWindow()).setY(event.getScreenY() + yOffset);
      }
    });
    
    errorText.setText("");
    
    resultChart.getXAxis().setTickLabelFill(Color.WHITE);
    resultChart.getYAxis().setTickLabelFill(Color.WHITE);
    resultChart.setTitle("Word Stats");
    resultChart.setAnimated(false);
    
    dataPoints = new HashMap<>();
    
    for(WordSplitterFactory impl : WordSplitterFactory.values()) {
      wordSplitterImpl.getItems().add(impl.name());
    }
    wordSplitterImpl.getSelectionModel().select(0);
    
    reader = new BookReader();
    
    scanButton.setOnMouseClicked(event -> {
      errorText.setText("");
      
      dataPoints.clear();
      barDataSeries = new XYChart.Series<>();
      barDataSeries.setName("Words by length");
      
      resultChart.getData().clear();
      resultChart.getData().add(barDataSeries);
            
      new Thread(() -> {
        try {
          reader.setWordSplitter(WordSplitterFactory.valueOf(wordSplitterImpl.getSelectionModel().getSelectedItem()).createWordSplitter(regex.getText()));
          WordStatistics readBook = reader.readBook(new File(filePath.getText()));
          System.out.println(readBook);
          updateScreenStats(readBook);
        } catch (Exception e) {
          Platform.runLater(() -> {
            errorText.setText(e.getMessage());
          });
        }
      }).start();
    });
    
    closeButton.setOnMouseClicked( event -> {
      Platform.exit();
      System.exit(0);
    });
  }
  
  private void updateScreenStats(WordStatistics stats) {
    Platform.runLater(() -> {
      stats.getWordMap().keySet().forEach(key -> {
        if(dataPoints.containsKey(key)) { //we already have a data point, so just update it.
          dataPoints.get(key).setYValue(stats.getWordMap().get(key));
        } else {
          Data<String, Integer> data = new XYChart.Data<String, Integer>(Integer.toString(key), stats.getWordMap().get(key));
          dataPoints.put(key, data);
          barDataSeries.getData().add(data);
        }
      });

      wordCount.setText(Integer.toString(stats.getWordCount()));
      avgWordLength.setText(Double.toString(stats.calculateAverageWordLength()));
    });
  }

  protected String getFxml() {
    return "amc/ui/BookReader.fxml";
  }
}
