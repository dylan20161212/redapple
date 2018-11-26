package com.thtf.deconfliction.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UserExtend entity.
 */
public class UserExtendDTO implements Serializable {

    private Long id;

    private String name;

    private String gender;

    private ZonedDateTime birthday;

    private String img;

    private String phone;

    private String idType;

    private String idNumber;

    private String dwellsProvince;

    private String dcityState;

    private String dareaCounty;

    private String dcommunityStreet;

    private String daddress;

    private Long dareaId;

    private String censusRegisterProvince;

    private String ccityState;

    private String careaCounty;

    private String ccommunityStreet;

    private Long careaId;

    private String mLevel;

    private String mDegree;

    private String mPoliticsStatus;

    private String mduty;

    private Long userId;
    
    private String login;
    
    private String email;
    
    private Long organizationId;
    
    private String organizationName;
    
    
    

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDwellsProvince() {
        return dwellsProvince;
    }

    public void setDwellsProvince(String dwellsProvince) {
        this.dwellsProvince = dwellsProvince;
    }

    public String getDcityState() {
        return dcityState;
    }

    public void setDcityState(String dcityState) {
        this.dcityState = dcityState;
    }

    public String getDareaCounty() {
        return dareaCounty;
    }

    public void setDareaCounty(String dareaCounty) {
        this.dareaCounty = dareaCounty;
    }

    public String getDcommunityStreet() {
        return dcommunityStreet;
    }

    public void setDcommunityStreet(String dcommunityStreet) {
        this.dcommunityStreet = dcommunityStreet;
    }

    public String getDaddress() {
        return daddress;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public Long getDareaId() {
        return dareaId;
    }

    public void setDareaId(Long dareaId) {
        this.dareaId = dareaId;
    }

    public String getCensusRegisterProvince() {
        return censusRegisterProvince;
    }

    public void setCensusRegisterProvince(String censusRegisterProvince) {
        this.censusRegisterProvince = censusRegisterProvince;
    }

    public String getCcityState() {
        return ccityState;
    }

    public void setCcityState(String ccityState) {
        this.ccityState = ccityState;
    }

    public String getCareaCounty() {
        return careaCounty;
    }

    public void setCareaCounty(String careaCounty) {
        this.careaCounty = careaCounty;
    }

    public String getCcommunityStreet() {
        return ccommunityStreet;
    }

    public void setCcommunityStreet(String ccommunityStreet) {
        this.ccommunityStreet = ccommunityStreet;
    }

    public Long getCareaId() {
        return careaId;
    }

    public void setCareaId(Long careaId) {
        this.careaId = careaId;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmDegree() {
        return mDegree;
    }

    public void setmDegree(String mDegree) {
        this.mDegree = mDegree;
    }

    public String getmPoliticsStatus() {
        return mPoliticsStatus;
    }

    public void setmPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
    }

    public String getMduty() {
        return mduty;
    }

    public void setMduty(String mduty) {
        this.mduty = mduty;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserExtendDTO userExtendDTO = (UserExtendDTO) o;
        if(userExtendDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtendDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtendDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", img='" + getImg() + "'" +
            ", phone='" + getPhone() + "'" +
            ", idType='" + getIdType() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", dwellsProvince='" + getDwellsProvince() + "'" +
            ", dcityState='" + getDcityState() + "'" +
            ", dareaCounty='" + getDareaCounty() + "'" +
            ", dcommunityStreet='" + getDcommunityStreet() + "'" +
            ", daddress='" + getDaddress() + "'" +
            ", dareaId=" + getDareaId() +
            ", censusRegisterProvince='" + getCensusRegisterProvince() + "'" +
            ", ccityState='" + getCcityState() + "'" +
            ", careaCounty='" + getCareaCounty() + "'" +
            ", ccommunityStreet='" + getCcommunityStreet() + "'" +
            ", careaId=" + getCareaId() +
            ", mLevel='" + getmLevel() + "'" +
            ", mDegree='" + getmDegree() + "'" +
            ", mPoliticsStatus='" + getmPoliticsStatus() + "'" +
            ", mduty='" + getMduty() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
