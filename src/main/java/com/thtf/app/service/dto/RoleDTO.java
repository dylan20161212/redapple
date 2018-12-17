package com.thtf.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String roleName;

	private String roleDescription;

	private Integer roleFlag;

	private ZonedDateTime roleEffDate;

	private ZonedDateTime roleExpDate;

	private Set<ResourceDTO> resources = new HashSet<>();

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Integer roleFlag) {
		this.roleFlag = roleFlag;
	}

	public ZonedDateTime getRoleEffDate() {
		return roleEffDate;
	}

	public void setRoleEffDate(ZonedDateTime roleEffDate) {
		this.roleEffDate = roleEffDate;
	}

	public ZonedDateTime getRoleExpDate() {
		return roleExpDate;
	}

	public void setRoleExpDate(ZonedDateTime roleExpDate) {
		this.roleExpDate = roleExpDate;
	}

	public Set<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceDTO> resources) {
		this.resources = resources;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public RoleDTO() {
	}

	public RoleDTO(Long id, String roleName, String roleDescription, Integer roleFlag, ZonedDateTime roleEffDate,
			ZonedDateTime roleExpDate, Set<ResourceDTO> resources, String createdBy, Instant createdDate,
			String lastModifiedBy, Instant lastModifiedDate) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleFlag = roleFlag;
		this.roleEffDate = roleEffDate;
		this.roleExpDate = roleExpDate;
		this.resources = resources;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RoleDTO roleDTO = (RoleDTO) o;
		if (roleDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), roleDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "RoleDTO{" + "id=" + getId() + ", roleName='" + getRoleName() + "'" + ", roleDescription='"
				+ getRoleDescription() + "'" + ", roleFlag='" + getRoleFlag() + "'" + ", roleEffDate='"
				+ getRoleEffDate() + "'" + ", roleExpDate='" + getRoleExpDate() + "'" + "}";
	}
}
