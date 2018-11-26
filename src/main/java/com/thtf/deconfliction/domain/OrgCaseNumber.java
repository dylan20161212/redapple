package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 矛盾与纠纷案件表
 */
@ApiModel(description = "调解机构案件数量视图")
@Entity
@Table(name = "v_org_case_number")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgCaseNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调节机构Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 调节机构名称
     */
    @ApiModelProperty(value = "调节机构名称")
    @Column(name = "mediate_org_name")
    private String mediateOrgName;
    
    /**
     * 案件数量
     */
    @ApiModelProperty(value = "案件数量")
    @Column(name = "number")
    private Long number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public OrgCaseNumber mediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
        return this;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrgCaseNumber conflictCase = (OrgCaseNumber) o;
        if (conflictCase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conflictCase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConflictCase{" +
            "id=" + getId() +
            ", mediateOrgName='" + getMediateOrgName() + "'" +
            ", number='" + getNumber() + "'" +
            "}";
    }
}
