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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 专家薪酬明细表
 */
@ApiModel(description = "专家薪酬明细表")
@Entity
@Table(name = "de_user_salary_info")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserSalaryInfo extends AbstractAuditingEntity implements Serializable {

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
     * 明细项目数量
     */
    @ApiModelProperty(value = "明细项目数量")
    @Column(name = "s_item_quantity")
    private Long sItemQuantity;

    /**
     * 明细项目单价（以分为单位）
     */
    @ApiModelProperty(value = "明细项目单价（以分为单位）")
    @Column(name = "s_item_price")
    private Long sItemPrice;

    /**
     * 明细项目数量乘以单价
     */
    @ApiModelProperty(value = "明细项目数量乘以单价")
    @Column(name = "s_item_amount")
    private Long sItemAmount;

    /**
     * 工资核算日期
     */
    @ApiModelProperty(value = "工资核算日期")
    @Column(name = "s_sum_date")
    private ZonedDateTime sSumDate;

    /**
     * 备用字段
     */
    @ApiModelProperty(value = "备用字段")
    @Column(name = "s_other")
    private String sOther;

    @ManyToOne
    private UserExtendSalary userExtendSalary;

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

    public UserSalaryInfo userExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
        return this;
    }

    public void setUserExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
    }

    public String getUserName() {
        return userName;
    }

    public UserSalaryInfo userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMdutyCode() {
        return mdutyCode;
    }

    public UserSalaryInfo mdutyCode(String mdutyCode) {
        this.mdutyCode = mdutyCode;
        return this;
    }

    public void setMdutyCode(String mdutyCode) {
        this.mdutyCode = mdutyCode;
    }

    public String getMdutyName() {
        return mdutyName;
    }

    public UserSalaryInfo mdutyName(String mdutyName) {
        this.mdutyName = mdutyName;
        return this;
    }

    public void setMdutyName(String mdutyName) {
        this.mdutyName = mdutyName;
    }

    public String getsMonth() {
        return sMonth;
    }

    public UserSalaryInfo sMonth(String sMonth) {
        this.sMonth = sMonth;
        return this;
    }

    public void setsMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public Long getsItemID() {
        return sItemID;
    }

    public UserSalaryInfo sItemID(Long sItemID) {
        this.sItemID = sItemID;
        return this;
    }

    public void setsItemID(Long sItemID) {
        this.sItemID = sItemID;
    }

    public String getsItemName() {
        return sItemName;
    }

    public UserSalaryInfo sItemName(String sItemName) {
        this.sItemName = sItemName;
        return this;
    }

    public void setsItemName(String sItemName) {
        this.sItemName = sItemName;
    }

    public Long getsItemQuantity() {
        return sItemQuantity;
    }

    public UserSalaryInfo sItemQuantity(Long sItemQuantity) {
        this.sItemQuantity = sItemQuantity;
        return this;
    }

    public void setsItemQuantity(Long sItemQuantity) {
        this.sItemQuantity = sItemQuantity;
    }

    public Long getsItemPrice() {
        return sItemPrice;
    }

    public UserSalaryInfo sItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
        return this;
    }

    public void setsItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
    }

    public Long getsItemAmount() {
        return sItemAmount;
    }

    public UserSalaryInfo sItemAmount(Long sItemAmount) {
        this.sItemAmount = sItemAmount;
        return this;
    }

    public void setsItemAmount(Long sItemAmount) {
        this.sItemAmount = sItemAmount;
    }

    public ZonedDateTime getsSumDate() {
        return sSumDate;
    }

    public UserSalaryInfo sSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
        return this;
    }

    public void setsSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
    }

    public String getsOther() {
        return sOther;
    }

    public UserSalaryInfo sOther(String sOther) {
        this.sOther = sOther;
        return this;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    public UserExtendSalary getUserExtendSalary() {
        return userExtendSalary;
    }

    public UserSalaryInfo userExtendSalary(UserExtendSalary userExtendSalary) {
        this.userExtendSalary = userExtendSalary;
        return this;
    }

    public void setUserExtendSalary(UserExtendSalary userExtendSalary) {
        this.userExtendSalary = userExtendSalary;
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
        UserSalaryInfo userSalaryInfo = (UserSalaryInfo) o;
        if (userSalaryInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSalaryInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSalaryInfo{" +
            "id=" + getId() +
            ", userExtendId=" + getUserExtendId() +
            ", userName='" + getUserName() + "'" +
            ", mdutyCode='" + getMdutyCode() + "'" +
            ", mdutyName='" + getMdutyName() + "'" +
            ", sMonth='" + getsMonth() + "'" +
            ", sItemID=" + getsItemID() +
            ", sItemName='" + getsItemName() + "'" +
            ", sItemQuantity=" + getsItemQuantity() +
            ", sItemPrice=" + getsItemPrice() +
            ", sItemAmount=" + getsItemAmount() +
            ", sSumDate='" + getsSumDate() + "'" +
            ", sOther='" + getsOther() + "'" +
            "}";
    }
}
