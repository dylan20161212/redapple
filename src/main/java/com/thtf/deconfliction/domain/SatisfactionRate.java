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
@ApiModel(description = "满意率视图")
@Entity
@Table(name = "v_satisfaction_rate")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SatisfactionRate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @Id
    private String id;

    /**
     * 满意数量
     */
    @ApiModelProperty(value = "满意案件数量")
    @Column(name = "satisfaction")
    private Long satisfaction;
    
    /**
     * 不满意数量
     */
    @ApiModelProperty(value = "不满意案件数量")
    @Column(name = "unsatisfactory")
    private Long unsatisfactory;
    
    /**
     * 总案件数量
     */
    @ApiModelProperty(value = "总案件数量")
    @Column(name = "total")
    private Long total;
    
    /**
     * 满意率
     */
    @ApiModelProperty(value = "满意率")
    @Column(name = "rate")
    private Double rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Long getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Long satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Long getUnsatisfactory() {
		return unsatisfactory;
	}

	public void setUnsatisfactory(Long unsatisfactory) {
		this.unsatisfactory = unsatisfactory;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SatisfactionRate satisfactionRate = (SatisfactionRate) o;
        if (satisfactionRate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), satisfactionRate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SatisfactionRate{" +
            "id=" + getId() +
            ", satisfaction='" + getSatisfaction() + "'" +
            "}";
    }
}
