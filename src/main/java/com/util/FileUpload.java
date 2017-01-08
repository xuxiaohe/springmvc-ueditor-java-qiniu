package com.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuxiaohe on 2016/12/15.
 */
public class FileUpload {

    //文件上传
    public static Map uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String path=request.getSession().getServletContext().getRealPath("images/");
        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdir();
        }
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        file.transferTo(tempFile);
        Map m=new HashMap();
        m.put("path",path + tempFile.getName());
        m.put("end",tempFile.getName());
        return m;
    }


}
