package com.thtf.app.web.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thtf.app.config.ApplicationProperties;
import com.thtf.app.domain.Attachment;
import com.thtf.app.repository.AttachmentRepository;
import com.thtf.app.web.rest.util.StreamUtil;
import com.thtf.app.web.rest.util.VedioAudioUtil;


import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

@RequestMapping("/api")
@Controller
public class AttachmentPreviewerrResources {
	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	private AttachmentRepository attachmentRepository;
	private final Logger log = LoggerFactory.getLogger(AttachmentPreviewerrResources.class);

	@GetMapping("/preview/{id}")
	private void previewVideo(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {

		String videoPath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		String fileName = attachmentRepository.findById(id).map(Attachment::getFilePath).orElse(null);
		VedioAudioUtil.processVedio(request, response, videoPath, fileName);
	}

	

	@GetMapping("/preview/img/{id}")
	public void previewAttachement2(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
			throws IOException, MagicParseException, MagicMatchNotFoundException, MagicException {

		String videoPath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		String filekey = applicationProperties.getFilekey();// 这是密钥
		byte k[] = filekey.getBytes();
		SecretKeySpec key = new SecretKeySpec(k, "AES");
		Cipher dec = null;
		FileInputStream fis = null;
		CipherInputStream cin = null;
		BufferedOutputStream bos = null;
		try {
			dec = Cipher.getInstance("AES");
			dec.init(Cipher.DECRYPT_MODE, key);
			fis = new FileInputStream(videoPath + File.separator + attachment.getFilePath());
			cin = new CipherInputStream(fis, dec);
			byte[] data = StreamUtil.getBytes(cin);
			MagicMatch match = Magic.getMagicMatch(data);
			String mimeType = match.getMimeType();
			// 设置响应的类型格式为图片格式
			response.setContentType(mimeType);
			// 禁止图像缓存。
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			bos = new BufferedOutputStream(response.getOutputStream());			
			bos.write(data);
			bos.flush();
			bos.close();

		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage());
		} catch (InvalidKeyException e) {
			log.error(e.getMessage());
		}finally{
			if(fis !=null){
				fis.close();
			}
			if(cin !=null){
				cin.close();
			}
			if(bos !=null){
				bos.close();
			}			
		}
	}

	
	
	
	
	

}
