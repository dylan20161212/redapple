package com.thtf.app.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.thtf.app.config.ApplicationProperties;
import com.thtf.app.domain.Attachment;
import com.thtf.app.repository.AttachmentRepository;
import com.thtf.app.security.SecurityUtils;
import com.thtf.app.service.dto.AttachmentDTO;
import com.thtf.app.service.mapper.AttachmentMapper;
import com.thtf.app.web.rest.errors.BadRequestAlertException;
import com.thtf.app.web.rest.errors.InternalServerErrorException;
import com.thtf.app.web.rest.util.FileUtil;
import com.thtf.app.web.rest.util.HeaderUtil;
import com.thtf.app.web.rest.util.PaginationUtil;
import com.thtf.app.web.rest.util.VedioAudioUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Attachment.
 */
@RestController
@RequestMapping("/api")
public class AttachmentResource {

	private final Logger log = LoggerFactory.getLogger(AttachmentResource.class);

	private static final String ENTITY_NAME = "attachment";

	private final AttachmentRepository attachmentRepository;

	private final AttachmentMapper attachmentMapper;

	@Autowired
	ApplicationProperties applicationProperties;

	public AttachmentResource(AttachmentRepository attachmentRepository, AttachmentMapper attachmentMapper) {
		this.attachmentRepository = attachmentRepository;
		this.attachmentMapper = attachmentMapper;

	}

