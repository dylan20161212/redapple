package com.thtf.app.web.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FileUtil {
	private final static Logger log = LoggerFactory.getLogger(FileUtil.class);

	public static ResponseEntity<InputStreamResource> downLoadEnc(String filekey, String filePath)
			throws FileNotFoundException {
		byte k[] = filekey.getBytes();
		SecretKeySpec key = new SecretKeySpec(k, "AES");
		Cipher dec = null;
		try {
			dec = Cipher.getInstance("AES");
			dec.init(Cipher.DECRYPT_MODE, key);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error(e.getMessage());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			log.error(e.getMessage());
		}
		FileInputStream fis = new FileInputStream(filePath);
		CipherInputStream cin = new CipherInputStream(fis, dec);
		InputStreamResource resource = new InputStreamResource(cin);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("charset", "utf-8");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload"))
				.body(resource);
	}

	public static ResponseEntity<InputStreamResource> downLoadUnEnc(String fileName, String filePath)
			throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		InputStreamResource resource = new InputStreamResource(fis);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("charset", "utf-8");
		headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload"))
				.body(resource);
	}

	public static ResponseEntity<InputStreamResource> downLoadUnEncDoc(String fileName, String filePath)
			throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		InputStreamResource resource = new InputStreamResource(fis);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add("charset", "utf-8");
		headers.add("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "utf-8"));
		if (fileName.endsWith(".pdf")) {
			headers.add("Content-type", "application/pdf");
		} else if (fileName.endsWith(".doc")) {
			headers.add("Content-type", "application/msword");
		} else if (fileName.endsWith(".docx")) {
			headers.add("Content-type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		} else if (fileName.endsWith(".ppt")) {
			headers.add("Content-type", "application/vnd.ms-powerpoint");
		} else if (fileName.endsWith(".pptx")) {
			headers.add("Content-type", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
		}
		return ResponseEntity.ok().headers(headers).body(resource);
	}

	public static boolean deleteFile(String fullName) {
		File f = new File(fullName);
		return f.delete();
	}

	public static String changeFileSuffixName(String filePath, String suffix) {
		filePath = filePath.substring(0, filePath.indexOf(".") + 1) + suffix;
		return filePath;
	}

	public static String getSuffix(String name) {
		int index;
		if (name == null || (index = name.lastIndexOf(".")) == -1) {
			return "NOSUFFIX";
		}
		return name.substring(index);

	}
}
