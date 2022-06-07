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
        boolean answer;
        System.out.println("===>>> Добро пожаловать в игру \"Tic-Tac-Toe\" !!!");
        do {
            System.out.println("===>>> Вы хотите сыграть в игру?\n===>>> 1. Да\n===>>> 2. Нет");
            System.out.print("===>>> Введите ответ: ");
            answer = askYesOrNo();

            if (answer) {
                System.out.println("===>>> Выберите размер игрового поля, например 3 - это клетка 3 на 3 - максимум 10 на 10");
                int sizeSpace = getInteger(1, 10);
                runGame(sizeSpace);
            }
        } while (answer);

        System.out.println("===>>> Всего хорошоего!");
    }



    private static void runGame(int sizeSPace) {
        SPACE = new String[sizeSPace][sizeSPace];
        System.out.println("===>>> Ваше игровое поле");
        fillSpace();
        printSpace();
        currentSymbol = randomSymbol();

        int xCord;
        int yCord;
        boolean isValidCoordinates = false;

        do {
            do {

                isWinner();

                if (winner.equals(X)) {
                    System.out.println("===>>> Победа за X");
                    return;
                } else if (winner.equals(O)) {
                    System.out.println("===>>> Победа за O");
                    return;
                }

                if (!isFreeCells()) {
                    System.out.println("===>>> Ничья!");
                    return;
                }
                System.out.println("===> Ход за " + currentSymbol);

                printSpace();

                System.out.print("===>>> Выберите кординату по оси X: ");
                xCord = getInteger(1, sizeSPace);
                System.out.print("===>>> Выберите кординату по оси Y: ");
                yCord = getInteger(1, sizeSPace);

                if (isCurrentCellFree(xCord, yCord)) {
                    isValidCoordinates = true;
                    move(xCord, yCord, currentSymbol);
                    if (X.equals(currentSymbol)) {
                        currentSymbol = O;
                    } else if (O.equals(currentSymbol)){
                        currentSymbol = X;
                    }
                } else {
                    System.out.println("===>>> Текущая ячейка занята, выберите другую!");
                }

            } while (!isValidCoordinates);




        } while (true);


    }




    private static void isWinner() {
        for (int x = 1; x < SPACE.length - 1; x++) {
            for (int y = 1; y < SPACE[x].length - 1; y++) {

                //Проверка по вертикали на победу X и O
                if ((SPACE[x][y].equals(X) && SPACE[x - 1][y].equals(X) && SPACE[x + 1][y].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x][y].equals(O) && SPACE[x - 1][y].equals(O) && SPACE[x + 1][y].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка по горизонтали на победу X и O
                if ((SPACE[x][y].equals(X) && SPACE[x][y - 1].equals(X) && SPACE[x][y + 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x][y].equals(O) && SPACE[x][y - 1].equals(O) && SPACE[x][y + 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка по диагонали с лева на право на победу X и O
                if ((SPACE[x][y].equals(X) && SPACE[x - 1][y - 1].equals(X) && SPACE[x + 1][y + 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x][y].equals(O) && SPACE[x - 1][y - 1].equals(O) && SPACE[x + 1][y + 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка по диагонали с права на лево на победу X и O
                if ((SPACE[x][y].equals(X) && SPACE[x - 1][y + 1].equals(X) && SPACE[x + 1][y - 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x][y].equals(O) && SPACE[x - 1][y + 1].equals(O) && SPACE[x + 1][y - 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка по вверху от текущей ячейки на победу X и O
                if ((SPACE[x - 1][y - 1].equals(X) && SPACE[x - 1][y].equals(X) && SPACE[x - 1][y + 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x - 1][y - 1].equals(O) && SPACE[x - 1][y].equals(O) && SPACE[x - 1][y + 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка по низу от текущей ячейки на победу X и O
                if ((SPACE[x + 1][y - 1].equals(X) && SPACE[x + 1][y].equals(X) && SPACE[x + 1][y + 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x + 1][y - 1].equals(O) && SPACE[x + 1][y].equals(O) && SPACE[x + 1][y + 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка с лева от текущей ячейки на победу X и O
                if ((SPACE[x - 1][y - 1].equals(X) && SPACE[x][y - 1].equals(X) && SPACE[x + 1][y - 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x - 1][y - 1].equals(O) && SPACE[x][y - 1].equals(O) && SPACE[x + 1][y - 1].equals(O))) {
                    winner = O;
                    break;
                }

                //Проверка с права от текущей ячейки на победу X и O
                if ((SPACE[x - 1][y + 1].equals(X) && SPACE[x][y + 1].equals(X) && SPACE[x + 1][y + 1].equals(X))) {
                    winner = X;
                    break;
                } else if ((SPACE[x - 1][y + 1].equals(O) && SPACE[x][y + 1].equals(O) && SPACE[x + 1][y + 1].equals(O))) {
                    winner = O;
                    break;
                }
            }
        }

    }
    private static boolean isFreeCells() {
        for (String[] strings : SPACE) {
            for (String string : strings) {
                if (string.equals("-")) {
                    return true;
                }
            }
        }
        return false;
    }
    private static void move(int xCoordinate, int yCoordinate, String currentSymbol) {
        SPACE[xCoordinate - 1][yCoordinate - 1] = currentSymbol;
    }
    private static boolean isCurrentCellFree(int xCoordinate, int yCoordinate) {
        return !SPACE[xCoordinate - 1][yCoordinate - 1].equals(X) &&
                !SPACE[xCoordinate - 1][yCoordinate - 1].equals(O);
    }
    private static String randomSymbol() {
        int number = (int) (Math.random() * 2);
        return number == 1 ? O : X;
    }
    private static void fillSpace() {
        final String SYMBOL_EMPTY_CELL = "-";
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
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();

        while (!isAnswerCorrect(answer)) {
            System.out.println("Не вверный ввод, повторите еще раз");
            answer = in.nextLine();
        }
        return YES_TO_PLAY.equals(answer);
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
    private static boolean isAnswerCorrect(String answer) {
        return YES_TO_PLAY.equals(answer) || NO_TO_PLAY.equals(answer);
    }

}


