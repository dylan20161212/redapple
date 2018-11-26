package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 过程处理新信息
 */
@ApiModel(description = "过程处理新信息")
@Entity
@Table(name = "filing_case_process_info")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FillingCaseProcessInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * original原来的ID
     */
    @ApiModelProperty(value = "original原来的ID")
    @Column(name = "o_process_id")
    private Long oProcessId;

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
     * 归档时间
     */
    @ApiModelProperty(value = "归档时间")
    @Column(name = "filing_date")
    private ZonedDateTime filingDate;

    /**
     * 归档人
     */
    @ApiModelProperty(value = "归档人")
    @Column(name = "filing_staff")
    private String filingStaff;
    
    /**
     * 归档案件ID
     */
    @ApiModelProperty(value = "归档案件ID")
    @Column(name = "filing_case_id")
    private Long filingCaseId;

    @ManyToMany(mappedBy = "filingProcesses")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FilingUserExtend> staff = new HashSet<>();

    @ManyToMany(mappedBy = "filingProcesses")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FilingRelevantPerson> relevantPeople = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoProcessId() {
        return oProcessId;
    }

    public FillingCaseProcessInfo oProcessId(Long oProcessId) {
        this.oProcessId = oProcessId;
        return this;
    }

    public void setoProcessId(Long oProcessId) {
        this.oProcessId = oProcessId;
    }

    public String getmType() {
        return mType;
    }

    public FillingCaseProcessInfo mType(String mType) {
        this.mType = mType;
        return this;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Long getmPersonId() {
        return mPersonId;
    }

    public FillingCaseProcessInfo mPersonId(Long mPersonId) {
        this.mPersonId = mPersonId;
        return this;
    }

    public void setmPersonId(Long mPersonId) {
        this.mPersonId = mPersonId;
    }

    public String getmPersonName() {
        return mPersonName;
    }

    public FillingCaseProcessInfo mPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
        return this;
    }

    public void setmPersonName(String mPersonName) {
        this.mPersonName = mPersonName;
    }

    public String getRespondents() {
        return respondents;
    }

    public FillingCaseProcessInfo respondents(String respondents) {
        this.respondents = respondents;
        return this;
    }

    public void setRespondents(String respondents) {
        this.respondents = respondents;
    }

    public String getParticipant() {
        return participant;
    }

    public FillingCaseProcessInfo participant(String participant) {
        this.participant = participant;
        return this;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getRecorder() {
        return recorder;
    }

    public FillingCaseProcessInfo recorder(String recorder) {
        this.recorder = recorder;
        return this;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getmRecordContent() {
        return mRecordContent;
    }

    public FillingCaseProcessInfo mRecordContent(String mRecordContent) {
        this.mRecordContent = mRecordContent;
        return this;
    }

    public void setmRecordContent(String mRecordContent) {
        this.mRecordContent = mRecordContent;
    }

    public String getmResultContent() {
        return mResultContent;
    }

    public FillingCaseProcessInfo mResultContent(String mResultContent) {
        this.mResultContent = mResultContent;
        return this;
    }

    public void setmResultContent(String mResultContent) {
        this.mResultContent = mResultContent;
    }

    public Instant getBeginDateTime() {
        return beginDateTime;
    }

    public FillingCaseProcessInfo beginDateTime(Instant beginDateTime) {
        this.beginDateTime = beginDateTime;
        return this;
    }

    public void setBeginDateTime(Instant beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    public Instant getEndDateTime() {
        return endDateTime;
    }

    public FillingCaseProcessInfo endDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public void setEndDateTime(Instant endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getmAddress() {
        return mAddress;
    }

    public FillingCaseProcessInfo mAddress(String mAddress) {
        this.mAddress = mAddress;
        return this;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public FillingCaseProcessInfo mEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
        return this;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getmScore() {
        return mScore;
    }

    public FillingCaseProcessInfo mScore(Double mScore) {
        this.mScore = mScore;
        return this;
    }

    public void setmScore(Double mScore) {
        this.mScore = mScore;
    }

    public String getAppraise() {
        return appraise;
    }

    public FillingCaseProcessInfo appraise(String appraise) {
        this.appraise = appraise;
        return this;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Instant getEvaluatDateTime() {
        return evaluatDateTime;
    }

    public FillingCaseProcessInfo evaluatDateTime(Instant evaluatDateTime) {
        this.evaluatDateTime = evaluatDateTime;
        return this;
    }

    public void setEvaluatDateTime(Instant evaluatDateTime) {
        this.evaluatDateTime = evaluatDateTime;
    }

    public ZonedDateTime getFilingDate() {
        return filingDate;
    }

    public FillingCaseProcessInfo filingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
        return this;
    }

    public void setFilingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public Long getFilingCaseId() {
        return filingCaseId;
    }

    public FillingCaseProcessInfo filingCaseId(Long filingCaseId) {
        this.filingCaseId = filingCaseId;
        return this;
    }

    public void setFilingCaseId(Long filingCaseId) {
        this.filingCaseId = filingCaseId;
    }

    public String getFilingStaff() {
        return filingStaff;
    }

    public FillingCaseProcessInfo filingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
        return this;
    }

    public void setFilingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
    }


    public Set<FilingUserExtend> getStaff() {
        return staff;
    }

    public FillingCaseProcessInfo staff(Set<FilingUserExtend> filingUserExtends) {
        this.staff = filingUserExtends;
        return this;
    }

    public FillingCaseProcessInfo addStaff(FilingUserExtend filingUserExtend) {
        this.staff.add(filingUserExtend);
        filingUserExtend.getFilingProcesses().add(this);
        return this;
    }

    public FillingCaseProcessInfo removeStaff(FilingUserExtend filingUserExtend) {
        this.staff.remove(filingUserExtend);
        filingUserExtend.getFilingProcesses().remove(this);
        return this;
    }

    public void setStaff(Set<FilingUserExtend> filingUserExtends) {
        this.staff = filingUserExtends;
    }

    public Set<FilingRelevantPerson> getRelevantPeople() {
        return relevantPeople;
    }

    public FillingCaseProcessInfo relevantPeople(Set<FilingRelevantPerson> filingRelevantPeople) {
        this.relevantPeople = filingRelevantPeople;
        return this;
    }

    public FillingCaseProcessInfo addRelevantPerson(FilingRelevantPerson filingRelevantPerson) {
        this.relevantPeople.add(filingRelevantPerson);
        filingRelevantPerson.getFilingProcesses().add(this);
        return this;
    }

    public FillingCaseProcessInfo removeRelevantPerson(FilingRelevantPerson filingRelevantPerson) {
        this.relevantPeople.remove(filingRelevantPerson);
        filingRelevantPerson.getFilingProcesses().remove(this);
        return this;
    }

    public void setRelevantPeople(Set<FilingRelevantPerson> filingRelevantPeople) {
        this.relevantPeople = filingRelevantPeople;
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
        FillingCaseProcessInfo fillingCaseProcessInfo = (FillingCaseProcessInfo) o;
        if (fillingCaseProcessInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingCaseProcessInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingCaseProcessInfo{" +
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
            ", filingCaseId='" + getFilingCaseId() + "'" +
            "}";
    }
}
