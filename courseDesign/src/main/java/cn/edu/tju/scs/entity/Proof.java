package cn.edu.tju.scs.entity;

import java.util.Date;

/**
 * Created by Takahashi on 2016/12/19.
 */
public class Proof {

    private int id;
    private int aid;
    private String name;
    private String url;
    private String tempUrl;
    private String size;

    private String type;
    private int typeNum;

    private String mediaType;
    private String originName;
    private String fileMd5;

    private Date createTime;
    private Date updateTime;
    private byte deleteMark;

    private Application application;

    public Proof(int aid, String name, String url, String tempUrl, String size, int typeNum, String mediaType, String originName, String fileMd5) {
        this.aid = aid;
        this.name = name;
        this.url = url;
        this.tempUrl = tempUrl;
        this.size = size;
        this.typeNum = typeNum;
        this.mediaType = mediaType;
        this.originName = originName;
        this.fileMd5 = fileMd5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTempUrl() {
        return tempUrl;
    }

    public void setTempUrl(String tempUrl) {
        this.tempUrl = tempUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Proof{" +
                "id=" + id +
                ", aid=" + aid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", tempUrl='" + tempUrl + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", typeNum=" + typeNum +
                ", mediaType='" + mediaType + '\'' +
                ", originName='" + originName + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                ", application=" + application +
                '}';
    }
}
