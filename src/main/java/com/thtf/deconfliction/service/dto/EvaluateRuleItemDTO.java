package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EvaluateRuleItem entity.
 */
public class EvaluateRuleItemDTO implements Serializable {

    private Long id;

    private String evaluateType;

    private Long ruleId;

    private String ruleName;

    private Long rItemId;

    private String rItemName;

    private String rItemGrade;

    private Double rItemscore;

    private String remarks;

    private Long mediatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getrItemId() {
        return rItemId;
    }

    public void setrItemId(Long rItemId) {
        this.rItemId = rItemId;
    }

    public String getrItemName() {
        return rItemName;
    }

    public void setrItemName(String rItemName) {
        this.rItemName = rItemName;
    }

    public String getrItemGrade() {
        return rItemGrade;
    }

    public void setrItemGrade(String rItemGrade) {
        this.rItemGrade = rItemGrade;
    }

    public Double getrItemscore() {
        return rItemscore;
    }

    public void setrItemscore(Double rItemscore) {
        this.rItemscore = rItemscore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public void setMediatorId(Long evaluateMediatorId) {
        this.mediatorId = evaluateMediatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluateRuleItemDTO evaluateRuleItemDTO = (EvaluateRuleItemDTO) o;
        if(evaluateRuleItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateRuleItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateRuleItemDTO{" +
            "id=" + getId() +
            ", evaluateType='" + getEvaluateType() + "'" +
            ", ruleId=" + getRuleId() +
            ", ruleName='" + getRuleName() + "'" +
            ", rItemId=" + getrItemId() +
            ", rItemName='" + getrItemName() + "'" +
            ", rItemGrade='" + getrItemGrade() + "'" +
            ", rItemscore=" + getrItemscore() +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
