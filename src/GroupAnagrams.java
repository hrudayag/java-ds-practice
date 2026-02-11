import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] words) {

        Map<String, List<String>> groups = new HashMap<>();

        for (String word : words) {

            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(word);
        }

        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {

        GroupAnagrams solver = new GroupAnagrams();

        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> result = solver.groupAnagrams(input);

        System.out.println("Grouped Anagrams:");
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
}
