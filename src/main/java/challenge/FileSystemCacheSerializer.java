package challenge;

import challenge.model.FileNode;

import java.io.*;

public class FileSystemCacheSerializer {

    private String DEFAULT_FILE_LOCATION = "system.txt";

    public FileSystemCacheSerializer() {
    }

    public void writeFileSystem(FileNode root, String fileName) throws IOException {
        FileOutputStream fileOutputStream;

        if (fileName == null) {
            fileOutputStream = new FileOutputStream(DEFAULT_FILE_LOCATION);
        } else {
            fileOutputStream = new FileOutputStream(fileName);
        }

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(root);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public FileNode readFileSystem(String fileName) {
        FileInputStream fileInputStream;
        try {

            if (fileName == null) {
                fileInputStream = new FileInputStream(DEFAULT_FILE_LOCATION);
            } else {
                fileInputStream = new FileInputStream(fileName);
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            FileNode root = (FileNode) objectInputStream.readObject();
            objectInputStream.close();

            return root == null ? defaultFileSystem() : root;
        } catch (Exception cause) {
            return defaultFileSystem();
        }
    }

    private FileNode defaultFileSystem() {
        return new FileNode("root", null, true, false);
    }
}