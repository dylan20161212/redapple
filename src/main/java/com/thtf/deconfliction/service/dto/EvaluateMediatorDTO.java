package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EvaluateMediator entity.
 */
public class EvaluateMediatorDTO implements Serializable {

    private Long id;

    private String evaluateType;

    private String evaluateName;

    private String appraise;

    private ZonedDateTime evaluatDate;

    private String mediatorName;

    private String mediatorGender;

    private Long mediatorAge;

    private String mediatorDuty;

    private String mPoliticsStatus;

    private String mOrgName;

    private Double eScore;

    private String mLevel;

    private String mEgroupOpinion;

    private String mEgroupOpinionEr;

    private ZonedDateTime mEgroupOpinionDt;

    private String mEofficeOpinion;

    private String mEofficeOpinionEr;

    private ZonedDateTime mEofficeOpinionDt;

    private String remarks;

    private Set<EvaluateRuleDTO> rules = new HashSet<>();

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

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getMediatorGender() {
        return mediatorGender;
    }

    public void setMediatorGender(String mediatorGender) {
        this.mediatorGender = mediatorGender;
    }

    public Long getMediatorAge() {
        return mediatorAge;
    }

    public void setMediatorAge(Long mediatorAge) {
        this.mediatorAge = mediatorAge;
    }

    public String getMediatorDuty() {
        return mediatorDuty;
    }

    public void setMediatorDuty(String mediatorDuty) {
        this.mediatorDuty = mediatorDuty;
    }

    public String getmPoliticsStatus() {
        return mPoliticsStatus;
    }

    public void setmPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
    }

    public String getmOrgName() {
        return mOrgName;
    }

    public void setmOrgName(String mOrgName) {
        this.mOrgName = mOrgName;
    }

    public Double geteScore() {
        return eScore;
    }

    public void seteScore(Double eScore) {
        this.eScore = eScore;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmEgroupOpinion() {
        return mEgroupOpinion;
    }

    public void setmEgroupOpinion(String mEgroupOpinion) {
        this.mEgroupOpinion = mEgroupOpinion;
    }

    public String getmEgroupOpinionEr() {
        return mEgroupOpinionEr;
    }

    public void setmEgroupOpinionEr(String mEgroupOpinionEr) {
        this.mEgroupOpinionEr = mEgroupOpinionEr;
    }

    public ZonedDateTime getmEgroupOpinionDt() {
        return mEgroupOpinionDt;
    }

    public void setmEgroupOpinionDt(ZonedDateTime mEgroupOpinionDt) {
        this.mEgroupOpinionDt = mEgroupOpinionDt;
    }

    public String getmEofficeOpinion() {
        return mEofficeOpinion;
    }

    public void setmEofficeOpinion(String mEofficeOpinion) {
        this.mEofficeOpinion = mEofficeOpinion;
    }

    public String getmEofficeOpinionEr() {
        return mEofficeOpinionEr;
    }

    public void setmEofficeOpinionEr(String mEofficeOpinionEr) {
        this.mEofficeOpinionEr = mEofficeOpinionEr;
    }

    public ZonedDateTime getmEofficeOpinionDt() {
        return mEofficeOpinionDt;
    }

    public void setmEofficeOpinionDt(ZonedDateTime mEofficeOpinionDt) {
        this.mEofficeOpinionDt = mEofficeOpinionDt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<EvaluateRuleDTO> getRules() {
        return rules;
    }

    public void setRules(Set<EvaluateRuleDTO> evaluateRules) {
        this.rules = evaluateRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluateMediatorDTO evaluateMediatorDTO = (EvaluateMediatorDTO) o;
        if(evaluateMediatorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateMediatorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateMediatorDTO{" +
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
