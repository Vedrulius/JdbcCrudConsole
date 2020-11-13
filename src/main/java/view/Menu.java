package view;

public class Menu {

    private final String menuMessage = "Choose action:\n" +
            "1. Create Post\n" +
            "2. Show post\n" +
            "3. Edit post\n" +
            "4. Delete post\n" +
            "5. Delete User\n" +
            "6. Exit";

    public void runMenu() {
        System.out.println(menuMessage);
    }
}
