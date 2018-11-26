package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConflictCase entity.
 */
public class BusinessNumberDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

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

        BusinessNumberDTO conflictCaseDTO = (BusinessNumberDTO) o;
        if(conflictCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conflictCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConflictCaseDTO{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            "}";
    }
}
