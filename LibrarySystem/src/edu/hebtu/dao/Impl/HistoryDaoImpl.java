package edu.hebtu.dao.Impl;

import edu.hebtu.dao.HistoryDao;
import edu.hebtu.domain.Book;
import edu.hebtu.domain.History;
import edu.hebtu.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 15:02
 */
public class HistoryDaoImpl implements HistoryDao {
    Connection conn=null;
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
    /**
     * 将用户借阅的图书存到history表中
     * @param barcode
     * @param username
     * @param type
     * @param bookname
     */
    @Override
    public void save(String barcode, String username, String type, String bookname) {
        try {
            String sql="insert into history(barcode,username,type,bookname,borrowdate,returndate,status) values(?,?,?,?,?,?,?)";
            Date date=new Date();
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start = sf.format(date);
            Date startdate=null;
            try {
                 startdate=sf.parse(start);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c= Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH,1);
            String end = sf.format(c.getTime());
            Date enddate=null;
            try {
                enddate=sf.parse(end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            qr.update(sql,barcode,username,type,bookname,startdate,enddate,2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findToalCount(String username) {
        int total=0;
        try {
            String sql="select count(*) from history where username=? and status=2";
            Long count=qr.query(sql,new ScalarHandler<Long>(),username);
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    /**
     * 查询满足条件的记录
     * @param start
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public List<History> findBorrow(int start, int pageSize, String username) {
        List<History> list =new ArrayList<History>();
        try {
            String sql="select * from history where username=? and status=2 limit ?,?";
            list=qr.query(sql,new BeanListHandler<History>(History.class),username,start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对读者指定的图书进行还书操作
     * @param username
     * @param barcode
     */
    @Override
    public void returnBook(String username, String barcode) {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end = sf.format(date);
        Date enddate=null;
        try {
            enddate=sf.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            String sql="update history set status=?,returndate=?where username=?and barcode=?";
            qr.update(sql,1,enddate,username,barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询读者已还图书的总记录数
     * @param username
     * @return
     */
    @Override
    public int findReturnToalCount(String username) {
        int total=0;
        try {
            String sql="select count(*) from history where username=? and status=1";
            Long count=qr.query(sql,new ScalarHandler<Long>(),username);
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    /**
     * 分页查询读者已还图书的记录
     * @param start
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public List<History> findReturn(int start, int pageSize, String username) {
        List<History> list =new ArrayList<History>();
        try {
            String sql="select * from history where username=? and status=1 limit ?,?";
            list=qr.query(sql,new BeanListHandler<History>(History.class),username,start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询此用户当前是否正在借阅此书
     * @param barcode
     * @param username
     * @return
     */
    @Override
    public int findBorrowByUsername(String barcode, String username) {
        int sum=0;
        try {
            String sql="select count(*) from history where barcode=? and username=? and status=2";
            Long count=qr.query(sql,new ScalarHandler<Long>(),barcode,username);
            sum=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    /**
     * 管理员查询当前用户的借阅数
     * @param username
     * @return
     */
    @Override
    public int findCurrentBorrow(String username) {
        int current=0;
        try {
            String sql="select count(*) from history where username=? and status=2";
            Long curr=qr.query(sql,new ScalarHandler<Long>(),username);
            current= Integer.parseInt(curr.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return current;
    }

    /**
     * 管理员查询用户的历史借阅数
     * @param username
     * @return
     */
    @Override
    public int findHistoryBorrow(String username) {
        int history=0;
        try {
            String sql="select count(*) from history where username=? and status=1";
            Long his=qr.query(sql,new ScalarHandler<Long>(),username);
            history= Integer.parseInt(his.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    /**
     * 管理员查询当前借阅的书的总记录
     * @return
     */
    @Override
    public int findBorrowCount() {
        int sum=0;
        try {
            String sql="select count(*) from history where status=2";
            Long count=qr.query(sql,new ScalarHandler<Long>());
            sum=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    /**
     * 管理员分页查询用户的借阅信息
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<History> findBorrowBook(int start, int pageSize) {
        List<History> list =new ArrayList<History>();
        try {
            String sql="select * from history where status=2 limit ?,?";
            list=qr.query(sql,new BeanListHandler<History>(History.class),start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 管理员查询所有用户的历史借阅总数
     * @return
     */
    @Override
    public int findReturnCount() {
        int total=0;
        try {
            String sql="select count(*) from history where status=1";
            Long count=qr.query(sql,new ScalarHandler<Long>());
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;

    }

    /**
     * 分页查询历史借阅用户信息
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<History> findReturnBook(int start, int pageSize) {
        List<History> list =new ArrayList<History>();
        try {
            String sql="select * from history where status=1 limit ?,?";
            list=qr.query(sql,new BeanListHandler<History>(History.class),start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
