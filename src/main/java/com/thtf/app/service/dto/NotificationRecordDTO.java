package com.thtf.app.service.dto;

import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.thtf.app.domain.NotificationRecord;

/**
 * A DTO for the NotificationRecord entity.
 */
public class NotificationRecordDTO {

	private Long id;

	private String readFlag;

	private ZonedDateTime readDate;

	private String recordRemarks;

	@NotNull
	private String userLogin;

	private String firstName;

	private Long notificationId;

	private NotificationDTO notification;

	public NotificationRecordDTO() {
		// Empty constructor needed for Jackson.
		this.userLogin = "";
	}

	public NotificationRecordDTO(String readFlag) {
		super();
		this.readFlag = readFlag;
		this.userLogin = "";
	}

	public NotificationRecordDTO(NotificationRecord notificationRecord, String firstName) {
		super();
		this.firstName = firstName;
		this.id = notificationRecord.getId();
		this.readFlag = notificationRecord.getReadFlag();
		this.readDate = notificationRecord.getReadDate();
		this.recordRemarks = notificationRecord.getRecordRemarks();
		this.userLogin = notificationRecord.getUserLogin();
		if (null != notificationRecord.getNotification()) {
			this.notificationId = notificationRecord.getNotification().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public ZonedDateTime getReadDate() {
		return readDate;
	}

	public void setReadDate(ZonedDateTime readDate) {
		this.readDate = readDate;
	}

	public String getRecordRemarks() {
		return recordRemarks;
	}

	public void setRecordRemarks(String recordRemarks) {
		this.recordRemarks = recordRemarks;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public NotificationDTO getNotification() {
		return notification;
	}

	public void setNotification(NotificationDTO notification) {
		this.notification = notification;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		NotificationRecordDTO notificationRecordDTO = (NotificationRecordDTO) o;
		if (notificationRecordDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), notificationRecordDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "NotificationRecordDTO{" + "id=" + getId() + ", readFlag='" + getReadFlag() + "'" + ", readDate='"
				+ getReadDate() + "'" + ", recordRemarks='" + getRecordRemarks() + "'" + ", userLogin='"
				+ getUserLogin() + "'" + ", firstName='" + getFirstName() + "'" + "}";
	}
}
