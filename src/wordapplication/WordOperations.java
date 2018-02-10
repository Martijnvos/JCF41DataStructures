package wordapplication;

import wordapplication.gui.WordController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordOperations {

    private long beginTime;
    private int amountOfWords;

    public WordOperations(int amountOfWords) {
        this.amountOfWords = amountOfWords;

        WordController.DEFAULT_TEXT = WordController.DEFAULT_TEXT.replace("/n", "");
        WordController.DEFAULT_TEXT = WordController.DEFAULT_TEXT.replace(",", "");
    }

    public void calculateAmountOfWords() {
        startTimer();

        endTimer();
    }

    public void calculateAmountOfDifferentWords() {

    }

    public void sortWords() {

    }

    public void calculateFrequencyOfWords() {

    }

    public void createConcordance() {

    }

    public void startTimer() {
        beginTime = System.currentTimeMillis();
    }

    public void endTimer() {
        long endTime = System.currentTimeMillis();
        long timeDifference = endTime - beginTime;

        System.out.println("Computation took " + timeDifference + "ms");
    }

}
