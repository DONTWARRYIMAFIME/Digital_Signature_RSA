package by.cryptography.RSA;

import by.cryptography.RSA.RSAPack.RSA;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final RSA rsa = new RSA(scanner);
    private boolean exit = false;

    private void setExit() {
        exit = true;
    }

    private void printCommands() {
        System.out.println("1 - Manual Generation");
        System.out.println("2 - Automatic Generation");
        System.out.println("0 - Exit");
    }

    public void start() {
        printCommands();
        String command = scanner.next();

        switch (command) {
            case "1" -> rsa.manualGeneration();
            case "2" -> rsa.automaticGeneration();
            case "0" -> setExit();
        }

    }

    public boolean isExit() {
        return exit;
    }


}
