package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 人民调解员考核详细规则项目与得分
 */
@ApiModel(description = "人民调解员考核详细规则项目与得分")
@Entity
@Table(name = "de_evaluate_rule_item")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateRuleItem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 考核表类型，模板，真实考核
     */
    @ApiModelProperty(value = "考核表类型，模板，真实考核")
    @Column(name = "evaluate_type")
    private String evaluateType;

    /**
     * 规则ID
     */
    @ApiModelProperty(value = "规则ID")
    @Column(name = "rule_id")
    private Long ruleId;

    /**
     * 规则名称
     */
    @ApiModelProperty(value = "规则名称")
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    @Column(name = "r_item_id")
    private Long rItemId;

    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    @Column(name = "r_item_name")
    private String rItemName;

    /**
     * 项目分值
     */
    @ApiModelProperty(value = "项目分值")
    @Column(name = "r_item_grade")
    private String rItemGrade;

    /**
     * 项目得分
     */
    @ApiModelProperty(value = "项目得分")
    @Column(name = "r_itemscore")
    private Double rItemscore;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    private EvaluateMediator mediator;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvaluateType() {
        return evaluateType;
    }

    public EvaluateRuleItem evaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
        return this;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public EvaluateRuleItem ruleId(Long ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public EvaluateRuleItem ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getrItemId() {
        return rItemId;
    }

    public EvaluateRuleItem rItemId(Long rItemId) {
        this.rItemId = rItemId;
        return this;
    }

    public void setrItemId(Long rItemId) {
        this.rItemId = rItemId;
    }

    public String getrItemName() {
        return rItemName;
    }

    public EvaluateRuleItem rItemName(String rItemName) {
        this.rItemName = rItemName;
        return this;
    }

    public void setrItemName(String rItemName) {
        this.rItemName = rItemName;
    }

    public String getrItemGrade() {
        return rItemGrade;
    }

    public EvaluateRuleItem rItemGrade(String rItemGrade) {
        this.rItemGrade = rItemGrade;
        return this;
    }

    public void setrItemGrade(String rItemGrade) {
        this.rItemGrade = rItemGrade;
    }

    public Double getrItemscore() {
        return rItemscore;
    }

    public EvaluateRuleItem rItemscore(Double rItemscore) {
        this.rItemscore = rItemscore;
        return this;
    }

    public void setrItemscore(Double rItemscore) {
        this.rItemscore = rItemscore;
    }

    public String getRemarks() {
        return remarks;
    }

    public EvaluateRuleItem remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public EvaluateMediator getMediator() {
        return mediator;
    }

    public EvaluateRuleItem mediator(EvaluateMediator evaluateMediator) {
        this.mediator = evaluateMediator;
        return this;
    }

    public void setMediator(EvaluateMediator evaluateMediator) {
        this.mediator = evaluateMediator;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EvaluateRuleItem evaluateRuleItem = (EvaluateRuleItem) o;
        if (evaluateRuleItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateRuleItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateRuleItem{" +
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
