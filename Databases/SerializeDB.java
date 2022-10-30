import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

// Note : When structure of the Object type (the class file) in the list changed
// the Serialized file may fail.
public abstract class SerializeDB {
    public static List getSerializedObject(String filename) {
        // we return a un-resizable List bcos we don't want the other parts of app to be
        // able to add on to this List on their own, but rather to call the setFunction
        List data = null;
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

    public static void setSerializedObject(String filename, List list) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
            System.out.println("Object successfully written to DB");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}