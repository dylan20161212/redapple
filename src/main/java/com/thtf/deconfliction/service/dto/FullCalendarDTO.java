package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FullCalendar entity.
 */
public class FullCalendarDTO implements Serializable {

    private Long id;

    private ZonedDateTime startDate;

    private ZonedDateTime endDate;

    private String eventDesc;

    private Long eventLevel;

    private Long eventOrder;

    private ZonedDateTime remindTime;

    private String remarks;

    private Long userId;

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

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Long getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Long eventLevel) {
        this.eventLevel = eventLevel;
    }

    public Long getEventOrder() {
        return eventOrder;
    }

    public void setEventOrder(Long eventOrder) {
        this.eventOrder = eventOrder;
    }

    public ZonedDateTime getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(ZonedDateTime remindTime) {
        this.remindTime = remindTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FullCalendarDTO fullCalendarDTO = (FullCalendarDTO) o;
        if(fullCalendarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fullCalendarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FullCalendarDTO{" +
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
