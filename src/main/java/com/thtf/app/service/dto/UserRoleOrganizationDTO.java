package com.thtf.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserRoleOrganization entity.
 */
public class UserRoleOrganizationDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userName;

    private String roleName;

    private String orgName;

    private Long userId;

    private String userLogin;

    private Long roleId;

    private Long organizationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userBId) {
        this.userId = userBId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userBLogin) {
        this.userLogin = userBLogin;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRoleOrganizationDTO userRoleOrganizationDTO = (UserRoleOrganizationDTO) o;
        if(userRoleOrganizationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userRoleOrganizationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserRoleOrganizationDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            "}";
    }
}
