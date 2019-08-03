package edu.hebtu.domain;

import java.io.Serializable;

/**
 * @author DuZengXin
 * @date 2019/5/21 - 15:14
 */
public class User implements Serializable{
    private int uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private int grade;//1代表用户  2 代表管理员
    private String status;//激活状态
    private String code;//激活码
    private int currentborrow;//当前借阅数
    private int historyborrow;//历史借阅数

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCurrentborrow() {
        return currentborrow;
    }

    public void setCurrentborrow(int currentborrow) {
        this.currentborrow = currentborrow;
    }

    public int getHistoryborrow() {
        return historyborrow;
    }

    public void setHistoryborrow(int historyborrow) {
        this.historyborrow = historyborrow;
    }

    public User() {
    }

    public User(int uid, String username, String password, String name, String email, String phone, int grade, String status, String code, int currentborrow, int historyborrow) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.status = status;
        this.code = code;
        this.currentborrow = currentborrow;
        this.historyborrow = historyborrow;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", grade=" + grade +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", currentborrow=" + currentborrow +
                ", historyborrow=" + historyborrow +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
