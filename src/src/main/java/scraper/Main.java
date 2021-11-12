package sample;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.*;



public class Main {

    public static void main(String[] args) {
    }

    public static void getLunch(URL url){
        try {
            URLConnection connection = url.openConnection();
            connection.connect();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static double getValueAt(String pattern, StringBuilder page) {
        int index = page.indexOf(pattern) + pattern.length();
        StringBuilder result = new StringBuilder();
        while (44 < page.charAt(index) && page.charAt(index) < 58 && page.charAt(index) != 47) { //Number 0-9
            result.append(page.charAt(index));
            index++;
        }
        return Double.parseDouble(result.toString().replace(",", "."));
    }


}
