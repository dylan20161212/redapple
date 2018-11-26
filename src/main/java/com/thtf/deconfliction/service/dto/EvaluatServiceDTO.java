package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the EvaluatService entity.
 */
public class EvaluatServiceDTO implements Serializable {

    private Long id;

    private String mEvaluat;

    private Double score;

    private String appraise;

    private Long caseId;

    private Long staffId;

    private Long userId;

    private ZonedDateTime evaluatDate;

    private Long conflictCaseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public Long getConflictCaseId() {
        return conflictCaseId;
    }

    public void setConflictCaseId(Long conflictCaseId) {
        this.conflictCaseId = conflictCaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvaluatServiceDTO evaluatServiceDTO = (EvaluatServiceDTO) o;
        if(evaluatServiceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluatServiceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluatServiceDTO{" +
            "id=" + getId() +
            ", mEvaluat='" + getmEvaluat() + "'" +
            ", score=" + getScore() +
            ", appraise='" + getAppraise() + "'" +
            ", caseId=" + getCaseId() +
            ", staffId=" + getStaffId() +
            ", userId=" + getUserId() +
            ", evaluatDate='" + getEvaluatDate() + "'" +
            "}";
    }
}
