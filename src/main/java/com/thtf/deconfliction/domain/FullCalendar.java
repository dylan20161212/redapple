package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 日历日程
 */
@ApiModel(description = "日历日程")
@Entity
@Table(name = "de_full_calendar")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FullCalendar extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @Column(name = "start_date")
    private ZonedDateTime startDate;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    /**
     * 事件描述
     */
    @ApiModelProperty(value = "事件描述")
    @Column(name = "event_desc")
    private String eventDesc;

    @Column(name = "event_level")
    private Long eventLevel;

    @Column(name = "event_order")
    private Long eventOrder;

    /**
     * alert before
     */
    @ApiModelProperty(value = "alert before")
    @Column(name = "remind_time")
    private ZonedDateTime remindTime;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "user_id")
    private Long userId;

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

    public FullCalendar startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public FullCalendar endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public FullCalendar eventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
        return this;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Long getEventLevel() {
        return eventLevel;
    }

    public FullCalendar eventLevel(Long eventLevel) {
        this.eventLevel = eventLevel;
        return this;
    }

    public void setEventLevel(Long eventLevel) {
        this.eventLevel = eventLevel;
    }

    public Long getEventOrder() {
        return eventOrder;
    }

    public FullCalendar eventOrder(Long eventOrder) {
        this.eventOrder = eventOrder;
        return this;
    }

    public void setEventOrder(Long eventOrder) {
        this.eventOrder = eventOrder;
    }

    public ZonedDateTime getRemindTime() {
        return remindTime;
    }

    public FullCalendar remindTime(ZonedDateTime remindTime) {
        this.remindTime = remindTime;
        return this;
    }

    public void setRemindTime(ZonedDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public FullCalendar remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getUserId() {
        return userId;
    }

    public FullCalendar userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        FullCalendar fullCalendar = (FullCalendar) o;
        if (fullCalendar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fullCalendar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FullCalendar{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", eventDesc='" + getEventDesc() + "'" +
            ", eventLevel=" + getEventLevel() +
            ", eventOrder=" + getEventOrder() +
            ", remindTime='" + getRemindTime() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
