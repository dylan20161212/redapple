package com.thtf.app.web.rest.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamUtil {
	private final static Logger log = LoggerFactory.getLogger(StreamUtil.class);

	public static String processInputStream(InputStream in) {
		StringBuffer buffer = new StringBuffer();
		try (InputStream in0 = in;
				InputStreamReader ibr = new InputStreamReader(in0);
				BufferedReader br = new BufferedReader(ibr);) {
			String line = "";
			while ((line = br.readLine()) != null) {
				log.debug(line);
				buffer.append(line + "\n");
			}
		} catch (IOException e) {
			log.error("iOE exception", e);
		}
		return buffer.toString();

	}

	/**
	 * 获得指定文件的byte数组
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static byte[] getBytes(String filePath) {
		if(filePath == null){
			return null;
		}
		File file = new File(filePath);
		try(
				FileInputStream fis = new FileInputStream(file);
			){
			return getBytes(fis);
		}catch(FileNotFoundException e){
			log.error("文件找不到异常！", e);
		}catch(IOException e){
			log.error("IO异常！", e);
		}
		return null;
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(InputStream inputStream) {
		if( inputStream == null){
			return null;
		}
		byte[] buffer = null;
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			inputStream.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (IOException e) {
			log.error("IO 异常！", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(),e);
			}

		}
		return buffer;
	}

}
