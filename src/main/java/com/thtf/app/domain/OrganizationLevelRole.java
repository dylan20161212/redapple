package com.thtf.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sys_org_level_role")
public class OrganizationLevelRole {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Role role;

	@Size(max = 2)
	@Column(name = "org_level", length = 2)
	private Integer org_level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getOrg_level() {
		return org_level;
	}

	public void setOrg_level(Integer org_level) {
		this.org_level = org_level;
	}

	@Override
	public String toString() {
		return "OrganizationLevelRole [id=" + id + ", role=" + role + ", org_level=" + org_level + "]";
	}

}
