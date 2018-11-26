package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 自然人法人，社会团体，其他组织
 */
@ApiModel(description = "自然人法人，社会团体，其他组织")
@Entity
@Table(name = "de_relevant_person")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RelevantPerson extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）申请人类型（自然人，非自然人）
     */
    @ApiModelProperty(value = "（来自于字典表）申请人类型（自然人，非自然人）")
    @Column(name = "a_type")
    private String aType;

    /**
     * （来自于字典表）申请人组别（申请人，被申请人，甲，乙，丙，丁）
     */
    @ApiModelProperty(value = "（来自于字典表）申请人组别（申请人，被申请人，甲，乙，丙，丁）")
    @Column(name = "a_group")
    private String aGroup;

    /**
     * 申请人名称（人名，法人名）
     */
    @ApiModelProperty(value = "申请人名称（人名，法人名）")
    @Column(name = "name")
    private String name;

    /**
     * 法人，社会团体，其他组织
     */
    @ApiModelProperty(value = "法人，社会团体，其他组织")
    @Column(name = "social_credit_type")
    private String socialCreditType;

    /**
     * 法人，社会团体，其他组织代码
     */
    @ApiModelProperty(value = "法人，社会团体，其他组织代码")
    @Column(name = "social_credit_code")
    private String socialCreditCode;

    /**
     * 法人代表
     */
    @ApiModelProperty(value = "法人代表")
    @Column(name = "legal_representation")
    private String legalRepresentation;

    /**
     * 申请人工作单位
     */
    @ApiModelProperty(value = "申请人工作单位")
    @Column(name = "company")
    private String company;

    /**
     * 申请人职业
     */
    @ApiModelProperty(value = "申请人职业")
    @Column(name = "job")
    private String job;

    /**
     * 电话（法人或自然人）
     */
    @ApiModelProperty(value = "电话（法人或自然人）")
    @Column(name = "telephone")
    private String telephone;

    /**
     * 性别（自然人，法人代表）
     */
    @ApiModelProperty(value = "性别（自然人，法人代表）")
    @Column(name = "gender")
    private String gender;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Long age;

    /**
     * （来自于字典表）证件类型（自然人，法人代表）
     */
    @ApiModelProperty(value = "（来自于字典表）证件类型（自然人，法人代表）")
    @Column(name = "id_type")
    private String idType;

    /**
     * 证件号码（自然人，法人代表）
     */
    @ApiModelProperty(value = "证件号码（自然人，法人代表）")
    @Column(name = "id_number")
    private String idNumber;

    /**
     * （来自于字典表）省
     */
    @ApiModelProperty(value = "（来自于字典表）省")
    @Column(name = "wells_province")
    private String wellsProvince;

    /**
     * 市州
     */
    @ApiModelProperty(value = "市州")
    @Column(name = "city_state")
    private String cityState;

    /**
     * 区县
     */
    @ApiModelProperty(value = "区县")
    @Column(name = "area_county")
    private String areaCounty;

    /**
     * 社区，街道
     */
    @ApiModelProperty(value = "社区，街道")
    @Column(name = "community_street")
    private String communityStreet;

    /**
     * 地址详情
     */
    @ApiModelProperty(value = "地址详情")
    @Column(name = "address")
    private String address;

    /**
     * （来自于字典表）areaId
     */
    @ApiModelProperty(value = "（来自于字典表）areaId")
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 代理人
     */
    @ApiModelProperty(value = "代理人")
    @Column(name = "proxy_name")
    private String proxyName;

    /**
     * 被代理人id
     */
    @ApiModelProperty(value = "被代理人id")
    @Column(name = "principal_id")
    private Long principalId;

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "relevant_person_conflict_case",
               joinColumns = @JoinColumn(name="relevant_people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="conflict_cases_id", referencedColumnName="id"))
    private Set<ConflictCase> conflictCases = new HashSet<>();

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "relevant_person_mediation_info",
               joinColumns = @JoinColumn(name="relevant_people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="mediation_infos_id", referencedColumnName="id"))
    private Set<CaseProcessInfo> mediationInfos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getaType() {
        return aType;
    }

    public RelevantPerson aType(String aType) {
        this.aType = aType;
        return this;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaGroup() {
        return aGroup;
    }

    public RelevantPerson aGroup(String aGroup) {
        this.aGroup = aGroup;
        return this;
    }

    public void setaGroup(String aGroup) {
        this.aGroup = aGroup;
    }

    public String getName() {
        return name;
    }

    public RelevantPerson name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialCreditType() {
        return socialCreditType;
    }

    public RelevantPerson socialCreditType(String socialCreditType) {
        this.socialCreditType = socialCreditType;
        return this;
    }

    public void setSocialCreditType(String socialCreditType) {
        this.socialCreditType = socialCreditType;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public RelevantPerson socialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
        return this;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getLegalRepresentation() {
        return legalRepresentation;
    }

    public RelevantPerson legalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
        return this;
    }

    public void setLegalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
    }

    public String getCompany() {
        return company;
    }

    public RelevantPerson company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public RelevantPerson job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTelephone() {
        return telephone;
    }

    public RelevantPerson telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public RelevantPerson gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getIdType() {
        return idType;
    }

    public RelevantPerson idType(String idType) {
        this.idType = idType;
        return this;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public RelevantPerson idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getWellsProvince() {
        return wellsProvince;
    }

    public RelevantPerson wellsProvince(String wellsProvince) {
        this.wellsProvince = wellsProvince;
        return this;
    }

    public void setWellsProvince(String wellsProvince) {
        this.wellsProvince = wellsProvince;
    }

    public String getCityState() {
        return cityState;
    }

    public RelevantPerson cityState(String cityState) {
        this.cityState = cityState;
        return this;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getAreaCounty() {
        return areaCounty;
    }

    public RelevantPerson areaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
        return this;
    }

    public void setAreaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
    }

    public String getCommunityStreet() {
        return communityStreet;
    }

    public RelevantPerson communityStreet(String communityStreet) {
        this.communityStreet = communityStreet;
        return this;
    }

    public void setCommunityStreet(String communityStreet) {
        this.communityStreet = communityStreet;
    }

    public String getAddress() {
        return address;
    }

    public RelevantPerson address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAreaId() {
        return areaId;
    }

    public RelevantPerson areaId(Long areaId) {
        this.areaId = areaId;
        return this;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getProxyName() {
        return proxyName;
    }

    public RelevantPerson proxyName(String proxyName) {
        this.proxyName = proxyName;
        return this;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public RelevantPerson principalId(Long principalId) {
        this.principalId = principalId;
        return this;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Set<ConflictCase> getConflictCases() {
        return conflictCases;
    }

    public RelevantPerson conflictCases(Set<ConflictCase> conflictCases) {
        this.conflictCases = conflictCases;
        return this;
    }

    public RelevantPerson addConflictCase(ConflictCase conflictCase) {
        this.conflictCases.add(conflictCase);
        conflictCase.getRelevantPeople().add(this);
        return this;
    }

    public RelevantPerson removeConflictCase(ConflictCase conflictCase) {
        this.conflictCases.remove(conflictCase);
        conflictCase.getRelevantPeople().remove(this);
        return this;
    }

    public void setConflictCases(Set<ConflictCase> conflictCases) {
        this.conflictCases = conflictCases;
    }

    public Set<CaseProcessInfo> getMediationInfos() {
        return mediationInfos;
    }

    public RelevantPerson mediationInfos(Set<CaseProcessInfo> caseProcessInfos) {
        this.mediationInfos = caseProcessInfos;
        return this;
    }

    public RelevantPerson addMediationInfo(CaseProcessInfo caseProcessInfo) {
        this.mediationInfos.add(caseProcessInfo);
        return this;
    }

    public RelevantPerson removeMediationInfo(CaseProcessInfo caseProcessInfo) {
        this.mediationInfos.remove(caseProcessInfo);
        return this;
    }

    public void setMediationInfos(Set<CaseProcessInfo> caseProcessInfos) {
        this.mediationInfos = caseProcessInfos;
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
        RelevantPerson relevantPerson = (RelevantPerson) o;
        if (relevantPerson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relevantPerson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelevantPerson{" +
            "id=" + getId() +
            ", aType='" + getaType() + "'" +
            ", aGroup='" + getaGroup() + "'" +
            ", name='" + getName() + "'" +
            ", socialCreditType='" + getSocialCreditType() + "'" +
            ", socialCreditCode='" + getSocialCreditCode() + "'" +
            ", legalRepresentation='" + getLegalRepresentation() + "'" +
            ", company='" + getCompany() + "'" +
            ", job='" + getJob() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", gender='" + getGender() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", wellsProvince='" + getWellsProvince() + "'" +
            ", cityState='" + getCityState() + "'" +
            ", areaCounty='" + getAreaCounty() + "'" +
            ", communityStreet='" + getCommunityStreet() + "'" +
            ", address='" + getAddress() + "'" +
            ", areaId=" + getAreaId() +
            ", proxyName='" + getProxyName() + "'" +
            ", principalId=" + getPrincipalId() +
            "}";
    }
}
