// Code is stored as 13template.java
import java.text.*;
import java.util.Random;

public class timeMethods {
   public static int N = 30;
   public static void main(String args[]){
      int[] array = generateKeys(N);

   DecimalFormat twoD = new DecimalFormat("0.00");
   DecimalFormat fourD = new DecimalFormat("0.0000");
   DecimalFormat fiveD = new DecimalFormat("0.00000");

   long start, finish;
   double runTime = 0, runTime2 = 0, time;
   double totalTime = 0.0;
   int n = N;
   int repetition, repetitions = 30;

      runTime = 0;
      for(repetition = 0; repetition < repetitions; repetition++) {
         start = System.currentTimeMillis();
         
         // call the procedures to time here:
         linearsearch (array, 12345);
         binarySearch (array, 12345);
         // Figure out how to alter this guideline here,
         
         finish = System.currentTimeMillis();
            
         time = (double)(finish - start);
         runTime += time;
         runTime2 += (time*time); }

      double aveRuntime = runTime/repetitions;
      double stdDeviation = 
         Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

      System.out.printf("\n\n\fStatistics\n");
      System.out.println("________________________________________________");
      System.out.println("Total time   =           " + runTime/1000 + "s.");
      System.out.println("Total time\u00b2  =           " + runTime2);
      System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                        + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
      System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
      System.out.println("n            =           " + n);
      System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000) 
                        + '\u00B5' + "s. "); 

      System.out.println("Repetitions  =             " + repetitions);
      System.out.println("________________________________________________");
      System.out.println();
      System.out.println(); 
   }
    public static void binarySearch(int[] array, int value) {
        insertionSort(array); // Ensure the array is sorted before performing binary search
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == value) {
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
   private static void linearsearch(int[] array, int key) {
      for (int i = 0; i < array.length; i++) {
         if (array[i] == key) {
            return;
         }
      }
   }

   //This is to make sure that the array we implement is sorted if it is not already sorted
   public static void insertionSort(int[] array) {
      for (int i = 1; i < array.length; i++) {
         int temp = array[i];
         int j = i - 1;

         // Shift elements greater than temp to the right
           while (j >= 0 && array[j] > temp) {
               array[j + 1] = array[j];
               j--;
           }

           // Insert temp at the correct position
           array[j + 1] = temp;
        }
   }

public static int[] generateKeys(int count) {
   int[] keys = new int[count];
   Random rand = new Random();
    for (int i = 0; i < count; i++){
       // Generate a random integer between 1 and 32654 
       keys[i] = rand.nextInt(32654) + 1;
       }
      return keys;
   }

}

