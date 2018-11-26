package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the GroupMember entity.
 */
public class GroupMemberDTO implements Serializable {

    private Long id;

    private String mType;

    private Long userId;

    private String userName;

    private String gRole;

    private String remarks;

    private Long userExtendId;

    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
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

    public String getgRole() {
        return gRole;
    }

    public void setgRole(String gRole) {
        this.gRole = gRole;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getUserExtendId() {
        return userExtendId;
    }

    public void setUserExtendId(Long userExtendId) {
        this.userExtendId = userExtendId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long officeGroupId) {
        this.groupId = officeGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroupMemberDTO groupMemberDTO = (GroupMemberDTO) o;
        if(groupMemberDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupMemberDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupMemberDTO{" +
            "id=" + getId() +
            ", mType='" + getmType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", gRole='" + getgRole() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
