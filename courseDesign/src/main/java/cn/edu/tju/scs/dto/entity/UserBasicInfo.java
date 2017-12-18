package cn.edu.tju.scs.dto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Takahashi on 2016/12/19.
 */
public class UserBasicInfo implements Serializable{

    private int id;
    private String uid;
    private String username;
    private int age;
    private String tel;

    private String rolename;
    private String roleE;

    private List<PermissionDTO> permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    public String getRoleE() {
        return roleE;
    }

    public void setRoleE(String roleE) {
        this.roleE = roleE;
    }

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                ", rolename='" + rolename + '\'' +
                ", roleE='" + roleE + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
