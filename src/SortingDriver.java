// @author Bryson Meiling

import java.util.*;

public class SortingDriver {
    public static void main(String[] args) {
        System.out.println("--- Timing Results---");
        for (int i = 0; i < 10; i++) {
            int[] randomNumbers = generateNumbers((i + 1) * 1000);
            System.out.println(" --Number of items--   :  " + (i+1)*1000 );
            System.out.println("  Bubble Sort Time     :  " + bubbleSort(randomNumbers).getTimeInMs() + "ms");
            System.out.println("  Selection Sort Time  :  " + selectionSort(randomNumbers).getTimeInMs() + "ms\n");

        }
    }

    static int [] generateNumbers(int howMany) { //generates integers between 1 - 100,000
        int[] numberList = new int[howMany];
        for (int i = 0 ; i < howMany ; i++) {
            int r = (int)(Math.random() * 1000000);
            numberList[i]= r;
        }
        return numberList;
    }

    static SortingStats bubbleSort(int[] data){ //compares two numbers that are next to one another and swaps them if they are out of order
        long startTime = System.currentTimeMillis();
        SortingStats results = new SortingStats();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                results.incrementCompareCount(); //counts number of comparisons made
                if (data[j] > data[j + 1]) { //if the number to the right is smaller, then they are switched
                    int holdingBin = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = holdingBin;
                    results.incrementSwapCount(); //counts number of swaps made
                }
            }
        }
        long endTime = System.currentTimeMillis();
        results.setTime((endTime - startTime));
        return results; //sends data points back to object "results"
    }

    static SortingStats selectionSort(int[] data) {
        long startTime = System.currentTimeMillis(); //starts instance of time to start the recording
        SortingStats results = new SortingStats();


        for (int i = 0 ; i < data.length; i++) { //iterates as many times as there are in the list.
            int minNumIndex = i;
            int minimumNumber = data[i];
            for (int j = i; j < data.length -1 ; j++) { //goes from j to end of list
                results.incrementCompareCount();
                if (data[j+1] < minimumNumber) {  //finds minimum number in array between j and the end
                    minimumNumber = data[j+1];
                    minNumIndex = j+1;
                }
            }
            int holdingBin = data[i]; //saves the information in the cell that will be altered
            data[i] = minimumNumber; //switches the minimum number and another cell
            data[minNumIndex] = holdingBin;
            results.incrementSwapCount();
            }
        long endTime = System.currentTimeMillis();
        results.setTime((endTime - startTime));
        //System.out.println("sorted list: " + java.util.Arrays.toString(data));
        return results;  //sends data points back to object "results"
        }

    public static SortingStats QuickSort (int[] data) {
        long startTime = System.currentTimeMillis();
        SortingStats results = new SortingStats();
        QuickSortRecursive(results, data);
        results.setTime(System.currentTimeMillis() - startTime);
        return results;

    }
    public static SortingStats QuickSortRecursive(SortingStats stats, int[] list) {
        int pivot = 0;
        int low = 1;
        int high = list.length - 1;

        while (low <= high) {
            stats.incrementCompareCount();

            while (list[low] < list[pivot]) {
                stats.incrementCompareCount();
                low++;
            }
            while (list[high] > list[pivot]) {
                stats.incrementCompareCount();
                high--;
            }
            int storage = list[low];
            list[low] = list[high];
            list[high] = storage;
            stats.incrementSwapCount();
        }

        int storageHigh = high;
        int storageValue = list[high];
        list[high] = list[pivot];
        list[pivot] = storageValue;
        stats.incrementSwapCount();

        return stats;
    }
}
