package edu.hebtu.dao;

import edu.hebtu.domain.Reserve;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:33
 */
public interface ReserveDao {
    /**
     * 预约操作
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param place
     * @param username
     */
    void updateReserve(String barcode, String type, String bookname, String author, String place, String username);

    /**
     * 查询是否有人借阅此书（一本书只能有一个人在借阅）
     * @param barcode
     * @return
     */
    int findBook(String barcode);

    /**
     * 查询此用户的借阅总量
     * @param username
     * @return
     */
    int findUserTotalCount(String username,String bookname);

    /**
     * 分页查询此用户的借阅信息
     * @param start
     * @param pageSize
     * @param bookname
     * @param username
     * @return
     */
    List<Reserve> findByPage(int start, int pageSize, String bookname, String username);

    /**
     * 用户取消预约
     * @param username
     * @param barcode
     */
    void deleteReserveBook(String username, String barcode);

    /**
     * 查询数据库是否有人预约此书
     * @param barcode
     * @return
     */
    Reserve findReserveBook(String barcode);
}
