package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FilingUserExtend entity.
 */
public class FilingUserExtendDTO implements Serializable {

    private Long id;

    private Long oUserExtendId;

    private String name;

    private String sex;

    @NotNull
    private String phone;

    private String email;

    @NotNull
    private String password;

    @NotNull
    private String login;

    @NotNull
    private String workNumber;

    private Long mediateOrgId;

    private String mediateOrgName;

    private String idNumber;

    private String cityState;

    private String community;

    private String street;

    private String address;

    private Long areaId;

    private String hCityState;

    private String hCommunity;

    private String hStreet;

    private Long hAreaId;

    private Long userId;

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

    public Long getoUserExtendId() {
        return oUserExtendId;
    }

    public void setoUserExtendId(Long oUserExtendId) {
        this.oUserExtendId = oUserExtendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public Long getMediateOrgId() {
        return mediateOrgId;
    }

    public void setMediateOrgId(Long mediateOrgId) {
        this.mediateOrgId = mediateOrgId;
    }

    public String getMediateOrgName() {
        return mediateOrgName;
    }

    public void setMediateOrgName(String mediateOrgName) {
        this.mediateOrgName = mediateOrgName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String gethCityState() {
        return hCityState;
    }

    public void sethCityState(String hCityState) {
        this.hCityState = hCityState;
    }

    public String gethCommunity() {
        return hCommunity;
    }

    public void sethCommunity(String hCommunity) {
        this.hCommunity = hCommunity;
    }

    public String gethStreet() {
        return hStreet;
    }

    public void sethStreet(String hStreet) {
        this.hStreet = hStreet;
    }

    public Long gethAreaId() {
        return hAreaId;
    }

    public void sethAreaId(Long hAreaId) {
        this.hAreaId = hAreaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        FilingUserExtendDTO filingUserExtendDTO = (FilingUserExtendDTO) o;
        if(filingUserExtendDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filingUserExtendDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilingUserExtendDTO{" +
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
