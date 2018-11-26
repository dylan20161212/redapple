package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 矛盾与纠纷案件表
 */
@ApiModel(description = "业务办理数量视图")
@Entity
@Table(name = "v_business_number")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    private String id;

    /**
     * 案件数量
     */
    @ApiModelProperty(value = "案件数量")
    @Column(name = "number")
    private Long number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        BusinessNumber conflictCase = (BusinessNumber) o;
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
            ", number='" + getNumber() + "'" +
            "}";
    }
}
