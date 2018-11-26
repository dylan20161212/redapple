package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the CaseProcessInfo entity.
 */
public class CaseProcessInfoDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String mType;

    private Long mPersonId;

    private String mPersonName;

    private String respondents;

    private String participant;

    private String recorder;

    private String mRecordContent;

    private String mResultContent;
    
    private String detailInfo;

    private Instant beginDateTime;

    private Instant endDateTime;

    private String mAddress;

    private String mEvaluat;

    private Double mScore;
    
    private String mFiles;

    private String appraise;

    private Instant evaluatDateTime;

    private String specialityKey;
    
    private String specialityValue;
    
    private Instant delayDateTime;
    
    private Long conflictCaseId;

    private Long personalInfoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Long getmPersonId() {
        return mPersonId;
    }

    public void setmPersonId(Long mPersonId) {
        this.mPersonId = mPersonId;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public String getRespondents() {
        return respondents;
    }

    public void setRespondents(String respondents) {
        this.respondents = respondents;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getmRecordContent() {
        return mRecordContent;
    }

    public void setmRecordContent(String mRecordContent) {
        this.mRecordContent = mRecordContent;
    }

    public String getmResultContent() {
        return mResultContent;
    }

    public void setmResultContent(String mResultContent) {
        this.mResultContent = mResultContent;
    }

    public Instant getBeginDateTime() {
        return beginDateTime;
    }

    public void setBeginDateTime(Instant beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getmScore() {
        return mScore;
    }

    public void setmScore(Double mScore) {
        this.mScore = mScore;
    }

    public String getmFiles() {
		return mFiles;
	}

	public void setmFiles(String mFiles) {
		this.mFiles = mFiles;
	}

	public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Instant getEvaluatDateTime() {
        return evaluatDateTime;
    }

    public void setEvaluatDateTime(Instant evaluatDateTime) {
        this.evaluatDateTime = evaluatDateTime;
    }

    public String getSpecialityKey() {
		return specialityKey;
	}

	public void setSpecialityKey(String specialityKey) {
		this.specialityKey = specialityKey;
	}

	public String getSpecialityValue() {
		return specialityValue;
	}

	public void setSpecialityValue(String specialityValue) {
		this.specialityValue = specialityValue;
	}

	public Instant getDelayDateTime() {
		return delayDateTime;
	}

	public void setDelayDateTime(Instant delayDateTime) {
		this.delayDateTime = delayDateTime;
	}

	public Long getConflictCaseId() {
        return conflictCaseId;
    }

    public void setConflictCaseId(Long conflictCaseId) {
        this.conflictCaseId = conflictCaseId;
    }

    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long userExtendId) {
        this.personalInfoId = userExtendId;
    }
    
    
    

    public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseProcessInfoDTO caseProcessInfoDTO = (CaseProcessInfoDTO) o;
        if(caseProcessInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseProcessInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseProcessInfoDTO{" +
            "id=" + getId() +
            ", mType='" + getmType() + "'" +
            ", mPersonId=" + getmPersonId() +
            ", mPersonName='" + getmPersonName() + "'" +
            ", respondents='" + getRespondents() + "'" +
            ", participant='" + getParticipant() + "'" +
            ", recorder='" + getRecorder() + "'" +
            ", mRecordContent='" + getmRecordContent() + "'" +
            ", mResultContent='" + getmResultContent() + "'" +
            ", beginDateTime='" + getBeginDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", mAddress='" + getmAddress() + "'" +
            ", mEvaluat='" + getmEvaluat() + "'" +
            ", mScore=" + getmScore() +
            ", appraise='" + getAppraise() + "'" +
            ", evaluatDateTime='" + getEvaluatDateTime() + "'" +
            "}";
    }
}
