package users;

import tools.FileReaderAndWriter;
import tools.FinalList;

import java.util.ArrayList;

public class UserChecker {
    String userSaveLocation = FinalList.USER_SAVE_LOCATION;
    private ArrayList<User> readUserList() {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        return (ArrayList<User>) fileReaderAndWriter.readFile(userSaveLocation);
    }

    public String isUser(String firstInput, String secondInput) {
        ArrayList<User> usersList = readUserList();

        for (User user: usersList) {
            if (firstInput.equals(user.getName()) || firstInput.equals(user.getEmail())) {
                if (secondInput.equals(user.getPassword())) {
                    if ((user.getType()).equals(FinalList.ADMIN)) {
                        return FinalList.ADMIN;
                    }
                    else {
                        return FinalList.USER;
                    }
                }
            }
        }
        return FinalList.NOT_AN_USER;
    }

    public boolean isValidUser(String input) {
        ArrayList<User> usersList = readUserList();

        for (User user: usersList) {
            if (input.equals(user.getName()) || input.equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public boolean isOldUser(String name, String email) {
        ArrayList<User> usersList = readUserList();

        for (User user: usersList) {
            if (name.equals(user.getName()) && email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void saveNewUser(String name, String email, String password) {
        User user = new User(name, email, password, FinalList.USER);
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();

        ArrayList<User> usersList = (ArrayList<User>) fileReaderAndWriter.readFile(userSaveLocation);

        usersList.add(user);
        fileReaderAndWriter.writeToFile(usersList, userSaveLocation);
    }

    public void saveUserInfo(String name, String email, String password) {
        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();

        ArrayList<User> usersList = (ArrayList<User>) fileReaderAndWriter.readFile(userSaveLocation);

        for (User user: usersList) {
            if (name.equals(user.getName()) && email.equals(user.getEmail())) {
                user.setPassword(password);
            }
        }

        fileReaderAndWriter.writeToFile(usersList, userSaveLocation);
    }
}
