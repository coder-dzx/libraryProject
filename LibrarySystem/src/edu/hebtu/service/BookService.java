package edu.hebtu.service;

import edu.hebtu.domain.Book;
import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/27 - 15:11
 */
public interface BookService {
    /**
     * 根据书名分页查询
     * @param currentPage
     * @param pageSize
     * @param bookname
     * @return
     */
    PageBean<Book> pageQuery(int currentPage, int pageSize, String bookname);

    /**
     * 查询图书类型
     * @return
     */
    List<Type> selectType();

    /**
     * 管理员修改图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    void updateBook(String barcode, String type, String bookname, String author, String sum, String place);

    /**
     * 管理员添加图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    void addBook(String barcode, String type, String bookname, String author, String sum, String place);

    /**
     * 管理员删除图书
     * @param barcode
     */
    void deleteBook(String barcode);

    /**
     * 管理员分页查询图书类型
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Type> adminSelectType(int currentPage, int pageSize);

    /**
     * 管理员修改图书类型
     * @param typename
     */
    void updateType(String typename,String typeid);

    /**
     * 管理员添加图书类型
     * @param typename
     */
    void adminAddType(String typename);

    /**
     * 管理员删除图书类型
     * @param typename
     */
    void deleteType(String typename);
}
