package wordapplication.comparators;

import java.util.Comparator;
import java.util.Map;

public class FrequencyComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        int result = o1.getValue().compareTo(o2.getValue());
        return result != 0 ? result : 1;
    }

}
