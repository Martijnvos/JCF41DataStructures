package wordapplication.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import wordapplication.WordOperations;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WordController implements Initializable {
    
   public static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n";
    
    @FXML
    private Button btAmount;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSort;
    @FXML
    private Button btFrequency;
    @FXML
    private Button btConcordance;
    @FXML
    private TextArea taOutput;

    private WordOperations wordOperations;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
        wordOperations = new WordOperations(10000 / 15);
    }
    
    @FXML
    private void amountAction(ActionEvent event) {
        wordOperations.calculateAmountOfWords();

        wordOperations.calculateAmountOfDifferentWords();
    }

    @FXML
    private void sortAction(ActionEvent event) {
        wordOperations.sortWords();
    }

    @FXML
    private void frequencyAction(ActionEvent event) {
        wordOperations.calculateFrequencyOfWords();
    }

    @FXML
    private void concordanceAction(ActionEvent event) {
        wordOperations.createConcordance();
    }
   
}
