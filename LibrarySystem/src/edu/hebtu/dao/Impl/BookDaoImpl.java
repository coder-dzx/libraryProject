package edu.hebtu.dao.Impl;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.hebtu.dao.BookDao;
import edu.hebtu.domain.Book;
import edu.hebtu.domain.Type;
import edu.hebtu.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/27 - 15:29
 */
public class BookDaoImpl implements BookDao {
    Connection conn=null;
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());

    /**
     * 查询总页数
     * @param bookname
     * @return
     */
    @Override
    public int findTotalCount(String bookname) {
        int total=0;
        try {
            String sql="select count(*) from books where 1=1";
            StringBuilder sb=new StringBuilder(sql);
            List params=new ArrayList();
            if(bookname!=null && bookname.length()>0 && !"null".equals(bookname)){
                sb.append(" and bookname like ? or type like ? or author like ? or place like ? or barcode like ? or sum like ? ");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
                params.add("%"+bookname+"%");
            }
            sql=sb.toString();
            Long count = qr.query(sql, new ScalarHandler<Long>(),params.toArray());
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 查询当前页的记录
     * @param start
     * @param pageSize
     * @param bookname
     * @return
     */
    @Override
    public List<Book> findByPage(int start, int pageSize, String bookname) {
        List<Book> list=new ArrayList<Book>();

        try {
            String sql="select * from books where 1=1";
            StringBuilder sb=new StringBuilder(sql);
            List params=new ArrayList();
            if(bookname!=null&&bookname.length()>0&&!"null".equals(bookname)){
                sb.append(" and bookname like ? or type like ? or author like ? or place like ? or barcode like ? or sum like ? ");
                params.add("%"+bookname+"%");
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
            list=qr.query(sql,new BeanListHandler<Book>(Book.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据图书id查询图书信息
     * @param barcode
     * @return
     */
    @Override
    public List<Book> findByBarcode(String barcode) {
        List<Book> list=new ArrayList<Book>();
        try {
            String sql="select * from books where barcode=?";
            list=qr.query(sql,new BeanListHandler<Book>(Book.class),barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 对借阅的读书数量进行更新
     * @param barcode
     */
    @Override
    public void updateBook(String barcode) {
        try {
            String sql="update books set sum=sum-1 where barcode=?";
            qr.update(sql,barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对图书的数量进行更新
     * @param barcode
     */
    @Override
    public void updateBookSum(String barcode) {
        try {
            String sql="update books set sum=sum+1 where barcode=?";
            qr.update(sql,barcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询图书类型个数
     * @return
     */
    @Override
    public List<Type> findType() {
        List<Type> list=new ArrayList<Type>();
        try {
            String sql="select * from booktype";
            list=qr.query(sql,new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-------------"+list);
        return list;
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
    public void adminUpdateBook(String barcode, String type, String bookname, String author, String sum, String place) {
        try {
            String sql="update books set barcode=?,type=?,bookname=?,author=?,sum=?,place=? where barcode=?";
            qr.update(sql,barcode,type,bookname,author,sum,place,barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void adminAddBook(String barcode, String type, String bookname, String author, String sum, String place) {
        try {
            String sql="insert into books (barcode,type,bookname,author,sum,place)values(?,?,?,?,?,?)";
            qr.update(sql,barcode,type,bookname,author,sum,place);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员删除图书
     * @param barcode
     */
    @Override
    public void adminDeleteBook(String barcode) {
        try {
            String sql="delete from books where barcode=?";
            qr.update(sql,barcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员查询图书类型个数
     * @return
     */
    @Override
    public int findTypeCount() {
        int total=0;
        try {
            String sql="select count(*) from booktype";
            Long count=qr.query(sql,new ScalarHandler<Long>());
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 管理员分页查询图书类型
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Type> findTypeByPage(int start, int pageSize) {
        List<Type> list=new ArrayList<Type>();
        try {
            String sql="select * from booktype limit ?,?";
            list=qr.query(sql,new BeanListHandler<Type>(Type.class),start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /***
     * 管理员修改图书类型
     * @param typename
     */
    @Override
    public void adminUpdateType(String typename,String typeid) {
        try {
            String sql="update booktype set typename=? where id=?";
            qr.update(sql,typename,typeid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员添加图书类型
     * @param typename
     */
    @Override
    public void adminAddType(String typename) {
        try {
            String sql="insert into booktype (typename) values(?)";
            qr.update(sql,typename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员删除图书类型
     * @param typename
     */
    @Override
    public void adminDeleteType(String typename) {
        try {
            String sql="delete from booktype where typename=?";
            qr.update(sql,typename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
