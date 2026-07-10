package com.lucca.bankcli.cli;

import java.util.Scanner;

public class Menu {

    private static void printMenu() {
        System.out.println("=====Bank CLI=====");
        System.out.println("\n");
        System.out.println("1. Create Client");
        System.out.println("2. Create Account");
        System.out.println("3. Display Accounts");
        System.out.println("4. Quit");
    }

    Scanner scanner = new Scanner(System.in);
    printMenu();
}
