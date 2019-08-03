package edu.hebtu.dao.Impl;

import edu.hebtu.dao.ReserveDao;
import edu.hebtu.domain.Book;
import edu.hebtu.domain.Reserve;
import edu.hebtu.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:33
 */
public class ReserveDaoImpl implements ReserveDao {
    Connection conn=null;
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
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
    public void updateReserve(String barcode, String type, String bookname, String author, String place, String username) {
        try {
            String str="insert into reservebook values(?,?,?,?,?,?)";
            qr.update(str,barcode,type,bookname,author,place,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询是否有人借阅此书（一本书只能有一个人在借阅）
     * @param barcode
     * @return
     */
    @Override
    public int findBook(String barcode) {
        int count=0;
        try {
            String sql="select count(*) from reservebook where barcode=?";
            Long sum=qr.query(sql,new ScalarHandler<Long>(),barcode);
            count=Integer.parseInt(sum.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询此用户的借阅数量
     * @return
     */
    @Override
    public int findUserTotalCount(String username,String bookname) {
        int count=0;
        try {
            String sql="select count(*) from reservebook where username=?";
            StringBuilder sb=new StringBuilder(sql);
            List params=new ArrayList();
            params.add(username);
            if(bookname!=null && bookname.length()>0 && !"null".equals(bookname)){
                sb.append(" and bookname like ? or type like ? or author like ? or place like ? or barcode like ? ");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
            }
            sql=sb.toString();
            Long sum=qr.query(sql,new ScalarHandler<Long>(),params.toArray());
            count=Integer.parseInt(sum.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 分页查询此用户的借阅信息
     * @param start
     * @param pageSize
     * @param bookname
     * @param username
     * @return
     */
    @Override
    public List<Reserve> findByPage(int start, int pageSize, String bookname, String username) {
        List<Reserve> list=new ArrayList<Reserve>();
        try {
            String sql="select * from reservebook where username=? ";
            StringBuilder sb=new StringBuilder(sql);
            List params=new ArrayList();
            params.add(username);
            if(bookname!=null&&bookname.length()>0&&!"null".equals(bookname)){
                sb.append(" and bookname like ? or type like ? or author like ? or place like ? or barcode like ? ");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
            }
            sb.append(" limit ?,? ");
            sql=sb.toString();
            params.add(start);
            params.add(pageSize);
            list=qr.query(sql,new BeanListHandler<Reserve>(Reserve.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 用户取消预约
     * @param username
     * @param barcode
     */
    @Override
    public void deleteReserveBook(String username, String barcode) {
        try {
            String sql="delete from reservebook where username=? and barcode=?";
            qr.update(sql,username,barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据库是否有人预约此书
     * @param barcode
     * @return
     */
    @Override
    public Reserve findReserveBook(String barcode) {
        Reserve reserve=null;
        try {
            String sql="select * from reservebook where barcode=?";
            reserve=qr.query(sql,new BeanHandler<Reserve>(Reserve.class),barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserve;
    }
}
