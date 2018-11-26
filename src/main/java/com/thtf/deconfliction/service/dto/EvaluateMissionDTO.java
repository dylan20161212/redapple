package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EvaluateMission entity.
 */
public class EvaluateMissionDTO implements Serializable {

    private Long id;

    private String gType;

    private Long userId;

    private String userName;

    private Long orgId;

    private String orgName;

    private String theName;

    private String theDescribe;

    private String remarks;

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

    public String getTheName() {
        return theName;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    public String getTheDescribe() {
        return theDescribe;
    }

    public void setTheDescribe(String theDescribe) {
        this.theDescribe = theDescribe;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluateMissionDTO evaluateMissionDTO = (EvaluateMissionDTO) o;
        if(evaluateMissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateMissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateMissionDTO{" +
            "id=" + getId() +
            ", gType='" + getgType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", orgId=" + getOrgId() +
            ", orgName='" + getOrgName() + "'" +
            ", theName='" + getTheName() + "'" +
            ", theDescribe='" + getTheDescribe() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
