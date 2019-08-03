package edu.hebtu.service.Impl;

import edu.hebtu.dao.BookDao;
import edu.hebtu.dao.HistoryDao;
import edu.hebtu.dao.Impl.BookDaoImpl;
import edu.hebtu.dao.Impl.HistoryDaoImpl;
import edu.hebtu.domain.Book;
import edu.hebtu.domain.History;
import edu.hebtu.domain.PageBean;
import edu.hebtu.service.HistoryService;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 15:02
 */
public class HistoryServiceImpl implements HistoryService {
    HistoryDao historyDao=new HistoryDaoImpl();
    BookDao bookDao=new BookDaoImpl();

    /**
     * 借阅操作
     * @param barcode
     * @param username
     */
    @Override
    public void borrowBook(String barcode, String username) {
        List<Book> list=bookDao.findByBarcode(barcode);
        Book book = list.get(0);
        String type = book.getType();
        String bookname = book.getBookname();
        historyDao.save(barcode,username,type,bookname);
        bookDao.updateBook(barcode);
    }

    /**
     * 分页查询读者正在借阅的图书
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<History> pageQuery(int currentPage, int pageSize, String username) {
        HistoryDao historyDao=new HistoryDaoImpl();
        PageBean<History> pb=new PageBean<History>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
//        查询总记录数
        int totalCount=historyDao.findToalCount(username);
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<History> list=historyDao.findBorrow(start,pageSize,username);
        pb.setList(list);
        System.out.println(list);
        //总页数
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 对读者进行还书操作
     * @param username
     * @param barcode
     */
    @Override
    public void returnBook(String username, String barcode) {
        historyDao.returnBook(username,barcode);
        bookDao.updateBookSum(barcode);
    }

    @Override
    public PageBean<History> returnpageQuery(int currentPage, int pageSize, String username) {
        HistoryDao historyDao=new HistoryDaoImpl();
        PageBean<History> pb=new PageBean<History>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
//        查询总记录数
        int totalCount=historyDao.findReturnToalCount(username);
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<History> list=historyDao.findReturn(start,pageSize,username);
        pb.setList(list);
        System.out.println(list);
        //总页数
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 管理员查询所有用户的当前借阅信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<History> findBorrowBook(int currentPage, int pageSize) {
        HistoryDao historyDao=new HistoryDaoImpl();
        PageBean<History> pb=new PageBean<History>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount=historyDao.findBorrowCount();
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<History> list=historyDao.findBorrowBook(start,pageSize);
        pb.setList(list);
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 管理员查询所有用户的历史借阅信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<History> findReturnBook(int currentPage, int pageSize) {
        HistoryDao historyDao=new HistoryDaoImpl();
        PageBean<History> pb=new PageBean<History>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
//        查询总记录数
        int totalCount=historyDao.findReturnCount();
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<History> list=historyDao.findReturnBook(start,pageSize);
        pb.setList(list);
        System.out.println(list);
        //总页数
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 查询用户是否借阅了此书
     * @param barcode
     * @param username
     * @return
     */
    @Override
    public int findBorrowByUsername(String barcode, String username) {
         return historyDao.findBorrowByUsername(barcode,username);
    }
}
