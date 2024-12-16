import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

class Main {
    private static void printMaxKey(HashMap<Integer, Integer> map) {
        Optional<Integer> maxKey =map.keySet().stream().max(Integer::compareTo);

        System.out.println(map.get(maxKey.get()));
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            map.put(scanner.nextInt(), scanner.nextInt());
        }

        printMaxKey(map);
    }
}
