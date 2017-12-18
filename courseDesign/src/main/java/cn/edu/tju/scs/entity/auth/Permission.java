package cn.edu.tju.scs.entity.auth;

import java.util.Date;
import java.util.List;

/**
 * Created by haoxiaotian on 2016/9/4 17:03.
 */
public class Permission {
    private int id;
    private String name;
    private String action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", action='" + action + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                '}';
    }
}
