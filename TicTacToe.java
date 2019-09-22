import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final String DOT_X = "X";
    private static final String DOT_O = "O";
    private static final String DOT_EMPTY = ".";
    private static String[][] map = new String[SIZE][SIZE];
    private static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили! Поздравляем!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }
        }

    }

    private static void initMap() {
        map = new String[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите коорд в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    private static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkLine(int start_x, int start_y, int dx, int dy, String sign) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (map[start_x + i * dx][start_y + i * dy] != sign)
                return false;
        }
        return true;
    }

    private static boolean checkWin(String sign) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (checkLine(i, 0, 0, 1, sign)) return true;
            if (checkLine(0, i, 1, 0, sign)) return true;
        }
        if (checkLine(0, 0, 1, 1, sign)) return true;
        return checkLine(0, DOTS_TO_WIN, 1, -1, sign);
    }
}
