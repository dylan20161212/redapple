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

import com.thtf.deconfliction.domain.UserExtend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 专职调解员酬劳计算表
 */
@ApiModel(description = "专职调解员酬劳计算表")
@Entity
@Table(name = "de_cmediator_salary")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseMediatorSalary extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）薪酬核算类型
     */
    @ApiModelProperty(value = "（来自于字典表）薪酬核算类型")
    @Column(name = "s_type")
    private String sType;

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
    private UserExtend caseSalary;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsType() {
        return sType;
    }

    public CaseMediatorSalary sType(String sType) {
        this.sType = sType;
        return this;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getRemarks() {
        return remarks;
    }

    public CaseMediatorSalary remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getBonusTotal() {
        return bonusTotal;
    }

    public CaseMediatorSalary bonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
        return this;
    }

    public void setBonusTotal(Long bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public Long getDifficultyLevel() {
        return difficultyLevel;
    }

    public CaseMediatorSalary difficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        return this;
    }

    public void setDifficultyLevel(Long difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevelName() {
        return difficultyLevelName;
    }

    public CaseMediatorSalary difficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
        return this;
    }

    public void setDifficultyLevelName(String difficultyLevelName) {
        this.difficultyLevelName = difficultyLevelName;
    }

    public Long getOtherBonusValue() {
        return otherBonusValue;
    }

    public CaseMediatorSalary otherBonusValue(Long otherBonusValue) {
        this.otherBonusValue = otherBonusValue;
        return this;
    }

    public void setOtherBonusValue(Long otherBonusValue) {
        this.otherBonusValue = otherBonusValue;
    }

    public String getOtherBonusContent() {
        return otherBonusContent;
    }

    public CaseMediatorSalary otherBonusContent(String otherBonusContent) {
        this.otherBonusContent = otherBonusContent;
        return this;
    }

    public void setOtherBonusContent(String otherBonusContent) {
        this.otherBonusContent = otherBonusContent;
    }

    public UserExtend getCaseSalary() {
        return caseSalary;
    }

    public CaseMediatorSalary caseSalary(UserExtend userExtend) {
        this.caseSalary = userExtend;
        return this;
    }

    public void setCaseSalary(UserExtend userExtend) {
        this.caseSalary = userExtend;
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
        CaseMediatorSalary caseMediatorSalary = (CaseMediatorSalary) o;
        if (caseMediatorSalary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseMediatorSalary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseMediatorSalary{" +
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
