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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 案件归档
 */
@ApiModel(description = "案件归档")
@Entity
@Table(name = "filing_conflict_case")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FillingConflictCase extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * original原来的案件ID
     */
    @ApiModelProperty(value = "original原来的案件ID")
    @Column(name = "o_filing_case_id")
    private Long oFilingCaseId;

    /**
     * （来自于字典表）咨询，调解，评估
     */
    @ApiModelProperty(value = "（来自于字典表）咨询，调解，评估")
    @Column(name = "c_type")
    private String cType;

    /**
     * 案件发生时间
     */
    @ApiModelProperty(value = "案件发生时间")
    @Column(name = "occurrence_date")
    private ZonedDateTime occurrenceDate;

    /**
     * 案件申请时间
     */
    @ApiModelProperty(value = "案件申请时间")
    @Column(name = "apply_date")
    private ZonedDateTime applyDate;

    /**
     * 案件受理时间
     */
    @ApiModelProperty(value = "案件受理时间")
    @Column(name = "accept_date")
    private ZonedDateTime acceptDate;

    /**
     * 结案时间
     */
    @ApiModelProperty(value = "结案时间")
    @Column(name = "end_date")
    private ZonedDateTime endDate;

    /**
     * 案件名称
     */
    @ApiModelProperty(value = "案件名称")
    @Column(name = "c_name")
    private String cName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Column(name = "c_description")
    private String cDescription;

    /**
     * 反馈方式（电话、微信、人工智能等）
     */
    @ApiModelProperty(value = "反馈方式（电话、微信、人工智能等）")
    @Column(name = "feedback_type")
    private String feedbackType;

    /**
     * 反馈方式（电话、微信、人工智能等具体内容）
     */
    @ApiModelProperty(value = "反馈方式（电话、微信、人工智能等具体内容）")
    @Column(name = "feedback_value")
    private String feedbackValue;

    /**
     * 信息来源（直接申请、网上申请、法院转交、公安机关转交等）
     */
    @ApiModelProperty(value = "信息来源（直接申请、网上申请、法院转交、公安机关转交等）")
    @Column(name = "info_source")
    private String infoSource;

    /**
     * 我的诉求
     */
    @ApiModelProperty(value = "我的诉求")
    @Column(name = "my_appeal")
    private String myAppeal;

    /**
     * 省（案件发生）
     */
    @ApiModelProperty(value = "省（案件发生）")
    @Column(name = "occur_province")
    private String occurProvince;

    /**
     * 市州（案件发生）
     */
    @ApiModelProperty(value = "市州（案件发生）")
    @Column(name = "ocity_state")
    private String ocityState;

    /**
     * 区县（案件发生）
     */
    @ApiModelProperty(value = "区县（案件发生）")
    @Column(name = "oarea_county")
    private String oareaCounty;

    /**
     * 街道 （案件发生）
     */
    @ApiModelProperty(value = "街道 （案件发生）")
    @Column(name = "ocommunity_street")
    private String ocommunityStreet;

    /**
     * 发生地址areaId
     */
    @ApiModelProperty(value = "发生地址areaId")
    @Column(name = "oarea_id")
    private Long oareaId;

    /**
     * 调节机构Id
     */
    @ApiModelProperty(value = "调节机构Id")
    @Column(name = "mediate_org_id")
    private Long mediateOrgId;

    /**
     * 调节机构名称
     */
    @ApiModelProperty(value = "调节机构名称")
    @Column(name = "mediate_org_name")
    private String mediateOrgName;

    /**
     * 调节机构负责人
     */
    @ApiModelProperty(value = "调节机构负责人")
    @Column(name = "dutyofficer_name")
    private String dutyofficerName;

    /**
     * 记录人
     */
    @ApiModelProperty(value = "记录人")
    @Column(name = "recorder_name")
    private String recorderName;

    /**
     * 调节员Id
     */
    @ApiModelProperty(value = "调节员Id")
    @Column(name = "mediator_id")
    private Long mediatorId;

    /**
     * 调节员名称
     */
    @ApiModelProperty(value = "调节员名称")
    @Column(name = "mediator_name")
    private String mediatorName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    /**
     * 案件办理状态 （案件办理过程中的状态）直接中汉字表示key和value
     */
    @ApiModelProperty(value = "案件办理状态 （案件办理过程中的状态）直接中汉字表示key和value")
    @Column(name = "c_status")
    private String cStatus;

    /**
     * 案件办理意见 （案件办理过程中的意见）
     */
    @ApiModelProperty(value = "案件办理意见 （案件办理过程中的意见）")
    @Column(name = "deal_opinion")
    private String dealOpinion;

    /**
     * 父级案件ID
     */
    @ApiModelProperty(value = "父级案件ID")
    @Column(name = "upper_id")
    private Long upperId;

    /**
     * 父级案件名称
     */
    @ApiModelProperty(value = "父级案件名称")
    @Column(name = "upper_name")
    private Long upperName;

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
     * 评价回访工作人员
     */
    @ApiModelProperty(value = "评价回访工作人员")
    @Column(name = "staff_name")
    private String staffName;

    /**
     * 案件相关公众用户查询账号，身份证号码等
     */
    @ApiModelProperty(value = "案件相关公众用户查询账号，身份证号码等")
    @Column(name = "user_login")
    private Long userLogin;

    /**
     * 案件相关公众用户人姓名
     */
    @ApiModelProperty(value = "案件相关公众用户人姓名")
    @Column(name = "user_name")
    private Long userName;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间")
    @Column(name = "evaluat_date")
    private ZonedDateTime evaluatDate;

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
     * 纠纷类型（来自字典表）
     */
    @ApiModelProperty(value = "纠纷类型（来自字典表）")
    @Column(name = "dispute_type")
    private String disputeType;
    
    /**
     * 是否疑难案件
     */
    @ApiModelProperty(value = "是否疑难案件")
    @Column(name = "is_difficult")
    private String isDifficult;
    
    /**
     * 案件难度级别
     */
    @ApiModelProperty(value = "案件难度级别")
    @Column(name = "difficult_level")
    private String difficultLevel;
    
    /**
     * 是否快速办理
     */
    @ApiModelProperty(value = "是否快速办理")
    @Column(name = "is_quick")
    private String isQuick;
    
    /**
     * 案件预测
     */
    @ApiModelProperty(value = "案件预测（来自字典表）")
    @Column(name = "case_prediction")
    private String casePrediction;

    @ManyToMany(mappedBy = "filingCases")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FilingUserExtend> staff = new HashSet<>();

    @ManyToMany(mappedBy = "filingCases")
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

    public Long getoFilingCaseId() {
        return oFilingCaseId;
    }

    public FillingConflictCase oFilingCaseId(Long oFilingCaseId) {
        this.oFilingCaseId = oFilingCaseId;
        return this;
    }

    public void setoFilingCaseId(Long oFilingCaseId) {
        this.oFilingCaseId = oFilingCaseId;
    }

    public String getcType() {
        return cType;
    }

    public FillingConflictCase cType(String cType) {
        this.cType = cType;
        return this;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public ZonedDateTime getOccurrenceDate() {
        return occurrenceDate;
    }

    public FillingConflictCase occurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
        return this;
    }

    public void setOccurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public ZonedDateTime getApplyDate() {
        return applyDate;
    }

    public FillingConflictCase applyDate(ZonedDateTime applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(ZonedDateTime applyDate) {
        this.applyDate = applyDate;
    }

    public ZonedDateTime getAcceptDate() {
        return acceptDate;
    }

    public FillingConflictCase acceptDate(ZonedDateTime acceptDate) {
        this.acceptDate = acceptDate;
        return this;
    }

    public void setAcceptDate(ZonedDateTime acceptDate) {
        this.acceptDate = acceptDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public FillingConflictCase endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getcName() {
        return cName;
    }

    public FillingConflictCase cName(String cName) {
        this.cName = cName;
        return this;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public FillingConflictCase cDescription(String cDescription) {
        this.cDescription = cDescription;
        return this;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public FillingConflictCase feedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
        return this;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackValue() {
        return feedbackValue;
    }

    public FillingConflictCase feedbackValue(String feedbackValue) {
        this.feedbackValue = feedbackValue;
        return this;
    }

    public void setFeedbackValue(String feedbackValue) {
        this.feedbackValue = feedbackValue;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public FillingConflictCase infoSource(String infoSource) {
        this.infoSource = infoSource;
        return this;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getMyAppeal() {
        return myAppeal;
    }

    public FillingConflictCase myAppeal(String myAppeal) {
        this.myAppeal = myAppeal;
        return this;
    }

    public void setMyAppeal(String myAppeal) {
        this.myAppeal = myAppeal;
    }

    public String getOccurProvince() {
        return occurProvince;
    }

    public FillingConflictCase occurProvince(String occurProvince) {
        this.occurProvince = occurProvince;
        return this;
    }

    public void setOccurProvince(String occurProvince) {
        this.occurProvince = occurProvince;
    }

    public String getOcityState() {
        return ocityState;
    }

    public FillingConflictCase ocityState(String ocityState) {
        this.ocityState = ocityState;
        return this;
    }

    public void setOcityState(String ocityState) {
        this.ocityState = ocityState;
    }

    public String getOareaCounty() {
        return oareaCounty;
    }

    public FillingConflictCase oareaCounty(String oareaCounty) {
        this.oareaCounty = oareaCounty;
        return this;
    }

    public void setOareaCounty(String oareaCounty) {
        this.oareaCounty = oareaCounty;
    }

    public String getOcommunityStreet() {
        return ocommunityStreet;
    }

    public FillingConflictCase ocommunityStreet(String ocommunityStreet) {
        this.ocommunityStreet = ocommunityStreet;
        return this;
    }

    public void setOcommunityStreet(String ocommunityStreet) {
        this.ocommunityStreet = ocommunityStreet;
    }

    public Long getOareaId() {
        return oareaId;
    }

    public FillingConflictCase oareaId(Long oareaId) {
        this.oareaId = oareaId;
        return this;
    }

    public void setOareaId(Long oareaId) {
        this.oareaId = oareaId;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public FillingConflictCase mediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
        return this;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public FillingConflictCase mediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
        return this;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public String getDutyofficerName() {
        return dutyofficerName;
    }

    public FillingConflictCase dutyofficerName(String dutyofficerName) {
        this.dutyofficerName = dutyofficerName;
        return this;
    }

    public void setDutyofficerName(String dutyofficerName) {
        this.dutyofficerName = dutyofficerName;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public FillingConflictCase recorderName(String recorderName) {
        this.recorderName = recorderName;
        return this;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public Long getMediatorId() {
        return mediatorId;
    }

    public FillingConflictCase mediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
        return this;
    }

    public void setMediatorId(Long mediatorId) {
        this.mediatorId = mediatorId;
    }

    public String getMediatorName() {
        return mediatorName;
    }

    public FillingConflictCase mediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
        return this;
    }

    public void setMediatorName(String mediatorName) {
        this.mediatorName = mediatorName;
    }

    public String getRemarks() {
        return remarks;
    }

    public FillingConflictCase remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getcStatus() {
        return cStatus;
    }

    public FillingConflictCase cStatus(String cStatus) {
        this.cStatus = cStatus;
        return this;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getDealOpinion() {
        return dealOpinion;
    }

    public FillingConflictCase dealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
        return this;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public Long getUpperId() {
        return upperId;
    }

    public FillingConflictCase upperId(Long upperId) {
        this.upperId = upperId;
        return this;
    }

    public void setUpperId(Long upperId) {
        this.upperId = upperId;
    }

    public Long getUpperName() {
        return upperName;
    }

    public FillingConflictCase upperName(Long upperName) {
        this.upperName = upperName;
        return this;
    }

    public void setUpperName(Long upperName) {
        this.upperName = upperName;
    }

    public String getmEvaluat() {
        return mEvaluat;
    }

    public FillingConflictCase mEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
        return this;
    }

    public void setmEvaluat(String mEvaluat) {
        this.mEvaluat = mEvaluat;
    }

    public Double getScore() {
        return score;
    }

    public FillingConflictCase score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAppraise() {
        return appraise;
    }

    public FillingConflictCase appraise(String appraise) {
        this.appraise = appraise;
        return this;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getStaffName() {
        return staffName;
    }

    public FillingConflictCase staffName(String staffName) {
        this.staffName = staffName;
        return this;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getUserLogin() {
        return userLogin;
    }

    public FillingConflictCase userLogin(Long userLogin) {
        this.userLogin = userLogin;
        return this;
    }

    public void setUserLogin(Long userLogin) {
        this.userLogin = userLogin;
    }

    public Long getUserName() {
        return userName;
    }

    public FillingConflictCase userName(Long userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public ZonedDateTime getEvaluatDate() {
        return evaluatDate;
    }

    public FillingConflictCase evaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
        return this;
    }

    public void setEvaluatDate(ZonedDateTime evaluatDate) {
        this.evaluatDate = evaluatDate;
    }

    public ZonedDateTime getFilingDate() {
        return filingDate;
    }

    public FillingConflictCase filingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
        return this;
    }

    public void setFilingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingStaff() {
        return filingStaff;
    }

    public FillingConflictCase filingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
        return this;
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

	public Set<FilingUserExtend> getStaff() {
        return staff;
    }

    public FillingConflictCase staff(Set<FilingUserExtend> filingUserExtends) {
        this.staff = filingUserExtends;
        return this;
    }

    public FillingConflictCase addStaff(FilingUserExtend filingUserExtend) {
        this.staff.add(filingUserExtend);
        filingUserExtend.getFilingCases().add(this);
        return this;
    }

    public FillingConflictCase removeStaff(FilingUserExtend filingUserExtend) {
        this.staff.remove(filingUserExtend);
        filingUserExtend.getFilingCases().remove(this);
        return this;
    }

    public void setStaff(Set<FilingUserExtend> filingUserExtends) {
        this.staff = filingUserExtends;
    }

    public Set<FilingRelevantPerson> getRelevantPeople() {
        return relevantPeople;
    }

    public FillingConflictCase relevantPeople(Set<FilingRelevantPerson> filingRelevantPeople) {
        this.relevantPeople = filingRelevantPeople;
        return this;
    }

    public FillingConflictCase addRelevantPerson(FilingRelevantPerson filingRelevantPerson) {
        this.relevantPeople.add(filingRelevantPerson);
        filingRelevantPerson.getFilingCases().add(this);
        return this;
    }

    public FillingConflictCase removeRelevantPerson(FilingRelevantPerson filingRelevantPerson) {
        this.relevantPeople.remove(filingRelevantPerson);
        filingRelevantPerson.getFilingCases().remove(this);
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
        FillingConflictCase fillingConflictCase = (FillingConflictCase) o;
        if (fillingConflictCase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fillingConflictCase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FillingConflictCase{" +
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
