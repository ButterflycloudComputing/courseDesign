package cn.edu.tju.scs.entity;

import cn.edu.tju.scs.utils.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by Takahashi on 2016/12/19.
 */
public class Bonus {

    private int id;
    private String uid;
    private String month;
    private int bonus;
    private String info;

    private int replacea;
    private int replaceb;
    private int half;
    private int zero;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
    private byte deleteMark;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public int getReplacea() {
        return replacea;
    }

    public void setReplacea(int replacea) {
        this.replacea = replacea;
    }

    public int getReplaceb() {
        return replaceb;
    }

    public void setReplaceb(int replaceb) {
        this.replaceb = replaceb;
    }

    public int getHalf() {
        return half;
    }

    public void setHalf(int half) {
        this.half = half;
    }

    public int getZero() {
        return zero;
    }

    public void setZero(int zero) {
        this.zero = zero;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", month='" + month + '\'' +
                ", bonus=" + bonus +
                ", info='" + info + '\'' +
                ", replacea=" + replacea +
                ", replaceb=" + replaceb +
                ", half=" + half +
                ", zero=" + zero +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                '}';
    }
}
