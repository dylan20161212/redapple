package com.thtf.deconfliction.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the RelevantPerson entity.
 */
public class RelevantPersonDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String aType;

    private String aGroup;

    private String name;

    private String socialCreditType;

    private String socialCreditCode;

    private String legalRepresentation;

    private String company;

    private String job;

    private String telephone;

    private String gender;
    
    private Long age;

    private String idType;

    private String idNumber;

    private String wellsProvince;

    private String cityState;

    private String areaCounty;

    private String communityStreet;

    private String address;

    private Long areaId;

    private String proxyName;

    private Long principalId;

    private Set<ConflictCaseDTO> conflictCases = new HashSet<>();

    private Set<CaseProcessInfoDTO> mediationInfos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
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

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Set<ConflictCaseDTO> getConflictCases() {
        return conflictCases;
    }

    public void setConflictCases(Set<ConflictCaseDTO> conflictCases) {
        this.conflictCases = conflictCases;
    }

    public Set<CaseProcessInfoDTO> getMediationInfos() {
        return mediationInfos;
    }

    public void setMediationInfos(Set<CaseProcessInfoDTO> caseProcessInfos) {
        this.mediationInfos = caseProcessInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RelevantPersonDTO relevantPersonDTO = (RelevantPersonDTO) o;
        if(relevantPersonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relevantPersonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelevantPersonDTO{" +
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
