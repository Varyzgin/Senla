
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Hangman {
    public static void main(String[] args) {
        List<String> wordsList = List.of(
                "висельник", "палка", "бумага", "слово", "сердце",
                "коробка", "книга", "сумка", "таблица", "монета"
        );
        Random random = new Random();
        int choice = random.nextInt(wordsList.size());


        char[] charArray = wordsList.get(choice).toCharArray();
        char[] resultArray = new char[charArray.length];
        Arrays.fill(resultArray, '_');
        Set<Character> wrongLetters = new HashSet<>();

        int lives = 7;

        System.out.println("Welcome to the club, buddy!\nTry to guess the word with " + lives + " lives!");
        moveReactDisplay(resultArray, wrongLetters, lives);

        Scanner scanner = new Scanner(System.in);
        while (lives > 0 && new String(resultArray).indexOf('_') != -1) {
            System.out.print("Enter the letter: ");
            String inputLine = scanner.nextLine().toLowerCase();

            if (inputLine.length() != 1 || !Character.isLetter(inputLine.charAt(0))) {
                System.out.println("Try again");
                continue;
            }

            char input = inputLine.charAt(0);

            if (new String(resultArray).indexOf(input) != -1 || wrongLetters.contains(input)) {
                System.out.println("Try another letter");
                continue;
            }

            boolean success = false;

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == input) {
                    resultArray[i] = input;
                    success = true;
                }
            }

            if (!success) {
                wrongLetters.add(input);
                lives--;
            }

            moveReactDisplay(resultArray, wrongLetters, lives);
        }
    }

    public static String sps(int count) {
        return " ".repeat(Math.max(0, abs(count)));
    }

    public static void moveReactDisplay(char[] wordArray, Set<Character> wrongSet, int lives) {
        StringBuilder word = new StringBuilder();
        for (char c : wordArray) {
            word.append(c);
        }
        int ml = max("Your word".length(), word.length());
        int dl = "Your word".length() - word.length();
        String label = dl > 0 ? "Your word" : sps(dl / 2) + "Your word" + sps(dl - dl / 2); // yourWordLabel
        String wordDisplay = dl > 0 ? sps(dl / 2) + word + sps(dl - dl / 2) : word.toString();

        StringBuilder wrong = new StringBuilder();
        for (char c : wrongSet) {
            wrong.append(c);
            wrong.append(" ");
        }
        int dr = "Wrong letters".length() - wrong.length();
        String wlabel = dr > 0 ? "Wrong letters" : sps(dr / 2) + "Wrong letters"; // wrongsLabel
        String wrongs = dr > 0 ? sps(dr - dr / 2) + wrong.toString() : wrong.toString();

        switch (lives) {
            case 6:
                System.out.println("""
                        %s
                        %s  |
                        %s  |
                        %s  |
                        %s      |              %s
                        %s      |              %s
                        %s  |
                        %s  |
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            case 5:
                System.out.println("""
                        %s _________
                        %s  |/
                        %s  |
                        %s  |
                        %s      |              %s
                        %s      |              %s
                        %s  |
                        %s  |
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            case 4:
                System.out.println("""
                        %s _________
                        %s  |/    |
                        %s  |    (+)
                        %s  |     |
                        %s      |     |        %s
                        %s      |     |        %s
                        %s  |
                        %s  |
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            case 3:
                System.out.println("""
                        %s _________
                        %s  |/    |
                        %s  |    (+)
                        %s  |    _|
                        %s      |   / |        %s
                        %s      |     |        %s
                        %s  |
                        %s  |
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
            case 2:
                System.out.println("""
                        %s _________
                        %s  |/    |
                        %s  |    (+)
                        %s  |    _|_
                        %s      |   / | \\      %s
                        %s      |     |        %s
                        %s  |
                        %s  |
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            case 1:
                System.out.println("""
                        %s _________
                        %s  |/    |
                        %s  |    (+)
                        %s  |    _|_
                        %s      |   / | \\      %s
                        %s      |     |        %s
                        %s  |    /
                        %s  |   /
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            case 0:
                System.out.println("""
                        %s _________
                        %s  |/    |
                        %s  |    (+)
                        %s  |    _|_
                        %s      |   / | \\      %s
                        %s      |     |        %s
                        %s  |    / \\
                        %s  |   /   \\
                        %s__|___________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
                break;
            default:
                System.out.println("""
                        %s
                        %s
                        %s
                        %s
                        %s                     %s
                        %s                     %s
                        %s
                        %s
                        %s______________
                        %s|  Lives: %d  |
                        """.formatted(sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), label, wlabel,
                        wordDisplay, wrongs, sps(ml + 4), sps(ml + 4), sps(ml + 4), sps(ml + 4), lives));
        }
    }
}