	/**
	 * POST /attachments : Create a new attachment.
	 *
	 * @param attachmentDTO
	 *            the attachmentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         attachmentDTO, or with status 400 (Bad Request) if the attachment has
	 *         already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/attachments")
	@Timed
	public ResponseEntity<AttachmentDTO> createAttachment(@RequestBody AttachmentDTO attachmentDTO)
			throws URISyntaxException {
		log.debug("REST request to save Attachment : {}", attachmentDTO);
		if (attachmentDTO.getId() != null) {
			throw new BadRequestAlertException("A new attachment cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Attachment attachment = attachmentMapper.toEntity(attachmentDTO);
		attachment = attachmentRepository.save(attachment);
		AttachmentDTO result = attachmentMapper.toDto(attachment);
		return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * POST /attachments : upload a attachment.
	 *
	 * @param attachmentDTO
	 *            the attachmentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         attachmentDTO, or with status 400 (Bad Request) if the attachment has
	 *         already an ID
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Transactional
	@PostMapping("/attachmentsUpload/{id}")
	@Timed
	public ResponseEntity<AttachmentDTO> attachmentsUpload(@RequestBody MultipartFile uploadedfile,
			@PathVariable Long id,
			@RequestParam(name = "type", required = false, defaultValue = "othertype") String type)
			throws URISyntaxException, IOException {
		// log.debug("REST request to save Attachment : {}", uploadedfile, id);
		String fileName = uploadedfile.getOriginalFilename(); // 获取文件的名称
		long fileSize = uploadedfile.getSize();
		String uploader = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		Attachment attachment = new Attachment();
		if (id > 0) {
			attachment = attachmentRepository.findById(id).orElse(null);
			if (attachment == null) {
				attachment = new Attachment();
			}
		}
		String filetype = applicationProperties.getFiletype();// 这是文件的类型
		if (type.indexOf(",") >= 1 || filetype.indexOf(type) < 0) {
			type = "othertype";
		}
		String filePath = null;
		if (attachment.getFilePath() == null) {
			// 直接上传
			String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			if (!VedioAudioUtil.isVideo(uploadedfile.getContentType(), FileUtil.getSuffix(fileName))) {
				filePath = encryptFile(uploadedfile, fileName, type, uploader + date);
			} else {
				filePath = noEncryptFile(uploadedfile, fileName, type, uploader + date);
				if (!VedioAudioUtil.isMp4(uploadedfile.getContentType())) {
					VedioAudioUtil.vedioDecodeToMp4(this.getUploadPath() + File.separator + filePath);
					filePath = FileUtil.changeFileSuffixName(filePath, "mp4");
					fileName = FileUtil.changeFileSuffixName(fileName, "mp4");
				}
			}

		} else {
			// 断点续传
			if (!VedioAudioUtil.isVideo(uploadedfile.getContentType(), FileUtil.getSuffix(fileName))) {
				filePath = encryptFile(uploadedfile, fileName, type, attachment.getFilePath());
			} else {
				filePath = noEncryptFile(uploadedfile, fileName, type, attachment.getFilePath());
				if (!VedioAudioUtil.isMp4(uploadedfile.getContentType())) {
					VedioAudioUtil.vedioDecodeToMp4(this.getUploadPath() + File.separator + filePath);
					filePath = FileUtil.changeFileSuffixName(filePath, "mp4");
					fileName = FileUtil.changeFileSuffixName(fileName, "mp4");
				}
			}

		}
		if (null != filePath) {
			attachment.setUploadTime(ZonedDateTime.now());
			attachment.setFileName(fileName);
			attachment.setFileType(type);
			attachment.setUploader(uploader);
			attachment.setFilePath(filePath);
			attachment.setFileType("othertype");
			attachment.setFileSize(fileSize);
			attachment = attachmentRepository.save(attachment);

		}
		AttachmentDTO result = attachmentMapper.toDto(attachment);
		return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * POST /attachments : upload a attachment.
	 *
	 * @param attachmentDTO
	 *            the attachmentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         attachmentDTO, or with status 400 (Bad Request) if the attachment has
	 *         already an ID
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Transactional
	@PostMapping("/attachmentsToUpload/{id}")
	@Timed
	public ResponseEntity<AttachmentDTO> attachmentsUploadNotify(@RequestBody MultipartFile fileToUpload,
			@PathVariable Long id,
			@RequestParam(name = "type", required = false, defaultValue = "othertype") String type)
			throws URISyntaxException, IOException {
		log.debug("REST request to save Attachment : {}", fileToUpload);
		long fileSize = fileToUpload.getSize();
		String fileName = fileToUpload.getOriginalFilename(); // 这是文件的类型
		String uploader = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		Attachment attachment = new Attachment();
		if (id > 0) {
			attachment = attachmentRepository.findById(id).orElse(null);
			if (attachment == null) {
				attachment = new Attachment();
			}
		}
		String filetype = applicationProperties.getFiletype();// 这是文件的保存路径
		if (type.indexOf(",") >= 1 || filetype.indexOf(type) < 0) {
			type = "othertype";
		}
		String filePath = null;
		if (attachment.getFilePath() == null) {
			// 直接上传
			String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			if (!VedioAudioUtil.isVideo(fileToUpload.getContentType(), FileUtil.getSuffix(fileName))) {
				filePath = encryptFile(fileToUpload, fileName, type, uploader + date);
			} else {
				filePath = this.noEncryptFile(fileToUpload, fileName, type, uploader + date);
				if (!VedioAudioUtil.isMp4(fileToUpload.getContentType())) {
					VedioAudioUtil.vedioDecodeToMp4(this.getUploadPath() + File.separator + filePath);
					filePath = FileUtil.changeFileSuffixName(filePath, "mp4");
					fileName = FileUtil.changeFileSuffixName(fileName, "mp4");
				}
			}

		} else {
			// 断点续传
			if (!VedioAudioUtil.isVideo(fileToUpload.getContentType(), FileUtil.getSuffix(fileName))) {
				filePath = encryptFile(fileToUpload, fileName, type, attachment.getFilePath());
			} else {
				filePath = this.noEncryptFile(fileToUpload, fileName, type, attachment.getFilePath());
				if (!VedioAudioUtil.isMp4(fileToUpload.getContentType())) {
					VedioAudioUtil.vedioDecodeToMp4(this.getUploadPath() + File.separator + filePath);
					filePath = FileUtil.changeFileSuffixName(filePath, "mp4");
					fileName = FileUtil.changeFileSuffixName(fileName, "mp4");
				}
			}
		}
		if (null != filePath) {
			attachment.setUploadTime(ZonedDateTime.now());
			attachment.setFileName(fileName);
			attachment.setFileType(type);
			attachment.setUploader(uploader);
			attachment.setFilePath(filePath);
			attachment.setFileSize(fileSize);
			attachment = attachmentRepository.save(attachment);
		}
		AttachmentDTO result = attachmentMapper.toDto(attachment);
		return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.contentType(MediaType.TEXT_PLAIN).body(result);
	}

	/**
	 * POST /attachmentsKnowledge : 法律法规知识库 upload a
	 * attachment.知识库不要加密存储，需要搜索，法律法规本来就是通识，没有加密的必要
	 *
	 * @param attachmentDTO
	 *            the attachmentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         attachmentDTO, or with status 400 (Bad Request) if the attachment has
	 *         already an ID
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@Transactional
	@PostMapping("/attachmentsknowledge/{id}")
	@Timed
	public ResponseEntity<AttachmentDTO> attachmentsKnowledge(@RequestBody MultipartFile fileToUpload,
			@PathVariable Long id,
			@RequestParam(name = "type", required = false, defaultValue = "knowledge") String type)
			throws URISyntaxException, IOException {
		log.debug("REST request to save Attachment : {}", fileToUpload);
		long fileSize = fileToUpload.getSize();
		String fileName = fileToUpload.getOriginalFilename(); // 这是文件的类型
		String uploader = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		Attachment attachment = new Attachment();
		if (id > 0) {
			attachment = attachmentRepository.findById(id).orElse(null);
			if (attachment == null) {
				attachment = new Attachment();
			}
		}
		String filetype = applicationProperties.getFiletype();// 这是文件的保存路径
		if (type.indexOf(",") >= 1 || filetype.indexOf(type) < 0) {
			type = "knowledge";
		}
		String filePath = null;
		if (attachment.getFilePath() == null) {
			// 直接上传
			String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			filePath = noEncryptFileKnowledge(fileToUpload, fileName, type, uploader + date);

		} else {
			// 断点续传
			filePath = noEncryptFileKnowledge(fileToUpload, fileName, type, attachment.getFilePath());
		}
		if (null != filePath) {
			attachment.setUploadTime(ZonedDateTime.now());
			attachment.setFileName(fileName);
			attachment.setFileType(type);
			attachment.setUploader(uploader);
			attachment.setFilePath(filePath);
			attachment.setFileSize(fileSize);
			attachment = attachmentRepository.save(attachment);
		}
		AttachmentDTO result = attachmentMapper.toDto(attachment);
		return ResponseEntity.created(new URI("/api/attachments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.contentType(MediaType.TEXT_PLAIN).body(result);
	}

	private String encryptFile(MultipartFile uploadedfile, String fileName, String fileType, String uploaderAndDate) {
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存路径
		String filekey = applicationProperties.getFilekey();// 这是密钥
		String type = "";
		if (fileName.contains(".")) {
			type = fileName.substring(fileName.lastIndexOf('.'));
		}
		File parentdir = new File(fileuploadath + File.separator + fileType);
		if (!parentdir.exists()) {
			parentdir.mkdirs();
		}
		File ifExists = new File(fileuploadath + File.separator + uploaderAndDate);

		byte k[] = filekey.getBytes();
		SecretKeySpec key = new SecretKeySpec(k, "AES");
		CipherOutputStream outputStream = null;
		FileOutputStream fos = null;
		try {
			Cipher enc = Cipher.getInstance("AES");
			enc.init(Cipher.ENCRYPT_MODE, key);
			// int serverLength = 0; // 存储在服务器的文件长度，默认-1
			if (ifExists.exists() && !ifExists.isDirectory()) {
				// 文件存在，断点续传
				fos = new FileOutputStream(ifExists, true);
			} else {
				ifExists = new File(parentdir, uploaderAndDate + type);
				fos = new FileOutputStream(ifExists);
			}

			outputStream = new CipherOutputStream(fos, enc);
			outputStream.write(uploadedfile.getBytes());
			outputStream.flush();
			outputStream.close();
			return fileType + File.separator + uploaderAndDate + type;
		} catch (IOException ex) {
			log.error("附件上传: {}" + ex.toString());
			Throwable[] suppressed = ex.getSuppressed();
			for (int i = 0; i < suppressed.length; i++) {
				log.error("附件上传: {}" + suppressed[i].toString());
			}
			try {
				throw ex;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error(e.getMessage());
			}
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
		} finally {
			try {
				if (null != outputStream) {
					outputStream.close();
				}
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e3) {
				// e.printStackTrace();
				log.error(e3.getMessage());
			}
		}
		return null;
	}

	/**
	 * 知识库附件上传，不需要加密存储
	 * 
	 * @param uploadedfile
	 * @param fileName
	 * @param fileType
	 * @param uploaderAndDate
	 * @return
	 */
	private String noEncryptFileKnowledge(MultipartFile uploadedfile, String fileName, String fileType,
			String uploaderAndDate) {
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存路径
		String type = "";
		if (fileName.contains(".")) {
			type = fileName.substring(fileName.lastIndexOf('.'));
		}
		File parentdir = new File(fileuploadath + File.separator + fileType);
		if (!parentdir.exists()) {
			parentdir.mkdirs();
		}
		File ifExists = new File(fileuploadath + File.separator + uploaderAndDate);

		if (!ifExists.exists() || ifExists.isDirectory()) {
			ifExists = new File(parentdir, uploaderAndDate + type);
		}
		try (OutputStream fos = new FileOutputStream(ifExists, true); InputStream in = uploadedfile.getInputStream();) {
			final int BUFFER_LENGTH = 1024 * 16;
			byte buffer[] = new byte[BUFFER_LENGTH];
			int readLen = 0;
			while ((readLen = in.read(buffer)) != -1) {
				fos.write(buffer, 0, readLen);
			}
			return uploaderAndDate + type;
		} catch (IOException e) {
			log.error("文件上传异常！", e);
		}

		return null;
	}

