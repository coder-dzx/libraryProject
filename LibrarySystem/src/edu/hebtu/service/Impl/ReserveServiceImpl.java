package edu.hebtu.service.Impl;

import edu.hebtu.dao.Impl.ReserveDaoImpl;
import edu.hebtu.dao.ReserveDao;
import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.Reserve;
import edu.hebtu.service.ReserveService;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:32
 */
public class ReserveServiceImpl implements ReserveService {
    ReserveDao reserverDao=new ReserveDaoImpl();
    /**
     * 预约操作
     * @param barcode
     * @param type
     * @param bookname
     * @param author
     * @param place
     * @param username
     */
    @Override
    public void ReserveBook(String barcode, String type, String bookname, String author, String place, String username) {
        reserverDao.updateReserve(barcode,type,bookname,author,place,username);
    }

    /**
     * 查询是否有人借阅此书（一本书只能有一个人在借阅）
     * @param barcode
     * @return
     */
    @Override
    public int selectBook(String barcode) {

        return reserverDao.findBook(barcode);
    }

    /**
     * 分页查询用户借阅信息
     * @param currentPage
     * @param pageSize
     * @param bookname
     * @param username
     * @return
     */
    @Override
    public PageBean<Reserve> PageQuery(int currentPage, int pageSize, String bookname, String username) {
        PageBean<Reserve> pb=new PageBean<Reserve>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount=reserverDao.findUserTotalCount(username,bookname);
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<Reserve> list=reserverDao.findByPage(start,pageSize,bookname,username);
        pb.setList(list);
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 用户取消预约
     * @param username
     * @param barcode
     */
    @Override
    public void deleteReserve(String username, String barcode) {
        reserverDao.deleteReserveBook(username,barcode);
    }

    /**
     * 查询该用户要归还的书是否有人预约
     * @param barcode
     * @return
     */
    @Override
    public Reserve findReserveBook(String barcode) {

        return reserverDao.findReserveBook(barcode);
    }
}
