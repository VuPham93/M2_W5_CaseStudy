package tools;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderAndWriter<ObjectType> {
    //Ghi danh sách:
    public void writeToFile(ArrayList<ObjectType> objects, String path) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (ObjectType object : objects) {
                objectOutputStream.writeObject(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Đọc danh sách:
    public List<ObjectType> readFile(String path) {
        List<ObjectType> objects = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + path);
            objectInputStream = new ObjectInputStream(fileInputStream);
            ObjectType object = null;

            while ((object = (ObjectType) objectInputStream.readObject()) != null) {
                objects.add(object);
            }

        } catch (EOFException e) {
            System.out.println("End of file in path: " + path);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
