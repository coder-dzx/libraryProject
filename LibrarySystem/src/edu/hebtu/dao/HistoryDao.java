package edu.hebtu.dao;

import edu.hebtu.domain.Book;
import edu.hebtu.domain.History;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 15:02
 */
public interface HistoryDao {
    /**
     * 将读者借阅的读书存到history表
     * @param barcode
     * @param username
     * @param type
     * @param bookname
     */
    void save(String barcode, String username, String type, String bookname);

    /**
     * 查询读者借阅中的总记录数
     * @return
     */
    int findToalCount(String username);

    /**
     * 查询读者的记录
     * @param start
     * @param pageSize
     * @param username
     * @return
     */
    List<History> findBorrow(int start, int pageSize, String username);

    /**
     * 根据读者进行还书操作
     * @param username
     * @param barcode
     */
    void returnBook(String username, String barcode);

    /**
     * 查询读者已还图书的总记录数
     * @param username
     * @return
     */
    int findReturnToalCount(String username);

    /**
     * 分页查询已还图书的记录
     * @param start
     * @param pageSize
     * @param username
     * @return
     */
    List<History> findReturn(int start, int pageSize, String username);

    /**
     * 查询此用户是否借阅过该图书
     * @param barcode
     * @param username
     * @return
     */
    int findBorrowByUsername(String barcode, String username);

    /**
     * 管理员查询用户的当前借阅数
     * @param username
     * @return
     */
    int findCurrentBorrow(String username);

    /**
     * 管理员查询用户的历史借阅数
     * @param username
     * @return
     */
    int findHistoryBorrow(String username);

    /**
     * 管理员查询所有用户的当前借阅总数
     * @return
     */
    int findBorrowCount();

    /**
     * 管理员分页查询当前借阅用户
     * @param start
     * @param pageSize
     * @return
     */
    List<History> findBorrowBook(int start, int pageSize);

    /**
     * 管理员查询所有用户的历史借阅总数
     * @return
     */
    int findReturnCount();

    /**
     * 管理员分页查询历史借阅用户信息
     * @param start
     * @param pageSize
     * @return
     */
    List<History> findReturnBook(int start, int pageSize);
}
