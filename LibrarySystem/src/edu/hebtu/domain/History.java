package edu.hebtu.domain;

import java.util.Date;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 15:06
 */
public class History {
    private int bid;
    private String barcode;
    private String username;
    private String type;
    private String bookname;
    private Date borrowdate;
    private Date returndate;
    private int status;//2表示未还  1表示已还

    public History() {
    }

    public History(int bid, String barcode, String username, String type, String bookname, Date borrowdate, Date returndate, int status) {
        this.bid = bid;
        this.barcode = barcode;
        this.username = username;
        this.type = type;
        this.bookname = bookname;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.status = status;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
