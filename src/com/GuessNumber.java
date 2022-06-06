package com;

import java.util.Scanner;

public class GuessNumber {

    public static void main(String[] args) {
        System.out.println("Давайте сыграем в игру отгадай число!");
        String answer;
        do {
            runGame();
            System.out.println("Вы хотите сыграть еще раз?\n" +
                    "1. Да\n" +
                    "2. Нет");
            Scanner in = new Scanner(System.in);
            answer = in.nextLine();

        } while (answer.equals("1"));
        System.out.println("Всего доброго!");
    }

    public static void runGame() {

        final int THE_NUMBER = (int) (Math.random() * 100 + 1);
        System.out.println("Отгадайте число от 1 до 100");
        final int TRIES = 7;
        int countTries = 0;
        int guess;

        do {
            System.out.print("Введите ваш вариант:");

            guess = getValidNumber();

            if (THE_NUMBER > guess) {
                System.out.println("Ваше число меньше загаданного числа");
                countTries++;
            } else if (THE_NUMBER < guess) {
                System.out.println("Ваше число больше загаданного числа");
                countTries++;
            }
        } while (countTries != TRIES && THE_NUMBER != guess);

        if (THE_NUMBER == guess) {
            System.out.println("Вы выйграли");
        } else {
            System.out.println("Колличество попыток закончилось! Вы проиграли!");
        }
    }

    public static int getValidNumber() {
        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextInt()) {
                return in.nextInt();

            } else {
                System.out.println("Введите коректный ввод!");
            }
        }
    }
}
