package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DutySalary entity.
 */
public class DutySalaryDTO implements Serializable {

    private Long id;

    private String dutyCode;

    private String dutyName;

    private String dFlag;

    private ZonedDateTime dChangeTime;

    private String dRemarks;

    private String dDetailInfo;

    private String dOther;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getdFlag() {
        return dFlag;
    }

    public void setdFlag(String dFlag) {
        this.dFlag = dFlag;
    }

    public ZonedDateTime getdChangeTime() {
        return dChangeTime;
    }

    public void setdChangeTime(ZonedDateTime dChangeTime) {
        this.dChangeTime = dChangeTime;
    }

    public String getdRemarks() {
        return dRemarks;
    }

    public void setdRemarks(String dRemarks) {
        this.dRemarks = dRemarks;
    }

    public String getdDetailInfo() {
        return dDetailInfo;
    }

    public void setdDetailInfo(String dDetailInfo) {
        this.dDetailInfo = dDetailInfo;
    }

    public String getdOther() {
        return dOther;
    }

    public void setdOther(String dOther) {
        this.dOther = dOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DutySalaryDTO dutySalaryDTO = (DutySalaryDTO) o;
        if(dutySalaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dutySalaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DutySalaryDTO{" +
            "id=" + getId() +
            ", dutyCode='" + getDutyCode() + "'" +
            ", dutyName='" + getDutyName() + "'" +
            ", dFlag='" + getdFlag() + "'" +
            ", dChangeTime='" + getdChangeTime() + "'" +
            ", dRemarks='" + getdRemarks() + "'" +
            ", dDetailInfo='" + getdDetailInfo() + "'" +
            ", dOther='" + getdOther() + "'" +
            "}";
    }
}
