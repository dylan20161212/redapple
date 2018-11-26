package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserExtendSalary entity.
 */
public class UserExtendSalaryDTO implements Serializable {

    private Long id;

    private Long userExtendId;

    private String userName;

    private String mdutyCode;

    private String mdutyName;

    private String sMonth;

    private Long sTotal;

    private String sFlag;

    private String sDetailInfo;

    private ZonedDateTime sSumDate;

    private ZonedDateTime sSendDate;

    private Long sStaffId;

    private String sStaffName;

    private String sOther;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserExtendId() {
        return userExtendId;
    }

    public void setUserExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMdutyCode() {
        return mdutyCode;
    }

    public void setMdutyCode(String mdutyCode) {
        this.mdutyCode = mdutyCode;
    }

    public String getMdutyName() {
        return mdutyName;
    }

    public void setMdutyName(String mdutyName) {
        this.mdutyName = mdutyName;
    }

    public String getsMonth() {
        return sMonth;
    }

    public void setsMonth(String sMonth) {
        this.sMonth = sMonth;
    }

    public Long getsTotal() {
        return sTotal;
    }

    public void setsTotal(Long sTotal) {
        this.sTotal = sTotal;
    }

    public String getsFlag() {
        return sFlag;
    }

    public void setsFlag(String sFlag) {
        this.sFlag = sFlag;
    }

    public String getsDetailInfo() {
        return sDetailInfo;
    }

    public void setsDetailInfo(String sDetailInfo) {
        this.sDetailInfo = sDetailInfo;
    }

    public ZonedDateTime getsSumDate() {
        return sSumDate;
    }

    public void setsSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
    }

    public ZonedDateTime getsSendDate() {
        return sSendDate;
    }

    public void setsSendDate(ZonedDateTime sSendDate) {
        this.sSendDate = sSendDate;
    }

    public Long getsStaffId() {
        return sStaffId;
    }

    public void setsStaffId(Long sStaffId) {
        this.sStaffId = sStaffId;
    }

    public String getsStaffName() {
        return sStaffName;
    }

    public void setsStaffName(String sStaffName) {
        this.sStaffName = sStaffName;
    }

    public String getsOther() {
        return sOther;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserExtendSalaryDTO userExtendSalaryDTO = (UserExtendSalaryDTO) o;
        if(userExtendSalaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtendSalaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtendSalaryDTO{" +
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
