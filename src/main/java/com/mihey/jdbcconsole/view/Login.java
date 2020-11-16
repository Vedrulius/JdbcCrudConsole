package com.mihey.jdbcconsole.view;

import com.mihey.jdbcconsole.controller.RegionController;
import com.mihey.jdbcconsole.controller.UserController;
import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.User;

import java.util.Scanner;

public class Login {

    private final Scanner sc = new Scanner(System.in);
    private User user;
    private Region region;
    private UserController userController = new UserController();
    private RegionController regionController = new RegionController();
    private final String loginMessage = "1. Login:\n" + "2. Exit";

    public Login() {
        System.out.println(loginMessage);
        int userId = 0;
        if (sc.nextLine().equals("1")) {
            System.out.println("Enter your firstname: ");
            String name = sc.nextLine();
            System.out.println("Enter your lastname: ");
            String surname = sc.nextLine();
            System.out.println("Enter your region: ");
            String reg = sc.nextLine();
            region = new Region(reg);
            regionController.save(region);
            user = new User(name, surname, region);
            userId = userController.saveUser(user).getId();
            new Menu().runMenu(userId);
        } else {
            System.out.println("Good by!");
        }
    }
}
