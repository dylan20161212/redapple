package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConflictCase entity.
 */
public class OrgCaseNumberDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String mediateOrgName;
    
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

        OrgCaseNumberDTO conflictCaseDTO = (OrgCaseNumberDTO) o;
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
            ", mediateOrgName='" + getMediateOrgName() + "'" +
            ", number='" + getNumber() + "'" +
            "}";
    }
}
