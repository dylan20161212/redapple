package com.thtf.deconfliction.service.dto;


import java.time.Instant;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FillingCaseProcessInfo entity.
 */
public class FillingCaseProcessInfoDTO implements Serializable {

    private Long id;

    private Long oProcessId;

    private String mType;

    private Long mPersonId;

    private String mPersonName;

    private String respondents;

    private String participant;

    private String recorder;

    private String mRecordContent;

    private String mResultContent;

    private Instant beginDateTime;

    private Instant endDateTime;

    private String mAddress;

    private String mEvaluat;

    private Double mScore;

    private String appraise;

    private Instant evaluatDateTime;

    private ZonedDateTime filingDate;

    private String filingStaff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoProcessId() {
        return oProcessId;
    }

    public void setoProcessId(Long oProcessId) {
        this.oProcessId = oProcessId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FillingCaseProcessInfoDTO fillingCaseProcessInfoDTO = (FillingCaseProcessInfoDTO) o;
        if(fillingCaseProcessInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingCaseProcessInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingCaseProcessInfoDTO{" +
            "id=" + getId() +
            ", oProcessId=" + getoProcessId() +
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
            ", filingDate='" + getFilingDate() + "'" +
            ", filingStaff='" + getFilingStaff() + "'" +
            "}";
    }
}
