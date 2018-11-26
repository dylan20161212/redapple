package com.thtf.app.service.dto;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO {

	private Long id;

	private ZonedDateTime startDate;

	private ZonedDateTime endDate;

	@NotNull
	private String notifyType;

	@NotNull
	private String notifyTitle;

	@NotNull
	private String notifyContent;

	private String notifyStatus;

	private String notifyScope;

    private String notifyFiles;
    
    private Instant createdDate;

	private Instant lastModifiedDate;
	
    public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	private Set<NotificationRecordDTO> notificationRecords = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(ZonedDateTime startDate) {
		this.startDate = startDate;
	}

	public ZonedDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(ZonedDateTime endDate) {
		this.endDate = endDate;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getNotifyTitle() {
		return notifyTitle;
	}

	public void setNotifyTitle(String notifyTitle) {
		this.notifyTitle = notifyTitle;
	}

	public String getNotifyContent() {
		return notifyContent;
	}

	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public String getNotifyScope() {
		return notifyScope;
	}

	public void setNotifyScope(String notifyScope) {
		this.notifyScope = notifyScope;
	}

	public String getNotifyFiles() {
		return notifyFiles;
	}

	public void setNotifyFiles(String notifyFiles) {
		this.notifyFiles = notifyFiles;
	}

	
	public Set<NotificationRecordDTO> getNotificationRecords() {
		return notificationRecords;
	}

	public void setNotificationRecords(Set<NotificationRecordDTO> notificationRecords) {
		this.notificationRecords = notificationRecords;
	}
	
	

	public NotificationDTO() {
		this.notifyType = "";
		this.notifyTitle = "";
		this.notifyContent = "";
	}

	public NotificationDTO(Long id, ZonedDateTime startDate, ZonedDateTime endDate, String notifyType,
			String notifyTitle, String notifyContent, String notifyStatus, String notifyScope, String notifyFiles,
			Instant createdDate, Instant lastModifiedDate, Set<NotificationRecordDTO> notificationRecords) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.notifyType = notifyType;
		this.notifyTitle = notifyTitle;
		this.notifyContent = notifyContent;
		this.notifyStatus = notifyStatus;
		this.notifyScope = notifyScope;
		this.notifyFiles = notifyFiles;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.notificationRecords = notificationRecords;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		NotificationDTO notificationDTO = (NotificationDTO) o;
		if (notificationDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), notificationDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "NotificationDTO{" + "id=" + getId() + ", startDate='" + getStartDate() + "'" + ", endDate='"
				+ getEndDate() + "'" + ", notifyType='" + getNotifyType() + "'" + ", notifyTitle='" + getNotifyTitle()
				+ "'" + ", notifyContent='" + getNotifyContent() + "'" + ", notifyStatus='" + getNotifyStatus() + "'"
				+ ", notifyScope='" + getNotifyScope() + "'" + ", notifyFiles='" + getNotifyFiles() + "'" + "}";
	}
}
