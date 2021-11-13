package scraper;

import com.google.common.net.PercentEscaper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import scraper.Book;
import org.apache.commons.io.FileUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.text.html.Option;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;



public class Main {

    private final WebDriver driver;
    List<String> links;
    List<Beer> beers;

    public Main() throws Exception{
        long time = System.currentTimeMillis();

        System.setProperty("webdriver.gecko.driver","C:/Skola/geckodriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("marionette",true);
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        //options.setHeadless(true);  // <-- headless set here
        this.driver = new FirefoxDriver(options);
        this.links = new ArrayList<>();
        this.beers = new ArrayList<>();
        getToTheBeers();
        getBeers();
        sort(beers, 0, beers.size()-1);
        beers.forEach(b -> System.out.println(b.toString()));
        System.out.println(beers.size());

        /*
        TimeUnit.MILLISECONDS.sleep(500);
        String volume = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[1]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[1]")).getText();
        System.out.println(volume);*/
        /*
        TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[1]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[1]")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[2]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[3]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[2]")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[4]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[2]")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[5]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[2]")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[6]/div/a/div/div/div[3]/div[1]/div[1]/div[1]/div/h3/span[2]")).click();
        beers.add(new Beer(driver));
        driver.navigate().back();
        //TimeUnit.MILLISECONDS.sleep(500);

        for (Beer b : beers) {
            System.out.println(b.toString());
        }

        beers.forEach(b -> System.out.println(b.toString()));

        System.out.println(System.currentTimeMillis() - time);
        */
        /*
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

    private void getToTheBeers() throws Exception{
        //Accepts cookies and that we are of legal age, then goes to our local store
        driver.get("https://www.systembolaget.se/sok/?categoryLevel1=%C3%96l&sortBy=Price&sortDirection=Ascending");
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/section/div/div/div[4]/button")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/div/div[2]/div[2]/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[1]/div/button[2]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/input")).sendKeys("Kapell");
        TimeUnit.MILLISECONDS.sleep(200);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/input")).sendKeys("p");
        TimeUnit.MILLISECONDS.sleep(800);
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/form/label/div/div/div/ul/li/div")).click();
        //TimeUnit.MILLISECONDS.sleep(800);
        //driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[3]/div[1]/select")).click();


        //TODO code to click "Visa fler"
        int antalSidor = 4;
        for(int i = 0; i < antalSidor; i++){
            for(int j = i*31; j < 32 + i*31; j++){
                try {
                    links.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + j + "]/div/a")).getAttribute("href"));
                }
                catch (Exception e){
                    System.out.println("Link not found");
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + (32 + i*31) + "]/div[1]/button")).click();

        }
        /*
        for(int i = 0; i < 5; i++){
            for(int j = 155-30 + i*30; j < 155 + i*30; j++){
                try {
                    links.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + j + "]/div/a")).getAttribute("href"));
                }
                catch (Exception e){
                    System.out.println("Link not found");
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + (155 + i*30) + "]/div[1]/button")).click();
        }*/

        /*
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
        */

        /*
        //Add all links to the beers in the internal link list
        for(int i = 1; i < 50; i++){
            try {
                links.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + i + "]/div/a")).getAttribute("href"));
            }
            catch (Exception e){
                System.out.println("Link not found");
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }*/
    }

    private void getBeers(){

        int progress = 0;
        for(String link : links){
            try {
                driver.get(link);
                beers.add(new Beer(driver, link));
                progress++;
            }
            catch (Exception e){
                System.out.println("Beer not found");
            }
            System.out.println(progress);
        }
    }

    public static final void sort(List<Beer> b, int lo, int hi) {

        for (int i = lo; i <= hi; i++) {
            // Insert a[i] into a[lo..i-1].
            Beer value = b.get(i);
            int j = i;
            while (j > lo && b.get(j - 1).apk < value.apk) {
                b.set(j, b.get(j - 1));
                j--;
            }
            b.set(j, value);
        }
    }


    public class Beer {
        final private String name;
        final private int volume;
        final private double price;
        final private double apk;
        final private double percent;
        final private String imgLink;
        final private String systemetLink;
        final private String lager;

        public Beer(String name, int volume, int price, double percent, String imgLink, String systemetLink, String lager) {
            this.name = name;
            this.volume = volume;
            this.price = price;
            this.percent = percent;
            this.apk = (this.percent * 0.01 * this.volume) / this.price;
            this.imgLink = imgLink;
            this.systemetLink = systemetLink;
            this.lager = lager;

        }

        public Beer(WebDriver driver){
            this.name = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/main/div[1]/div/div[2]/div[2]/div[1]/div[1]/div[2]/h1/span")).getText();
            String temp = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/main/div[1]/div/div[2]/div[2]/div[3]/div[1]/div/div[2]/span")).getText();
            this.volume = Integer.parseInt(temp.split(" ")[0]);
            this.price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/main/div[1]/div/div[2]/div[2]/div[3]/div[2]/div[1]")).
                    getText().replace(":", ".").replace("-", "0"));
            temp = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/main/div[1]/div/div[2]/div[2]/div[3]/div[1]/div/div[3]/span")).getText();
            this.percent = Double.parseDouble(temp.split(" ")[0].replace(",", "."));
            this.apk = (this.percent * 0.01 * this.volume) / this.price;
            this.imgLink = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div/div[2]/div[1]/button/div/img")).getAttribute("src");
            this.systemetLink = driver.getCurrentUrl();
            this.lager = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div/div[2]/div[1]/button/div/img")).getAttribute("src");
        }

        @Override
        public String toString() {
            return "Beer{" +
                    "name='" + name + '\'' +
                    ", volume=" + volume +
                    ", price=" + price +
                    ", apk=" + apk +
                    ", percent=" + percent +
                    ", imgLink=" + imgLink +
                    '}';
        }

        public String getName() {
            return name;
        }

        public int getVolume() {
            return volume;
        }

        public double getPrice() {
            return price;
        }

        public double getApk() {
            return apk;
        }

        public String getImgLink(){
            return imgLink;
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