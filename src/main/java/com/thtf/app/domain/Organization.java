package com.thtf.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModel;

/**
 * A Organization.
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "t_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_code")
    private String orgCode;

    @Size(max = 255)
    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_flag")
    private String orgFlag;

    @Column(name = "org_description")
    private String orgDescription;

    @Column(name = "org_lft")
    private Long orgLft;

    @Column(name = "org_rgt")
    private Long orgRgt;

    @Column(name = "org_level")
    private Long orgLevel;

    @Column(name = "org_order")
    private Float orgOrder;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @ManyToOne
    private Organization upper;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
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

    public String getOrgFlag() {
        return orgFlag;
    }

    public Organization orgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
        return this;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
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

    public Long getOrgLft() {
        return orgLft;
    }

    public Organization orgLft(Long orgLft) {
        this.orgLft = orgLft;
        return this;
    }

    public void setOrgLft(Long orgLft) {
        this.orgLft = orgLft;
    }

    public Long getOrgRgt() {
        return orgRgt;
    }

    public Organization orgRgt(Long orgRgt) {
        this.orgRgt = orgRgt;
        return this;
    }

    public void setOrgRgt(Long orgRgt) {
        this.orgRgt = orgRgt;
    }

    public Long getOrgLevel() {
        return orgLevel;
    }

    public Organization orgLevel(Long orgLevel) {
        this.orgLevel = orgLevel;
        return this;
    }

    public void setOrgLevel(Long orgLevel) {
        this.orgLevel = orgLevel;
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
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

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
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", orgCode='" + getOrgCode() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgFlag='" + getOrgFlag() + "'" +
            ", orgDescription='" + getOrgDescription() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", orgLft='" + getOrgLft() + "'" +
            ", orgRgt='" + getOrgRgt() + "'" +
            ", orgLevel='" + getOrgLevel() + "'" +
            ", orgOrder='" + getOrgOrder() + "'" +
            ", isLeaf='" + isIsLeaf() + "'" +
            "}";
    }
}
