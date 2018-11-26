package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
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
 * 人民调解员考核表
 */
@ApiModel(description = "人民调解员考核表")
@Entity
@Table(name = "de_evaluate_mediator")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateMediator extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）考核表类型，模板，真实考核
     */
    @ApiModelProperty(value = "（来自于字典表）考核表类型，模板，真实考核")
    @Column(name = "evaluate_type")
    private String evaluateType;

    /**
     * 考核表名称
     */
    @ApiModelProperty(value = "考核表名称")
    @Column(name = "evaluate_name")
    private String evaluateName;

    /**
     * 评价
     */
    @ApiModelProperty(value = "评价")
    @Column(name = "appraise")
    private String appraise;

    /**
     * 考核时间
     */
    @ApiModelProperty(value = "考核时间")
    @Column(name = "evaluat_date")
    private ZonedDateTime evaluatDate;

    /**
     * 调解员姓名
     */
    @ApiModelProperty(value = "调解员姓名")
    @Column(name = "mediator_name")
    private String mediatorName;

    /**
     * 调解员性别
     */
    @ApiModelProperty(value = "调解员性别")
    @Column(name = "mediator_gender")
    private String mediatorGender;

    /**
     * 调解员年龄
     */
    @ApiModelProperty(value = "调解员年龄")
    @Column(name = "mediator_age")
    private Long mediatorAge;

    /**
     * 调解员职务
     */
    @ApiModelProperty(value = "调解员职务")
    @Column(name = "mediator_duty")
    private String mediatorDuty;

    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")
    @Column(name = "m_politics_status")
    private String mPoliticsStatus;

    /**
     * 所在调委会名称
     */
    @ApiModelProperty(value = "所在调委会名称")
    @Column(name = "m_org_name")
    private String mOrgName;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")
    @Column(name = "e_score")
    private Double eScore;

    /**
     * 等次
     */
    @ApiModelProperty(value = "等次")
    @Column(name = "m_level")
    private String mLevel;

    /**
     * 考核小组意见
     */
    @ApiModelProperty(value = "考核小组意见")
    @Column(name = "m_egroup_opinion")
    private String mEgroupOpinion;

    /**
     * 考核小组意见负责人
     */
    @ApiModelProperty(value = "考核小组意见负责人")
    @Column(name = "m_egroup_opinion_er")
    private String mEgroupOpinionEr;

    /**
     * 考核小组意见负责人时间
     */
    @ApiModelProperty(value = "考核小组意见负责人时间")
    @Column(name = "m_egroup_opinion_dt")
    private ZonedDateTime mEgroupOpinionDt;

    /**
     * 考核机关意见
     */
    @ApiModelProperty(value = "考核机关意见")
    @Column(name = "m_eoffice_opinion")
    private String mEofficeOpinion;

    /**
     * 考核机关意见负责人
     */
    @ApiModelProperty(value = "考核机关意见负责人")
    @Column(name = "m_eoffice_opinion_er")
    private String mEofficeOpinionEr;

    /**
     * 考核机关意见负责人时间
     */
    @ApiModelProperty(value = "考核机关意见负责人时间")
    @Column(name = "m_eoffice_opinion_dt")
    private ZonedDateTime mEofficeOpinionDt;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "evaluate_mediator_rules",
               joinColumns = @JoinColumn(name="evaluate_mediators_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="rules_id", referencedColumnName="id"))
    private Set<EvaluateRule> rules = new HashSet<>();

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

    public EvaluateMediator evaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
        return this;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public EvaluateMediator evaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
        return this;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public String getAppraise() {
        return appraise;
    }

    public EvaluateMediator appraise(String appraise) {
        this.appraise = appraise;
        return this;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public EvaluateMediator evaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
        return this;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public EvaluateMediator mediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
        return this;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getMediatorGender() {
        return mediatorGender;
    }

    public EvaluateMediator mediatorGender(String mediatorGender) {
        this.mediatorGender = mediatorGender;
        return this;
    }

    public void setMediatorGender(String mediatorGender) {
        this.mediatorGender = mediatorGender;
    }

    public Long getMediatorAge() {
        return mediatorAge;
    }

    public EvaluateMediator mediatorAge(Long mediatorAge) {
        this.mediatorAge = mediatorAge;
        return this;
    }

    public void setMediatorAge(Long mediatorAge) {
        this.mediatorAge = mediatorAge;
    }

    public String getMediatorDuty() {
        return mediatorDuty;
    }

    public EvaluateMediator mediatorDuty(String mediatorDuty) {
        this.mediatorDuty = mediatorDuty;
        return this;
    }

    public void setMediatorDuty(String mediatorDuty) {
        this.mediatorDuty = mediatorDuty;
    }

    public String getmPoliticsStatus() {
        return mPoliticsStatus;
    }

    public EvaluateMediator mPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
        return this;
    }

    public void setmPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
    }

    public String getmOrgName() {
        return mOrgName;
    }

    public EvaluateMediator mOrgName(String mOrgName) {
        this.mOrgName = mOrgName;
        return this;
    }

    public void setmOrgName(String mOrgName) {
        this.mOrgName = mOrgName;
    }

    public Double geteScore() {
        return eScore;
    }

    public EvaluateMediator eScore(Double eScore) {
        this.eScore = eScore;
        return this;
    }

    public void seteScore(Double eScore) {
        this.eScore = eScore;
    }

    public String getmLevel() {
        return mLevel;
    }

    public EvaluateMediator mLevel(String mLevel) {
        this.mLevel = mLevel;
        return this;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmEgroupOpinion() {
        return mEgroupOpinion;
    }

    public EvaluateMediator mEgroupOpinion(String mEgroupOpinion) {
        this.mEgroupOpinion = mEgroupOpinion;
        return this;
    }

    public void setmEgroupOpinion(String mEgroupOpinion) {
        this.mEgroupOpinion = mEgroupOpinion;
    }

    public String getmEgroupOpinionEr() {
        return mEgroupOpinionEr;
    }

    public EvaluateMediator mEgroupOpinionEr(String mEgroupOpinionEr) {
        this.mEgroupOpinionEr = mEgroupOpinionEr;
        return this;
    }

    public void setmEgroupOpinionEr(String mEgroupOpinionEr) {
        this.mEgroupOpinionEr = mEgroupOpinionEr;
    }

    public ZonedDateTime getmEgroupOpinionDt() {
        return mEgroupOpinionDt;
    }

    public EvaluateMediator mEgroupOpinionDt(ZonedDateTime mEgroupOpinionDt) {
        this.mEgroupOpinionDt = mEgroupOpinionDt;
        return this;
    }

    public void setmEgroupOpinionDt(ZonedDateTime mEgroupOpinionDt) {
        this.mEgroupOpinionDt = mEgroupOpinionDt;
    }

    public String getmEofficeOpinion() {
        return mEofficeOpinion;
    }

    public EvaluateMediator mEofficeOpinion(String mEofficeOpinion) {
        this.mEofficeOpinion = mEofficeOpinion;
        return this;
    }

    public void setmEofficeOpinion(String mEofficeOpinion) {
        this.mEofficeOpinion = mEofficeOpinion;
    }

    public String getmEofficeOpinionEr() {
        return mEofficeOpinionEr;
    }

    public EvaluateMediator mEofficeOpinionEr(String mEofficeOpinionEr) {
        this.mEofficeOpinionEr = mEofficeOpinionEr;
        return this;
    }

    public void setmEofficeOpinionEr(String mEofficeOpinionEr) {
        this.mEofficeOpinionEr = mEofficeOpinionEr;
    }

    public ZonedDateTime getmEofficeOpinionDt() {
        return mEofficeOpinionDt;
    }

    public EvaluateMediator mEofficeOpinionDt(ZonedDateTime mEofficeOpinionDt) {
        this.mEofficeOpinionDt = mEofficeOpinionDt;
        return this;
    }

    public void setmEofficeOpinionDt(ZonedDateTime mEofficeOpinionDt) {
        this.mEofficeOpinionDt = mEofficeOpinionDt;
    }

    public String getRemarks() {
        return remarks;
    }

    public EvaluateMediator remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<EvaluateRule> getRules() {
        return rules;
    }

    public EvaluateMediator rules(Set<EvaluateRule> evaluateRules) {
        this.rules = evaluateRules;
        return this;
    }

    public EvaluateMediator addRules(EvaluateRule evaluateRule) {
        this.rules.add(evaluateRule);
        return this;
    }

    public EvaluateMediator removeRules(EvaluateRule evaluateRule) {
        this.rules.remove(evaluateRule);
        return this;
    }

    public void setRules(Set<EvaluateRule> evaluateRules) {
        this.rules = evaluateRules;
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
        EvaluateMediator evaluateMediator = (EvaluateMediator) o;
        if (evaluateMediator.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateMediator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateMediator{" +
            "id=" + getId() +
            ", evaluateType='" + getEvaluateType() + "'" +
            ", evaluateName='" + getEvaluateName() + "'" +
            ", appraise='" + getAppraise() + "'" +
            ", evaluatDate='" + getEvaluatDate() + "'" +
            ", mediatorName='" + getMediatorName() + "'" +
            ", mediatorGender='" + getMediatorGender() + "'" +
            ", mediatorAge=" + getMediatorAge() +
            ", mediatorDuty='" + getMediatorDuty() + "'" +
            ", mPoliticsStatus='" + getmPoliticsStatus() + "'" +
            ", mOrgName='" + getmOrgName() + "'" +
            ", eScore=" + geteScore() +
            ", mLevel='" + getmLevel() + "'" +
            ", mEgroupOpinion='" + getmEgroupOpinion() + "'" +
            ", mEgroupOpinionEr='" + getmEgroupOpinionEr() + "'" +
            ", mEgroupOpinionDt='" + getmEgroupOpinionDt() + "'" +
            ", mEofficeOpinion='" + getmEofficeOpinion() + "'" +
            ", mEofficeOpinionEr='" + getmEofficeOpinionEr() + "'" +
            ", mEofficeOpinionDt='" + getmEofficeOpinionDt() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
