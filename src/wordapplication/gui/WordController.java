package wordapplication.gui;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import wordapplication.WordOperations;

public class WordController implements Initializable {
    
   public static final String DEFAULT_TEXT =   "Een, twee, drie, vier\n" +
                                                "Hoedje van, hoedje van\n" +
                                                "Een, twee, drie, vier\n" +
                                                "Hoedje van papier\n";
   private static final String ONE_MILLION_CONCORDANCE_MESSAGE = "The concordance of 1 million words isn't displayed because it " +
           "would freeze the application."
           + System.lineSeparator() + "Check the console for the calculation time.";

    private static final int TEN_THOUSAND_WORDS = 10000;
    private static final int ONE_MILLION_WORDS = 1000000;

    private String outputTenThousandWords;
    private String outputOneMillionWords;

    @FXML
    private TextArea taInput;
    @FXML
    private TextArea taOutput;

    private WordOperations wordOperations;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
        wordOperations = new WordOperations();
    }
    
    @FXML
    private void amountAction(ActionEvent event) {
        outputTenThousandWords = wordOperations.calculateAmountOfWords(TEN_THOUSAND_WORDS) + System.lineSeparator() +
                                wordOperations.calculateAmountOfWords(ONE_MILLION_WORDS);
        outputOneMillionWords = wordOperations.calculateAmountOfDifferentWords(TEN_THOUSAND_WORDS)+ System.lineSeparator() +
                wordOperations.calculateAmountOfDifferentWords(ONE_MILLION_WORDS);
        populateOutput();
    }

    @FXML
    private void sortAction(ActionEvent event) {
        outputTenThousandWords =  wordOperations.sortWords(TEN_THOUSAND_WORDS);
        outputOneMillionWords = wordOperations.sortWords(ONE_MILLION_WORDS);
        populateOutput();
    }

    @FXML
    private void frequencyAction(ActionEvent event) {
        outputTenThousandWords = wordOperations.calculateFrequencyOfWords(TEN_THOUSAND_WORDS);
        outputOneMillionWords = wordOperations.calculateFrequencyOfWords(ONE_MILLION_WORDS);
        populateOutput();
    }

    @FXML
    private void concordanceAction(ActionEvent event) {
        outputTenThousandWords = wordOperations.createConcordance(TEN_THOUSAND_WORDS);
        // Setting the output for one million words has been removed on purpose
        // Having to print the location of 1 million words to the screen locks up the program.
        // Instead of this, an informational message will be printed to the screen.
        wordOperations.createConcordance(ONE_MILLION_WORDS);
        outputOneMillionWords = ONE_MILLION_CONCORDANCE_MESSAGE;
        populateOutput();
    }

    /**
     * Shows the user the gathered information in a readable format.
     */
    public void populateOutput() {
        if (Objects.equals(taOutput.getText(), "")) {
            taOutput.setText(outputTenThousandWords + System.lineSeparator() + outputOneMillionWords);
        } else {
            taOutput.setText(taOutput.getText() + System.lineSeparator() +
                    outputTenThousandWords + System.lineSeparator() + outputOneMillionWords);
        }
    }
   
}
