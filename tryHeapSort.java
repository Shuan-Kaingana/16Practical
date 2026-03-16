// AI Assistance: Claude (claude.ai) was used to help structure this solution.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class tryHeapSort {
        public class Node {
            int data;
            Node left;
            Node right;
            
            public Node(int data) {
                this.data = data;
            }
        }
    public static void main(String[]args){
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

                    if (!Words.contains(w)) { // avoid duplicates
                            Words.add(w);
                    }
                }
                System.out.print(Words.toString());
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

//This section is to time the 2 heap methods
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
}

/*
public class MinHeap {
    private int[] data;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = new int[capacity];
    }

    private int parent(int i)    { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        data[size] = value;
        size++;
        bubbleUp(size - 1);
    }

    private void bubbleUp(int i) {
        while (i > 0 && data[i] < data[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        bubbleDown(0);
        return min;
    }

    private void bubbleDown(int i) {
        int smallest = i;
        int left  = leftChild(i);
        int right = rightChild(i);

        if (left  < size && data[left]  < data[smallest]) smallest = left;
        if (right < size && data[right] < data[smallest]) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            bubbleDown(smallest);
        }
    }

    public int peekMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return data[0];
    }

    public void delete(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException("Invalid index");
        data[i] = Integer.MIN_VALUE;
        bubbleUp(i);
        extractMin();
    }

    public static MinHeap fromArray(int[] arr) {
        MinHeap h = new MinHeap(arr.length);
        h.data = arr.clone();
        h.size = arr.length;
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            h.bubbleDown(i);
        }
        return h;
    }
}
*/
//This is the Min heap method(The build up)


//This is the Max heap method(The build up)
/*
public class MaxHeap {
    private String[] data;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = new int[capacity];
    }

    private int parent(int i)    { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i){ return 2 * i + 2; }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        data[size] = value;
        size++;
        bubbleUp(size - 1);
    }

    private void bubbleUp(int i) {
        while (i > 0 && data[i] < data[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int min = data[0];
        data[0] = data[size - 1];
        size--;
        bubbleDown(0);
        return min;
    }

    private void bubbleDown(int i) {
        int smallest = i;
        int left  = leftChild(i);
        int right = rightChild(i);

        if (left  < size && data[left]  < data[smallest]) smallest = left;
        if (right < size && data[right] < data[smallest]) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            bubbleDown(smallest);
        }
    }

    public int peekMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return data[0];
    }

    public void delete(int i) {
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException("Invalid index");
        data[i] = Integer.MIN_VALUE;
        bubbleUp(i);
        extractMin();
    }

    public static MinHeap fromArray(int[] arr) {
        MinHeap h = new MinHeap(arr.length);
        h.data = arr.clone();
        h.size = arr.length;
        for (int i = (arr.length / 2) - 1; i >= 0; i--) {
            h.bubbleDown(i);
        }
        return h;
    }
}
*/



