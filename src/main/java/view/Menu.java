package view;

import model.User;

import java.util.Scanner;

public class Menu {

    User user = new User();

    Scanner sc = new Scanner(System.in);

    public void greeting(){
        System.out.println("Enter your firstname: ");
        user.setFirstName(sc.nextLine());
        System.out.println("Enter your lastname: ");
        user.setLastName(sc.nextLine());
        System.out.println("Enter your region: ");

    }
}
