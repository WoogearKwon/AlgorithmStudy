import practices.Sort.*;
import practices.algorithm.GreatestCommonDivisor;
import practices.algorithm.PrimeNumber;
import practices.etc.QueenAlgorithm;
import practices.etc.StringSearch;
import practices.Practice;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Practice> practices = new HashMap<>();
        practices.put(0, new QueenAlgorithm());
        practices.put(1, new StringSearch());
        practices.put(2, new BubbleSort());
        practices.put(3, new CompareTest());
        practices.put(4, new FrequencySort());
        practices.put(5, new HeapSort());
        practices.put(6, new MergeSort());
        practices.put(7, new QuickSort());
        practices.put(8, new SelectionSort());
        practices.put(9, new ShellSort());
        practices.put(10, new ShuttleSort());
        practices.put(11, new PracticeField());
        practices.put(12, new PrimeNumber());
        practices.put(13, new GreatestCommonDivisor());

        practices.get(practices.size() - 1).run();
    }
}
