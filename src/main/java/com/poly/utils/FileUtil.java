package com.poly.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    /**
     * Lưu file upload và trả về tên file
     * @param part           Part file từ request
     * @param context        ServletContext
     * @param uploadFolder   thư mục lưu
     * @return fileName hoặc null nếu không có file
     */
    public static String saveFile(Part part, ServletContext context, String uploadFolder)
            throws IOException {

        if (part == null || part.getSize() == 0) {
            return null;
        }

        String fileName = part.getSubmittedFileName();
        String realPath = context.getRealPath(uploadFolder);

        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        part.write(realPath + File.separator + fileName);
        return fileName;
    }
}
