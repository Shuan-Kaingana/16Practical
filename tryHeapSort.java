// AI Assistance: Claude (claude.ai) was used to help structure this solution.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tryHeapSort {

    public static void main(String[] args) {

        List<String> Words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ulysses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    w = w.replaceAll("[.,;:_!\\-]+$", ""); // strip trailing punctuation
                    w = w.replaceAll("^[.,;:_!\\-]+", ""); // strip leading punctuation
                    w = w.toLowerCase();
                    if (w.isEmpty()) continue;
                    Words.add(w);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Convert list to array for heap use
        String[] wordArray = Words.toArray(new String[0]);
        System.out.println("Loaded " + wordArray.length + " words from ulysses.txt\n");

        System.out.println("=== Small test (20 words) ===");
        String[] sample = {
            "whale", "apple", "mango", "zebra", "cat",
            "dog", "fig", "elderberry", "banana", "kiwi",
            "nectarine", "orange", "pear", "quince", "raspberry",
            "strawberry", "tomato", "ugli", "vanilla", "watermelon"
        };
        MinHeap testHeap = new MinHeap(sample.length);
        testHeap.buildUp(sample);
        System.out.println("Bottom-up sorted: " + Arrays.toString(testHeap.sort()));

        MinHeap testHeap2 = new MinHeap(sample.length);
        for (String w : sample) testHeap2.insert(w);
        System.out.println("Top-down sorted : " + Arrays.toString(testHeap2.sort()));
        System.out.println();

        DecimalFormat twoD  = new DecimalFormat("0.00");
        DecimalFormat fourD = new DecimalFormat("0.0000");
        DecimalFormat fiveD = new DecimalFormat("0.00000");

        long start, finish;
        double runTime = 0, runTime2 = 0, time;
        int n = wordArray.length;       // fixed: was undefined N
        int repetition, repetitions = 30;

        System.out.println("=== Timing: Bottom-up build (O(n)) ===");
        runTime = 0; runTime2 = 0;
        String[] sortedBottomUp = null;

        for (repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();

            MinHeap heapBottomUp = new MinHeap(wordArray.length);
            heapBottomUp.buildUp(wordArray);
            sortedBottomUp = heapBottomUp.sort();   // sort is shared between both methods

            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            runTime  += time;
            runTime2 += (time * time);
        }

        double aveRuntime1    = runTime / repetitions;
        double stdDeviation1  = Math.sqrt(runTime2 - repetitions * aveRuntime1 * aveRuntime1) / (repetitions - 1);

        System.out.printf("\n\fStatistics — Bottom-up\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTime / 1000 + "s.");
        System.out.println("Total time\u00b2  =           " + runTime2);
        System.out.println("Average time =           " + fiveD.format(aveRuntime1 / 1000)
                          + "s. " + '\u00B1' + " " + fourD.format(stdDeviation1) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviation1));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(aveRuntime1 / n * 1000)
                          + '\u00B5' + "s. ");
        System.out.println("Repetitions  =             "+ repetitions);
        System.out.println("________________________________________________");
        System.out.println("First 5 sorted: " + Arrays.toString(Arrays.copyOfRange(sortedBottomUp, 0, 5)));
        System.out.println("Last  5 sorted: " + Arrays.toString(Arrays.copyOfRange(sortedBottomUp,
                            sortedBottomUp.length - 5, sortedBottomUp.length)));
                            
        System.out.println("\n=== Timing: Top-down build (O(n log n)) ===");
        runTime = 0; runTime2 = 0;
        String[] sortedTopDown = null;

        for (repetition = 0; repetition < repetitions; repetition++) {
            start = System.currentTimeMillis();

            MinHeap heapTopDown = new MinHeap(wordArray.length);
            for (String word : wordArray) heapTopDown.insert(word);
            sortedTopDown = heapTopDown.sort();     // same sort step as bottom-up

            finish = System.currentTimeMillis();
            time = (double)(finish - start);
            runTime  += time;
            runTime2 += (time * time);
        }

        double aveRuntime2   = runTime / repetitions;
        double stdDeviation2 = Math.sqrt(runTime2 - repetitions * aveRuntime2 * aveRuntime2) / (repetitions - 1);

        System.out.printf("\n\fStatistics — Top-down\n");
        System.out.println("________________________________________________");
        System.out.println("Total time   =           " + runTime / 1000 + "s.");
        System.out.println("Total time\u00b2  =           " + runTime2);
        System.out.println("Average time =           " + fiveD.format(aveRuntime2 / 1000)
                          + "s. " + '\u00B1' + " " + fourD.format(stdDeviation2) + "ms.");
        System.out.println("Standard deviation =     " + fourD.format(stdDeviation2));
        System.out.println("n            =           " + n);
        System.out.println("Average time / run =     " + fiveD.format(aveRuntime2 / n * 1000)
                          + '\u00B5' + "s. ");
        System.out.println("Repetitions  =             " + repetitions);
        System.out.println("________________________________________________");
        System.out.println("First 5 sorted: " + Arrays.toString(Arrays.copyOfRange(sortedTopDown, 0, 5)));
        System.out.println("Last  5 sorted: " + Arrays.toString(Arrays.copyOfRange(sortedTopDown,
                            sortedTopDown.length - 5, sortedTopDown.length)));

        System.out.println("\n=== Comparison ===");
        System.out.printf("Bottom-up average : %s ms%n", fiveD.format(aveRuntime1));
        System.out.printf("Top-down  average : %s ms%n", fiveD.format(aveRuntime2));
        double ratio = aveRuntime2 / aveRuntime1;
        System.out.printf("Top-down is %.2fx %s than bottom-up%n",
                ratio > 1 ? ratio : 1.0 / ratio,
                ratio > 1 ? "slower" : "faster");
    }
}