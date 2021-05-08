package by.cryptography.RSA;

public class App {
    public void start() {
        Menu menu = new Menu();

        while (!menu.isExit()) {
            menu.start();
        }

        System.out.println("Bye!");
    }
}