	private String noEncryptFile(MultipartFile uploadedfile, String fileName, String fileType, String uploaderAndDate) {
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存路径
		String type = "";
		if (fileName.contains(".")) {
			type = fileName.substring(fileName.lastIndexOf('.'));
		}
		File parentdir = new File(fileuploadath + File.separator + fileType);
		if (!parentdir.exists()) {
			parentdir.mkdirs();
		}
		File ifExists = new File(fileuploadath + File.separator + uploaderAndDate);

		if (!ifExists.exists() || ifExists.isDirectory()) {
			ifExists = new File(parentdir, uploaderAndDate + type);
		}
		try (OutputStream fos = new FileOutputStream(ifExists, true); InputStream in = uploadedfile.getInputStream();) {
			log.info("video uploading ....");
			final int BUFFER_LENGTH = 1024 * 16;
			byte buffer[] = new byte[BUFFER_LENGTH];
			int readLen = 0;
			while ((readLen = in.read(buffer)) != -1) {
				fos.write(buffer, 0, readLen);
			}
			return fileType + File.separator + uploaderAndDate + type;
		} catch (IOException e) {
			log.error("文件上传异常！", e);
		}

		return null;
	}

	/**
	 * PUT /attachments : Updates an existing attachment.
	 *
	 * @param attachmentDTO
	 *            the attachmentDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         attachmentDTO, or with status 400 (Bad Request) if the attachmentDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         attachmentDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@Transactional
	@PutMapping("/attachments")
	@Timed
	public ResponseEntity<AttachmentDTO> updateAttachment(@RequestBody AttachmentDTO attachmentDTO)
			throws URISyntaxException {
		log.debug("REST request to update Attachment : {}", attachmentDTO);
		if (attachmentDTO.getId() == null) {
			return createAttachment(attachmentDTO);
		}
		Attachment attachment = attachmentMapper.toEntity(attachmentDTO);
		attachment = attachmentRepository.save(attachment);
		AttachmentDTO result = attachmentMapper.toDto(attachment);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attachmentDTO.getId().toString()))
				.body(result);
	}

	/**
	 * GET /attachments : get all the attachments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of attachments
	 *         in body
	 */
	@GetMapping("/attachments")
	@Timed
	public ResponseEntity<List<AttachmentDTO>> getAllAttachments(Pageable pageable) {
		log.debug("REST request to get a page of Attachments");
		Page<Attachment> page = attachmentRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attachments");
		return new ResponseEntity<>(attachmentMapper.toDto(page.getContent()), headers, HttpStatus.OK);
	}

