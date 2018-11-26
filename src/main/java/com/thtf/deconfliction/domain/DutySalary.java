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
 * 岗位薪酬表
 */
@ApiModel(description = "岗位薪酬表")
@Entity
@Table(name = "de_duty_salary")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DutySalary extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 岗位代码
     */
    @ApiModelProperty(value = "岗位代码")
    @Column(name = "duty_code")
    private String dutyCode;

    /**
     * 岗位职级
     */
    @ApiModelProperty(value = "岗位职级")
    @Column(name = "duty_name")
    private String dutyName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Column(name = "d_flag")
    private String dFlag;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    @Column(name = "d_change_time")
    private ZonedDateTime dChangeTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "d_remarks")
    private String dRemarks;

    /**
     * 岗位工资明细信息JSON串
     */
    @ApiModelProperty(value = "岗位工资明细信息JSON串")
    @Column(name = "d_detail_info")
    private String dDetailInfo;

    /**
     * 备用字段
     */
    @ApiModelProperty(value = "备用字段")
    @Column(name = "d_other")
    private String dOther;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public DutySalary dutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
        return this;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getDutyName() {
        return dutyName;
    }

    public DutySalary dutyName(String dutyName) {
        this.dutyName = dutyName;
        return this;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getdFlag() {
        return dFlag;
    }

    public DutySalary dFlag(String dFlag) {
        this.dFlag = dFlag;
        return this;
    }

    public void setdFlag(String dFlag) {
        this.dFlag = dFlag;
    }

    public ZonedDateTime getdChangeTime() {
        return dChangeTime;
    }

    public DutySalary dChangeTime(ZonedDateTime dChangeTime) {
        this.dChangeTime = dChangeTime;
        return this;
    }

    public void setdChangeTime(ZonedDateTime dChangeTime) {
        this.dChangeTime = dChangeTime;
    }

    public String getdRemarks() {
        return dRemarks;
    }

    public DutySalary dRemarks(String dRemarks) {
        this.dRemarks = dRemarks;
        return this;
    }

    public void setdRemarks(String dRemarks) {
        this.dRemarks = dRemarks;
    }

    public String getdDetailInfo() {
        return dDetailInfo;
    }

    public DutySalary dDetailInfo(String dDetailInfo) {
        this.dDetailInfo = dDetailInfo;
        return this;
    }

    public void setdDetailInfo(String dDetailInfo) {
        this.dDetailInfo = dDetailInfo;
    }

    public String getdOther() {
        return dOther;
    }

    public DutySalary dOther(String dOther) {
        this.dOther = dOther;
        return this;
    }

    public void setdOther(String dOther) {
        this.dOther = dOther;
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
        DutySalary dutySalary = (DutySalary) o;
        if (dutySalary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dutySalary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DutySalary{" +
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
