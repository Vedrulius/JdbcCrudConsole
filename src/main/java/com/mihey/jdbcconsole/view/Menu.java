package com.mihey.jdbcconsole.view;

import com.mihey.jdbcconsole.controller.PostController;
import com.mihey.jdbcconsole.controller.UserController;
import com.mihey.jdbcconsole.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private final PostController postController = new PostController();
    private final UserController userController = new UserController();

    private final String menuMessage = "Choose action:\n" +
            "1. Create Post\n" +
            "2. Edit post\n" +
            "3. Show post\n" +
            "4. Delete post\n" +
            "5. Delete User\n" +
            "6. Exit";

    public void runMenu(int userId) {
        boolean flag = true;
        while (flag) {
            System.out.println(menuMessage);
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    createPost(userId);
                    break;
                case 2:
                    editPost(userId);
                    break;
                case 3:
                    showPosts(userId);
                    break;
                case 4:
                    deletePost(userId);
                    break;
                case 5:
                    flag = deleteUser(userId, flag);
                    if (!flag) {
                        new Login();
                    }
                    break;
                case 6:
                    flag = false;
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
        postController.createPost(new Post(post, id));
    }

    public void editPost(int userId) {
        System.out.println("=============================================");
        List<Post> posts = new ArrayList<>(postController.showAllPosts());
        posts.stream().map(post -> post.getId() + ". " + post.getContent()).forEach(System.out::println);
        System.out.println("=============================================");
        System.out.println("Choose post from above:");
        int postId = sc.nextInt();
        sc.nextLine();
        System.out.println("Write new post: ");
        String post = sc.nextLine();
        postController.editPost(new Post(postId, post));
    }

    public void showPosts(int userId) {
        System.out.println("1. View your posts\n2. View all posts");
        int answer = sc.nextInt();
        List<Post> posts = new ArrayList<>(postController.showAllPosts());
        if (answer == 1) {
            System.out.println("==========================================================================================");
            posts.stream().map(Post::getContent).forEach(System.out::println);
            System.out.println("==========================================================================================");
        } else {
            System.out.println("==========================================================================================");
            posts.stream().map(Post::getContent).forEach(System.out::println);
            System.out.println("==========================================================================================");
        }


    }

    public void deletePost(int userId) {
        System.out.println("=============================================");
        List<Post> posts = new ArrayList<>(postController.showAllPosts());
        posts.stream().map(post -> post.getId() + ". " + post.getContent()).forEach(System.out::println);
        System.out.println("=============================================");
        System.out.println("Choose post from above:");
        int postId = sc.nextInt();
        postController.deletePostById(postId);
    }

    public boolean deleteUser(int userId, boolean flag) {
        System.out.println("Are you sure? If you delete account all your post will also be deleted.\nYes/No");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("Yes")) {
            flag = false;
            userController.deleteUser(userId);
        }
        return flag;
    }
}
