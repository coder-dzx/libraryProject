package edu.hebtu.service.Impl;

import edu.hebtu.dao.BookDao;
import edu.hebtu.dao.Impl.BookDaoImpl;
import edu.hebtu.domain.Book;
import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.Type;
import edu.hebtu.service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/27 - 15:11
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao=new BookDaoImpl();
    /**
     * 根据书名进行分页查询
     * @param currentPage
     * @param pageSize
     * @param bookname
     * @return
     */
    @Override
    public PageBean<Book> pageQuery(int currentPage, int pageSize, String bookname) {

        PageBean<Book> pb=new PageBean<Book>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        //总记录数
        int totalCount=bookDao.findTotalCount(bookname);
        pb.setTotalCount(totalCount);

        int start =(currentPage-1)*pageSize;
        List<Book> list=bookDao.findByPage(start,pageSize,bookname);
        pb.setList(list);
        //总页数
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 查询图书类型
     * @return
     */
    @Override
    public List<Type> selectType() {
        List<Type> types=bookDao.findType();
        return types;
    }

    /**
     * 管理员修改图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    @Override
    public void updateBook(String barcode, String type, String bookname, String author, String sum, String place) {
        bookDao.adminUpdateBook(barcode,type,bookname,author,sum,place);
    }

    /**
     * 管理员添加图书信息
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param sum
     * @param place
     */
    @Override
    public void addBook(String barcode, String type, String bookname, String author, String sum, String place) {
        bookDao.adminAddBook(barcode,type,bookname,author,sum,place);
    }

    /**
     * 管理员删除图书
     * @param barcode
     */
    @Override
    public void deleteBook(String barcode) {
        bookDao.adminDeleteBook(barcode);
    }

    /**
     * 管理员分页查询图书类型
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Type> adminSelectType(int currentPage, int pageSize) {
        PageBean<Type> pb=new PageBean<Type>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount=bookDao.findTypeCount();
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<Type> list=bookDao.findTypeByPage(start,pageSize);
        pb.setList(list);
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 管理员修改图书类型
     * @param typename
     */
    @Override
    public void updateType(String typename,String typeid) {
        bookDao.adminUpdateType(typename,typeid);
    }

    /**
     * 管理员添加图书类型
     * @param typename
     */
    @Override
    public void adminAddType(String typename) {
        bookDao.adminAddType(typename);
    }

    /**
     * 管理员删除图书类型
     * @param typename
     */
    @Override
    public void deleteType(String typename) {
        bookDao.adminDeleteType(typename);
    }
}
