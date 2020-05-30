package users;

import tools.FileReaderAndWriter;

import java.util.ArrayList;

public class UserChecker {
    public boolean isUser(String firstInput, String secondInput) {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        ArrayList<User> usersList = (ArrayList<User>) fileReaderAndWriter.readFile("/src/users/UsersList.txt");

        for (User user: usersList) {
            if (firstInput.equals(user.getName()) || firstInput.equals(user.getEmail())) {
                if (secondInput.equals(user.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValidUser(String input) {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        ArrayList<User> usersList = (ArrayList<User>) fileReaderAndWriter.readFile("/src/users/UsersList.txt");
        for (User user: usersList) {
            if (input.equals(user.getName()) || input.equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public void saveNewUser(String name, String email, String password) {
        User user = new User(name, email, password);
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        ArrayList<User> usersList = (ArrayList<User>) fileReaderAndWriter.readFile("/src/users/UsersList.txt");
        usersList.add(user);
        fileReaderAndWriter.writeToFile(usersList, "/src/users/UsersList.txt");
    }
}
