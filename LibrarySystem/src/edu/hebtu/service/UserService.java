package edu.hebtu.service;

import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.User;

/**
 * @author DuZengXin
 * @date 2019/5/21 - 15:33
 */
public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 修改密码
     * @param username
     * @param password
     * @param password2
     * @return
     */
    boolean updatePwd(String username,String password,String password2);

    /**
     * 修改信息
     * @param name
     * @param phone
     * @param email
     * @param username
     */
    void updateInfo(String name, String phone, String email, String username);

    /**
     * 管理员查询全部用户信息
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<User> adminSelectUser(int currentPage, int pageSize);

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 管理员修改读者信息
     * @param username
     * @param name
     * @param email
     * @param phone
     */
    void updateUserInfo(String username, String name, String email, String phone);

    /**
     * 管理员删除读者操作
     * @param username
     */
    void deleteUser(String username);

    /**
     * 管理员添加读者
     * @param user
     * @return
     */
    boolean addUser(User user);
}
