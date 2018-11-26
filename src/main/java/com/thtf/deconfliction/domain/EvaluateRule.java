package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 人民调解员考核规则
 */
@ApiModel(description = "人民调解员考核规则")
@Entity
@Table(name = "de_evaluate_rule")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateRule extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @Column(name = "r_state")
    private String rState;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "evaluate_rule_items",
               joinColumns = @JoinColumn(name="evaluate_rules_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="items_id", referencedColumnName="id"))
    private Set<EvaluateRuleItem> items = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public EvaluateRule ruleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getrItemId() {
        return rItemId;
    }

    public EvaluateRule rItemId(Long rItemId) {
        this.rItemId = rItemId;
        return this;
    }

    public void setrItemId(Long rItemId) {
        this.rItemId = rItemId;
    }

    public String getrItemName() {
        return rItemName;
    }

    public EvaluateRule rItemName(String rItemName) {
        this.rItemName = rItemName;
        return this;
    }

    public void setrItemName(String rItemName) {
        this.rItemName = rItemName;
    }

    public String getrItemGrade() {
        return rItemGrade;
    }

    public EvaluateRule rItemGrade(String rItemGrade) {
        this.rItemGrade = rItemGrade;
        return this;
    }

    public void setrItemGrade(String rItemGrade) {
        this.rItemGrade = rItemGrade;
    }

    public Double getrItemscore() {
        return rItemscore;
    }

    public EvaluateRule rItemscore(Double rItemscore) {
        this.rItemscore = rItemscore;
        return this;
    }

    public void setrItemscore(Double rItemscore) {
        this.rItemscore = rItemscore;
    }

    public String getrState() {
        return rState;
    }

    public EvaluateRule rState(String rState) {
        this.rState = rState;
        return this;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getRemarks() {
        return remarks;
    }

    public EvaluateRule remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<EvaluateRuleItem> getItems() {
        return items;
    }

    public EvaluateRule items(Set<EvaluateRuleItem> evaluateRuleItems) {
        this.items = evaluateRuleItems;
        return this;
    }

    public EvaluateRule addItems(EvaluateRuleItem evaluateRuleItem) {
        this.items.add(evaluateRuleItem);
        return this;
    }

    public EvaluateRule removeItems(EvaluateRuleItem evaluateRuleItem) {
        this.items.remove(evaluateRuleItem);
        return this;
    }

    public void setItems(Set<EvaluateRuleItem> evaluateRuleItems) {
        this.items = evaluateRuleItems;
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
        EvaluateRule evaluateRule = (EvaluateRule) o;
        if (evaluateRule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateRule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateRule{" +
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
