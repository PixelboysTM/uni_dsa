import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int naiveCount, moveToFrontCount;

        System.out.println("Comparing 'beide'");
        var beide = loadWords("./assets/beide"); //TODO: Möglicherweise anpassen
        naiveCount = countNaiv(beide);
        moveToFrontCount = countMoveToFront(beide);
        System.out.println("Naive       Count: "  + naiveCount);
        System.out.println("MoveToFront Count: "  + moveToFrontCount);

        System.out.println("\nComparing 'silber'");
        var silber = loadWords("./assets/silber"); //TODO: Möglicherweise anpassen
        naiveCount = countNaiv(silber);
        moveToFrontCount = countMoveToFront(silber);
        System.out.println("Naive       Count: "  + naiveCount);
        System.out.println("MoveToFront Count: "  + moveToFrontCount);

        System.out.println("\nComparing 'winnetou'");
        var winnetou = loadWords("./assets/winnetou"); //TODO: Möglicherweise anpassen
        naiveCount = countNaiv(winnetou);
        moveToFrontCount = countMoveToFront(winnetou);
        System.out.println("Naive       Count: "  + naiveCount);
        System.out.println("MoveToFront Count: "  + moveToFrontCount);
    }

    /**
     * Load the file from the given filename and read x words. X is the number in the first line. Each word must be on a separate line after this
     * @param filename The filename to load
     * @return The words as a String[]
     * @throws FileNotFoundException If the filename is wrong
     */
    public static String[] loadWords(String filename) throws FileNotFoundException {
        var file = new Scanner(new FileInputStream(filename));
        var numberOfLines = Integer.parseInt(file.nextLine());
        String[] words = new String[numberOfLines];
        for (int i = 0; i < numberOfLines; i++) {
            words[i] = file.nextLine();
        }

        return words;
    }

    /**
     * Build up the list in the 'naiv' way
     * @param words
     * @return The number of steps taken
     */
    public static int countNaiv(String[] words) {
        LinkedList<String, Integer> list = new LinkedList<>();
        StepCounter counter = new StepCounter();
        for (String word : words) {
            var maybeWord = list.find(word);
            if (maybeWord.isPresent()) {
                Integer value = maybeWord.get().value();
                maybeWord.get().setValue(value + 1);
            } else {
                list.add(word, 1);
            }
        }

        return counter.endCount();
    }

    /**
     * Build up the list in the 'moveToFront' way
     * @param words
     * @return Te number of steps taken
     */
    public static int countMoveToFront(String[] words) {
        LinkedList<String, Integer> list = new LinkedList<>();
        StepCounter counter = new StepCounter();
        for (String word : words) {
            var maybeWord = list.find(word);
            if (maybeWord.isPresent()) {
                var node = maybeWord.get();
                node.delete();
                list.insert(word, node.value() + 1);
            }else {
                list.insert(word, 1);
            }
        }

        return counter.endCount();
    }
}

