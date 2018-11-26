package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 档案借阅记录管理
 */
@ApiModel(description = "档案借阅记录管理")
@Entity
@Table(name = "filing_borrow")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FillingBorrow extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 借阅人机构Id
     */
    @ApiModelProperty(value = "借阅人机构Id")
    @Column(name = "borrow_org_id")
    private Long borrowOrgId;

    /**
     * 借阅人机构名称
     */
    @ApiModelProperty(value = "借阅人机构名称")
    @Column(name = "borrow_org_name")
    private String borrowOrgName;

    /**
     * 借阅登记人员Id
     */
    @ApiModelProperty(value = "借阅登记人员Id")
    @Column(name = "mediator_id")
    private Long mediatorId;

    /**
     * 借阅登记人名称
     */
    @ApiModelProperty(value = "借阅登记人名称")
    @Column(name = "mediator_name")
    private String mediatorName;

    /**
     * 借出备注
     */
    @ApiModelProperty(value = "借出备注")
    @Column(name = "borrow_remarks")
    private String borrowRemarks;

    /**
     * 归还备注
     */
    @ApiModelProperty(value = "归还备注")
    @Column(name = "return_remarks")
    private String returnRemarks;

    /**
     * 操作标识（借阅，工作人员查看，导出均记录操作） 001：借出，101：归还 ，111：导出，112：读取
     */
    @ApiModelProperty(value = "操作标识（借阅，工作人员查看，导出均记录操作） 001：借出，101：归还 ，111：导出，112：读取")
    @Column(name = "borrow_flag")
    private String borrowFlag;

    /**
     * 借阅人姓名
     */
    @ApiModelProperty(value = "借阅人姓名")
    @Column(name = "borrow_name")
    private String borrowName;

    /**
     * 借阅人身份标识类型
     */
    @ApiModelProperty(value = "借阅人身份标识类型")
    @Column(name = "borrow_id_type")
    private String borrowIdType;

    /**
     * 借阅人身份标识号码
     */
    @ApiModelProperty(value = "借阅人身份标识号码")
    @Column(name = "borrow_id_code")
    private String borrowIdCode;

    /**
     * 借阅开始时间
     */
    @ApiModelProperty(value = "借阅开始时间")
    @Column(name = "begin_date")
    private ZonedDateTime beginDate;

    /**
     * 预计借阅结束时间
     */
    @ApiModelProperty(value = "预计借阅结束时间")
    @Column(name = "pre_end_date")
    private ZonedDateTime preEndDate;

    /**
     * 借阅实际结束时间
     */
    @ApiModelProperty(value = "借阅实际结束时间")
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    /**
     * 档案ID
     */
    @ApiModelProperty(value = "档案ID")
    @Column(name = "filing_id")
    private String filingId;

    /**
     * 档案名称
     */
    @ApiModelProperty(value = "档案名称")
    @Column(name = "filing_case_name")
    private String filingCaseName;

    /**
     * 档案编码
     */
    @ApiModelProperty(value = "档案编码")
    @Column(name = "filing_no")
    private String filingNo;

    @ManyToOne(optional = false)
    @NotNull
    private FillingConflictCase fillingConflictCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBorrowOrgId() {
        return borrowOrgId;
    }

    public FillingBorrow borrowOrgId(Long borrowOrgId) {
        this.borrowOrgId = borrowOrgId;
        return this;
    }

    public void setBorrowOrgId(Long borrowOrgId) {
        this.borrowOrgId = borrowOrgId;
    }

    public String getBorrowOrgName() {
        return borrowOrgName;
    }

    public FillingBorrow borrowOrgName(String borrowOrgName) {
        this.borrowOrgName = borrowOrgName;
        return this;
    }

    public void setBorrowOrgName(String borrowOrgName) {
        this.borrowOrgName = borrowOrgName;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public FillingBorrow mediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
        return this;
    }

    public void setMediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public FillingBorrow mediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
        return this;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getBorrowRemarks() {
        return borrowRemarks;
    }

    public FillingBorrow borrowRemarks(String borrowRemarks) {
        this.borrowRemarks = borrowRemarks;
        return this;
    }

    public void setBorrowRemarks(String borrowRemarks) {
        this.borrowRemarks = borrowRemarks;
    }

    public String getReturnRemarks() {
        return returnRemarks;
    }

    public FillingBorrow returnRemarks(String returnRemarks) {
        this.returnRemarks = returnRemarks;
        return this;
    }

    public void setReturnRemarks(String returnRemarks) {
        this.returnRemarks = returnRemarks;
    }

    public String getBorrowFlag() {
        return borrowFlag;
    }

    public FillingBorrow borrowFlag(String borrowFlag) {
        this.borrowFlag = borrowFlag;
        return this;
    }

    public void setBorrowFlag(String borrowFlag) {
        this.borrowFlag = borrowFlag;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public FillingBorrow borrowName(String borrowName) {
        this.borrowName = borrowName;
        return this;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getBorrowIdType() {
        return borrowIdType;
    }

    public FillingBorrow borrowIdType(String borrowIdType) {
        this.borrowIdType = borrowIdType;
        return this;
    }

    public void setBorrowIdType(String borrowIdType) {
        this.borrowIdType = borrowIdType;
    }

    public String getBorrowIdCode() {
        return borrowIdCode;
    }

    public FillingBorrow borrowIdCode(String borrowIdCode) {
        this.borrowIdCode = borrowIdCode;
        return this;
    }

    public void setBorrowIdCode(String borrowIdCode) {
        this.borrowIdCode = borrowIdCode;
    }

    public ZonedDateTime getBeginDate() {
        return beginDate;
    }

    public FillingBorrow beginDate(ZonedDateTime beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public void setBeginDate(ZonedDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public ZonedDateTime getPreEndDate() {
        return preEndDate;
    }

    public FillingBorrow preEndDate(ZonedDateTime preEndDate) {
        this.preEndDate = preEndDate;
        return this;
    }

    public void setPreEndDate(ZonedDateTime preEndDate) {
        this.preEndDate = preEndDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public FillingBorrow endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFilingId() {
        return filingId;
    }

    public FillingBorrow filingId(String filingId) {
        this.filingId = filingId;
        return this;
    }

    public void setFilingId(String filingId) {
        this.filingId = filingId;
    }

    public String getFilingCaseName() {
        return filingCaseName;
    }

    public FillingBorrow filingCaseName(String filingCaseName) {
        this.filingCaseName = filingCaseName;
        return this;
    }

    public void setFilingCaseName(String filingCaseName) {
        this.filingCaseName = filingCaseName;
    }

    public String getFilingNo() {
        return filingNo;
    }

    public FillingBorrow filingNo(String filingNo) {
        this.filingNo = filingNo;
        return this;
    }

    public void setFilingNo(String filingNo) {
        this.filingNo = filingNo;
    }

    public FillingConflictCase getFillingConflictCase() {
        return fillingConflictCase;
    }

    public FillingBorrow fillingConflictCase(FillingConflictCase fillingConflictCase) {
        this.fillingConflictCase = fillingConflictCase;
        return this;
    }

    public void setFillingConflictCase(FillingConflictCase fillingConflictCase) {
        this.fillingConflictCase = fillingConflictCase;
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
        FillingBorrow fillingBorrow = (FillingBorrow) o;
        if (fillingBorrow.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingBorrow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingBorrow{" +
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
