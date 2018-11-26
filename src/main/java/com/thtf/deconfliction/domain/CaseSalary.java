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
 * 矛盾与纠纷案件酬劳评定表
 */
@ApiModel(description = "矛盾与纠纷案件酬劳评定表")
@Entity
@Table(name = "de_case_salary")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseSalary extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）分配类型,给机构的，给调解员的
     */
    @ApiModelProperty(value = "（来自于字典表）分配类型,给机构的，给调解员的")
    @Column(name = "s_type")
    private Long sType;

    /**
     * 调节机构或调委会Id
     */
    @ApiModelProperty(value = "调节机构或调委会Id")
    @Column(name = "mediate_org_id")
    private Long mediateOrgId;

    /**
     * 调节机构或调委会名称
     */
    @ApiModelProperty(value = "调节机构或调委会名称")
    @Column(name = "mediate_org_name")
    private String mediateOrgName;

    /**
     * 调节员Id
     */
    @ApiModelProperty(value = "调节员Id")
    @Column(name = "mediator_id")
    private Long mediatorId;

    /**
     * 调节员名称
     */
    @ApiModelProperty(value = "调节员名称")
    @Column(name = "mediator_name")
    private String mediatorName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    /**
     * 奖金精确到分100表示1元
     */
    @ApiModelProperty(value = "奖金精确到分100表示1元")
    @Column(name = "bonus_total")
    private Long bonusTotal;

    /**
     * 也是金额调解难易程度分类，用以计算调解员酬劳：简易，一般，疑难
     */
    @ApiModelProperty(value = "也是金额调解难易程度分类，用以计算调解员酬劳：简易，一般，疑难")
    @Column(name = "difficulty_level")
    private Long difficultyLevel;

    /**
     * 调解难易程度分类，用以计算调解员酬劳：简易，一般，疑难
     */
    @ApiModelProperty(value = "调解难易程度分类，用以计算调解员酬劳：简易，一般，疑难")
    @Column(name = "difficulty_level_name")
    private String difficultyLevelName;

    /**
     * 其他奖励（备用）
     */
    @ApiModelProperty(value = "其他奖励（备用）")
    @Column(name = "other_bonus_value")
    private Long otherBonusValue;

    /**
     * 其他奖励内容
     */
    @ApiModelProperty(value = "其他奖励内容")
    @Column(name = "other_bonus_content")
    private String otherBonusContent;

    @ManyToOne
    private ConflictCase conflictCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getsType() {
        return sType;
    }

    public CaseSalary sType(Long sType) {
        this.sType = sType;
        return this;
    }

    public void setsType(Long sType) {
        this.sType = sType;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public CaseSalary mediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
        return this;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public CaseSalary mediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
        return this;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public CaseSalary mediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
        return this;
    }

    public void setMediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public CaseSalary mediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
        return this;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getRemarks() {
        return remarks;
    }

    public CaseSalary remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getBonusTotal() {
        return bonusTotal;
    }

    public CaseSalary bonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
        return this;
    }

    public void setBonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public Long getDifficultyLevel() {
        return difficultyLevel;
    }

    public CaseSalary difficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }

    public void setDifficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevelName() {
        return difficultyLevelName;
    }

    public CaseSalary difficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
        return this;
    }

    public void setDifficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
    }

    public Long getOtherBonusValue() {
        return otherBonusValue;
    }

    public CaseSalary otherBonusValue(Long otherBonusValue) {
        this.otherBonusValue = otherBonusValue;
        return this;
    }

    public void setOtherBonusValue(Long otherBonusValue) {
        this.otherBonusValue = otherBonusValue;
    }

    public String getOtherBonusContent() {
        return otherBonusContent;
    }

    public CaseSalary otherBonusContent(String otherBonusContent) {
        this.otherBonusContent = otherBonusContent;
        return this;
    }

    public void setOtherBonusContent(String otherBonusContent) {
        this.otherBonusContent = otherBonusContent;
    }

    public ConflictCase getConflictCase() {
        return conflictCase;
    }

    public CaseSalary conflictCase(ConflictCase conflictCase) {
        this.conflictCase = conflictCase;
        return this;
    }

    public void setConflictCase(ConflictCase conflictCase) {
        this.conflictCase = conflictCase;
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
        CaseSalary caseSalary = (CaseSalary) o;
        if (caseSalary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseSalary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseSalary{" +
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
