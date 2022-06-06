package com;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static final String YES_TO_PLAY = "1";
    static final String NO_TO_PLAY = "2";
    static String[][] SPACE;
    static final String X = "X";
    static final String O = "O";
    static String currentSymbol;

    static String winner = "";

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        System.out.println("Добро пожаловать в игру Tic Tac Toe");
        System.out.println("Хотите сыграть в игру?");
        boolean answer = askYesOrNo();

        if (answer) {
            runGame();
        }

        if ((winner.equals(X) || winner.equals(O)) || winner.equals("")) {
            System.out.println("Хотите сыграть еще раз?");
            answer = askYesOrNo();
            if (answer) {
                runGame();
            } else {
                System.out.println("Пока!!!");
            }
        }
    }

    public static int getInteger(int min, int max) {
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                int a = in.nextInt();
                if (a >= min && a <= max) {
                    return a;
                } else {
                    System.out.println("Введите число от " + min + " до " + max);
                    in.nextLine();
                }
            } else {
                System.out.println("Введите число от " + min + " до " + max);
                in.nextLine();
            }
        }
    }

    private static void runGame() {
        System.out.println("Выберите размер игрового поля по Высоте и Широте");
        System.out.println("Пример: Игровое поле 5 на 5 клеток -> ввести цифру 5");
        System.out.print("Введите желаемый размер: ");
        int sizeOfSpace = getInteger(3, 10);
        SPACE = new String[sizeOfSpace][sizeOfSpace];
        fillSpace();
        printSpace();
        currentSymbol = randomSymbol();


        int xCoordinate;
        int yCoordinate;

        do {

            isWinner();

            if (winner.equals(X)) {
                System.out.println("Победу одержал --> " + winner);
                winner = "";
                break;
            } else if (winner.equals(O)) {
                System.out.println("Победу одержал --> " + winner);
                winner = "";
                break;
            }

            if (!isFreeCells()) {
                return;
            }

            System.out.println("Игрок с символом: " + currentSymbol + " делает ход");

            printSpace();

            System.out.print("Введите кординату X: ");
            xCoordinate = getInteger(1, sizeOfSpace);
            System.out.print("Введите кординату Y: ");
            yCoordinate = getInteger(1, sizeOfSpace);

            move(xCoordinate, yCoordinate, currentSymbol);

            if (X.equals(currentSymbol)) {
                currentSymbol = O;
            } else {
                currentSymbol = X;
            }

        } while (true);

    }

    private static boolean isFreeCells() {
        for (String[] strings : SPACE) {
            for (String string : strings) {
                if (string.equals("#")) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void isWinner() {
        for (int x = 1; x < SPACE.length - 1; x++) {
            for (int y = 0; y < SPACE[x].length; y++) {
                if (isVerticalX(x, y) || isHorizontalX(x, y) || isDiagonalLeftRightX(x, y) || isDiagonalRightLeftX(x, y)) {
                    winner = X;
                } else if (isVerticalO(x, y) || isHorizontalO(x, y) || isDiagonalLeftRightO(x, y) || isDiagonalRightLeftO(x, y)) {
                    winner = O;
                }
            }
        }
    }
    private static boolean isDiagonalRightLeftX(int x, int y) {
        return SPACE[x][y].equals(X) && SPACE[x - 1][y + 1].equals(X) && SPACE[x + 1][y - 1].equals(X);
    }

    private static boolean isDiagonalLeftRightX(int x, int y) {
        return SPACE[x][y].equals(X) && SPACE[x - 1][y - 1].equals(X) && SPACE[x + 1][y + 1].equals(X);
    }

    private static boolean isHorizontalX(int x, int y) {
        return SPACE[x][y].equals(X) && SPACE[x][y - 1].equals(X) && SPACE[x][y + 1].equals(X);

    }

    private static boolean isVerticalX(int x, int y) {
        return SPACE[x][y].equals(X) && SPACE[x - 1][y].equals(X) && SPACE[x + 1][y].equals(X);
    }
    private static boolean isDiagonalRightLeftO(int x, int y) {
        return SPACE[x][y].equals(O) && SPACE[x - 1][y + 1].equals(O) && SPACE[x + 1][y - 1].equals(O);
    }

    private static boolean isDiagonalLeftRightO(int x, int y) {
        return SPACE[x][y].equals(O) && SPACE[x - 1][y - 1].equals(O) && SPACE[x + 1][y + 1].equals(O);
    }

    private static boolean isHorizontalO(int x, int y) {
        return SPACE[x][y].equals(O) && SPACE[x][y - 1].equals(O) && SPACE[x][y + 1].equals(O);
    }

    private static boolean isVerticalO(int x, int y) {
        return SPACE[x][y].equals(O) && SPACE[x - 1][y].equals(O) && SPACE[x + 1][y].equals(O);
    }

    private static void move(int xCoordinate, int yCoordinate, String currentSymbol) {
        SPACE[xCoordinate - 1][yCoordinate - 1] = currentSymbol;
    }

//    private static boolean isCurrentCellFree(int xCoordinate, int yCoordinate) {
//        return !SPACE[xCoordinate - 1][yCoordinate - 1].equals(X) &&
//                !SPACE[xCoordinate - 1][yCoordinate - 1].equals(O);
//    }
//
//    private static boolean isValidCoordinates(int xCoordinate, int yCoordinate, int sizeOfSpace) {
//        return (xCoordinate > 0 && xCoordinate < sizeOfSpace) &&
//                (yCoordinate > 0 && yCoordinate < sizeOfSpace);
//    }

    private static String randomSymbol() {
        int number = (int) (Math.random() * 2);
        return number == 1 ? O : X;
    }
    private static void fillSpace() {
        final String SYMBOL_EMPTY_CELL = "#";
        for (String[] strings : SPACE) {
            Arrays.fill(strings, SYMBOL_EMPTY_CELL);
        }
    }
    private static void printSpace() {
        for (String[] strings : SPACE) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
    private static boolean askYesOrNo() {
        System.out.println("1. Да");
        System.out.println("2. Нет");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        while (!isAnswerCorrect(answer)) {
            System.out.println("Не вверный ввод, повторите еще раз");
            answer = in.nextLine();
        }
        return YES_TO_PLAY.equals(answer);
    }
    private static boolean isAnswerCorrect(String answer) {
        return YES_TO_PLAY.equals(answer) || NO_TO_PLAY.equals(answer);
    }

}


