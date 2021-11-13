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

        System.setProperty("webdriver.gecko.driver","C:/Program Files/geckodriver.exe");


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
        driver.close();
        sort(beers, 0, beers.size()-1);
        beers.forEach(b -> System.out.println(b.toString()));
        generateHTML();

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

        TimeUnit.MILLISECONDS.sleep(800);

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

        for(int i = 0; i < 20; i++){
            for(int j = 155-30 + i*30; j < 155 + i*30; j++){
                try {
                    links.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + j + "]/div/a")).getAttribute("href"));
                }
                catch (Exception e){
                    System.out.println("Link not found");
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
            try {
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[2]/div/div/div/div[2]/div[4]/div[" + (155 + i*30) + "]/div[1]/button")).click();
            } catch (Exception e){
                break;
            }
        }
    }

    //Function for generating the html site
    private void generateHTML() {
        try {
            FileWriter index = new FileWriter("index.html");
            int random = new Random().nextInt(beers.size() - 10) + 10;
            Beer randomBeer = beers.get(random);
            index.write("<html>\n" +
                    "<head>\n" +
                    "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
                    "    <link rel=\"stylesheet\"\n" +
                    "          href=\"https://fonts.googleapis.com/css?family=Arapey\">\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "\n" +
                    "\n" +
                    "<body>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<div class=\"page\">\n" +
                    "    <h1>Øhl med högst APK på Systembolaget Landala</h1>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "    <div class=\"fill dagens\">\n" +
                    "        <a href=\"" + randomBeer.systemetLink + "\">\n" +
                    "            <h2>Dagens Øhl </h2>\n" +
                    "            <img class=\"ol\" src=\"" + randomBeer.imgLink + "\">\n" +
                    "            <div class=\"info\">\n" +
                    "                <p class=\"name\">" + randomBeer.name + "</p>\n" +
                    "                <p class=\"Volym\">Volym: " + randomBeer.volume +  " ml</p>\n" +
                    "                <p class=\"Pris\">Pris: " + Double.toString(randomBeer.price).replace(".", ":") + "0 kr</p>\n" +
                    "                <p class=\"halt\">Alkoholhalt: " + randomBeer.percent + "%</p>\n" +
                    "                <p class=\"apk\">APK: " + ((double)(int)(randomBeer.apk * 100))/100 + "</p>\n" +
                    "                <p class=\"Lager\">Kvar i lager: " + randomBeer.lager + " st</p>\n" +
                    "            </div>\n" +
                    "        </a>\n" +
                    "    </div>\n");




            for(int i = 0; i < 10; i++) {
                index.write("    <a href=\"" + beers.get(i).systemetLink + "\">\n" +
                        "        <div class=\"fill\">\n" +
                        "            <img class=\"ol\" src=\"" + beers.get(i).imgLink + "\">\n" +
                        "            <div class=\"info\">\n" +
                        "                <p class=\"name\">" + beers.get(i).name + "</p>\n" +
                        "                <p class=\"Volym\">Volym: " + beers.get(i).volume +  " ml</p>\n" +
                        "                <p class=\"Pris\">Pris: " + Double.toString(beers.get(i).price).replace(".", ":") + "0 kr</p>\n" +
                        "                <p class=\"halt\">Alkoholhalt: " + beers.get(i).percent + "%</p>\n" +
                        "                <p class=\"apk\">APK: " + ((double)(int)(beers.get(i).apk * 100))/100 + "</p>\n" +
                        "                <p class=\"Lager\">Kvar i lager: " + beers.get(i).lager + " st</p>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </a>\n");
            }

            index.write("</div>\n" +
                            "\n" +
                            "</body>\n" +
                            "\n" +
                            "\n" +
                            "</html>");
            index.close();
            System.out.println("Done generating html");
        } catch (Exception e) {
            System.out.println("Failed to generate file");
        }
    }

    private void getBeers(){
        String temp;

        int progress = 0;
        for(String link : links){
            try {
                driver.get(link);
                temp = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div/div[2]/div[2]/div[6]/div[1]/div[2]/div/div/div[2]/div[1]/div")).getText();
                if (Integer.parseInt(temp.split(" ")[0]) > 0){
                    beers.add(new Beer(driver));
                }
                progress++;
            }
            catch (Exception e){
                //System.out.println("Beer not found");
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
        final private int lager;

        public Beer(String name, int volume, int price, double percent, String imgLink, String systemetLink, int lager) {
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
            temp = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div/div[2]/div[2]/div[6]/div[1]/div[2]/div/div/div[2]/div[1]/div")).getText();
            this.lager = Integer.parseInt(temp.split(" ")[0]);
            this.systemetLink = driver.getCurrentUrl();

        }

        @Override
        public String toString() {
            return "Beer{" +
                    "name='" + name + '\'' +
                    ", volume=" + volume +
                    ", price=" + price +
                    ", apk=" + apk +
                    ", percent=" + percent +
                    ", lager status=" + lager +
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
