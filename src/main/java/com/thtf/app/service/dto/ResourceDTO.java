package com.thtf.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.Max;

public class ResourceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer resType;

	private String resRouterLink;

	private String resDescription;

	private String resFlag;

	private String resOperate;

	private String resText;

	private String resClass;

	private ZonedDateTime resEffDate;

	private ZonedDateTime resExpDate;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;


	private Long resOrder;

	private Boolean isLeaf;

	private Boolean resDisabled;

	private Long upperId;

	



	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResRouterLink() {
		return resRouterLink;
	}

	public void setResRouterLink(String resRouterLink) {
		this.resRouterLink = resRouterLink;
	}

	public String getResDescription() {
		return resDescription;
	}

	public void setResDescription(String resDescription) {
		this.resDescription = resDescription;
	}

	public String getResFlag() {
		return resFlag;
	}

	public void setResFlag(String resFlag) {
		this.resFlag = resFlag;
	}

	public String getResOperate() {
		return resOperate;
	}

	public void setResOperate(String resOperate) {
		this.resOperate = resOperate;
	}

	public String getResText() {
		return resText;
	}

	public void setResText(String resText) {
		this.resText = resText;
	}

	public String getResClass() {
		return resClass;
	}

	public void setResClass(String resClass) {
		this.resClass = resClass;
	}

	public ZonedDateTime getResEffDate() {
		return resEffDate;
	}

	public void setResEffDate(ZonedDateTime resEffDate) {
		this.resEffDate = resEffDate;
	}

	public ZonedDateTime getResExpDate() {
		return resExpDate;
	}

	public void setResExpDate(ZonedDateTime resExpDate) {
		this.resExpDate = resExpDate;
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

	public Long getResOrder() {
		return resOrder;
	}

	public void setResOrder(Long resOrder) {
		this.resOrder = resOrder;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getResDisabled() {
		return resDisabled;
	}

	public void setResDisabled(Boolean resDisabled) {
		this.resDisabled = resDisabled;
	}

	public Long getUpperId() {
		return upperId;
	}

	public void setUpperId(Long upperId) {
		this.upperId = upperId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ResourceDTO resourceDTO = (ResourceDTO) o;
		if (resourceDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), resourceDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "ResourceDTO [id=" + id + ", resRouterLink=" + resRouterLink + ", resDescription=" + resDescription
				+ ", resFlag=" + resFlag + ", resOperate=" + resOperate + ", resText=" + resText + ", resClass="
				+ resClass + ", resEffDate=" + resEffDate + ", resExpDate=" + resExpDate + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", resOrder=" + resOrder + ", isLeaf=" + isLeaf + ", resDisabled=" + resDisabled
				+ ", upperId=" + upperId + "]";
	}

	
}
