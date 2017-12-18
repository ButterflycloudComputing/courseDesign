package cn.edu.tju.scs.dto.entity;

import cn.edu.tju.scs.utils.CustomDateSerializer;
import cn.edu.tju.scs.utils.PasswordUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by haoxiaotian on 2016/11/23 20:56.
 */
public class UserResponseDTO {
    private int id;
    private String email;
    private String username;
    private String company;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
    private byte deleteMark;

    public UserResponseDTO() {
    }

    public UserResponseDTO(int id, String email, String username,String company) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponseDTO)) return false;

        UserResponseDTO user = (UserResponseDTO) o;

        if (id != user.id) return false;
        if (!company.equals(user.company)) return false;
        if (!email.equals(user.email)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + company.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", company='" + company + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                '}';
    }
}
