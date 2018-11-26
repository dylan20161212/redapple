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
 * 矛盾与纠纷案件表
 */
@ApiModel(description = "矛盾与纠纷案件表")
@Entity
@Table(name = "de_conflict_case")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConflictCase extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    @Column(name = "upper_id")
    private Long upperId;
    
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

    @ManyToMany(mappedBy = "conflictCases")
    @JsonIgnore
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RelevantPerson> relevantPeople = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getcType() {
        return cType;
    }

    public ConflictCase cType(String cType) {
        this.cType = cType;
        return this;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public ZonedDateTime getOccurrenceDate() {
        return occurrenceDate;
    }

    public ConflictCase occurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
        return this;
    }

    public void setOccurrenceDate(ZonedDateTime occurrenceDate) {
        this.occurrenceDate = occurrenceDate;
    }

    public ZonedDateTime getApplyDate() {
        return applyDate;
    }

    public ConflictCase applyDate(ZonedDateTime applyDate) {
        this.applyDate = applyDate;
        return this;
    }

    public void setApplyDate(ZonedDateTime applyDate) {
        this.applyDate = applyDate;
    }

    public ZonedDateTime getAcceptDate() {
        return acceptDate;
    }

    public ConflictCase acceptDate(ZonedDateTime acceptDate) {
        this.acceptDate = acceptDate;
        return this;
    }

    public void setAcceptDate(ZonedDateTime acceptDate) {
        this.acceptDate = acceptDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public ConflictCase endDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public String getcName() {
        return cName;
    }

    public ConflictCase cName(String cName) {
        this.cName = cName;
        return this;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcDescription() {
        return cDescription;
    }

    public ConflictCase cDescription(String cDescription) {
        this.cDescription = cDescription;
        return this;
    }

    public void setcDescription(String cDescription) {
        this.cDescription = cDescription;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public ConflictCase feedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
        return this;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackValue() {
        return feedbackValue;
    }

    public ConflictCase feedbackValue(String feedbackValue) {
        this.feedbackValue = feedbackValue;
        return this;
    }

    public void setFeedbackValue(String feedbackValue) {
        this.feedbackValue = feedbackValue;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public ConflictCase infoSource(String infoSource) {
        this.infoSource = infoSource;
        return this;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getMyAppeal() {
        return myAppeal;
    }

    public ConflictCase myAppeal(String myAppeal) {
        this.myAppeal = myAppeal;
        return this;
    }

    public void setMyAppeal(String myAppeal) {
        this.myAppeal = myAppeal;
    }

    public String getOccurProvince() {
        return occurProvince;
    }

    public ConflictCase occurProvince(String occurProvince) {
        this.occurProvince = occurProvince;
        return this;
    }

    public void setOccurProvince(String occurProvince) {
        this.occurProvince = occurProvince;
    }

    public String getOcityState() {
        return ocityState;
    }

    public ConflictCase ocityState(String ocityState) {
        this.ocityState = ocityState;
        return this;
    }

    public void setOcityState(String ocityState) {
        this.ocityState = ocityState;
    }

    public String getOareaCounty() {
        return oareaCounty;
    }

    public ConflictCase oareaCounty(String oareaCounty) {
        this.oareaCounty = oareaCounty;
        return this;
    }

    public void setOareaCounty(String oareaCounty) {
        this.oareaCounty = oareaCounty;
    }

    public String getOcommunityStreet() {
        return ocommunityStreet;
    }

    public ConflictCase ocommunityStreet(String ocommunityStreet) {
        this.ocommunityStreet = ocommunityStreet;
        return this;
    }

    public void setOcommunityStreet(String ocommunityStreet) {
        this.ocommunityStreet = ocommunityStreet;
    }

    public Long getOareaId() {
        return oareaId;
    }

    public ConflictCase oareaId(Long oareaId) {
        this.oareaId = oareaId;
        return this;
    }

    public void setOareaId(Long oareaId) {
        this.oareaId = oareaId;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public ConflictCase mediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
        return this;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public ConflictCase mediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
        return this;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public String getDutyofficerName() {
        return dutyofficerName;
    }

    public ConflictCase dutyofficerName(String dutyofficerName) {
        this.dutyofficerName = dutyofficerName;
        return this;
    }

    public void setDutyofficerName(String dutyofficerName) {
        this.dutyofficerName = dutyofficerName;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public ConflictCase recorderName(String recorderName) {
        this.recorderName = recorderName;
        return this;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public String getRemarks() {
        return remarks;
    }

    public ConflictCase remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getcStatus() {
        return cStatus;
    }

    public ConflictCase cStatus(String cStatus) {
        this.cStatus = cStatus;
        return this;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getDealOpinion() {
        return dealOpinion;
    }

    public ConflictCase dealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
        return this;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public Long getUpperId() {
        return upperId;
    }

    public ConflictCase upperId(Long upperId) {
        this.upperId = upperId;
        return this;
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

	public Set<RelevantPerson> getRelevantPeople() {
        return relevantPeople;
    }

    public ConflictCase relevantPeople(Set<RelevantPerson> relevantPeople) {
        this.relevantPeople = relevantPeople;
        return this;
    }

    public ConflictCase addRelevantPerson(RelevantPerson relevantPerson) {
        this.relevantPeople.add(relevantPerson);
        relevantPerson.getConflictCases().add(this);
        return this;
    }

    public ConflictCase removeRelevantPerson(RelevantPerson relevantPerson) {
        this.relevantPeople.remove(relevantPerson);
        relevantPerson.getConflictCases().remove(this);
        return this;
    }

    public void setRelevantPeople(Set<RelevantPerson> relevantPeople) {
        this.relevantPeople = relevantPeople;
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
        ConflictCase conflictCase = (ConflictCase) o;
        if (conflictCase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conflictCase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConflictCase{" +
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
