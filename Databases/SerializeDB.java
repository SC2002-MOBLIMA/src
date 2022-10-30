package Databases;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public abstract class SerializeDB {
    public static ArrayList readSerializedObject(String filename) {
        ArrayList data = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            data = (ArrayList) in.readObject();
            // The method readObject is used to read an object from the stream. Java's safe
            // casting should be used to get the desired type.
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void writeSerializedObject(String filename, ArrayList data) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(data);
            out.close();
            System.out.println("Object successfully written to DB");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
