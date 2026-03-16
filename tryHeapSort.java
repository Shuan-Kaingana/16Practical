import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class tryHeapSort {
    public static void main(String[]args){
        try (BufferedReader reader = new BufferedReader(new FileReader("ulysses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    w = w.replaceAll("[.,;:_!\\-]+$", ""); // strip trailing punctuation
                    w = w.replaceAll("^[.,;:_!\\-]+", ""); // strip leading punctuation
                    w = w.toLowerCase();

                    if (w.isEmpty()) continue;

                    if (!D.containsKey(key)) {
                        List<String> list = new ArrayList<>();
                        list.add(w);
                        D.put(key, list);
                    } else {
                        List<String> list = D.get(key);
                        if (!list.contains(w)) { // avoid duplicates
                            list.add(w);
                        }
                    }
                }
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
}
