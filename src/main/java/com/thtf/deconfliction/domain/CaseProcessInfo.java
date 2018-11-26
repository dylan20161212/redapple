package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.Instant;
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
 * 过程处理新信息
 */
@ApiModel(description = "过程处理新信息")
@Entity
@Table(name = "de_case_process_info")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseProcessInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）调解类型（调查，调解，回访，专家申请审核，回复审核，督办，延期申请，延期审核）
     */
    @ApiModelProperty(value = "（来自于字典表）调解类型（调查，调解，回访，专家申请审核，回复审核，督办，延期申请，延期审核）")
    @Column(name = "m_type")
    private String mType;

    /**
     * 调解员,调查人
     */
    @ApiModelProperty(value = "调解员,调查人")
    @Column(name = "m_person_id")
    private Long mPersonId;

    /**
     * 调解员,调查人
     */
    @ApiModelProperty(value = "调解员,调查人")
    @Column(name = "m_person_name")
    private String mPersonName;

    /**
     * 被调查人当事人一方
     */
    @ApiModelProperty(value = "被调查人当事人一方")
    @Column(name = "respondents")
    private String respondents;

    /**
     * 参加人如家属
     */
    @ApiModelProperty(value = "参加人如家属")
    @Column(name = "participant")
    private String participant;

    /**
     * 记录人做笔录的人
     */
    @ApiModelProperty(value = "记录人做笔录的人")
    @Column(name = "recorder")
    private String recorder;

    /**
     * 调解记录
     */
    @ApiModelProperty(value = "调解记录")
    @Column(name = "m_record_content")
    private String mRecordContent;

    /**
     * 调解结果或处理意见
     */
    @ApiModelProperty(value = "调解结果或处理意见")
    @Column(name = "m_result_content")
    private String mResultContent;
    
    /**
     * 调解结果或处理意见
     */
    @ApiModelProperty(value = "详细信息，内容为单一的json对象")
    @Column(name = "detail_info")
    private String detailInfo;

    /**
     * 开始调解时间
     */
    @ApiModelProperty(value = "开始调解时间")
    @Column(name = "begin_date_time")
    private Instant beginDateTime;

    /**
     * 结束调解时间
     */
    @ApiModelProperty(value = "结束调解时间")
    @Column(name = "end_date_time")
    private Instant endDateTime;

    /**
     * 调解地点
     */
    @ApiModelProperty(value = "调解地点")
    @Column(name = "m_address")
    private String mAddress;

    /**
     * 满意不满意评价
     */
    @ApiModelProperty(value = "满意不满意评价")
    @Column(name = "m_evaluat")
    private String mEvaluat;

    /**
     * 本次处理得分
     */
    @ApiModelProperty(value = "本次处理得分")
    @Column(name = "m_score")
    private Double mScore;

    /**
     * 调解附件
     */
    @ApiModelProperty(value = "调解附件")
    @Column(name = "m_files")
    private String mFiles;
    
    /**
     * 评价
     */
    @ApiModelProperty(value = "评价")
    @Column(name = "appraise")
    private String appraise;

    /**
     * 评分时间
     */
    @ApiModelProperty(value = "评分时间")
    @Column(name = "evaluat_date_time")
    private Instant evaluatDateTime;
    
    /**
     * 专家专长key
     */
    @ApiModelProperty(value = "专家专长")
    @Column(name = "speciality_key")
    private String specialityKey;
    
    /**
     * 专家专长value
     */
    @ApiModelProperty(value = "专家专长")
    @Column(name = "speciality_value")
    private String specialityValue;
    
    /**
     * 延期至时间
     */
    @ApiModelProperty(value = "延期至时间")
    @Column(name = "delay_date_time")
    private Instant delayDateTime;

    @ManyToOne
    private ConflictCase conflictCase;

    @ManyToOne
    private UserExtend personalInfo;
    
    
    
    

    public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmType() {
        return mType;
    }

    public CaseProcessInfo mType(String mType) {
        this.mType = mType;
        return this;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Long getmPersonId() {
        return mPersonId;
    }

    public CaseProcessInfo mPersonId(Long mPersonId) {
        this.mPersonId = mPersonId;
        return this;
    }

    public void setmPersonId(Long mPersonId) {
        this.mPersonId = mPersonId;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public CaseProcessInfo mPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
        return this;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public String getRespondents() {
        return respondents;
    }

    public CaseProcessInfo respondents(String respondents) {
        this.respondents = respondents;
        return this;
    }

    public void setRespondents(String respondents) {
        this.respondents = respondents;
    }

    public String getParticipant() {
        return participant;
    }

    public CaseProcessInfo participant(String participant) {
        this.participant = participant;
        return this;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getRecorder() {
        return recorder;
    }

    public CaseProcessInfo recorder(String recorder) {
        this.recorder = recorder;
        return this;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getmRecordContent() {
        return mRecordContent;
    }

    public CaseProcessInfo mRecordContent(String mRecordContent) {
        this.mRecordContent = mRecordContent;
        return this;
    }

    public void setmRecordContent(String mRecordContent) {
        this.mRecordContent = mRecordContent;
    }

    public String getmResultContent() {
        return mResultContent;
    }

    public CaseProcessInfo mResultContent(String mResultContent) {
        this.mResultContent = mResultContent;
        return this;
    }

    public void setmResultContent(String mResultContent) {
        this.mResultContent = mResultContent;
    }

    public Instant getBeginDateTime() {
        return beginDateTime;
    }

    public CaseProcessInfo beginDateTime(Instant beginDateTime) {
        this.beginDateTime = beginDateTime;
        return this;
    }

    public void setBeginDateTime(Instant beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public CaseProcessInfo endDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getmAddress() {
        return mAddress;
    }

    public CaseProcessInfo mAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public CaseProcessInfo mEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
        return this;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getmScore() {
        return mScore;
    }

    public CaseProcessInfo mScore(Double mScore) {
        this.mScore = mScore;
        return this;
    }

    public void setmScore(Double mScore) {
        this.mScore = mScore;
    }

    public String getmFiles() {
		return mFiles;
	}
    
    public CaseProcessInfo mFiles(String mFiles) {
    	this.mFiles = mFiles;
    	return this;
    }

	public void setmFiles(String mFiles) {
		this.mFiles = mFiles;
	}

	public String getAppraise() {
        return appraise;
    }

    public CaseProcessInfo appraise(String appraise) {
        this.appraise = appraise;
        return this;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Instant getEvaluatDateTime() {
        return evaluatDateTime;
    }

    public CaseProcessInfo evaluatDateTime(Instant evaluatDateTime) {
        this.evaluatDateTime = evaluatDateTime;
        return this;
    }

    public void setEvaluatDateTime(Instant evaluatDateTime) {
        this.evaluatDateTime = evaluatDateTime;
    }

    public ConflictCase getConflictCase() {
        return conflictCase;
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

	public CaseProcessInfo conflictCase(ConflictCase conflictCase) {
        this.conflictCase = conflictCase;
        return this;
    }

    public void setConflictCase(ConflictCase conflictCase) {
        this.conflictCase = conflictCase;
    }

    public UserExtend getPersonalInfo() {
        return personalInfo;
    }

    public CaseProcessInfo personalInfo(UserExtend userExtend) {
        this.personalInfo = userExtend;
        return this;
    }

    public void setPersonalInfo(UserExtend userExtend) {
        this.personalInfo = userExtend;
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
        CaseProcessInfo caseProcessInfo = (CaseProcessInfo) o;
        if (caseProcessInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseProcessInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseProcessInfo{" +
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
