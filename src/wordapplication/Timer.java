package wordapplication;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {

    private static final Logger LOGGER = Logger.getLogger(Timer.class.getName());

    private long beginTime;

    private String operation;
    private int amountOfWords;

    public Timer(String operation, int amountOfWords) {
        this.operation = operation;
        this.amountOfWords = amountOfWords;
        start();
    }

    public void start() {
        beginTime = System.currentTimeMillis();
    }

    public void end() {
        long endTime = System.currentTimeMillis();
        long timeDifference = endTime - beginTime;

        if (timeDifference == 0) {
            LOGGER.log(Level.INFO, "Computation of {0} took less than {1}ms for {2} words",
                    new Object[]{operation, timeDifference, amountOfWords});
        } else {
            LOGGER.log(Level.INFO, "Computation of {0} took {1}ms for {2} words",
                    new Object[]{operation, timeDifference, amountOfWords});
        }
    }
}
