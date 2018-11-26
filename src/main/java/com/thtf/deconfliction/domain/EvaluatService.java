package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * A EvaluatService.
 */
@Entity
@Table(name = "de_evaluate_service")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluatService extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 满意,不满意评价
     */
    @ApiModelProperty(value = "满意,不满意评价")
    @Column(name = "m_evaluat")
    private String mEvaluat;

    /**
     * 打分
     */
    @ApiModelProperty(value = "打分")
    @Column(name = "score")
    private Double score;

    /**
     * 评价
     */
    @ApiModelProperty(value = "评价")
    @Column(name = "appraise")
    private String appraise;

    /**
     * 案件id
     */
    @ApiModelProperty(value = "案件id")
    @Column(name = "case_id")
    private Long caseId;

    /**
     * 工作人员
     */
    @ApiModelProperty(value = "工作人员")
    @Column(name = "staff_id")
    private Long staffId;

    /**
     * 公众用户
     */
    @ApiModelProperty(value = "公众用户")
    @Column(name = "user_id")
    private Long userId;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @Column(name = "evaluat_date")
    private ZonedDateTime evaluatDate;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private ConflictCase conflictCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public EvaluatService mEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
        return this;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getScore() {
        return score;
    }

    public EvaluatService score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAppraise() {
        return appraise;
    }

    public EvaluatService appraise(String appraise) {
        this.appraise = appraise;
        return this;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Long getCaseId() {
        return caseId;
    }

    public EvaluatService caseId(Long caseId) {
        this.caseId = caseId;
        return this;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public EvaluatService staffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getUserId() {
        return userId;
    }

    public EvaluatService userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public EvaluatService evaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
        return this;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public ConflictCase getConflictCase() {
        return conflictCase;
    }

    public EvaluatService conflictCase(ConflictCase conflictCase) {
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
        EvaluatService evaluatService = (EvaluatService) o;
        if (evaluatService.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluatService.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluatService{" +
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
