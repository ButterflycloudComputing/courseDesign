package cn.edu.tju.scs;

/**
 * Created by haoxiaotian on 2016/11/29 11:28.
 */
public class Config {

    /**
     * 跟 imageServer 有关的配置
     */
    private String imageServerUrl;

    private String downloadSuffix;
    private String imageAccessUrl;
    private String audioAccessUrl;
    private String videoAccessUrl;
    private String analysisAccessUrl;

    private String searchServerUrl;

//  文件服务器目录前缀
    private String prefix;



    /**
     * 记住我 Cookie 相关设置
     */
    private String cookieName;

    private String cookieEncodeKey;

    private String cookiePath;

    private String cookieDomain;

    private int cookieMaxAge;


    /**
     * 附件类型配置
     */
    private String imageType;

    private String audioType;

    private String videoType;

    private String analysisType;


    /**
     * 邮件服务器配置信息
     *
     */
    private String emailSender;   // 发件人

    private String activeUrl;  // 激活链接位置

    private String forgetUrl; // 忘记密码链接位置


    public String getForgetUrl() {
        return forgetUrl;
    }

    public void setForgetUrl(String forgetUrl) {
        this.forgetUrl = forgetUrl;
    }

    public String getActiveUrl() {
        return activeUrl;
    }

    public void setActiveUrl(String activeUrl) {
        this.activeUrl = activeUrl;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }

    public String getDownloadSuffix() {
        return downloadSuffix;
    }

    public void setDownloadSuffix(String downloadSuffix) {
        this.downloadSuffix = downloadSuffix;
    }

    public String getImageAccessUrl() {
        return imageAccessUrl;
    }

    public void setImageAccessUrl(String imageAccessUrl) {
        this.imageAccessUrl = imageAccessUrl;
    }

    public String getAudioAccessUrl() {
        return audioAccessUrl;
    }

    public void setAudioAccessUrl(String audioAccessUrl) {
        this.audioAccessUrl = audioAccessUrl;
    }

    public String getVideoAccessUrl() {
        return videoAccessUrl;
    }

    public void setVideoAccessUrl(String videoAccessUrl) {
        this.videoAccessUrl = videoAccessUrl;
    }

    public String getAnalysisAccessUrl() {
        return analysisAccessUrl;
    }

    public void setAnalysisAccessUrl(String analysisAccessUrl) {
        this.analysisAccessUrl = analysisAccessUrl;
    }




    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getSearchServerUrl() {
        return searchServerUrl;
    }

    public void setSearchServerUrl(String searchServerUrl) {
        this.searchServerUrl = searchServerUrl;
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public String getCookieEncodeKey() {
        return cookieEncodeKey;
    }

    public void setCookieEncodeKey(String cookieEncodeKey) {
        this.cookieEncodeKey = cookieEncodeKey;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public int getCookieMaxAge() {
        return cookieMaxAge;
    }

    public void setCookieMaxAge(int cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }
}
