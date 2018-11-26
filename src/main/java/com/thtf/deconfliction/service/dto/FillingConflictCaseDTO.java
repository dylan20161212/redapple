package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the FillingConflictCase entity.
 */
public class FillingConflictCaseDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long oFilingCaseId;

    private String cType;

    private ZonedDateTime occurrenceDate;

    private ZonedDateTime applyDate;

    private ZonedDateTime acceptDate;

    private ZonedDateTime endDate;

    private String cName;

    private String cDescription;

    private String feedbackType;

    private String feedbackValue;

    private String infoSource;

    private String myAppeal;

    private String occurProvince;

    private String ocityState;

    private String oareaCounty;

    private String ocommunityStreet;

    private Long oareaId;

    private Long mediateOrgId;

    private String mediateOrgName;

    private String dutyofficerName;

    private String recorderName;

    private Long mediatorId;

    private String mediatorName;

    private String remarks;

    private String cStatus;

    private String dealOpinion;

    private Long upperId;

    private Long upperName;

    private String mEvaluat;

    private Double score;

    private String appraise;

    private String staffName;

    private Long userLogin;

    private Long userName;

    private ZonedDateTime evaluatDate;

    private ZonedDateTime filingDate;

    private String filingStaff;
    
    private String disputeType;
    
    private String isDifficult;
    
    private String difficultLevel;
    
    private String isQuick;
    
    private String casePrediction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoFilingCaseId() {
        return oFilingCaseId;
    }

    public void setoFilingCaseId(Long oFilingCaseId) {
        this.oFilingCaseId = oFilingCaseId;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public ZonedDateTime getOccurrenceDate() {
        return occurrenceDate;
    }

    public void setOccurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public ZonedDateTime getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(ZonedDateTime applyDate) {
        this.applyDate = applyDate;
    }

    public ZonedDateTime getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(ZonedDateTime acceptDate) {
        this.acceptDate = acceptDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackValue() {
        return feedbackValue;
    }

    public void setFeedbackValue(String feedbackValue) {
        this.feedbackValue = feedbackValue;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getMyAppeal() {
        return myAppeal;
    }

    public void setMyAppeal(String myAppeal) {
        this.myAppeal = myAppeal;
    }

    public String getOccurProvince() {
        return occurProvince;
    }

    public void setOccurProvince(String occurProvince) {
        this.occurProvince = occurProvince;
    }

    public String getOcityState() {
        return ocityState;
    }

    public void setOcityState(String ocityState) {
        this.ocityState = ocityState;
    }

    public String getOareaCounty() {
        return oareaCounty;
    }

    public void setOareaCounty(String oareaCounty) {
        this.oareaCounty = oareaCounty;
    }

    public String getOcommunityStreet() {
        return ocommunityStreet;
    }

    public void setOcommunityStreet(String ocommunityStreet) {
        this.ocommunityStreet = ocommunityStreet;
    }

    public Long getOareaId() {
        return oareaId;
    }

    public void setOareaId(Long oareaId) {
        this.oareaId = oareaId;
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

    public String getDutyofficerName() {
        return dutyofficerName;
    }

    public void setDutyofficerName(String dutyofficerName) {
        this.dutyofficerName = dutyofficerName;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
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

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getDealOpinion() {
        return dealOpinion;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public Long getUpperId() {
        return upperId;
    }

    public void setUpperId(Long upperId) {
        this.upperId = upperId;
    }

    public Long getUpperName() {
        return upperName;
    }

    public void setUpperName(Long upperName) {
        this.upperName = upperName;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(Long userLogin) {
        this.userLogin = userLogin;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public ZonedDateTime getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingStaff() {
        return filingStaff;
    }

    public void setFilingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
    }

    public String getDisputeType() {
		return disputeType;
	}

	public void setDisputeType(String disputeType) {
		this.disputeType = disputeType;
	}

	public String getIsDifficult() {
		return isDifficult;
	}

	public void setIsDifficult(String isDifficult) {
		this.isDifficult = isDifficult;
	}

	public String getDifficultLevel() {
		return difficultLevel;
	}

	public void setDifficultLevel(String difficultLevel) {
		this.difficultLevel = difficultLevel;
	}

	public String getIsQuick() {
		return isQuick;
	}

	public void setIsQuick(String isQuick) {
		this.isQuick = isQuick;
	}

	public String getCasePrediction() {
		return casePrediction;
	}

	public void setCasePrediction(String casePrediction) {
		this.casePrediction = casePrediction;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FillingConflictCaseDTO fillingConflictCaseDTO = (FillingConflictCaseDTO) o;
        if(fillingConflictCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingConflictCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingConflictCaseDTO{" +
            "id=" + getId() +
            ", oFilingCaseId=" + getoFilingCaseId() +
            ", cType='" + getcType() + "'" +
            ", occurrenceDate='" + getOccurrenceDate() + "'" +
            ", applyDate='" + getApplyDate() + "'" +
            ", acceptDate='" + getAcceptDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", cName='" + getcName() + "'" +
            ", cDescription='" + getcDescription() + "'" +
            ", feedbackType='" + getFeedbackType() + "'" +
            ", feedbackValue='" + getFeedbackValue() + "'" +
            ", infoSource='" + getInfoSource() + "'" +
            ", myAppeal='" + getMyAppeal() + "'" +
            ", occurProvince='" + getOccurProvince() + "'" +
            ", ocityState='" + getOcityState() + "'" +
            ", oareaCounty='" + getOareaCounty() + "'" +
            ", ocommunityStreet='" + getOcommunityStreet() + "'" +
            ", oareaId=" + getOareaId() +
            ", mediateOrgId=" + getMediateOrgId() +
            ", mediateOrgName='" + getMediateOrgName() + "'" +
            ", dutyofficerName='" + getDutyofficerName() + "'" +
            ", recorderName='" + getRecorderName() + "'" +
            ", mediatorId=" + getMediatorId() +
            ", mediatorName='" + getMediatorName() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", cStatus='" + getcStatus() + "'" +
            ", dealOpinion='" + getDealOpinion() + "'" +
            ", upperId=" + getUpperId() +
            ", upperName=" + getUpperName() +
            ", mEvaluat='" + getmEvaluat() + "'" +
            ", score=" + getScore() +
            ", appraise='" + getAppraise() + "'" +
            ", staffName='" + getStaffName() + "'" +
            ", userLogin=" + getUserLogin() +
            ", userName=" + getUserName() +
            ", evaluatDate='" + getEvaluatDate() + "'" +
            ", filingDate='" + getFilingDate() + "'" +
            ", filingStaff='" + getFilingStaff() + "'" +
            "}";
    }
}
