// AI Assistance: Claude (claude.ai) was used to help structure this solution.
public class MinHeap {
    private String[] data;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = new String[capacity];
    }

    private int parent(int i)     { return (i - 1) / 2; }
    private int leftChild(int i)  { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        String tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    // TOP-DOWN: insert one word at a time (called repeatedly)
    public void insert(String word) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        data[size] = word;
        size++;
        bubbleUp(size - 1);
    }

    // compareTo() used instead of < because we are comparing Strings not ints
    private void bubbleUp(int i) {
        while (i > 0 && data[i].compareTo(data[parent(i)]) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // BOTTOM-UP: load all words at once then heapify in O(n)
    public void buildUp(String[] words) {
        if (words.length > capacity) throw new IllegalStateException("Array exceeds heap capacity");
        for (int i = 0; i < words.length; i++) {
            data[i] = words[i];
        }
        size = words.length;
        // Start from last non-leaf node and bubble down to root
        for (int i = (size / 2) - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    public String extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        String min = data[0];
        data[0] = data[size - 1];
        size--;
        if (size > 0) bubbleDown(0);
        return min;
    }

    private void bubbleDown(int i) {
        int smallest = i;
        int left  = leftChild(i);
        int right = rightChild(i);

        if (left  < size && data[left].compareTo(data[smallest])  < 0) smallest = left;
        if (right < size && data[right].compareTo(data[smallest]) < 0) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            bubbleDown(smallest);
        }
    }

    public String peekMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return data[0];
    }

    public String[] sort() {
        String[] sorted = new String[size];
        int total = size;
        for (int i = 0; i < total; i++) {
            sorted[i] = extractMin();
        }
        return sorted;
    }

    public int size()        { return size; }
    public boolean isEmpty() { return size == 0; }
}
