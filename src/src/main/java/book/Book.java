package scraper;


public class Book{
    final private String link;
    final private int price;
    final private String author;
    final private String title;

    public Book(String link, int price, String author, String title) {
        this.link = link;
        this.price = price;
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "link='" + link + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

