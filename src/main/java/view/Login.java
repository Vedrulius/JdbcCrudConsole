package view;

import controller.UserController;
import model.User;

import java.util.Scanner;

public class Login {

    private final Scanner sc = new Scanner(System.in);
    private UserController userController = new UserController();

    private final String loginMessage = "1. Login:\n" + "2. Exit";

    public  Login() {
        System.out.println(loginMessage);
        if (sc.nextLine().equals("1")) {
            System.out.println("Enter your firstname: ");
            String name = sc.nextLine();
            System.out.println("Enter your lastname: ");
            String surname = sc.nextLine();
            System.out.println("Enter your region: ");
            String region = sc.nextLine();
            userController.saveUser(name,surname,region);
            new Menu().runMenu();
        }
    }
}
