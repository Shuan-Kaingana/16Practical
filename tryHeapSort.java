// AI Assistance: Claude (claude.ai) was used to help structure this solution.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
                    Words.add(w);
                }
                System.out.print(Words.toString());
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
    }
}

public class MinHeap{
    private int size;
    private int capacity;
    private int[] heap;
    
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private void swap(){
        return;
    }


    private void insert(){
        return;
    }

    private void remove(){
        return;
    }


}

public class MaxHeap{
    private int size;
    private int capacity;
    private int[] heap;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private void swap(){
        return;
    }
    
    private void insert(){
        return;
    }

    private void remove(){
        return;
    }
}


