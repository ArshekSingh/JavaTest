package test.arrays;

import java.io.*;

public class ReadFromFile {

    static public void main(String args[]) throws IOException {
        File file = new File("C:\\Users\\SASTS\\OneDrive\\Desktop\\" + "arrayonetohundread.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while((st = reader.readLine()) != null) {
            System.out.println(st);
        }


    }
}
