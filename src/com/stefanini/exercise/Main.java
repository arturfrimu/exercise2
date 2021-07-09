package com.stefanini.exercise;

import java.io.*;
import java.util.*;

public class Main {
    public static final String CREATE_USR_CMD = "-createUser";
    public static final String SHOW_ALL_USR_CMD = "-showAllUsers";
    public static final String ADD_TASK_CMD = "-addTask";
    public static final String SHOW_TASKS_CMD = "-showTasks";
    public static final String FILE_NAME = "test1.txt";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length == 0) {
            System.out.println("no command to execute");
            return;
        }
        String cmd = args[0];
        if (CREATE_USR_CMD.equals(cmd)) {
            User user = createUsers(args);
            List<User> users = readUsersFromFile();
            users.add(user);
            saveUsersToFile(users);
            System.out.println("User was created");
        } else if (SHOW_ALL_USR_CMD.equals(cmd)) {
            System.out.println(readUsersFromFile());
        } else if (ADD_TASK_CMD.equals(cmd)) {
            addTaskToUser(args);
        } else if (SHOW_TASKS_CMD.equals(cmd)) {
            showUserTasks(args);
        } else {
            System.out.println("incorrect command");
        }
    }

    public static void saveUsersToFile(List<User> users) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        oos.writeObject(users);
        oos.close();
    }

    public static List<User> readUsersFromFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
        List<User> users = (ArrayList<User>) ois.readObject();
        ois.close();
        return users;
    }

    public static User createUsers(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid create user command");
        }
        User user = new User();
        if (args[1].startsWith("-fn=")) {
            String fn = args[1].substring(4);
            user.setFirstName(fn);
        } else {
            throw new IllegalArgumentException("Invalid user firstName");
        }
        if (args[2].startsWith("-ln=")) {
            String ln = args[2].substring(4);
            user.setLastName(ln);
        } else {
            throw new IllegalArgumentException("Invalid user lastName");
        }
        if (args[3].startsWith("-un=")) {
            List<User> users = readUsersFromFile();
            String username = args[3].substring(4);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(username)) {
                    throw new IllegalArgumentException("User with username " + username + " already exists");
                }
            }
            user.setUserName(username);
        } else {
            throw new IllegalArgumentException("Invalid user UserName");
        }
        return user;
    }

    public static void addTaskToUser(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid create user command");
        }
        Task task = new Task();
        if (args[2].startsWith("-tt=")) {
            String title = args[2].substring(4);
            task.setTitle(title);
        } else {
            throw new IllegalArgumentException("Invalid title");
        }
        if (args[3].startsWith("-td=")) {
            String desc = args[3].substring(4);
            task.setDescription(desc);
        } else {
            throw new IllegalArgumentException("Invalid description");
        }
        if (args[1].startsWith("-un=")) {
            User user = null;
            List<User> users = readUsersFromFile();
            String username = args[1].substring(4);
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(username)) {
                    user = users.get(i);
                }
            }
            if (user == null) {
                throw new IllegalArgumentException("User with username " + username + " not found");
            } else {
                user.addTask(task);
                saveUsersToFile(users);
            }
        } else {
            throw new IllegalArgumentException("Invalid username argument");
        }
    }

    public static void showUserTasks(String[] args) throws IOException, ClassNotFoundException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid create user command");
        }

        if (args[1].startsWith("-un=")) {
            String username = args[1].substring(4);
            List<User> users = readUsersFromFile();
            List<Task> tasks = null;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(username)) {
                    tasks = users.get(i).getTasks();
                }
            }
            if (tasks != null) {
                System.out.println(tasks);
            } else {
                throw new IllegalArgumentException("User with username " + username + " not found");
            }

        } else {
            throw new IllegalArgumentException("Invalid username argument");
        }
    }
}



