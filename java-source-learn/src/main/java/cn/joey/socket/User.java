package cn.joey.socket;

import java.io.Serializable;

/**
 * @author liujiji
 */

public class User implements Serializable,Comparable {

    private static final long serialVersionUID = -1373610311847003424L;

    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[name="+name+",password="+password+"]";
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User o2=(User) o;
            if(o2.getName().equals(this.getName())){
                return 0;
            }else if(o2.getName().length()>this.getName().length()){
                return 1;
            }else{
                return -1;
            }
        } else {
          return -1;
        }
    }
}
