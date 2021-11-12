package sample;

import com.google.common.net.PercentEscaper;
import org.openqa.selenium.remote.DesiredCapabilities;
import scraper.Book;
import org.apache.commons.io.FileUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;



public class Main {


    public Main() throws Exception{
        System.setProperty("webdriver.gecko.driver","C:/Program Files/geckodriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("marionette",true);
        WebDriver driver= new FirefoxDriver(); driver.get("https://www.systembolaget.se/sok/?categoryLevel1=%C3%96l");
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/section/div/div/div[4]/button")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[1]/div/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/input")).sendKeys("Kapell");
        TimeUnit.MILLISECONDS.sleep(200);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/input")).sendKeys("p");
        TimeUnit.MILLISECONDS.sleep(800);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/div/div/ul/li/div")).click();

        int antalSidor = 4;
        for(int i = 0; i < antalSidor; i++){
            TimeUnit.MILLISECONDS.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + (32 + i*31) + "]/div[1]/button")).click();

        }
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[155]/div[1]/button")).click();
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[185]/div[1]/button")).click();
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[215]/div[1]/button")).click();
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[245]/div[1]/button")).click();
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[275]/div[1]/button")).click();
        /*
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[32]/div[1]/button")).click();
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[63]/div[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[94]/div[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[155]/div[1]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[185]/div[1]/button")).click();
        //driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[245]/div[1]/button")).click();
        String url = driver.getCurrentUrl();*/

        //driver.close();
    }

    public class Beer {
        final private String name;
        final private int volume;
        final private int price;
        final private double apk;

        public Beer(String name, int volume, int price) {
            this.name = name;
            this.volume = volume;
            this.price = price;
            this.apk = price;
        }

        public String getName() {
            return name;
        }

        public int getVolume() {
            return volume;
        }

        public int getPrice() {
            return price;
        }

        public double getApk() {
            return apk;
        }
    }

    public static void main(String[] args) throws Exception {
        new Main();
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
