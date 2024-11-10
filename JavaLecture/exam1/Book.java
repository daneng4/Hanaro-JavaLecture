package JavaLecture.exam1;

public class Book {

    private String isbn;
    private String title;
    private String price;
    private String writer;
    private String bookIntro;
    private String bookType;
    private String date;

    public Book(String isbn, String title, String price, String writer, String bookIntro,
        String bookType, String date) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.writer = writer;
        this.bookIntro = bookIntro;
        this.bookType = bookType;
        this.date = date;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price.substring(0, price.length() - 1);
    }

    public String getWriter() {
        return writer;
    }

    public String getBookIntro() {
        return bookIntro;
    }

    public String getBookType() {
        return bookType;
    }

    public String getDate() {
        return date;
    }
}