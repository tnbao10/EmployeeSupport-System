package com.demo.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UploadHelper {
	/* Tham số truyền vào lần lươt: 1-Đường dẫn tới folder muốn upload, 2-Request, 3-File được Upload */
	public static String uploadFile(String UPLOAD_DIR, HttpServletRequest request, Part part) {
		String fileName = null;
		try {
			fileName = FileHelper.generateFileName(part.getSubmittedFileName());
			String applicationPath = request.getServletContext().getRealPath("");
			String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
			System.out.println(basePath);
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try {
				File outputFilePath = new File(basePath + fileName);
				inputStream = part.getInputStream();
				outputStream = new FileOutputStream(outputFilePath);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
			} catch (Exception ex) {
				fileName = null;
			} finally {
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			}
		} catch (Exception e) {
			fileName = null;
		}
		return fileName;
	}
}
