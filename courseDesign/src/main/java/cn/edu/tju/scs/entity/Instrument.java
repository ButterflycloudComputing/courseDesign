package cn.edu.tju.scs.entity;

import cn.edu.tju.scs.utils.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by Takahashi on 2016/11/19.
 */
public class Instrument {

    private Long id;

    private String name;

    private String eName;

    private String category;  // 八音分类

    private String hscat;  // 霍萨分类

    private String hscat2; // 霍萨分类2

    private String play;  // 演奏方式

    private String location;

    private String race;

    private String time;

    private String info;

    private String briefInfo;

    private int visit;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createTime;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateTime;

    private byte deleteMark;


    public Instrument() {
    }

    public Instrument(Long id,String name, String ename, String category, String hscat, String hscat2, String play, String location, String race, String time, String info,String briefInfo) {
        this.id= id;
        this.name = name;
        eName = ename;
        this.category = category;
        this.hscat = hscat;
        this.hscat2 = hscat2;
        this.play = play;
        this.location = location;
        this.race = race;
        this.time = time;
        this.info = info;
        this.briefInfo = briefInfo;
    }

    public Instrument(String name, String ename, String category, String hscat, String hscat2, String play, String location, String race, String time, String info,String briefInfo) {
        this.name = name;
        eName = ename;
        this.category = category;
        this.hscat = hscat;
        this.hscat2 = hscat2;
        this.play = play;
        this.location = location;
        this.race = race;
        this.time = time;
        this.info = info;
        this.briefInfo = briefInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHscat() {
        return hscat;
    }

    public void setHscat(String hscat) {
        this.hscat = hscat;
    }

    public String getHscat2() {
        return hscat2;
    }

    public void setHscat2(String hscat2) {
        this.hscat2 = hscat2;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getLocationDownload() {
        if(location != null){
            return location.replace(",", ";");
        }else{
            return location;
        }
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRace() {
        return race;
    }

    public String getRaceDownload() {
        if(race != null){
            return race.replace(",", ";");
        }else{
            return race;
        }
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getTime() {
        return time;
    }

    public String getTimeDownload() {
        if(time != null){
            return time.replace(",", ";");
        }else{
            return time;
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
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

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eName='" + eName + '\'' +
                ", category='" + category + '\'' +
                ", hscat='" + hscat + '\'' +
                ", hscat2='" + hscat2 + '\'' +
                ", play='" + play + '\'' +
                ", location='" + location + '\'' +
                ", race='" + race + '\'' +
                ", time='" + time + '\'' +
                ", info='" + info + '\'' +
                ", visit=" + visit +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteMark=" + deleteMark +
                ", briefInfo=" + briefInfo +
                '}';
    }
}
