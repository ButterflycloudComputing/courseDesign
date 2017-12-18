package cn.edu.tju.scs.dto.entity;

import cn.edu.tju.scs.utils.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * Created by Takahashi on 2016/11/29.
 */
public class AdminUserDTO {

    private int id;
    private String email;
    private String company;
    private String username;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;
    private String roleName;
    private int roleId;

    public AdminUserDTO() {
    }

    public AdminUserDTO(int id, String email,
                        String username,String company,
                        Date createTime, String roleName,
                        int roleId) {
        this.id = id;
        this.email = email;
        this.company = company;
        this.username = username;
        this.createTime = createTime;
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminUserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", roleName='" + roleName + '\'' +
                ", roleId=" + roleId +
                '}';
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
