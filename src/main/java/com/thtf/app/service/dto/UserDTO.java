package com.thtf.app.service.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.thtf.app.config.Constants;
import com.thtf.app.domain.User;

public class UserDTO {

	private Long id;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String login;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	@Size(max = 50)
	private String organizationName;

	@Email
	@Size(min = 5, max = 100)
	private String email;

	@Size(max = 256)
	private String imageUrl;

	private boolean activated = false;

	@Size(min = 2, max = 6)
	private String langKey;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Set<String> authorities;

	private Long organizationId;

	private String organizationOrgName;

	private Set<RoleDTO> roles;

	public UserDTO() {
		// Empty constructor needed for Jackson.
	}

	public UserDTO(User user) {
		this(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getActivated(), user.getImageUrl(), user.getLangKey(), user.getCreatedBy(), user.getCreatedDate(),
				user.getLastModifiedBy(), user.getLastModifiedDate(),
				// user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()),
				null,
				// user.getRoles().stream()
				// .map((r) -> new RoleDTO(r.getId(), r.getRoleName(),
				// r.getRoleDescription(), r.getRoleFlag(),
				// r.getRoleEffDate(), r.getRoleExpDate(), null,
				// r.getCreatedBy(), r.getCreatedDate(),
				// r.getLastModifiedBy(), r.getLastModifiedDate()))
				// .collect(Collectors.toSet()),
				null,
				// user.getOrganization() == null ? null :
				// user.getOrganization().getId(),
				null, user.getOrganizationName());
	}

	public UserDTO(Long id, String login, String firstName, String lastName, String email, boolean activated,
			String imageUrl, String langKey, String createdBy, Instant createdDate, String lastModifiedBy,
			Instant lastModifiedDate, Set<String> authorities) {

		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.activated = activated;
		this.imageUrl = imageUrl;
		this.langKey = langKey;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.authorities = authorities;
	}

	public UserDTO(Long id, String login, String firstName, String lastName, String email, boolean activated,
			String imageUrl, String langKey, String createdBy, Instant createdDate, String lastModifiedBy,
			Instant lastModifiedDate, Set<String> authorities, Set<RoleDTO> roles, Long organizationId,
			String organizationName) {

		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.activated = activated;
		this.imageUrl = imageUrl;
		this.langKey = langKey;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.authorities = authorities;
		this.roles = roles;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
	}

	public UserDTO(Long id, String login, String firstName, String lastName, String email, boolean activated,
			String imageUrl, String langKey, String createdBy, Instant createdDate, String lastModifiedBy,
			Instant lastModifiedDate, Set<RoleDTO> roles, Long organizationId, String organizationName) {

		this.id = id;
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.activated = activated;
		this.imageUrl = imageUrl;
		this.langKey = langKey;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.roles = roles;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		Set<String> tempAuthorities = new HashSet<>();
		Set<ResourceDTO> tempRes = new HashSet<>();
		for (RoleDTO rd : this.roles) {
			tempRes.addAll(rd.getResources());
		}
		for (ResourceDTO rsd : tempRes) {
			if (rsd.getResOperate() != null && !"".equals(rsd.getResOperate())) {
				tempAuthorities.add(rsd.getResRouterLink() + ":" + rsd.getResOperate());
			} else {
				tempAuthorities.add(rsd.getResRouterLink());
			}
		}

		this.authorities = tempAuthorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public boolean isActivated() {
		return activated;
	}

	public String getLangKey() {
		return langKey;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationOrgName() {
		return organizationOrgName;
	}

	public void setOrganizationOrgName(String organizationOrgName) {
		this.organizationOrgName = organizationOrgName;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		UserDTO userBDTO = (UserDTO) o;
		if (userBDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), userBDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "UserDTO{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
				+ '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated=" + activated
				+ ", langKey='" + langKey + '\'' + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", lastModifiedBy='" + lastModifiedBy + '\'' + ", lastModifiedDate=" + lastModifiedDate
				+ ", authorities=" + authorities + "}";
	}
}
