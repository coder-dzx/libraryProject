package edu.hebtu.domain;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:30
 */
//预约
public class Reserve {
    private String barcode;
    private String type;
    private String bookname;
    private String author;
    private String place;
    private String username;


    @Override
    public String toString() {
        return "Reserve{" +
                "barcode='" + barcode + '\'' +
                ", type='" + type + '\'' +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", place='" + place + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
