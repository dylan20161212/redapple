package com.thtf.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModel;

/**
 * A Organization.
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "sys_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 48)
	@Column(name = "org_code")
	private String orgCode;

	@Size(max = 200)
	@Column(name = "org_name")
	private String orgName;

	@Size(max = 200)
	@Column(name = "org_full_name")
	private String orgFullName;

	
	@Column(name = "org_flag")
	private Integer orgFlag;

	@Size(max = 50)
	@Column(name = "org_identity")
	private String orgIdentity;

	@Size(max = 200)
	@Column(name = "org_description")
	private String orgDescription;

	@Column(name = "org_level")
	private Integer orgLevel;

	@Column(name = "org_order")
	private Float orgOrder;

	@Column(name = "is_leaf")
	private Boolean isLeaf;

	@ManyToOne(fetch = FetchType.LAZY)
	private Organization upper;

	// jhipster-needle-entity-add-field - Jhipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public Organization orgCode(String orgCode) {
		this.orgCode = orgCode;
		return this;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public Organization orgName(String orgName) {
		this.orgName = orgName;
		return this;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDescription() {
		return orgDescription;
	}

	public Organization orgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
		return this;
	}

	public void setOrgDescription(String orgDescription) {
		this.orgDescription = orgDescription;
	}

	public String getOrgFullName() {
		return orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	public Integer getOrgFlag() {
		return orgFlag;
	}

	public void setOrgFlag(Integer orgFlag) {
		this.orgFlag = orgFlag;
	}

	public String getOrgIdentity() {
		return orgIdentity;
	}

	public void setOrgIdentity(String orgIdentity) {
		this.orgIdentity = orgIdentity;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public Float getOrgOrder() {
		return orgOrder;
	}

	public Organization orgOrder(Float orgOrder) {
		this.orgOrder = orgOrder;
		return this;
	}

	public void setOrgOrder(Float orgOrder) {
		this.orgOrder = orgOrder;
	}

	public Boolean isIsLeaf() {
		return isLeaf;
	}

	public Organization isLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
		return this;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Organization getUpper() {
		return upper;
	}

	public Organization upper(Organization organization) {
		this.upper = organization;
		return this;
	}

	public void setUpper(Organization organization) {
		this.upper = organization;
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
		Organization organization = (Organization) o;
		if (organization.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), organization.getId());
	}
	
	

	@Override
	public String toString() {
		return "Organization [id=" + id + ", orgCode=" + orgCode + ", orgName=" + orgName + ", orgFullName="
				+ orgFullName + ", orgFlag=" + orgFlag + ", orgIdentity=" + orgIdentity + ", orgDescription="
				+ orgDescription + ", orgLevel=" + orgLevel + ", orgOrder=" + orgOrder + ", isLeaf=" + isLeaf
				+ ", upper=" + upper + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

}
