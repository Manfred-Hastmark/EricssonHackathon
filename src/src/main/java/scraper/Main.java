package sample;

import com.google.common.net.PercentEscaper;
import scraper.Book;
import org.apache.commons.io.FileUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;



public class Main {

    public static void main(String[] args) {
        getBookBySearch("Linjär algebra");
    }

    public static void getLunch(URL url){
        try {
            URLConnection connection = url.openConnection();
            connection.connect();

        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static Book getBookBySearch(String search) {
        try {
            //Setup connection to site
            search = search.replace(" ", "%20");
            search = search.replace("ä", "%C3%A4");



            URL url = new URL("https://www.chalmersstore.se/sok/" + search);
            url.
            File file = new File("temp.txt");
            FileUtils.copyURLToFile(url, file);
            URLConnection connection = url.openConnection();

            //Get page as stringbuilder
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder page = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                page.append(inputLine);
            in.close();



        } catch (Exception e){
            System.out.println(e);
        }



        /*
        int index = page.indexOf(pattern) + pattern.length();
        StringBuilder result = new StringBuilder();
        while (44 < page.charAt(index) && page.charAt(index) < 58 && page.charAt(index) != 47) { //Number 0-9
            result.append(page.charAt(index));
            index++;
        }
        return Double.parseDouble(result.toString().replace(",", "."));*/
        return null;
    }


}
