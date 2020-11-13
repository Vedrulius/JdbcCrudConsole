package view;

import controller.PostController;
import controller.UserController;
import model.Post;

import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private PostController postController = new PostController();
    private UserController userController = new UserController();

    private final String menuMessage = "Choose action:\n" +
            "1. Create Post\n" +
            "2. Edit post\n" +
            "3. Show post\n" +
            "4. Delete post\n" +
            "5. Delete User\n" +
            "6. Exit";

    public void runMenu(int id) {
        boolean flag = true;
        while (flag) {
            System.out.println(menuMessage);
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    createPost(id);
                    break;
                case 6:
                    flag=false;
                    new Login();
                    break;
                default:
                    System.out.println("Wrong number");

            }
        }
    }

    public void createPost(int id) {
        System.out.println("Write your post:");
        String post = sc.nextLine();
        postController.createPost(new Post(id, post));
    }
}
