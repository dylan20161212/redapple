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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A Resource.
 */
@ApiModel(description = "The Resource entity.")
@Entity
@Table(name = "sys_resource")
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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

//	@Size(max = 1)
	@Column(name = "res_type")
	private Integer resType;

	@Size(max = 20)
	@Column(name = "res_operate")
	private String resOperate;

	@Column(name = "res_text")
	private String resText;

	@Column(name = "res_eff_date")
	private ZonedDateTime resEffDate;

	@Column(name = "res_exp_date")
	private ZonedDateTime resExpDate;

	@Column(name = "res_order")
	private Long resOrder;

	@Column(name = "is_leaf")
	private Boolean isLeaf;

	@Column(name = "res_disabled")
	private Boolean resDisabled;

	@ManyToOne
	private Resource upper;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

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


	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
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

	public Resource getUpper() {
		return upper;
	}

	public void setUpper(Resource upper) {
		this.upper = upper;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", resRouterLink=" + resRouterLink 
				+ ", resType=" + resType + ", resOperate=" + resOperate + ", resText=" + resText + ", resEffDate="
				+ resEffDate + ", resExpDate=" + resExpDate +  ", resOrder=" + resOrder
				+ ", isLeaf=" + isLeaf + ", resDisabled=" + resDisabled + ", upper=" + upper + "]";
	}

}