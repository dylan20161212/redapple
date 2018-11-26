package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CaseSalary entity.
 */
public class CaseSalaryDTO implements Serializable {

    private Long id;

    private Long sType;

    private Long mediateOrgId;

    private String mediateOrgName;

    private Long mediatorId;

    private String mediatorName;

    private String remarks;

    private Long bonusTotal;

    private Long difficultyLevel;

    private String difficultyLevelName;

    private Long otherBonusValue;

    private String otherBonusContent;

    private Long conflictCaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsType() {
        return sType;
    }

    public void setsType(Long sType) {
        this.sType = sType;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public void setMediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public Long getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevelName() {
        return difficultyLevelName;
    }

    public void setDifficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
    }

    public Long getOtherBonusValue() {
        return otherBonusValue;
    }

    public void setOtherBonusValue(Long otherBonusValue) {
        this.otherBonusValue = otherBonusValue;
    }

    public String getOtherBonusContent() {
        return otherBonusContent;
    }

    public void setOtherBonusContent(String otherBonusContent) {
        this.otherBonusContent = otherBonusContent;
    }

    public Long getConflictCaseId() {
        return conflictCaseId;
    }

    public void setConflictCaseId(Long conflictCaseId) {
        this.conflictCaseId = conflictCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseSalaryDTO caseSalaryDTO = (CaseSalaryDTO) o;
        if(caseSalaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseSalaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseSalaryDTO{" +
            "id=" + getId() +
            ", sType=" + getsType() +
            ", mediateOrgId=" + getMediateOrgId() +
            ", mediateOrgName='" + getMediateOrgName() + "'" +
            ", mediatorId=" + getMediatorId() +
            ", mediatorName='" + getMediatorName() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", bonusTotal=" + getBonusTotal() +
            ", difficultyLevel=" + getDifficultyLevel() +
            ", difficultyLevelName='" + getDifficultyLevelName() + "'" +
            ", otherBonusValue=" + getOtherBonusValue() +
            ", otherBonusContent='" + getOtherBonusContent() + "'" +
            "}";
    }
}
