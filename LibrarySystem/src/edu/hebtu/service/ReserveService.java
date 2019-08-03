package edu.hebtu.service;

import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.Reserve;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:32
 */
public interface ReserveService {
    /**
     * 预约操作
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param place
     * @param username
     */
    void ReserveBook(String barcode, String type, String bookname, String author, String place, String username);

    /**
     * 查询是否有人借阅此书（一本书只能有一个人在借阅）
     * @param barcode
     * @return
     */
    int selectBook(String barcode);

    /**
     * 分页查询用户借阅情况
     * @param currentPage
     * @param pageSize
     * @param bookname
     * @param username
     * @return
     */
    PageBean<Reserve> PageQuery(int currentPage, int pageSize, String bookname,String username);

    /**
     * 用户取消预约
     * @param username
     * @param barcode
     */
    void deleteReserve(String username, String barcode);

    /**
     * 查询该用户还书是否有人预约了
     * @param barcode
     * @return
     */
    Reserve findReserveBook(String barcode);
}
