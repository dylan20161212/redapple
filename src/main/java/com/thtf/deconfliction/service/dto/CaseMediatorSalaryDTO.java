package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CaseMediatorSalary entity.
 */
public class CaseMediatorSalaryDTO implements Serializable {

    private Long id;

    private String sType;

    private String remarks;

    private Long bonusTotal;

    private Long difficultyLevel;

    private String difficultyLevelName;

    private Long otherBonusValue;

    private String otherBonusContent;

    private Long caseSalaryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
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

    public Long getCaseSalaryId() {
        return caseSalaryId;
    }

    public void setCaseSalaryId(Long userExtendId) {
        this.caseSalaryId = userExtendId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseMediatorSalaryDTO caseMediatorSalaryDTO = (CaseMediatorSalaryDTO) o;
        if(caseMediatorSalaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseMediatorSalaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseMediatorSalaryDTO{" +
            "id=" + getId() +
            ", sType='" + getsType() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", bonusTotal=" + getBonusTotal() +
            ", difficultyLevel=" + getDifficultyLevel() +
            ", difficultyLevelName='" + getDifficultyLevelName() + "'" +
            ", otherBonusValue=" + getOtherBonusValue() +
            ", otherBonusContent='" + getOtherBonusContent() + "'" +
            "}";
    }
}
