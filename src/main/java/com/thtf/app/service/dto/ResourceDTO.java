package com.thtf.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

public class ResourceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String resRouterLink;

	private String resDescription;

	private String resFlag;

	private String resOperate;

	private String resHref;

	private String resSrc;

	private String resText;

	private String resClass;

	private ZonedDateTime resEffDate;

	private ZonedDateTime resExpDate;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Long resLft;

	private Long resRgt;

	private Long resLevel;

	private Long resOrder;

	private Boolean isLeaf;

	private Boolean resDisabled;

	private Boolean resChecked;

	private Boolean resExpanded;

	private Boolean resSelected;

	private Long upperId;

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

	public String getResHref() {
		return resHref;
	}

	public void setResHref(String resHref) {
		this.resHref = resHref;
	}

	public String getResSrc() {
		return resSrc;
	}

	public void setResSrc(String resSrc) {
		this.resSrc = resSrc;
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

	public Long getResLft() {
		return resLft;
	}

	public void setResLft(Long resLft) {
		this.resLft = resLft;
	}

	public Long getResRgt() {
		return resRgt;
	}

	public void setResRgt(Long resRgt) {
		this.resRgt = resRgt;
	}

	public Long getResLevel() {
		return resLevel;
	}

	public void setResLevel(Long resLevel) {
		this.resLevel = resLevel;
	}

	public Long getResOrder() {
		return resOrder;
	}

	public void setResOrder(Long resOrder) {
		this.resOrder = resOrder;
	}

	public Boolean isIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public Boolean isResDisabled() {
		return resDisabled;
	}
	
	public Boolean getResDisabled() {
		return resDisabled;
	}

	public void setResDisabled(Boolean resDisabled) {
		this.resDisabled = resDisabled;
	}

	public Boolean isResChecked() {
		return resChecked;
	}

	public Boolean getResChecked() {
		return resChecked;
	}

	public void setResChecked(Boolean resChecked) {
		this.resChecked = resChecked;
	}

	public Boolean isResExpanded() {
		return resExpanded;
	}
	
	public Boolean getResExpanded() {
		return resExpanded;
	}

	public void setResExpanded(Boolean resExpanded) {
		this.resExpanded = resExpanded;
	}

	public Boolean isResSelected() {
		return resSelected;
	}

	public Boolean getResSelected() {
		return resSelected;
	}

	public void setResSelected(Boolean resSelected) {
		this.resSelected = resSelected;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public Long getUpperId() {
		return upperId;
	}

	public void setUpperId(Long resourceId) {
		this.upperId = resourceId;
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
		return "ResourceDTO{" + "id=" + getId() + ", resRouterLink='" + getResRouterLink() + "'" + ", resDescription='"
				+ getResDescription() + "'" + ", resFlag='" + getResFlag() + "'" + ", resOperate='" + getResOperate()
				+ "'" + ", resHref='" + getResHref() + "'" + ", resSrc='" + getResSrc() + "'" + ", resText='"
				+ getResText() + "'" + ", resClass='" + getResClass() + "'" + ", resEffDate='" + getResEffDate() + "'"
				+ ", resExpDate='" + getResExpDate() + "'" + ", lastModifiedBy='" + getLastModifiedBy() + "'"
				+ ", lastModifiedDate='" + getLastModifiedDate() + "'" + ", resLft='" + getResLft() + "'" + ", resRgt='"
				+ getResRgt() + "'" + ", resLevel='" + getResLevel() + "'" + ", resOrder='" + getResOrder() + "'"
				+ ", isLeaf='" + isIsLeaf() + "'" + ", resDisabled='" + isResDisabled() + "'" + 
				", resChecked='" + isResChecked() + "'"
				+ ", resExpanded='" + isResExpanded() + "'" + ", resSelected='" + isResSelected() + "'" + "}";
	}
}
