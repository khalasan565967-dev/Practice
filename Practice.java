package demo;
import java.util.*;

public class Practice {
    static final Scanner input = new Scanner(System.in);
    static final Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue\n[2] Display Queue\n[3] Generate Permutations\n[4] Generate Combinations\n[5] Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1 -> addElements();
                case 2 -> displayQueue();
                case 3 -> generate(true);
                case 4 -> generate(false);
                case 5 -> System.out.println("Program terminated.");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    static void addElements() {
        System.out.print("How many elements to add? ");
        int count = input.nextInt();
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter element " + i + ": ");
            queue.add(input.next());
        }
        System.out.println("Elements successfully added to the Queue.");
    }

    static void displayQueue() {
        System.out.println(queue.isEmpty() ? "Queue is empty!" : "Queue contents: " + queue);
    }

    static void generate(boolean isPermutation) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Please add elements first.");
            return;
        }
        System.out.print("Enter number of elements (r): ");
        int r = input.nextInt();
        if (r <= 0 || r > queue.size()) {
            System.out.println("Invalid number of elements!");
            return;
        }

        String[] elements = queue.toArray(new String[0]);
        List<String> results = new ArrayList<>();
        collect(elements, r, isPermutation, 0, new boolean[elements.length], new ArrayDeque<>(), results);

        System.out.println("\nGenerated " + (isPermutation ? "Permutations:" : "Combinations:"));
        results.forEach(System.out::println);
        System.out.println("Total " + (isPermutation ? "Permutations: " : "Combinations: ") + results.size());
    }

    static void collect(String[] src, int r, boolean perm, int start, boolean[] used, Deque<String> current, List<String> out) {
        if (current.size() == r) {
            out.add(String.join("", current));
            return;
        }
        for (int i = perm ? 0 : start; i < src.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            current.addLast(src[i]);
            collect(src, r, perm, i + 1, used, current, out);
            current.removeLast();
            used[i] = false;
        }
    }
}