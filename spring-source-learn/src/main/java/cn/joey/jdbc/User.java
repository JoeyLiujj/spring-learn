package cn.joey.jdbc;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @auther liujiji
 * @date 2018/12/6 11:07
 */
@Table(name="t_user")
public class User implements Serializable{
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "credis")
    private String credis;
    @Column(name = "last_ip")
    private String last_ip;
    @Column(name = "last_visit")
    private Date last_visit;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCredis() {
        return credis;
    }

    public void setCredis(String credis) {
        this.credis = credis;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public Date getLast_visit() {
        return last_visit;
    }

    public void setLast_visit(Date last_visit) {
        this.last_visit = last_visit;
    }
}
