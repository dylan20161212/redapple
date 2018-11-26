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
 * 专家薪酬管理
 */
@ApiModel(description = "专家薪酬管理")
@Entity
@Table(name = "de_user_extend_salary")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserExtendSalary extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 专家Id
     */
    @ApiModelProperty(value = "专家Id")
    @Column(name = "user_extend_id")
    private Long userExtendId;

    /**
     * 专家名称
     */
    @ApiModelProperty(value = "专家名称")
    @Column(name = "user_name")
    private String userName;

    /**
     * 岗位代码
     */
    @ApiModelProperty(value = "岗位代码")
    @Column(name = "mduty_code")
    private String mdutyCode;

    /**
     * 岗位职级
     */
    @ApiModelProperty(value = "岗位职级")
    @Column(name = "mduty_name")
    private String mdutyName;

    /**
     * 工资发放月
     */
    @ApiModelProperty(value = "工资发放月")
    @Column(name = "s_month")
    private String sMonth;

    /**
     * 工资合计金额(以分为单位)
     */
    @ApiModelProperty(value = "工资合计金额(以分为单位)")
    @Column(name = "s_total")
    private Long sTotal;

    /**
     * 工资发放状态
     */
    @ApiModelProperty(value = "工资发放状态")
    @Column(name = "s_flag")
    private String sFlag;

    /**
     * 工资明细信息JSON串
     */
    @ApiModelProperty(value = "工资明细信息JSON串")
    @Column(name = "s_detail_info")
    private String sDetailInfo;

    /**
     * 工资核算日期
     */
    @ApiModelProperty(value = "工资核算日期")
    @Column(name = "s_sum_date")
    private ZonedDateTime sSumDate;

    /**
     * 工资发放日期
     */
    @ApiModelProperty(value = "工资发放日期")
    @Column(name = "s_send_date")
    private ZonedDateTime sSendDate;

    /**
     * 计算机工资人ID
     */
    @ApiModelProperty(value = "计算机工资人ID")
    @Column(name = "s_staff_id")
    private Long sStaffId;

    /**
     * 计算机工资人姓名
     */
    @ApiModelProperty(value = "计算机工资人姓名")
    @Column(name = "s_staff_name")
    private String sStaffName;

    /**
     * 备用字段
     */
    @ApiModelProperty(value = "备用字段")
    @Column(name = "s_other")
    private String sOther;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserExtendId() {
        return userExtendId;
    }

    public UserExtendSalary userExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
        return this;
    }

    public void setUserExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
    }

    public String getUserName() {
        return userName;
    }

    public UserExtendSalary userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMdutyCode() {
        return mdutyCode;
    }

    public UserExtendSalary mdutyCode(String mdutyCode) {
        this.mdutyCode = mdutyCode;
        return this;
    }

    public void setMdutyCode(String mdutyCode) {
        this.mdutyCode = mdutyCode;
    }

    public String getMdutyName() {
        return mdutyName;
    }

    public UserExtendSalary mdutyName(String mdutyName) {
        this.mdutyName = mdutyName;
        return this;
    }

    public void setMdutyName(String mdutyName) {
        this.mdutyName = mdutyName;
    }

    public String getsMonth() {
        return sMonth;
    }

    public UserExtendSalary sMonth(String sMonth) {
        this.sMonth = sMonth;
        return this;
    }

    public void setsMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public Long getsTotal() {
        return sTotal;
    }

    public UserExtendSalary sTotal(Long sTotal) {
        this.sTotal = sTotal;
        return this;
    }

    public void setsTotal(Long sTotal) {
        this.sTotal = sTotal;
    }

    public String getsFlag() {
        return sFlag;
    }

    public UserExtendSalary sFlag(String sFlag) {
        this.sFlag = sFlag;
        return this;
    }

    public void setsFlag(String sFlag) {
        this.sFlag = sFlag;
    }

    public String getsDetailInfo() {
        return sDetailInfo;
    }

    public UserExtendSalary sDetailInfo(String sDetailInfo) {
        this.sDetailInfo = sDetailInfo;
        return this;
    }

    public void setsDetailInfo(String sDetailInfo) {
        this.sDetailInfo = sDetailInfo;
    }

    public ZonedDateTime getsSumDate() {
        return sSumDate;
    }

    public UserExtendSalary sSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
        return this;
    }

    public void setsSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
    }

    public ZonedDateTime getsSendDate() {
        return sSendDate;
    }

    public UserExtendSalary sSendDate(ZonedDateTime sSendDate) {
        this.sSendDate = sSendDate;
        return this;
    }

    public void setsSendDate(ZonedDateTime sSendDate) {
        this.sSendDate = sSendDate;
    }

    public Long getsStaffId() {
        return sStaffId;
    }

    public UserExtendSalary sStaffId(Long sStaffId) {
        this.sStaffId = sStaffId;
        return this;
    }

    public void setsStaffId(Long sStaffId) {
        this.sStaffId = sStaffId;
    }

    public String getsStaffName() {
        return sStaffName;
    }

    public UserExtendSalary sStaffName(String sStaffName) {
        this.sStaffName = sStaffName;
        return this;
    }

    public void setsStaffName(String sStaffName) {
        this.sStaffName = sStaffName;
    }

    public String getsOther() {
        return sOther;
    }

    public UserExtendSalary sOther(String sOther) {
        this.sOther = sOther;
        return this;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
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
        UserExtendSalary userExtendSalary = (UserExtendSalary) o;
        if (userExtendSalary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtendSalary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtendSalary{" +
            "id=" + getId() +
            ", userExtendId=" + getUserExtendId() +
            ", userName='" + getUserName() + "'" +
            ", mdutyCode='" + getMdutyCode() + "'" +
            ", mdutyName='" + getMdutyName() + "'" +
            ", sMonth='" + getsMonth() + "'" +
            ", sTotal=" + getsTotal() +
            ", sFlag='" + getsFlag() + "'" +
            ", sDetailInfo='" + getsDetailInfo() + "'" +
            ", sSumDate='" + getsSumDate() + "'" +
            ", sSendDate='" + getsSendDate() + "'" +
            ", sStaffId=" + getsStaffId() +
            ", sStaffName='" + getsStaffName() + "'" +
            ", sOther='" + getsOther() + "'" +
            "}";
    }
}
