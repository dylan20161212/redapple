package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DutySalaryInfo entity.
 */
public class DutySalaryInfoDTO implements Serializable {

    private Long id;

    private Long dSalaryId;

    private String dutyCode;

    private String dutyName;

    private Long sItemID;

    private String sItemName;

    private Long sItemPrice;

    private String sOther;

    private Long dutySalaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getdSalaryId() {
        return dSalaryId;
    }

    public void setdSalaryId(Long dSalaryId) {
        this.dSalaryId = dSalaryId;
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

    public Long getsItemID() {
        return sItemID;
    }

    public void setsItemID(Long sItemID) {
        this.sItemID = sItemID;
    }

    public String getsItemName() {
        return sItemName;
    }

    public void setsItemName(String sItemName) {
        this.sItemName = sItemName;
    }

    public Long getsItemPrice() {
        return sItemPrice;
    }

    public void setsItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
    }

    public String getsOther() {
        return sOther;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    public Long getDutySalaryId() {
        return dutySalaryId;
    }

    public void setDutySalaryId(Long dutySalaryId) {
        this.dutySalaryId = dutySalaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DutySalaryInfoDTO dutySalaryInfoDTO = (DutySalaryInfoDTO) o;
        if(dutySalaryInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dutySalaryInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DutySalaryInfoDTO{" +
            "id=" + getId() +
            ", dSalaryId=" + getdSalaryId() +
            ", dutyCode='" + getDutyCode() + "'" +
            ", dutyName='" + getDutyName() + "'" +
            ", sItemID=" + getsItemID() +
            ", sItemName='" + getsItemName() + "'" +
            ", sItemPrice=" + getsItemPrice() +
            ", sOther='" + getsOther() + "'" +
            "}";
    }
}
