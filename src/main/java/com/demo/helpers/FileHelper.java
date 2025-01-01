package com.demo.helpers;

import java.util.UUID;

public class FileHelper {
	// abc.doc.gif.txt.jpg
	public static String generateFileName(String fileName) {
		String name = UUID.randomUUID().toString().replace("-", "");
		int lastIndex = fileName.lastIndexOf(".");
		String ext = fileName.substring(lastIndex);
		return name + ext;
	}
}
