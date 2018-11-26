package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the ConflictCase entity.
 */
public class ConflictCaseDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

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

    private String remarks;

    private String cStatus;

    private String dealOpinion;

    private Long upperId;
    
    private String disputeType;
    
    private String isDifficult;
    
    private String difficultLevel;
    
    private String isQuick;
    
    private String casePrediction;
    
    private Set<RelevantPersonDTO> relevantPerson = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
	
	public Set<RelevantPersonDTO> getRelevantPerson() {
		return relevantPerson;
	}

	public void setRelevantPerson(Set<RelevantPersonDTO> relevantPerson) {
		this.relevantPerson = relevantPerson;
	}

	
	public ConflictCaseDTO() {
	}

	public ConflictCaseDTO(Long id, String cType, ZonedDateTime occurrenceDate, ZonedDateTime applyDate,
			ZonedDateTime acceptDate, ZonedDateTime endDate, String cName, String cDescription,
			String feedbackType, String feedbackValue, String infoSource, String myAppeal,
			String occurProvince, String ocityState, String oareaCounty, String ocommunityStreet,
			Long oareaId, Long mediateOrgId, String mediateOrgName,String dutyofficerName,
			String recorderName, String remarks, String cStatus,String dealOpinion, Long upperId,
			String disputeType, String isDifficult, String difficultLevel,
			String isQuick, String casePrediction, Set<RelevantPersonDTO> relevantPerson) {
		super();
		this.id = id;
		this.cType = cType;
		this.occurrenceDate = occurrenceDate;
		this.applyDate = applyDate;
		this.acceptDate = acceptDate;
		this.endDate = endDate;
		this.cName = cName;
		this.cDescription = cDescription;
		this.feedbackType = feedbackType;
		this.feedbackValue = feedbackValue;
		this.infoSource = infoSource;
		this.myAppeal = myAppeal;
		this.occurProvince = occurProvince;
		this.ocityState = ocityState;
		this.oareaCounty = oareaCounty;
		this.ocommunityStreet = ocommunityStreet;
		this.oareaId = oareaId;
		this.mediateOrgId = mediateOrgId;
		this.mediateOrgName = mediateOrgName;
		this.dutyofficerName = dutyofficerName;
		this.recorderName = recorderName;
		this.remarks = remarks;
		this.cStatus = cStatus;
		this.dealOpinion = dealOpinion;
		this.upperId = upperId;
		this.disputeType = disputeType;
		this.isDifficult = isDifficult;
		this.difficultLevel = difficultLevel;
		this.isQuick = isQuick;
		this.casePrediction = casePrediction;
		this.relevantPerson = relevantPerson;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConflictCaseDTO conflictCaseDTO = (ConflictCaseDTO) o;
        if(conflictCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conflictCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConflictCaseDTO{" +
            "id=" + getId() +
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
            ", remarks='" + getRemarks() + "'" +
            ", cStatus='" + getcStatus() + "'" +
            ", dealOpinion='" + getDealOpinion() + "'" +
            ", upperId=" + getUpperId() +
            "}";
    }
}
