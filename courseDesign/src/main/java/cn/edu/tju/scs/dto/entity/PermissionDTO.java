package cn.edu.tju.scs.dto.entity;

import java.io.Serializable;

/**
 * Created by haoxiaotian on 2016/11/23 3:58.
 */
public class PermissionDTO implements Serializable{

    private String name;
    private String action;

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

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "name='" + name + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
