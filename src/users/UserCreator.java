package users;

import library.FileReaderAndWriter;

import java.util.ArrayList;

public class UserCreator {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("admin", "admin@gmail.com", "123"));
        users.add(new User("vu", "vu.phamvan@gmail.com", "thu6ngay13"));
        users.add(new User());

        FileReaderAndWriter<User> fileReaderAndWriter = new FileReaderAndWriter<>();
        fileReaderAndWriter.writeToFile(users, "/src/users/UsersList.txt");
    }
}
