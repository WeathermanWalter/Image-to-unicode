import java.io.*;

public class FileInterface {
    static String outputName;

    FileInterface(String outputName) {
        this.outputName = outputName;
    }

    static void writeFile(String data) {
        try {
            FileWriter fw = new FileWriter(outputName);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void writeFile(String data, String outputName) {
        try {
            FileWriter fw = new FileWriter(outputName);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
