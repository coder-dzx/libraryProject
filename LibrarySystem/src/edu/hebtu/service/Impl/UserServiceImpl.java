package edu.hebtu.service.Impl;

import edu.hebtu.dao.HistoryDao;
import edu.hebtu.dao.Impl.HistoryDaoImpl;
import edu.hebtu.dao.Impl.UserDaoImpl;
import edu.hebtu.dao.UserDao;
import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.User;
import edu.hebtu.service.UserService;
import edu.hebtu.util.MailUtils;
import edu.hebtu.util.UuidUtil;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/21 - 16:00
 */
public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    HistoryDao historyDao=new HistoryDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean register(User user) {
        User u=userDao.findByUsername(user.getUsername());

        if(u!=null){
         return false;
        }

        //设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        System.out.println(user.getCode());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //激活邮件发送，邮件正文
        String content="<a href='http://localhost/activeUserServlet?code="+user.getCode()+"'>点击激活【河北师范大学图书馆管理系统】</a> ";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;

}

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {

        return userDao.findUser(username,password);
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @param password2
     * @return
     */
    @Override
    public boolean updatePwd(String username,String password,String password2) {
        boolean flag=false;
        User user = userDao.findByUsername(username);
        String pwd=(String)user.getPassword();
        if(pwd.equals(password)){
            userDao.updatePwd(username,password2);
            flag=true;
        }
        return flag;
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
        userDao.updateInfo(name,phone,email,username);
    }

    /**
     * 管理员查询所有用户信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<User> adminSelectUser(int currentPage, int pageSize) {
        PageBean<User> pb=new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount=userDao.findUserCount();
        pb.setTotalCount(totalCount);
        int start =(currentPage-1)*pageSize;
        List<User> list=userDao.findUserByPage(start,pageSize);
        //查询用户的当前借阅数和历史借阅数
        for (int i = 0; i <list.size(); i++) {
            String username = list.get(i).getUsername();
            int current=historyDao.findCurrentBorrow(username);
            list.get(i).setCurrentborrow(current);
            int history=historyDao.findHistoryBorrow(username);
            list.get(i).setHistoryborrow(history);
        }
        pb.setList(list);
        int totalPage=totalCount % pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user=userDao.findByCode(code);
        if(user!=null){
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 管理员修改图书信息
     * @param username
     * @param name
     * @param email
     * @param phone
     */
    @Override
    public void updateUserInfo(String username, String name, String email, String phone) {
        userDao.updateUserInfo(username,name,email,phone);
    }

    /**
     * 管理员删除读者操作
     * @param username
     */
    @Override
    public void deleteUser(String username) {
        userDao.deleteUser(username);
    }

    /**
     * 管理员添加读者
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        User u=userDao.findByUsername(user.getUsername());

        if(u!=null){
            return false;
        }
        //设置激活状态
        user.setStatus("Y");
        userDao.save(user);

        //激活邮件发送，邮件正文
        String content="【河北师范大学图书馆管理系统】<br> 您的账户已激活，请登陆使用。";
        MailUtils.sendMail(user.getEmail(),content,"通知邮件");
        return true;
    }


}
