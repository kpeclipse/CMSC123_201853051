import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;

public class WordFind {
    char[][] grid;
    ArrayList<Word> words = new ArrayList<Word>();

    public static void main(String[] args) {
        new WordFind();
    }

    public WordFind() {
        System.out.println("---WORD FIND---\n");

        displayGrid();
        displayWords();

        System.out.println();
        System.out.println();

        // Word location = diagonalDescendingReverse(words.get(6));
        // words.set(6, location);

        for (int i = 0; i < words.size(); i++) {
            Word location = null;
            int check = 1;
            while (location == null) {
                switch (check) {
                    case 1:
                        location = horizontalSearch(words.get(i));
                        break;
                    case 2:
                        location = verticalSearch(words.get(i));
                        break;
                    case 3:
                        location = diagonalAscending(words.get(i));
                        break;
                    case 4:
                        location = diagonalDescending(words.get(i));
                        break;
                    case 5:
                        location = horizontalSearchReverse(words.get(i));
                        break;
                    case 6:
                        location = verticalSearchReverse(words.get(i));
                        break;
                    case 7:
                        location = diagonalAscendingReverse(words.get(i));
                        break;
                    case 8:
                        location = diagonalDescendingReverse(words.get(i));
                        break;
                    default:
                        location = words.get(i);
                        location.setOrientation("NOT FOUND");
                        break;
                }

                check += 1;
            }
            words.set(i, location);

            System.out.println("Word: " + words.get(i).showWord());
            System.out.println("Direction: " + words.get(i).getOrientation());

            if (check <= 9) {
                System.out.println(
                        "First letter at: (" + words.get(i).getStartX() + ", " + words.get(i).getStartY() + ")");
                System.out.println("Last letter at: (" + words.get(i).getEndX() + ", " + words.get(i).getEndY() + ")");
            }

            System.out.println();
        }
    }

    public Word horizontalSearch(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < 20; i++) {
            int k = 0;

            for (int j = 0; j < 20; j++) {
                if (k < length
                        && Character.toString(grid[i][j]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = j;
                        startY = i;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(j);
                    input.setEndY(i);
                    input.setOrientation("horizontal");

                    return input;
                }
            }
        }

        return null;
    }

    public Word verticalSearch(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 0; i < 20; i++) {
            int k = 0;

            for (int j = 0; j < 20; j++) {
                if (k < length
                        && Character.toString(grid[j][i]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i);
                    input.setEndY(j);
                    input.setOrientation("vertical");

                    return input;
                }
            }
        }

        return null;
    }

    public Word diagonalAscending(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 0; (i + length) < 20; i++) {
            int k = 0;

            for (int j = 19; j >= 0; j--) {
                if (k < length
                        && Character.toString(grid[j][i + k]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i + k - 1);
                    input.setEndY(j);
                    input.setOrientation("diagonal (ascending - southwest to northeast)");

                    return input;
                }
            }
        }

        return null;
    }

    public Word diagonalDescending(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 0; (i + length) < 20; i++) {
            int k = 0;

            for (int j = 0; j < 20; j++) {
                if (k < length
                        && Character.toString(grid[j][i + k]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i + k - 1);
                    input.setEndY(j);
                    input.setOrientation("diagonal (descending - northwest to southeast)");

                    return input;
                }
            }
        }

        return null;
    }

    public Word horizontalSearchReverse(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 19; i >= 0; i--) {
            int k = 0;

            for (int j = 19; j >= 0; j--) {
                if (k < length
                        && Character.toString(grid[i][j]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = j;
                        startY = i;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(j);
                    input.setEndY(i);
                    input.setOrientation("horizontal (reverse)");

                    return input;
                }
            }
        }

        return null;
    }

    public Word verticalSearchReverse(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 19; i >= 0; i--) {
            int k = 0;

            for (int j = 19; j >= 0; j--) {
                if (k < length
                        && Character.toString(grid[j][i]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i);
                    input.setEndY(j);
                    input.setOrientation("vertical (reverse)");

                    return input;
                }
            }
        }

        return null;
    }

    public Word diagonalAscendingReverse(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 19; (i - length) >= 0; i--) {
            int k = 0;

            for (int j = 19; j >= 0; j--) {
                if (k < length
                        && Character.toString(grid[j][i - k]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i - k + 1);
                    input.setEndY(j);
                    input.setOrientation("diagonal (ascending - southeast to northwest)");

                    return input;
                }
            }
        }

        return null;
    }

    public Word diagonalDescendingReverse(Word input) {
        /*
         * check System.out.println(input.showWord());
         * System.out.println(input.showI()); System.out.println(input.showJ());
         * System.out.println(input.getLength());
         */

        int length = input.getLength();
        int startX = 0;
        int startY = 0;

        for (int i = 19; (i - length) >= 0; i--) {
            int k = 0;

            for (int j = 0; j < 20; j++) {
                if (k < length
                        && Character.toString(grid[j][i - k]).equals(Character.toString(input.showWord().charAt(k)))) {
                    // Take note of the coordinates of the first letter
                    if (k == 0) {
                        startX = i;
                        startY = j;
                    }

                    k += 1;
                }

                else {
                    // Reset
                    k = 0;
                    startX = 0;
                    startY = 0;
                }

                if (k == length) {
                    input.setStartX(startX);
                    input.setStartY(startY);
                    input.setEndX(i - k + 1);
                    input.setEndY(j);
                    input.setOrientation("diagonal (descending - northeast to southwest)");

                    return input;
                }
            }
        }

        return null;
    }

    public void displayGrid() {
        try {
            File file = new File(System.getProperty("user.dir") + "/Grid.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int x = 0;
            int y = 0;

            while ((line = bufferedReader.readLine()) != null) {
                if (x == 0) {
                    grid = new char[line.length()][line.length()];
                }

                char empty = '*';
                Random r = new Random();

                while (y < line.length()) {
                    if (Character.compare(line.charAt(y), empty) == 0) // if character in grid is "*"
                        grid[x][y] = (char) (r.nextInt(26) + 'a'); // replace to a random letter
                    else
                        grid[x][y] = line.charAt(y);
                    y += 1;
                }

                x += 1;
                y = 0;
            }

            bufferedReader.close();

            System.out.println("     0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19");
            for (int i = 0; i < 20; i++) {
                if (i < 10)
                    System.out.print(i + "  | ");
                else
                    System.out.print(i + " | ");

                for (int j = 0; j < 20; j++) {
                    System.out.print(grid[i][j] + "  ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayWords() {
        System.out.println("\nWORDS:\n");

        try {
            File file = new File(System.getProperty("user.dir") + "/RandomWords.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int counter = 0;

            while ((line = bufferedReader.readLine()) != null) {
                words.add(new Word(line, new Coordinates(0, 0), new Coordinates(0, 0)));

                counter += 1;
                if (counter % 3 == 0)
                    System.out.println(line);
                else
                    System.out.print(line + "\t\t\t");
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}