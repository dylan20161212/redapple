package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FilingRelevantPerson entity.
 */
public class FilingRelevantPersonDTO implements Serializable {

    private Long id;

    private Long oPersonId;

    @NotNull
    private String aType;

    private String aGroup;

    @NotNull
    private String name;

    @NotNull
    private String socialCreditType;

    @NotNull
    private String socialCreditCode;

    @NotNull
    private String legalRepresentation;

    private String company;

    private String job;

    private String telephone;

    private String gender;

    @NotNull
    private String idType;

    private String idNumber;

    private String wellsProvince;

    private String cityState;

    private String areaCounty;

    private String communityStreet;

    private String address;

    private Long areaId;

    private Long proxyId;

    private String proxyName;

    private ZonedDateTime filingDate;

    private String filingStaff;

    private Set<FillingConflictCaseDTO> filingCases = new HashSet<>();

    private Set<FillingCaseProcessInfoDTO> filingProcesses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getoPersonId() {
        return oPersonId;
    }

    public void setoPersonId(Long oPersonId) {
        this.oPersonId = oPersonId;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaGroup() {
        return aGroup;
    }

    public void setaGroup(String aGroup) {
        this.aGroup = aGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialCreditType() {
        return socialCreditType;
    }

    public void setSocialCreditType(String socialCreditType) {
        this.socialCreditType = socialCreditType;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getLegalRepresentation() {
        return legalRepresentation;
    }

    public void setLegalRepresentation(String legalRepresentation) {
        this.legalRepresentation = legalRepresentation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getWellsProvince() {
        return wellsProvince;
    }

    public void setWellsProvince(String wellsProvince) {
        this.wellsProvince = wellsProvince;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getAreaCounty() {
        return areaCounty;
    }

    public void setAreaCounty(String areaCounty) {
        this.areaCounty = areaCounty;
    }

    public String getCommunityStreet() {
        return communityStreet;
    }

    public void setCommunityStreet(String communityStreet) {
        this.communityStreet = communityStreet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getProxyId() {
        return proxyId;
    }

    public void setProxyId(Long proxyId) {
        this.proxyId = proxyId;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
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

    public Set<FillingConflictCaseDTO> getFilingCases() {
        return filingCases;
    }

    public void setFilingCases(Set<FillingConflictCaseDTO> fillingConflictCases) {
        this.filingCases = fillingConflictCases;
    }

    public Set<FillingCaseProcessInfoDTO> getFilingProcesses() {
        return filingProcesses;
    }

    public void setFilingProcesses(Set<FillingCaseProcessInfoDTO> fillingCaseProcessInfos) {
        this.filingProcesses = fillingCaseProcessInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilingRelevantPersonDTO filingRelevantPersonDTO = (FilingRelevantPersonDTO) o;
        if(filingRelevantPersonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filingRelevantPersonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilingRelevantPersonDTO{" +
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
