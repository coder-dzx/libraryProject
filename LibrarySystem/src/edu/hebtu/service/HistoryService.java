package edu.hebtu.service;

import edu.hebtu.domain.Book;
import edu.hebtu.domain.History;
import edu.hebtu.domain.PageBean;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 15:01
 */
public interface HistoryService {
    /**
     * 借阅操作
     * @param barcode
     * @param username
     */
    void borrowBook(String barcode, String username);

    /**
     * 分页查询读者正在借阅的图书
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<History> pageQuery(int currentPage, int pageSize, String username);

    /**
     * 还书操作
     * @param username
     * @param barcode
     */
    void returnBook(String username, String barcode);

    /**
     * 分页查询读者已还的图书
     * @param currentPage
     * @param pageSize
     * @param username
     * @return
     */
    PageBean<History> returnpageQuery(int currentPage, int pageSize, String username);

    /**
     * 管理员分页查询所有用户的当前借阅信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<History> findBorrowBook(int currentPage, int pageSize);

    /**
     * 管理员分页查询所有用户的历史借阅信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<History> findReturnBook(int currentPage, int pageSize);

    /**
     * 查询此用户是否借阅了此书
     * @param barcode
     * @param username
     * @return
     */
    int findBorrowByUsername(String barcode, String username);
}
