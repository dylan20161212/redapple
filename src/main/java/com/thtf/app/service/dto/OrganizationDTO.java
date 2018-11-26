package com.thtf.app.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.Size;

/**
 * A DTO for the Organization entity.
 */
public class OrganizationDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@Size(max = 255)
    private String orgCode;

    @Size(max = 255)
    private String orgName;

    private String orgFlag;
    
    @Size(max = 255)
    private String orgDescription;

    private String orgCreatedBy;

    private ZonedDateTime orgCreatedDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Long orgLft;

    private Long orgRgt;

    private Long orgLevel;

    private Float orgOrder;

    private Boolean isLeaf;

    private Long upperId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgCreatedBy() {
        return orgCreatedBy;
    }

    public void setOrgCreatedBy(String orgCreatedBy) {
        this.orgCreatedBy = orgCreatedBy;
    }

    public ZonedDateTime getOrgCreatedDate() {
        return orgCreatedDate;
    }

    public void setOrgCreatedDate(ZonedDateTime orgCreatedDate) {
        this.orgCreatedDate = orgCreatedDate;
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

    public Long getOrgLft() {
        return orgLft;
    }

    public void setOrgLft(Long orgLft) {
        this.orgLft = orgLft;
    }

    public Long getOrgRgt() {
        return orgRgt;
    }

    public void setOrgRgt(Long orgRgt) {
        this.orgRgt = orgRgt;
    }

    public Long getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Long orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Float getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(Float orgOrder) {
        this.orgOrder = orgOrder;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getUpperId() {
        return upperId;
    }

    public void setUpperId(Long organizationId) {
        this.upperId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationDTO organizationDTO = (OrganizationDTO) o;
        if(organizationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", orgCode='" + getOrgCode() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgFlag='" + getOrgFlag() + "'" +
            ", orgDescription='" + getOrgDescription() + "'" +
            ", orgCreatedBy='" + getOrgCreatedBy() + "'" +
            ", orgCreatedDate='" + getOrgCreatedDate() + "'" +
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
