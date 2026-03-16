// AI Assistance: Claude (claude.ai) was used to help structure this solution.

import java.io.*;
import java.util.*;

public class Anagrams{
    public static void main(String[]args){

        HashMap<String, List<String>> D = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("ulysses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    w = w.replaceAll("[.,;:_!\\-]+$", ""); // strip trailing punctuation
                    w = w.replaceAll("^[.,;:_!\\-]+", ""); // strip leading punctuation
                    w = w.toLowerCase();

                    if (w.isEmpty()) continue;

                    String key = makeSignature(w);

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
        for (String key : D.keySet()) {
            List<String> anagrams = D.get(key);
            if (anagrams.size() >= 2) {
                Collections.sort(anagrams);
                System.out.println(key + ": " + anagrams);
            }
        }
    }

    //Method to convert words to their signature
    public static String makeSignature(String word) {
        char[] chars = word.toCharArray();
        insertionSort(chars);
        return new String(chars);
    }
    
    public static char[] insertionSort(char[] array) {
        for (int i = 1; i < array.length; i++) {
            char temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = temp;
        }
        return array;
    }
}