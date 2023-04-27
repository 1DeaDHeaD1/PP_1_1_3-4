package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        for (byte i = 0; i < 4; i++) {
            String name = "User " + i;
            userService.saveUser(name, "Last name ".concat(name), i);
            System.out.printf("User с именем - %s добавлен в базу данных.%n", name);
        }
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
