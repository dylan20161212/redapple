package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位薪酬明细表
 */
@ApiModel(description = "岗位薪酬明细表")
@Entity
@Table(name = "de_duty_salary_info")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DutySalaryInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 岗位薪酬Id
     */
    @ApiModelProperty(value = "岗位薪酬Id")
    @Column(name = "d_salary_id")
    private Long dSalaryId;

    /**
     * 岗位薪酬代码
     */
    @ApiModelProperty(value = "岗位薪酬代码")
    @Column(name = "duty_code")
    private String dutyCode;

    /**
     * 岗位职级
     */
    @ApiModelProperty(value = "岗位职级")
    @Column(name = "duty_name")
    private String dutyName;

    /**
     * 明细项目ID
     */
    @ApiModelProperty(value = "明细项目ID")
    @Column(name = "s_item_id")
    private Long sItemID;

    /**
     * 明细项目名称
     */
    @ApiModelProperty(value = "明细项目名称")
    @Column(name = "s_item_name")
    private String sItemName;

    /**
     * 明细项目单价（以分为单位）
     */
    @ApiModelProperty(value = "明细项目单价（以分为单位）")
    @Column(name = "s_item_price")
    private Long sItemPrice;

    /**
     * 备用字段
     */
    @ApiModelProperty(value = "备用字段")
    @Column(name = "s_other")
    private String sOther;

    @ManyToOne
    private DutySalary dutySalary;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getdSalaryId() {
        return dSalaryId;
    }

    public DutySalaryInfo dSalaryId(Long dSalaryId) {
        this.dSalaryId = dSalaryId;
        return this;
    }

    public void setdSalaryId(Long dSalaryId) {
        this.dSalaryId = dSalaryId;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public DutySalaryInfo dutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
        return this;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public DutySalaryInfo dutyName(String dutyName) {
        this.dutyName = dutyName;
        return this;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public Long getsItemID() {
        return sItemID;
    }

    public DutySalaryInfo sItemID(Long sItemID) {
        this.sItemID = sItemID;
        return this;
    }

    public void setsItemID(Long sItemID) {
        this.sItemID = sItemID;
    }

    public String getsItemName() {
        return sItemName;
    }

    public DutySalaryInfo sItemName(String sItemName) {
        this.sItemName = sItemName;
        return this;
    }

    public void setsItemName(String sItemName) {
        this.sItemName = sItemName;
    }

    public Long getsItemPrice() {
        return sItemPrice;
    }

    public DutySalaryInfo sItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
        return this;
    }

    public void setsItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
    }

    public String getsOther() {
        return sOther;
    }

    public DutySalaryInfo sOther(String sOther) {
        this.sOther = sOther;
        return this;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    public DutySalary getDutySalary() {
        return dutySalary;
    }

    public DutySalaryInfo dutySalary(DutySalary dutySalary) {
        this.dutySalary = dutySalary;
        return this;
    }

    public void setDutySalary(DutySalary dutySalary) {
        this.dutySalary = dutySalary;
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
        DutySalaryInfo dutySalaryInfo = (DutySalaryInfo) o;
        if (dutySalaryInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dutySalaryInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DutySalaryInfo{" +
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
