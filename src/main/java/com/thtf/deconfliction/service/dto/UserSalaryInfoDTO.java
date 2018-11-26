package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the UserSalaryInfo entity.
 */
public class UserSalaryInfoDTO implements Serializable {

    private Long id;

    private Long userExtendId;

    private String userName;

    private String mdutyCode;

    private String mdutyName;

    private String sMonth;

    private Long sItemID;

    private String sItemName;

    private Long sItemQuantity;

    private Long sItemPrice;

    private Long sItemAmount;

    private ZonedDateTime sSumDate;

    private String sOther;

    private Long userExtendSalaryId;

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

    public Long getsItemQuantity() {
        return sItemQuantity;
    }

    public void setsItemQuantity(Long sItemQuantity) {
        this.sItemQuantity = sItemQuantity;
    }

    public Long getsItemPrice() {
        return sItemPrice;
    }

    public void setsItemPrice(Long sItemPrice) {
        this.sItemPrice = sItemPrice;
    }

    public Long getsItemAmount() {
        return sItemAmount;
    }

    public void setsItemAmount(Long sItemAmount) {
        this.sItemAmount = sItemAmount;
    }

    public ZonedDateTime getsSumDate() {
        return sSumDate;
    }

    public void setsSumDate(ZonedDateTime sSumDate) {
        this.sSumDate = sSumDate;
    }

    public String getsOther() {
        return sOther;
    }

    public void setsOther(String sOther) {
        this.sOther = sOther;
    }

    public Long getUserExtendSalaryId() {
        return userExtendSalaryId;
    }

    public void setUserExtendSalaryId(Long userExtendSalaryId) {
        this.userExtendSalaryId = userExtendSalaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserSalaryInfoDTO userSalaryInfoDTO = (UserSalaryInfoDTO) o;
        if(userSalaryInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userSalaryInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserSalaryInfoDTO{" +
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
