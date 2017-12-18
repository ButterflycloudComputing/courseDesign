package cn.edu.tju.scs.entity.auth;

import java.util.Date;

/**
 * Created by haoxiaotian on 2016/9/4 17:03.
 */
public class Role {
    private int id;
    private String name;
    private String ename;
    private Date createTime;
    private Date updateTime;
    private byte deleteMark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
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

    public int getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(byte deleteMark) {
        this.deleteMark = deleteMark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ename='" + ename + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                '}';
    }
}
