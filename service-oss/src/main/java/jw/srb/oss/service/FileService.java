package jw.srb.oss.service;

import java.io.InputStream;

public interface FileService {

    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