	/**
	 * GET /attachments : get all the attachments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of attachments
	 *         in body
	 */
	@GetMapping("/attachmentsx")
	@Timed
	public ResponseEntity<Page<AttachmentDTO>> getAllAttachmentsx(@RequestParam Map<String, Object> params) {
		log.debug("REST request to get a page of Attachments params" + params);
		String uploader = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		String filterscount = (String) params.get("filterscount");
		params.put("filterscount", (Integer.parseInt(filterscount) + 1) + "");
		params.put("filtervalue" + filterscount, uploader);
		params.put("filtercondition" + filterscount, "EQUAL");
		params.put("filterdatafield" + filterscount, "uploader");
		params.put("filteroperator" + filterscount, "0");
		Page<AttachmentDTO> page = new PageImpl<>(attachmentMapper.toDto(attachmentRepository.findAll(params)), PaginationUtil.getDefaultPageable(),
				attachmentRepository.getTotalRows(params));
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attachmentsx");
		return new ResponseEntity<>(page, headers, HttpStatus.OK);

	}

	/**
	 * GET /attachments/:id : get the "id" attachment.
	 *
	 * @param id
	 *            the id of the attachmentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         attachmentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/attachments/{id}")
	@Timed
	public ResponseEntity<AttachmentDTO> getAttachment(@PathVariable Long id) {
		log.debug("REST request to get Attachment : {}", id);
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		AttachmentDTO attachmentDTO = attachmentMapper.toDto(attachment);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(attachmentDTO));
	}

	@GetMapping("/attachmentsd/{id}")
	@Timed
	public ResponseEntity<InputStreamResource> downLoad(@PathVariable Long id) throws IOException {
		log.debug("REST request to get Attachment : {}", id);
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		if (fileuploadath.endsWith(File.separator) || fileuploadath.endsWith("/")) {
			fileuploadath = fileuploadath.substring(0, fileuploadath.length() - 1);
		}
		String filePath = fileuploadath + File.separator + attachment.getFilePath();
		if (VedioAudioUtil.isVideo(this.getMimeType(filePath), FileUtil.getSuffix(filePath))) {
			return this.downLoadNoEnc(id);
		} else {
			return this.downLoadEnc(id);
		}
	}

	@GetMapping("/attachmentsdknowl/{id}")
	@Timed
	public ResponseEntity<InputStreamResource> downLoadKnowledge(@PathVariable Long id) throws IOException {
		log.debug("REST request to get Attachment : {}", id);
		return this.downLoadNoEncKnowledge(id);
	}

	/**
	 * GET /attachments/:id : get the "id" attachment.
	 *
	 * @param id
	 *            the id of the attachmentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         attachmentDTO, or with status 404 (Not Found)
	 * @throws IOException
	 */
	// @GetMapping("/attachmentsd/{id}")
	// @Timed
	public ResponseEntity<InputStreamResource> downLoadEnc(@PathVariable Long id) throws IOException {
		log.debug("REST request to get Attachment : {}", id);
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		String filekey = applicationProperties.getFilekey();// 这是密钥
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		String filePath = fileuploadath + File.separator + attachment.getFilePath();
		return FileUtil.downLoadEnc(filekey, filePath);
	}

