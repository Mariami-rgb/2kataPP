package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Daniil", "Shabalin", (byte) 23);
        userService.saveUser("Viktor", "Petrov", (byte) 19);
        userService.saveUser("Semen", "Ivanov", (byte) 44);
        userService.saveUser("Nikolay", "Shabalin", (byte) 14);
        userService.removeUserById(2);
        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();
    }
}
