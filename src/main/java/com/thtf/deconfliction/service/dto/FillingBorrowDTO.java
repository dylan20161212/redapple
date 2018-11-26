package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FillingBorrow entity.
 */
public class FillingBorrowDTO implements Serializable {

    private Long id;

    private Long borrowOrgId;

    private String borrowOrgName;

    private Long mediatorId;

    private String mediatorName;

    private String borrowRemarks;

    private String returnRemarks;

    private String borrowFlag;

    private String borrowName;

    private String borrowIdType;

    private String borrowIdCode;

    private ZonedDateTime beginDate;

    private ZonedDateTime preEndDate;

    private ZonedDateTime endDate;

    private String filingId;

    private String filingCaseName;

    private String filingNo;

    private Long fillingConflictCaseId;

    private String fillingConflictCaseCName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBorrowOrgId() {
        return borrowOrgId;
    }

    public void setBorrowOrgId(Long borrowOrgId) {
        this.borrowOrgId = borrowOrgId;
    }

    public String getBorrowOrgName() {
        return borrowOrgName;
    }

    public void setBorrowOrgName(String borrowOrgName) {
        this.borrowOrgName = borrowOrgName;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public void setMediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getBorrowRemarks() {
        return borrowRemarks;
    }

    public void setBorrowRemarks(String borrowRemarks) {
        this.borrowRemarks = borrowRemarks;
    }

    public String getReturnRemarks() {
        return returnRemarks;
    }

    public void setReturnRemarks(String returnRemarks) {
        this.returnRemarks = returnRemarks;
    }

    public String getBorrowFlag() {
        return borrowFlag;
    }

    public void setBorrowFlag(String borrowFlag) {
        this.borrowFlag = borrowFlag;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getBorrowIdType() {
        return borrowIdType;
    }

    public void setBorrowIdType(String borrowIdType) {
        this.borrowIdType = borrowIdType;
    }

    public String getBorrowIdCode() {
        return borrowIdCode;
    }

    public void setBorrowIdCode(String borrowIdCode) {
        this.borrowIdCode = borrowIdCode;
    }

    public ZonedDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(ZonedDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public ZonedDateTime getPreEndDate() {
        return preEndDate;
    }

    public void setPreEndDate(ZonedDateTime preEndDate) {
        this.preEndDate = preEndDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFilingId() {
        return filingId;
    }

    public void setFilingId(String filingId) {
        this.filingId = filingId;
    }

    public String getFilingCaseName() {
        return filingCaseName;
    }

    public void setFilingCaseName(String filingCaseName) {
        this.filingCaseName = filingCaseName;
    }

    public String getFilingNo() {
        return filingNo;
    }

    public void setFilingNo(String filingNo) {
        this.filingNo = filingNo;
    }

    public Long getFillingConflictCaseId() {
        return fillingConflictCaseId;
    }

    public void setFillingConflictCaseId(Long fillingConflictCaseId) {
        this.fillingConflictCaseId = fillingConflictCaseId;
    }

    public String getFillingConflictCaseCName() {
        return fillingConflictCaseCName;
    }

    public void setFillingConflictCaseCName(String fillingConflictCaseCName) {
        this.fillingConflictCaseCName = fillingConflictCaseCName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FillingBorrowDTO fillingBorrowDTO = (FillingBorrowDTO) o;
        if(fillingBorrowDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingBorrowDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingBorrowDTO{" +
            "id=" + getId() +
            ", borrowOrgId=" + getBorrowOrgId() +
            ", borrowOrgName='" + getBorrowOrgName() + "'" +
            ", mediatorId=" + getMediatorId() +
            ", mediatorName='" + getMediatorName() + "'" +
            ", borrowRemarks='" + getBorrowRemarks() + "'" +
            ", returnRemarks='" + getReturnRemarks() + "'" +
            ", borrowFlag='" + getBorrowFlag() + "'" +
            ", borrowName='" + getBorrowName() + "'" +
            ", borrowIdType='" + getBorrowIdType() + "'" +
            ", borrowIdCode='" + getBorrowIdCode() + "'" +
            ", beginDate='" + getBeginDate() + "'" +
            ", preEndDate='" + getPreEndDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", filingId='" + getFilingId() + "'" +
            ", filingCaseName='" + getFilingCaseName() + "'" +
            ", filingNo='" + getFilingNo() + "'" +
            "}";
    }
}
