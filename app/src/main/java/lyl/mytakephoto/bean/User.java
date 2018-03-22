package lyl.mytakephoto.bean;

import java.io.Serializable;

/**
 * @author lyl
 * @date 2018/3/14.
 */

public class User implements Serializable {

    private int age;
    private String username;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
