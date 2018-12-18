package com.thtf.app.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thtf.app.config.Constants;

import io.swagger.annotations.ApiModelProperty;

/**
 * A user.
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
// @NamedEntityGraph(name = "withRoles", attributeNodes =
// @NamedAttributeNode(value = "roles", subgraph = "subgraphroles"), subgraphs =
// {
// @NamedSubgraph(name = "subgraphroles", attributeNodes =
// @NamedAttributeNode("resources")) })
public class User extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	@Column(name = "login", length = 50, unique = true, nullable = false)
	private String login;

	@JsonIgnore
	@NotNull
	@Size(min = 60, max = 60)
	@Column(name = "password", length = 60)
	private String password;

	@Size(max = 50)
	@Column(name = "real_name", length = 50)
	private String realName;


	@Email
	@Size(min = 5, max = 100)
	@Column(name = "email", length = 100, unique = true)
	private String email;

	@NotNull
	@Column(name = "activated", nullable = false)
	private boolean activated = false;

	@Size(min = 2, max = 5)
	@Column(name = "lang_key", length = 5)
	private String langKey;

	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	private String imageUrl;

	@Column(name = "created_date")
	private Instant createdDate = null;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "jhi_user_authority", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authority_name", referencedColumnName = "name") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private Set<Authority> authorities = new HashSet<>();

	@JsonIgnore
	// @ManyToMany(fetch=FetchType.EAGER)
	@ManyToMany
	@JoinTable(name = "t_user_role_organization", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@BatchSize(size = 20)
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<UserRoleOrganization> userRoleOrganizations = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@ApiModelProperty(value = "选择的组织机构角色标识")
	@Column(name = "sel_org_role_id")
	private Long selOrgRoleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	// Lowercase the login before saving it in database
	public void setLogin(String login) {
		this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getImageUrl() {
		return imageUrl;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean getActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Long getSelOrgRoleId() {
		return selOrgRoleId;
	}

	public void setSelOrgRoleId(Long selOrgRoleId) {
		this.selOrgRoleId = selOrgRoleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User user = (User) o;
		return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "User{" + "login='" + login + '\'' + '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\''
				+ ", activated='" + activated + '\'' + ", langKey='" + langKey + '\'' + '\''  + "}";
	}

	@Override
	public Instant getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Organization getOrganization() {
		return organization;
	}

	public User organization(Organization organization) {
		this.organization = organization;
		return this;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
