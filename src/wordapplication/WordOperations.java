package wordapplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordOperations {

    private long beginTime;
    private int loopAmount;

    public WordOperations(int loopAmount) {
        this.loopAmount = loopAmount;
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

        if (timeDifference == 0L) return;

        System.out.println("Computation took " + timeDifference + "ms");
    }

}
