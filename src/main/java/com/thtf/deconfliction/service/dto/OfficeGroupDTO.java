package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the OfficeGroup entity.
 */
public class OfficeGroupDTO implements Serializable {

    private Long id;

    private String gType;

    private Long userId;

    private String userName;

    private Long orgId;

    private String orgName;

    private String groupName;

    private String groupDescribe;

    private String remarks;

    private Long referId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgType() {
        return gType;
    }

    public void setgType(String gType) {
        this.gType = gType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescribe() {
        return groupDescribe;
    }

    public void setGroupDescribe(String groupDescribe) {
        this.groupDescribe = groupDescribe;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getReferId() {
        return referId;
    }

    public void setReferId(Long referId) {
        this.referId = referId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfficeGroupDTO officeGroupDTO = (OfficeGroupDTO) o;
        if(officeGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), officeGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OfficeGroupDTO{" +
            "id=" + getId() +
            ", gType='" + getgType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", orgId=" + getOrgId() +
            ", orgName='" + getOrgName() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", groupDescribe='" + getGroupDescribe() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", referId=" + getReferId() +
            "}";
    }
}
