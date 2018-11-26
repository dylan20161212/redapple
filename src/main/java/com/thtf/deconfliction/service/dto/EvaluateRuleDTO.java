package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EvaluateRule entity.
 */
public class EvaluateRuleDTO implements Serializable {

    private Long id;

    private String ruleName;

    private Long rItemId;

    private String rItemName;

    private String rItemGrade;

    private Double rItemscore;

    private String rState;

    private String remarks;

    private Set<EvaluateRuleItemDTO> items = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getrState() {
        return rState;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<EvaluateRuleItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<EvaluateRuleItemDTO> evaluateRuleItems) {
        this.items = evaluateRuleItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluateRuleDTO evaluateRuleDTO = (EvaluateRuleDTO) o;
        if(evaluateRuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateRuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateRuleDTO{" +
            "id=" + getId() +
            ", ruleName='" + getRuleName() + "'" +
            ", rItemId=" + getrItemId() +
            ", rItemName='" + getrItemName() + "'" +
            ", rItemGrade='" + getrItemGrade() + "'" +
            ", rItemscore=" + getrItemscore() +
            ", rState='" + getrState() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
