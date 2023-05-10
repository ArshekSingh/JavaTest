package test;

import java.net.MalformedURLException;
import java.net.URL;

public class url {

    public static void main(String args[]) {
        try {
           URL url = new URL("sddsvfs");
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getProtocol());
            System.out.println(url.getFile());
            System.out.println(url.getPath());
       } catch (MalformedURLException e) {
           System.out.println(e.getMessage());
       }
    }
}