	public ResponseEntity<InputStreamResource> downLoadNoEnc(Long id) throws IOException {
		log.debug("REST request to get Attachment : {}", id);
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		String filePath = fileuploadath + File.separator + attachment.getFileType() + File.separator
				+ attachment.getFilePath();
		return FileUtil.downLoadUnEnc(attachment.getFileName(), filePath);
	}

	/**
	 * 法律法规库预览附件，下载时调相应得app打开
	 * @param id
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<InputStreamResource> downLoadNoEncKnowledge(Long id) throws IOException {
		log.debug("REST request to get Attachment : {}", id);
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		Attachment attachment = attachmentRepository.findById(id).orElse(null);
		String filePath = fileuploadath + File.separator + attachment.getFileType() + File.separator
				+ attachment.getFilePath();
		return FileUtil.downLoadUnEncDoc(attachment.getFileName(), filePath);
	}

	/**
	 * DELETE /attachments/:id : delete the "id" attachment.
	 *
	 * @param id
	 *            the id of the attachmentDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/attachments/{id}")
	@Timed
	public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
		log.debug("REST request to delete Attachment : {}", id);
		attachmentRepository.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * DELETE /attachments/:id : delete my "id" attachment.
	 *
	 * @param id
	 *            the id of the attachmentDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	// @Transactional
	// @DeleteMapping("/attachmentsx/{id}")
	// @Timed
	// public ResponseEntity<Void> deleteMyAttachment(@PathVariable Long id) {
	// log.debug("REST request to delete Attachment : {}", id);
	// String uploader = SecurityUtils.getCurrentUserLogin()
	// .orElseThrow(() -> new InternalServerErrorException("Current user login
	// not found"));// 获取当前用户名
	// attachmentRepository.deleteByIdAndUploader(id, uploader);
	// return
	// ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
	// id.toString())).build();
	// }
	/**
	 * DELETE /attachment/:id : delete the "id" .
	 *
	 * @param id
	 *            the id of the attachment to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@Transactional
	@DeleteMapping("/attachmentsx/{id}")
	@Timed
	public ResponseEntity<Void> deleteByid(@PathVariable String[] id) {
		log.debug("REST request to delete Attachment: {}", Arrays.toString(id));
		String uploader = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));// 获取当前用户名
		List<Long> lId = new ArrayList<>();
		for (int i = 0; i < id.length; i++) {
			lId.add(Long.parseLong(id[i]));
		}
		List<Attachment> attachment = attachmentRepository.findByIdIn(lId);
		int delSum = 0;
		for (int j = 0; j < attachment.size(); j++) {
			if (uploader.equals(attachment.get(j).getUploader())) {
				delSum++;
			}
		}
		if (delSum == attachment.size()) {
			attachmentRepository.deleteAll(attachmentRepository.findByIdIn(lId));
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("attachment.deleted", Arrays.toString(id)))
					.build();
		}
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("Operator is not an uploader", Arrays.toString(id)))
				.build();
	}

	private String getMimeType(String fileName) {

		String type = "";
		Path path = Paths.get(fileName);
		try {
			type = Files.probeContentType(path);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		return type;
	}

	private String getUploadPath() {
		String fileuploadath = applicationProperties.getFileuploadpath();// 这是文件的保存地址
		if (fileuploadath.endsWith(File.separator)) {
			fileuploadath = fileuploadath.substring(0, fileuploadath.lastIndexOf(File.separator) - 1);
		}
		if (fileuploadath.endsWith("/")) {
			fileuploadath = fileuploadath.substring(0, fileuploadath.lastIndexOf("/") - 1);
		}
		return fileuploadath;
	}
}
