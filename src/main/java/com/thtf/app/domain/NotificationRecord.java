package com.thtf.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 通知公告记录，用于标识阅读与否
 */
@ApiModel(description = "通知公告记录，用于标识阅读与否")
@Entity
@Table(name = "t_notification_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NotificationRecord extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 阅读标记
     */
    @ApiModelProperty(value = "阅读标记")
    @Column(name = "read_flag")
    private String readFlag;

    /**
     * 阅读日期
     */
    @ApiModelProperty(value = "阅读日期")
    @Column(name = "read_date")
    private ZonedDateTime readDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "record_remarks")
    private String recordRemarks;

    /**
     * 哪个用户查阅过公告或者哪个用户接收的通知
     */
    @NotNull
    @ApiModelProperty(value = "哪个用户查阅过公告或者哪个用户接收的通知", required = true)
    @Column(name = "user_login", nullable = false)
    private String userLogin;

    @ManyToOne
    private Notification notification;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReadFlag() {
        return readFlag;
    }

    public NotificationRecord readFlag(String readFlag) {
        this.readFlag = readFlag;
        return this;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    public ZonedDateTime getReadDate() {
        return readDate;
    }

    public NotificationRecord readDate(ZonedDateTime readDate) {
        this.readDate = readDate;
        return this;
    }

    public void setReadDate(ZonedDateTime readDate) {
        this.readDate = readDate;
    }

    public String getRecordRemarks() {
        return recordRemarks;
    }

    public NotificationRecord recordRemarks(String recordRemarks) {
        this.recordRemarks = recordRemarks;
        return this;
    }

    public void setRecordRemarks(String recordRemarks) {
        this.recordRemarks = recordRemarks;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public NotificationRecord userLogin(String userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Notification getNotification() {
        return notification;
    }

    public NotificationRecord notification(Notification notification) {
        this.notification = notification;
        return this;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotificationRecord notificationRecord = (NotificationRecord) o;
        if (notificationRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationRecord{" +
            "id=" + getId() +
            ", readFlag='" + getReadFlag() + "'" +
            ", readDate='" + getReadDate() + "'" +
            ", recordRemarks='" + getRecordRemarks() + "'" +
            ", userLogin='" + getUserLogin() + "'" +
            "}";
    }
}
