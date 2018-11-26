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
 * 通用用户信息
 */
@ApiModel(description = "通用用户信息")
@Entity
@Table(name = "filing_user_extend")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FilingUserExtend extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * original原来的ID
     */
    @ApiModelProperty(value = "original原来的ID")
    @Column(name = "o_user_extend_id")
    private Long oUserExtendId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Column(name = "name")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Column(name = "sex")
    private String sex;

    /**
     * 电话
     */
    @NotNull
    @ApiModelProperty(value = "电话", required = true)
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "jhi_password", nullable = false)
    private String password;

    /**
     * 登录账号
     */
    @NotNull
    @ApiModelProperty(value = "登录账号", required = true)
    @Column(name = "login", nullable = false)
    private String login;

    /**
     * 工号
     */
    @NotNull
    @ApiModelProperty(value = "工号", required = true)
    @Column(name = "work_number", nullable = false)
    private String workNumber;

    /**
     * 所属调节机构Id
     */
    @ApiModelProperty(value = "所属调节机构Id")
    @Column(name = "mediate_org_id")
    private Long mediateOrgId;

    /**
     * 所属调节机构名称
     */
    @ApiModelProperty(value = "所属调节机构名称")
    @Column(name = "mediate_org_name")
    private String mediateOrgName;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 市州（居住地址）
     */
    @ApiModelProperty(value = "市州（居住地址）")
    @Column(name = "city_state")
    private String cityState;

    /**
     * 区县（居住地址）
     */
    @ApiModelProperty(value = "区县（居住地址）")
    @Column(name = "community")
    private String community;

    /**
     * 街道 （居住地址）
     */
    @ApiModelProperty(value = "街道 （居住地址）")
    @Column(name = "street")
    private String street;

    /**
     * 地址详情 （居住地址）
     */
    @ApiModelProperty(value = "地址详情 （居住地址）")
    @Column(name = "address")
    private String address;

    /**
     * 居住地址areaId
     */
    @ApiModelProperty(value = "居住地址areaId")
    @Column(name = "area_id")
    private Long areaId;

    /**
     * 市州（户籍地址）
     */
    @ApiModelProperty(value = "市州（户籍地址）")
    @Column(name = "h_city_state")
    private String hCityState;

    /**
     * 区县（户籍地址）
     */
    @ApiModelProperty(value = "区县（户籍地址）")
    @Column(name = "h_community")
    private String hCommunity;

    /**
     * 街道 （户籍地址）
     */
    @ApiModelProperty(value = "街道 （户籍地址）")
    @Column(name = "h_street")
    private String hStreet;

    /**
     * 户籍地址areaId
     */
    @ApiModelProperty(value = "户籍地址areaId")
    @Column(name = "h_area_id")
    private Long hAreaId;

    /**
     * 登录用户ID
     */
    @ApiModelProperty(value = "登录用户ID")
    @Column(name = "user_id")
    private Long userId;

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
    @JoinTable(name = "filing_user_extend_filing_case",
               joinColumns = @JoinColumn(name="filing_user_extends_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="filing_cases_id", referencedColumnName="id"))
    private Set<FillingConflictCase> filingCases = new HashSet<>();

    @ManyToMany
    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "filing_user_extend_filing_process",
               joinColumns = @JoinColumn(name="filing_user_extends_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="filing_processes_id", referencedColumnName="id"))
    private Set<FillingCaseProcessInfo> filingProcesses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoUserExtendId() {
        return oUserExtendId;
    }

    public FilingUserExtend oUserExtendId(Long oUserExtendId) {
        this.oUserExtendId = oUserExtendId;
        return this;
    }

    public void setoUserExtendId(Long oUserExtendId) {
        this.oUserExtendId = oUserExtendId;
    }

    public String getName() {
        return name;
    }

    public FilingUserExtend name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public FilingUserExtend sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public FilingUserExtend phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public FilingUserExtend email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public FilingUserExtend password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public FilingUserExtend login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public FilingUserExtend workNumber(String workNumber) {
        this.workNumber = workNumber;
        return this;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public FilingUserExtend mediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
        return this;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public FilingUserExtend mediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
        return this;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public FilingUserExtend idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCityState() {
        return cityState;
    }

    public FilingUserExtend cityState(String cityState) {
        this.cityState = cityState;
        return this;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getCommunity() {
        return community;
    }

    public FilingUserExtend community(String community) {
        this.community = community;
        return this;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getStreet() {
        return street;
    }

    public FilingUserExtend street(String street) {
        this.street = street;
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public FilingUserExtend address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAreaId() {
        return areaId;
    }

    public FilingUserExtend areaId(Long areaId) {
        this.areaId = areaId;
        return this;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String gethCityState() {
        return hCityState;
    }

    public FilingUserExtend hCityState(String hCityState) {
        this.hCityState = hCityState;
        return this;
    }

    public void sethCityState(String hCityState) {
        this.hCityState = hCityState;
    }

    public String gethCommunity() {
        return hCommunity;
    }

    public FilingUserExtend hCommunity(String hCommunity) {
        this.hCommunity = hCommunity;
        return this;
    }

    public void sethCommunity(String hCommunity) {
        this.hCommunity = hCommunity;
    }

    public String gethStreet() {
        return hStreet;
    }

    public FilingUserExtend hStreet(String hStreet) {
        this.hStreet = hStreet;
        return this;
    }

    public void sethStreet(String hStreet) {
        this.hStreet = hStreet;
    }

    public Long gethAreaId() {
        return hAreaId;
    }

    public FilingUserExtend hAreaId(Long hAreaId) {
        this.hAreaId = hAreaId;
        return this;
    }

    public void sethAreaId(Long hAreaId) {
        this.hAreaId = hAreaId;
    }

    public Long getUserId() {
        return userId;
    }

    public FilingUserExtend userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ZonedDateTime getFilingDate() {
        return filingDate;
    }

    public FilingUserExtend filingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
        return this;
    }

    public void setFilingDate(ZonedDateTime filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingStaff() {
        return filingStaff;
    }

    public FilingUserExtend filingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
        return this;
    }

    public void setFilingStaff(String filingStaff) {
        this.filingStaff = filingStaff;
    }

    public Set<FillingConflictCase> getFilingCases() {
        return filingCases;
    }

    public FilingUserExtend filingCases(Set<FillingConflictCase> fillingConflictCases) {
        this.filingCases = fillingConflictCases;
        return this;
    }

    public FilingUserExtend addFilingCase(FillingConflictCase fillingConflictCase) {
        this.filingCases.add(fillingConflictCase);
        fillingConflictCase.getStaff().add(this);
        return this;
    }

    public FilingUserExtend removeFilingCase(FillingConflictCase fillingConflictCase) {
        this.filingCases.remove(fillingConflictCase);
        fillingConflictCase.getStaff().remove(this);
        return this;
    }

    public void setFilingCases(Set<FillingConflictCase> fillingConflictCases) {
        this.filingCases = fillingConflictCases;
    }

    public Set<FillingCaseProcessInfo> getFilingProcesses() {
        return filingProcesses;
    }

    public FilingUserExtend filingProcesses(Set<FillingCaseProcessInfo> fillingCaseProcessInfos) {
        this.filingProcesses = fillingCaseProcessInfos;
        return this;
    }

    public FilingUserExtend addFilingProcess(FillingCaseProcessInfo fillingCaseProcessInfo) {
        this.filingProcesses.add(fillingCaseProcessInfo);
        fillingCaseProcessInfo.getStaff().add(this);
        return this;
    }

    public FilingUserExtend removeFilingProcess(FillingCaseProcessInfo fillingCaseProcessInfo) {
        this.filingProcesses.remove(fillingCaseProcessInfo);
        fillingCaseProcessInfo.getStaff().remove(this);
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
        FilingUserExtend filingUserExtend = (FilingUserExtend) o;
        if (filingUserExtend.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filingUserExtend.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilingUserExtend{" +
            "id=" + getId() +
            ", oUserExtendId=" + getoUserExtendId() +
            ", name='" + getName() + "'" +
            ", sex='" + getSex() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", login='" + getLogin() + "'" +
            ", workNumber='" + getWorkNumber() + "'" +
            ", mediateOrgId=" + getMediateOrgId() +
            ", mediateOrgName='" + getMediateOrgName() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", cityState='" + getCityState() + "'" +
            ", community='" + getCommunity() + "'" +
            ", street='" + getStreet() + "'" +
            ", address='" + getAddress() + "'" +
            ", areaId=" + getAreaId() +
            ", hCityState='" + gethCityState() + "'" +
            ", hCommunity='" + gethCommunity() + "'" +
            ", hStreet='" + gethStreet() + "'" +
            ", hAreaId=" + gethAreaId() +
            ", userId=" + getUserId() +
            ", filingDate='" + getFilingDate() + "'" +
            ", filingStaff='" + getFilingStaff() + "'" +
            "}";
    }
}
