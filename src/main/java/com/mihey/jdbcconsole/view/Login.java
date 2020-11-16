package com.mihey.jdbcconsole.view;

import com.mihey.jdbcconsole.controller.RegionController;
import com.mihey.jdbcconsole.controller.UserController;
import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.util.DBUtil;

import java.util.Scanner;

public class Login {

    private final Scanner sc = new Scanner(System.in);
    private String name;
    private String surname;
    private String regionName;
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
            name = sc.nextLine();
            System.out.println("Enter your lastname: ");
            surname = sc.nextLine();
            System.out.println("Enter your region: ");
            regionName = sc.nextLine();
            region = new Region(regionName);
            DBUtil.setConnection();
            regionController.save(region);
            user = new User(name, surname, region);
            userId = userController.saveUser(user).getId();
            new Menu().runMenu(userId);
        } else {
            System.out.println("Good by!");
            DBUtil.closeConnection();
        }
    }
}
