package com.thtf.app.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A Resource.
 */
@ApiModel(description = "The Resource entity.")
@Entity
@Table(name = "t_resource")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Resource extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The resRouterLink attribute.
     */
    @ApiModelProperty(value = "The resRouterLink attribute.")
    @Column(name = "res_router_link")
    private String resRouterLink;

    @Column(name = "res_description")
    private String resDescription;

    @Column(name = "res_flag")
    private String resFlag;

    @Column(name = "res_operate")
    private String resOperate;

    @Column(name = "res_href")
    private String resHref;

    @Column(name = "res_src")
    private String resSrc;

    @Column(name = "res_text")
    private String resText;

    @Column(name = "res_class")
    private String resClass;

    @Column(name = "res_eff_date")
    private ZonedDateTime resEffDate;

    @Column(name = "res_exp_date")
    private ZonedDateTime resExpDate;

    @Column(name = "res_lft")
    private Long resLft;

    @Column(name = "res_rgt")
    private Long resRgt;

    @Column(name = "res_level")
    private Long resLevel;

    @Column(name = "res_order")
    private Long resOrder;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @Column(name = "res_disabled")
    private Boolean resDisabled;

    @Column(name = "res_checked")
    private Boolean resChecked;

    @Column(name = "res_expanded")
    private Boolean resExpanded;

    @Column(name = "res_selected")
    private Boolean resSelected;

    @ManyToOne
    private Resource upper;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResRouterLink() {
        return resRouterLink;
    }

    public Resource resRouterLink(String resRouterLink) {
        this.resRouterLink = resRouterLink;
        return this;
    }

    public void setResRouterLink(String resRouterLink) {
        this.resRouterLink = resRouterLink;
    }

    public String getResDescription() {
        return resDescription;
    }

    public Resource resDescription(String resDescription) {
        this.resDescription = resDescription;
        return this;
    }

    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }

    public String getResFlag() {
        return resFlag;
    }

    public Resource resFlag(String resFlag) {
        this.resFlag = resFlag;
        return this;
    }

    public void setResFlag(String resFlag) {
        this.resFlag = resFlag;
    }

    public String getResOperate() {
        return resOperate;
    }

    public Resource resOperate(String resOperate) {
        this.resOperate = resOperate;
        return this;
    }

    public void setResOperate(String resOperate) {
        this.resOperate = resOperate;
    }

    public String getResHref() {
        return resHref;
    }

    public Resource resHref(String resHref) {
        this.resHref = resHref;
        return this;
    }

    public void setResHref(String resHref) {
        this.resHref = resHref;
    }

    public String getResSrc() {
        return resSrc;
    }

    public Resource resSrc(String resSrc) {
        this.resSrc = resSrc;
        return this;
    }

    public void setResSrc(String resSrc) {
        this.resSrc = resSrc;
    }

    public String getResText() {
        return resText;
    }

    public Resource resText(String resText) {
        this.resText = resText;
        return this;
    }

    public void setResText(String resText) {
        this.resText = resText;
    }

    public String getResClass() {
        return resClass;
    }

    public Resource resClass(String resClass) {
        this.resClass = resClass;
        return this;
    }

    public void setResClass(String resClass) {
        this.resClass = resClass;
    }

    public ZonedDateTime getResEffDate() {
        return resEffDate;
    }

    public Resource resEffDate(ZonedDateTime resEffDate) {
        this.resEffDate = resEffDate;
        return this;
    }

    public void setResEffDate(ZonedDateTime resEffDate) {
        this.resEffDate = resEffDate;
    }

    public ZonedDateTime getResExpDate() {
        return resExpDate;
    }

    public Resource resExpDate(ZonedDateTime resExpDate) {
        this.resExpDate = resExpDate;
        return this;
    }

    public void setResExpDate(ZonedDateTime resExpDate) {
        this.resExpDate = resExpDate;
    }

    public Long getResLft() {
        return resLft;
    }

    public Resource resLft(Long resLft) {
        this.resLft = resLft;
        return this;
    }

    public void setResLft(Long resLft) {
        this.resLft = resLft;
    }

    public Long getResRgt() {
        return resRgt;
    }

    public Resource resRgt(Long resRgt) {
        this.resRgt = resRgt;
        return this;
    }

    public void setResRgt(Long resRgt) {
        this.resRgt = resRgt;
    }

    public Long getResLevel() {
        return resLevel;
    }

    public Resource resLevel(Long resLevel) {
        this.resLevel = resLevel;
        return this;
    }

    public void setResLevel(Long resLevel) {
        this.resLevel = resLevel;
    }

    public Long getResOrder() {
        return resOrder;
    }

    public Resource resOrder(Long resOrder) {
        this.resOrder = resOrder;
        return this;
    }

    public void setResOrder(Long resOrder) {
        this.resOrder = resOrder;
    }

    public Boolean isIsLeaf() {
        return isLeaf;
    }

    public Resource isLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
    
    public Boolean isResDisabled() {
		return resDisabled;
	}

	public void setResDisabled(Boolean resDisabled) {
		this.resDisabled = resDisabled;
	}

	public Boolean isResChecked() {
		return resChecked;
	}

	public void setResChecked(Boolean resChecked) {
		this.resChecked = resChecked;
	}

	public Boolean isResExpanded() {
		return resExpanded;
	}

	public void setResExpanded(Boolean resExpanded) {
		this.resExpanded = resExpanded;
	}

	public Boolean isResSelected() {
		return resSelected;
	}

	public void setResSelected(Boolean resSelected) {
		this.resSelected = resSelected;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public Resource getUpper() {
        return upper;
    }

    public Resource upper(Resource resource) {
        this.upper = resource;
        return this;
    }

    public void setUpper(Resource resource) {
        this.upper = resource;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resource resource = (Resource) o;
        if (resource.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resource.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resource{" +
            "id=" + getId() +
            ", resRouterLink='" + getResRouterLink() + "'" +
            ", resDescription='" + getResDescription() + "'" +
            ", resFlag='" + getResFlag() + "'" +
            ", resOperate='" + getResOperate() + "'" +
            ", resHref='" + getResHref() + "'" +
            ", resSrc='" + getResSrc() + "'" +
            ", resText='" + getResText() + "'" +
            ", resClass='" + getResClass() + "'" +
            ", resEffDate='" + getResEffDate() + "'" +
            ", resExpDate='" + getResExpDate() + "'" +
            ", resLft=" + getResLft() +
            ", resRgt=" + getResRgt() +
            ", resLevel=" + getResLevel() +
            ", resOrder=" + getResOrder() +
            ", isLeaf='" + isIsLeaf() + "'" +
            ", resDisabled='" + isResDisabled() + "'" +
            ", resChecked='" + isResChecked() + "'" +
            ", resExpanded='" + isResExpanded() + "'" +
            ", resSelected='" + isResSelected() + "'" +
            "}";
    }
}