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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 自然人法人，社会团体，其他组织
 */
@ApiModel(description = "自然人法人，社会团体，其他组织")
@Entity
@Table(name = "filing_relevant_person")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FilingRelevantPerson extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * original原来的ID
     */
    @ApiModelProperty(value = "original原来的ID")
    @Column(name = "o_person_id")
    private Long oPersonId;

    /**
     * 申请人类型（自然人，非自然人）
     */
    @NotNull
    @ApiModelProperty(value = "申请人类型（自然人，非自然人）", required = true)
    @Column(name = "a_type", nullable = false)
    private String aType;

    /**
     * 申请人组别（申请人，被申请人，甲，乙，丙，丁）
     */
    @ApiModelProperty(value = "申请人组别（申请人，被申请人，甲，乙，丙，丁）")
    @Column(name = "a_group")
    private String aGroup;

    /**
     * 申请人名称（人名，法人名）
     */
    @NotNull
    @ApiModelProperty(value = "申请人名称（人名，法人名）", required = true)
    @Column(name = "name", nullable = false)
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
     * 证件类型（自然人，法人代表）
     */
    @ApiModelProperty(value = "证件类型（自然人，法人代表）")
    @Column(name = "id_type")
    private String idType;

    /**
     * 证件号码（自然人，法人代表）
     */
    @ApiModelProperty(value = "证件号码（自然人，法人代表）")
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
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
     * areaId
     */
    @ApiModelProperty(value = "areaId")
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 被代理人id
     */
    @ApiModelProperty(value = "被代理人id")
    @Column(name = "proxy_id")
    private Long proxyId;

    /**
     * 代理人
     */
    @ApiModelProperty(value = "代理人")
    @Column(name = "proxy_name")
    private String proxyName;

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

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "filing_relevant_person_filing_case",
               joinColumns = @JoinColumn(name="filing_relevant_people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="filing_cases_id", referencedColumnName="id"))
    private Set<FillingConflictCase> filingCases = new HashSet<>();

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "filing_relevant_person_filing_process",
               joinColumns = @JoinColumn(name="filing_relevant_people_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="filing_processes_id", referencedColumnName="id"))
    private Set<FillingCaseProcessInfo> filingProcesses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoPersonId() {
        return oPersonId;
    }

    public FilingRelevantPerson oPersonId(Long oPersonId) {
        this.oPersonId = oPersonId;
        return this;
    }

    public void setoPersonId(Long oPersonId) {
        this.oPersonId = oPersonId;
    }

    public String getaType() {
        return aType;
    }

    public FilingRelevantPerson aType(String aType) {
        this.aType = aType;
        return this;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaGroup() {
        return aGroup;
    }

    public FilingRelevantPerson aGroup(String aGroup) {
        this.aGroup = aGroup;
        return this;
    }

    public void setaGroup(String aGroup) {
        this.aGroup = aGroup;
    }

    public String getName() {
        return name;
    }

    public FilingRelevantPerson name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialCreditType() {
        return socialCreditType;
    }

    public FilingRelevantPerson socialCreditType(String socialCreditType) {
        this.socialCreditType = socialCreditType;
        return this;
    }

    public void setSocialCreditType(String socialCreditType) {
        this.socialCreditType = socialCreditType;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public FilingRelevantPerson socialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
        return this;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getLegalRepresentation() {
        return legalRepresentation;
    }

    public FilingRelevantPerson legalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
        return this;
    }

    public void setLegalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
    }

    public String getCompany() {
        return company;
    }

    public FilingRelevantPerson company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public FilingRelevantPerson job(String job) {
        this.job = job;
        return this;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTelephone() {
        return telephone;
    }

    public FilingRelevantPerson telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public FilingRelevantPerson gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdType() {
        return idType;
    }

    public FilingRelevantPerson idType(String idType) {
        this.idType = idType;
        return this;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public FilingRelevantPerson idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getWellsProvince() {
        return wellsProvince;
    }

    public FilingRelevantPerson wellsProvince(String wellsProvince) {
        this.wellsProvince = wellsProvince;
        return this;
    }

    public void setWellsProvince(String wellsProvince) {
        this.wellsProvince = wellsProvince;
    }

    public String getCityState() {
        return cityState;
    }

    public FilingRelevantPerson cityState(String cityState) {
        this.cityState = cityState;
        return this;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getAreaCounty() {
        return areaCounty;
    }

    public FilingRelevantPerson areaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
        return this;
    }

    public void setAreaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
    }

    public String getCommunityStreet() {
        return communityStreet;
    }

    public FilingRelevantPerson communityStreet(String communityStreet) {
        this.communityStreet = communityStreet;
        return this;
    }

    public void setCommunityStreet(String communityStreet) {
        this.communityStreet = communityStreet;
    }

    public String getAddress() {
        return address;
    }

    public FilingRelevantPerson address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAreaId() {
        return areaId;
    }

    public FilingRelevantPerson areaId(Long areaId) {
        this.areaId = areaId;
        return this;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getProxyId() {
        return proxyId;
    }

    public FilingRelevantPerson proxyId(Long proxyId) {
        this.proxyId = proxyId;
        return this;
    }

    public void setProxyId(Long proxyId) {
        this.proxyId = proxyId;
    }

    public String getProxyName() {
        return proxyName;
    }

    public FilingRelevantPerson proxyName(String proxyName) {
        this.proxyName = proxyName;
        return this;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public ZonedDateTime getFilingDate() {
        return filingDate;
    }

    public FilingRelevantPerson filingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
        return this;
    }

    public void setFilingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingStaff() {
        return filingStaff;
    }

    public FilingRelevantPerson filingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
        return this;
    }

    public void setFilingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
    }

    public Set<FillingConflictCase> getFilingCases() {
        return filingCases;
    }

    public FilingRelevantPerson filingCases(Set<FillingConflictCase> fillingConflictCases) {
        this.filingCases = fillingConflictCases;
        return this;
    }

    public FilingRelevantPerson addFilingCase(FillingConflictCase fillingConflictCase) {
        this.filingCases.add(fillingConflictCase);
        fillingConflictCase.getRelevantPeople().add(this);
        return this;
    }

    public FilingRelevantPerson removeFilingCase(FillingConflictCase fillingConflictCase) {
        this.filingCases.remove(fillingConflictCase);
        fillingConflictCase.getRelevantPeople().remove(this);
        return this;
    }

    public void setFilingCases(Set<FillingConflictCase> fillingConflictCases) {
        this.filingCases = fillingConflictCases;
    }

    public Set<FillingCaseProcessInfo> getFilingProcesses() {
        return filingProcesses;
    }

    public FilingRelevantPerson filingProcesses(Set<FillingCaseProcessInfo> fillingCaseProcessInfos) {
        this.filingProcesses = fillingCaseProcessInfos;
        return this;
    }

    public FilingRelevantPerson addFilingProcess(FillingCaseProcessInfo fillingCaseProcessInfo) {
        this.filingProcesses.add(fillingCaseProcessInfo);
        fillingCaseProcessInfo.getRelevantPeople().add(this);
        return this;
    }

    public FilingRelevantPerson removeFilingProcess(FillingCaseProcessInfo fillingCaseProcessInfo) {
        this.filingProcesses.remove(fillingCaseProcessInfo);
        fillingCaseProcessInfo.getRelevantPeople().remove(this);
        return this;
    }

    public void setFilingProcesses(Set<FillingCaseProcessInfo> fillingCaseProcessInfos) {
        this.filingProcesses = fillingCaseProcessInfos;
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
        FilingRelevantPerson filingRelevantPerson = (FilingRelevantPerson) o;
        if (filingRelevantPerson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filingRelevantPerson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilingRelevantPerson{" +
            "id=" + getId() +
            ", oPersonId=" + getoPersonId() +
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
            ", proxyId=" + getProxyId() +
            ", proxyName='" + getProxyName() + "'" +
            ", filingDate='" + getFilingDate() + "'" +
            ", filingStaff='" + getFilingStaff() + "'" +
            "}";
    }
}
