package com.thtf.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通知公告
 */
/**
 * @author Administrator
 *
 */
@ApiModel(description = "通知公告")
@Entity
@Table(name = "t_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notification extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    /**
     * 通知公告类别，包括勾选组织机构的下级组织机构的公告（能够多选且一个分支只需要选一个），只包括勾选组织机构的公告，不需要勾选组织机构的公告（所有人可以看），通知（需要指定接收人员）
     */
    @NotNull
    @ApiModelProperty(value = "通知公告类别，包括勾选组织机构的下级组织机构的公告（能够多选且一个分支只需要选一个），只包括勾选组织机构的公告，不需要勾选组织机构的公告（所有人可以看），通知（需要指定接收人员）", required = true)
    @Column(name = "notify_type", nullable = false)
    private String notifyType;

    /**
     * 标题
     */
    @NotNull
    @ApiModelProperty(value = "标题", required = true)
    @Column(name = "notify_title", nullable = false)
    private String notifyTitle;

    /**
     * 内容
     */
    @NotNull
    @ApiModelProperty(value = "内容", required = true)
    @Column(name = "notify_content", nullable = false)
    private String notifyContent;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Column(name = "notify_status")
    private String notifyStatus;

    /**
     * 指定接收范围，用“,”分隔的（组织机构等，一个用户只属于一个组织机构避免一个用户接收多次重复的通知公告）公告不需要指定具体人员，通知需要指定具体接收人员
     */
    @ApiModelProperty(value = "指定接收范围，用“,”分隔的（组织机构等，一个用户只属于一个组织机构避免一个用户接收多次重复的通知公告）公告不需要指定具体人员，通知需要指定具体接收人员")
    @Column(name = "notify_scope")
    private String notifyScope;

    /**
     * 存放附件
     */
    @ApiModelProperty(value = "存放附件")
    @Column(name = "notify_files")
    private String notifyFiles;
    
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "notification")
    @OneToMany(mappedBy = "notification")
    private Set<NotificationRecord> notificationRecords;
    
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @Column(name = "created_by")
    private String createdBy;
    
    public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Notification startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public Notification endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public Notification notifyType(String notifyType) {
        this.notifyType = notifyType;
        return this;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public Notification notifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
        return this;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public Notification notifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
        return this;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public Notification notifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
        return this;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getNotifyScope() {
        return notifyScope;
    }

    public Notification notifyScope(String notifyScope) {
        this.notifyScope = notifyScope;
        return this;
    }

    public void setNotifyScope(String notifyScope) {
        this.notifyScope = notifyScope;
    }

    public String getNotifyFiles() {
        return notifyFiles;
    }

    public Notification notifyFiles(String notifyFiles) {
        this.notifyFiles = notifyFiles;
        return this;
    }

    public void setNotifyFiles(String notifyFiles) {
        this.notifyFiles = notifyFiles;
    }

    
	public Set<NotificationRecord> getNotificationRecords() {
		return notificationRecords;
	}

	public void setNotificationRecords(Set<NotificationRecord> notificationRecords) {
		this.notificationRecords = notificationRecords;
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
        Notification notification = (Notification) o;
        if (notification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notification.getId());
    }

	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", notifyType='" + getNotifyType() + "'" +
            ", notifyTitle='" + getNotifyTitle() + "'" +
            ", notifyContent='" + getNotifyContent() + "'" +
            ", notifyStatus='" + getNotifyStatus() + "'" +
            ", notifyScope='" + getNotifyScope() + "'" +
            ", notifyFiles='" + getNotifyFiles() + "'" +
            "}";
    }
}
