package edu.hebtu.domain;

/**
 * @author DuZengXin
 * @date 2019/5/27 - 14:00
 */
public class Book {
private int num;
private String barcode;
private String type;
private String bookname;
private String author;
private int sum;
private String place;

    public Book() {
    }

    public Book(int num, String barcode, String type, String bookname, String author, int sum, String place) {
        this.num = num;
        this.barcode = barcode;
        this.type = type;
        this.bookname = bookname;
        this.author = author;
        this.sum = sum;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Book{" +
                "num=" + num +
                ", barcode='" + barcode + '\'' +
                ", type='" + type + '\'' +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                ", sum=" + sum +
                ", place='" + place + '\'' +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
