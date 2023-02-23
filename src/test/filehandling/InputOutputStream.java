package test.filehandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InputOutputStream {

    public static void main(String args[]) throws IOException {

        String path = "C:/Users/SASTS/OneDrive/Documents/SAS files/";
        FileInputStream inputStream = new FileInputStream(path + "document.txt");
//        int i = 0;
//        while((i=inputStream.read())!= -1) {
//            System.out.print((char) i);
//        }
        byte[] bytes = inputStream.readAllBytes();
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        outputStream.write(bytes);
        inputStream.close();
        outputStream.close();
    }
}
