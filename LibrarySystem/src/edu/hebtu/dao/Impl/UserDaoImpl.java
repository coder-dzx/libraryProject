package edu.hebtu.dao.Impl;

import edu.hebtu.dao.UserDao;
import edu.hebtu.domain.User;
import edu.hebtu.util.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/21 - 15:18
 */
public class UserDaoImpl implements UserDao {
    Connection conn=null;
    QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
    /**
     * 根据用户名查询是否存在
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user=null;
        try {
            String sql="select * from users where username=?";
            user=qr.query(sql,new BeanHandler<User>(User.class),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void save(User user) {
        try {
            String sql="insert into users(username,password,name,email,phone,grade,status,code) values(?,?,?,?,?,?,?,?)";
            qr.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),1,user.getStatus(),user.getCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUser(String username, String password) {
        User user=null;
        try {
            String sql="select * from users where username=? and password=?";
            user=qr.query(sql,new BeanHandler<User>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改密码
     * @param username
     */
    @Override
    public void updatePwd(String username,String password) {
        try {
            String sql="update users set password=? where username=?";
            qr.update(sql,password,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改信息
     * @param name
     * @param phone
     * @param email
     * @param username
     */
    @Override
    public void updateInfo(String name, String phone, String email, String username) {
        try {
            String sql="update users set name=?,phone=?,email=? where username=?";
            qr.update(sql,name,phone,email,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员查询用户个数
     * @return
     */
    @Override
    public int findUserCount() {
        int total=0;
        try {
            String sql="select count(*) from users where grade=1 ";
            Long count=qr.query(sql,new ScalarHandler<Long>());
            total=Integer.parseInt(count.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 管理员分页查询用户信息
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<User> findUserByPage(int start, int pageSize) {
        List<User> list=new ArrayList<User>();
        try {
            String sql="select * from users where grade=1 limit ?,? ";
            list=qr.query(sql,new BeanListHandler<User>(User.class),start,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;


    }

    /***
     * 查询是否有该激活码
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user=null;
        try {
            String sql="select * from users where code=?";
            user=qr.query(sql,new BeanHandler<User>(User.class),code);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 更新用户的激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        try {
            String sql="update users set status='Y' where username=?";
            qr.update(sql,user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员修改读者信息
     * @param username
     * @param name
     * @param email
     * @param phone
     */
    @Override
    public void updateUserInfo(String username, String name, String email, String phone) {
        try {
            String sql="update users set name=?,email=?,phone=? where username=?";
            qr.update(sql,name,email,phone,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员删除读者
     * @param username
     */
    @Override
    public void deleteUser(String username) {
        try {
            String sql="delete from users where username=?";
            qr.update(sql,username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
