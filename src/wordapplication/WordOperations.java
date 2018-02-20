package wordapplication;

import wordapplication.comparators.FrequencyComparator;
import wordapplication.gui.WordController;
import java.util.*;

public class WordOperations {

    private static final String AMOUNT = "Amount";
    private static final String AMOUNT_DIFFERENT = "Individual Amount";
    private static final String SORTING = "Sorting";
    private static final String FREQUENCY = "Frequency";
    private static final String CONCORDANCE = "Concordance";

    private Timer timer;

    private Set<String> individualWords;
    private Set<String> sortedWords;
    private SortedMap<String, Integer> frequencyOfWordsMap;
    private Map<String, Set<Integer>> concordanceMap;

    private String[] words;

    public WordOperations() {

        // Instantiation of objects: O(1)
        individualWords = new HashSet<>();
        sortedWords = new TreeSet<>(Comparator.reverseOrder());
        frequencyOfWordsMap = new TreeMap<>();
        concordanceMap = new HashMap<>();
    }

    /**
     * Calculates the amount of words in the provided text.
     * The total time complexity of this is O(1)
     */
    public String calculateAmountOfWords(int amountOfWords) {
        prepareWords(amountOfWords);

        // creating new Timer object: O(1)
        timer = new Timer(AMOUNT, amountOfWords);

        // .length: O(1)
        int amountOfWordsInText = words.length;

        timer.end();

        return "Total amount of words: " + amountOfWordsInText;
    }


    /**
     *  Checks the amount of different/individual words are in the given text.
     *  For this, a HashSet is used because it cannot hold duplicate values.
     *  A TreeSet wouldn't give an advantage as sorting doesn't matter here.
     *  The total time complexity of this method is O(n)
     */
    public String calculateAmountOfDifferentWords(int amountOfWords) {
        prepareWords(amountOfWords);

        // creating new Timer object: O(1)
        timer = new Timer(AMOUNT_DIFFERENT, amountOfWords);

        // Arrays.asList: O(1) because of unmodifiable list
        // addAll of HashSet: O(n)
        individualWords.addAll(Arrays.asList(words));

        // .size: O(1)
        int amountOfDifferentWords = individualWords.size();

        timer.end();

        return "Amount of different words: " + amountOfDifferentWords;
    }

    /**
     * A Set implementation can be used for this since this method only shows the different words, not all words.
     * A TreeSet is the right implementation for this use case because we want to have words sorted.
     * The Comparator to reverse alphabetical order in the constructor instantiation.
     * The total time complexity of this method is O(log n)
     */
    public String sortWords(int amountOfWords) {
        prepareWords(amountOfWords);

        // creating new Timer object: O(1)
        timer = new Timer(SORTING, amountOfWords);

        // addAll of TreeSet: O(log(n))
        sortedWords.addAll(Arrays.asList(words));

        timer.end();

        return sortedWords.toString();
    }

    /**
     * This method benefits from using a SortedSet (TreeSet) with Map.Entry as it's a way of saving key value pairs,
     * which is perfect for saving the frequency of words based on the word as key.
     * The reason TreeMap can not be used for sorting is because of the inability to override the compare method for comparing values.
     * It's only possible to compare keys in a TreeMap.
     * As every individual word will be counted and no duplicate words need to be saved individually, we can safely use a TreeSet.
     * The total time complexity of this function is O(n)
     */
    public String calculateFrequencyOfWords(int amountOfWords) {
        prepareWords(amountOfWords);

        // creating new Timer object: O(1)
        timer = new Timer(FREQUENCY, amountOfWords);

        // Loop: O(n)
        for(String word : words) {
            // HashMap get method: O(1)
            Integer value = frequencyOfWordsMap.get(word);
            if(value == null) {
                // HashMap put method:O(1)
                frequencyOfWordsMap.put(word, 1);
            }
            else {
                // HashMap put method: O(1)
                frequencyOfWordsMap.put(word, value + 1);
            }
        }

        // entriesSortedByValues method: O(log(n))
        SortedSet<Map.Entry<String, Integer>> valuesSorted = entriesSortedByValues(frequencyOfWordsMap);

        timer.end();

        return valuesSorted.toString();
    }

    /**
     * The concordance is a variant that looks like the frequency array
     * They both use a HashMap, but the frequency method uses an integer as counter and the concordance a set for the location of items
     * Because of the loop this method has a total time complexity of O(n)
     */
    public String createConcordance(int amountOfWords) {
        prepareWords(amountOfWords);

        // creating new Timer object: O(1)
        timer = new Timer(CONCORDANCE, amountOfWords);

        // Loop: O(n)
        for (int i = 0; i < words.length; i++) {
            // Array locator at index: O(1)
            String wordAt = words[i];
            // Get method of HashMap: O(1)
            Set<Integer> set = concordanceMap.get(wordAt);
            if (set == null) {
                set = new HashSet<>();
            }
            // Add method of Set (HashSet): O(1)
            set.add(i);
            // Put method of HashMap: O(1)
            concordanceMap.put(wordAt, set);
        }

        timer.end();

        return concordanceMap.toString();
    }

    /**
     * Convenience method for sorting a Map based on values instead of keys.
     * This method is needed because TreeMap can only compare on keys.
     * The total time complexity of this function is O(log(n))
     * @param map Map of which you want the values sorted
     * @return SortedSet containing the Map entries sorted on value
     */
    private SortedSet<Map.Entry<String, Integer>> entriesSortedByValues(Map<String, Integer> map) {
        SortedSet<Map.Entry<String, Integer>> sortedEntries = new TreeSet<>(new FrequencyComparator());
        // addAll method on TreeSet: O(log(n))
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

    /**
     * Convenience method to prepare the text for operations
     *
     */
    public void prepareWords(int amountOfWords) {

        String desiredWordString = String.join("",
                Collections.nCopies(amountOfWords / 15, WordController.DEFAULT_TEXT));

        // .split: should be O(n) with this regular expression as it's not particularly complex
        // (no backreference, concatenation or alternation etc.)
        words = desiredWordString.split("\\W+");

        // for-loop: O(n)
        for (int i = 0; i < words.length; i++) {
            // Replacing item in words array: O(1)
            words[i] = words[i].toLowerCase();
        }
    }
}
