import java.util.*;

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(
                (a, b) -> countMap.get(a) - countMap.get(b)
        );

        for (int key : countMap.keySet()) {
            heap.offer(key);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] result = new int[k];
        int i = 0;

        while (!heap.isEmpty()) {
            result[i++] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args) {

        TopKFrequentElements solver = new TopKFrequentElements();

        int[] input = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] answer = solver.topKFrequent(input, k);

        System.out.println("Top " + k + " elements: " + Arrays.toString(answer));
    }
}
