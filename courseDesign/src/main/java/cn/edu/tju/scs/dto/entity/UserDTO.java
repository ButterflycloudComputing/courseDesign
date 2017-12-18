package cn.edu.tju.scs.dto.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/3 2:24.
 */
public class UserDTO implements Serializable{


    private int id;
    private String email;
    private String company;
    private String username;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    List<PermissionDTO> permissions;

    public UserDTO() {
    }

    public UserDTO(int id, String email, String username,String company) {
        this.id = id;
        this.email = email;
        this.company = company;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
