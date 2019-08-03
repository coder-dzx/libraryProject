package edu.hebtu.dao;

import edu.hebtu.domain.Book;
import edu.hebtu.domain.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/27 - 15:29
 */
public interface BookDao {
    /**
     * 查询满足条件的总记录数
     * @param bookname
     * @return
     */
    int findTotalCount(String bookname);

    /**
     * 查询当前页的记录
     * @param start
     * @param pageSize
     * @param bookname
     * @return
     */
    List<Book> findByPage(int start, int pageSize, String bookname);

    /**
     * 根据图书编号查询图书信息
     * @param barcode
     * @return
     */
    List<Book> findByBarcode(String barcode);

    /**
     * 读者借阅图书后，对总数量进行更新
     * @param barcode
     */
    void updateBook(String barcode);

    /**
     * 读者还书后，对总数量进行更新
     * @param barcode
     */
    void updateBookSum(String barcode);

    /**
     * 查询图书类型
     * @return
     */
     List<Type> findType();

    /**
     * 管理员修改图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    void adminUpdateBook(String barcode, String type, String bookname, String author, String sum, String place);

    /**
     * 管理员添加图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    void adminAddBook(String barcode, String type, String bookname, String author, String sum, String place);

    /**
     * 管理员删除图书
     * @param barcode
     */
    void adminDeleteBook(String barcode);

    /**
     * 管理员查询图书类型总数
     * @return
     */
    int findTypeCount();

    /**
     * 管理员分页查询图书类型记录
     * @param start
     * @param pageSize
     * @return
     */
    List<Type> findTypeByPage(int start, int pageSize);

    /**
     * 管理员修改图书类型
     * @param typename
     */
    void adminUpdateType(String typename,String typeid);

    /**
     * 管理员添加图书类型
     * @param typename
     */
    void adminAddType(String typename);

    /**
     * 管理员删除图书类型
     * @param typename
     */
    void adminDeleteType(String typename);
}
