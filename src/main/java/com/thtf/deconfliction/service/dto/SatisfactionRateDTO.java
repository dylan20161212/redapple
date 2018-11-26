package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the SatisfactionRate entity.
 */
public class SatisfactionRateDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private Long satisfaction;
    
    private Long unsatisfactory;
    
    private Long total;
    
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

        SatisfactionRateDTO satisfactionRateDTO = (SatisfactionRateDTO) o;
        if(satisfactionRateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), satisfactionRateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SatisfactionRateDTO{" +
            "id=" + getId() +
            ", satisfaction='" + getSatisfaction() + "'" +
            "}";
    }
}
