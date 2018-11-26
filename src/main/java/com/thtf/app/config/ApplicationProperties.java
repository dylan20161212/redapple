package com.thtf.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to The Jhi.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
	
	private String fileuploadpath;
	private String filekey;
	private String filetype;

	public String getFileuploadpath() {
		return fileuploadpath;
	}

	public void setFileuploadpath(String fileuploadpath) {
		this.fileuploadpath = fileuploadpath;
	}

	public String getFilekey() {
		return filekey;
	}

	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
