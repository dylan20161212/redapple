package com.thtf.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Role.
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 200)
	@Column(name = "role_name", nullable = false)
	private String roleName;

	@Size(max = 200)
	@Column(name = "role_description")
	private String roleDescription;

	@Size(max = 1)
	@Column(name = "role_flag")
	private Integer roleFlag;

	@Column(name = "role_eff_date")
	private ZonedDateTime roleEffDate;

	@Column(name = "role_exp_date")
	private ZonedDateTime roleExpDate;

	@ManyToMany
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "t_role_resource", joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "resources_id", referencedColumnName = "id"))
	private Set<Resource> resources = new HashSet<>();

	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	// jhipster-needle-entity-add-field - Jhipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public Role roleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public Role roleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
		return this;
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

	public Role roleEffDate(ZonedDateTime roleEffDate) {
		this.roleEffDate = roleEffDate;
		return this;
	}

	public void setRoleEffDate(ZonedDateTime roleEffDate) {
		this.roleEffDate = roleEffDate;
	}

	public ZonedDateTime getRoleExpDate() {
		return roleExpDate;
	}

	public Role roleExpDate(ZonedDateTime roleExpDate) {
		this.roleExpDate = roleExpDate;
		return this;
	}

	public void setRoleExpDate(ZonedDateTime roleExpDate) {
		this.roleExpDate = roleExpDate;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public Role resources(Set<Resource> resources) {
		this.resources = resources;
		return this;
	}

	public Role addResource(Resource resource) {
		this.resources.add(resource);
		return this;
	}

	public Role removeResource(Resource resource) {
		this.resources.remove(resource);
		return this;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	// jhipster-needle-entity-add-getters-setters - Jhipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Role role = (Role) o;
		if (role.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), role.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Role{" + "id=" + getId() + ", roleName='" + getRoleName() + "'" + ", roleDescription='"
				+ getRoleDescription() + "'" + ", roleFlag='" + getRoleFlag() + "'" + ", roleEffDate='"
				+ getRoleEffDate() + "'" + ", roleExpDate='" + getRoleExpDate() + "'" + "}";
	}
}
