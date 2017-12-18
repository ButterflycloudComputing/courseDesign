package cn.edu.tju.scs.entity;

import cn.edu.tju.scs.utils.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by Takahashi on 2016/12/19.
 */
public class Application {

    private int id;
    private String uid;
    private int vid;  // 假期类型
    private String date;  // 时间序列
    private int count;  // 天数
    private String reason;  // 请假原因
    private String info;    //
    private int status;   // 请假申请状态，0（未通过），1（已通过，未销假），2（已销假），-1（已拒绝）
    private int prover;  // 审查者 角色id


    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;
    private byte deleteMark;  // 删除标志：0（正常）；-1（已删除）

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getProver() {
        return prover;
    }

    public void setProver(int prover) {
        this.prover = prover;
    }

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

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "Application{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", vid=" + vid +
                ", date='" + date + '\'' +
                ", count=" + count +
                ", reason='" + reason + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", prover=" + prover +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                '}';
    }
}
