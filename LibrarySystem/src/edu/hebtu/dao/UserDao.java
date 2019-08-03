package edu.hebtu.dao;

import edu.hebtu.domain.User;
import edu.hebtu.service.UserService;

import java.util.List;

/**
 * @author DuZengXin
 * @date 2019/5/21 - 15:13
 */
public interface UserDao {
    /**
     * 根据用户名查询是否存在
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 保存用户信息
     * @param user
     */
    public void save(User user);

    /**
     * 查询用户
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);

    /**
     * 修改密码
     * @param username
     * @param password
     */
    public void updatePwd(String username,String password);

    /**
     * 修改信息
     * @param name
     * @param phone
     * @param email
     * @param username
     */
    void updateInfo(String name, String phone, String email, String username);

    /**
     * 管理员查询用户个数
     * @return
     */
    int findUserCount();

    /**
     * 管理员分页查询用户信息
     * @param start
     * @param pageSize
     * @return
     */
    List<User> findUserByPage(int start, int pageSize);

    /**
     * 查询是否有该激活码
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新用户的激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 管理员修改读者信息
     * @param username
     * @param name
     * @param email
     * @param phone
     */
    void updateUserInfo(String username, String name, String email, String phone);

    /**
     * 管理员删除读者
     * @param username
     */
    void deleteUser(String username);
}
