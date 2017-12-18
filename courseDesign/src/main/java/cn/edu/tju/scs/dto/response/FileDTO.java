package cn.edu.tju.scs.dto.response;

import java.util.List;

/**
 * Created by haoxiaotian on 2016/11/29 0:45.
 */
public class FileDTO {
    private String fileName;
    private String fileType;
    private String fileMd5;
    private String filePath;
    private String fileSize;
    private String ip;

    public FileDTO() {
    }

    public FileDTO(String fileName, String fileType, String fileMd5, String filePath, String fileSize, String ip) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileMd5 = fileMd5;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.ip = ip;
    }

    public FileDTO(List<String> parameter) {
        this.fileName = parameter.get(0);
        this.fileType = parameter.get(1);
        this.fileMd5 = parameter.get(3);
        this.filePath = parameter.get(2);
        this.fileSize = parameter.get(4);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
