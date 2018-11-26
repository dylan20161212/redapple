package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用用户扩展信息
 */
@ApiModel(description = "通用用户扩展信息")
@Entity
@Table(name = "de_user_extend")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserExtend extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Column(name = "gender")
    private String gender;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @Column(name = "birthday")
    private ZonedDateTime birthday;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
    @Column(name = "img")
    private String img;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Column(name = "phone")
    private String phone;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    @Column(name = "id_type")
    private String idType;

    /**
     * 证号
     */
    @ApiModelProperty(value = "证号")
    @Column(name = "id_number")
    private String idNumber;

    /**
     * 省（居住地址）
     */
    @ApiModelProperty(value = "省（居住地址）")
    @Column(name = "dwells_province")
    private String dwellsProvince;

    /**
     * 市州（居住地址）
     */
    @ApiModelProperty(value = "市州（居住地址）")
    @Column(name = "dcity_state")
    private String dcityState;

    /**
     * 区县（居住地址）
     */
    @ApiModelProperty(value = "区县（居住地址）")
    @Column(name = "darea_county")
    private String dareaCounty;

    /**
     * 社区，街道 （居住地址）
     */
    @ApiModelProperty(value = "社区，街道 （居住地址）")
    @Column(name = "dcommunity_street")
    private String dcommunityStreet;

    /**
     * 地址详情 （居住地址）
     */
    @ApiModelProperty(value = "地址详情 （居住地址）")
    @Column(name = "daddress")
    private String daddress;

    /**
     * 居住地址areaId
     */
    @ApiModelProperty(value = "居住地址areaId")
    @Column(name = "darea_id")
    private Long dareaId;

    /**
     * 省（户籍地址）
     */
    @ApiModelProperty(value = "省（户籍地址）")
    @Column(name = "census_register_province")
    private String censusRegisterProvince;

    /**
     * 市州（户籍地址）
     */
    @ApiModelProperty(value = "市州（户籍地址）")
    @Column(name = "ccity_state")
    private String ccityState;

    /**
     * 区县（户籍地址）
     */
    @ApiModelProperty(value = "区县（户籍地址）")
    @Column(name = "carea_county")
    private String careaCounty;

    /**
     * 街道 （户籍地址）
     */
    @ApiModelProperty(value = "街道 （户籍地址）")
    @Column(name = "ccommunity_street")
    private String ccommunityStreet;

    /**
     * 户籍地址areaId
     */
    @ApiModelProperty(value = "户籍地址areaId")
    @Column(name = "carea_id")
    private Long careaId;

    /**
     * 调解员级别 第一位0兼职1专职第二位代表级别第三位代表A专家B非专家，01A兼职一级专家,02A,03A,040,050;11A专职一级,12A专职二级,13A,140,150;01B兼职一级,02B,03B,04B,05B
     */
    @ApiModelProperty(value = "调解员级别 第一位0兼职1专职第二位代表级别第三位代表A专家B非专家，01A兼职一级专家,02A,03A,040,050;11A专职一级,12A专职二级,13A,140,150;01B兼职一级,02B,03B,04B,05B")
    @Column(name = "m_level")
    private String mLevel;

    /**
     * 调解员学历
     */
    @ApiModelProperty(value = "调解员学历")
    @Column(name = "m_degree")
    private String mDegree;

    /**
     * 调解员政治面貌
     */
    @ApiModelProperty(value = "调解员政治面貌")
    @Column(name = "m_politics_status")
    private String mPoliticsStatus;

    /**
     * 调解员职务
     */
    @ApiModelProperty(value = "调解员职务")
    @Column(name = "mduty")
    private String mduty;

    /**
     * UserId
     */
    @ApiModelProperty(value = "UserId")
    @Column(name = "user_id")
    private Long userId;
    
    /**
     * Login
     */
    @ApiModelProperty(value = "Login")
    @Column(name = "login")
    private String login;
    
    
    /**
     * email
     */
    @ApiModelProperty(value = "email")
    @Column(name = "email")
    private String email;
    
    /**
     * organizationId
     */
    @ApiModelProperty(value = "organizationId")
    @Column(name = "organization_id")
    private String organizationId;
    
    
    /**
     * organizationName
     */
    @ApiModelProperty(value = "organizationName")
    @Column(name = "organization_name")
    private String organizationName;
    

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserExtend name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public UserExtend gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public UserExtend birthday(ZonedDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(ZonedDateTime birthday) {
        this.birthday = birthday;
    }

    public String getImg() {
        return img;
    }

    public UserExtend img(String img) {
        this.img = img;
        return this;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public UserExtend phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdType() {
        return idType;
    }

    public UserExtend idType(String idType) {
        this.idType = idType;
        return this;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public UserExtend idNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDwellsProvince() {
        return dwellsProvince;
    }

    public UserExtend dwellsProvince(String dwellsProvince) {
        this.dwellsProvince = dwellsProvince;
        return this;
    }

    public void setDwellsProvince(String dwellsProvince) {
        this.dwellsProvince = dwellsProvince;
    }

    public String getDcityState() {
        return dcityState;
    }

    public UserExtend dcityState(String dcityState) {
        this.dcityState = dcityState;
        return this;
    }

    public void setDcityState(String dcityState) {
        this.dcityState = dcityState;
    }

    public String getDareaCounty() {
        return dareaCounty;
    }

    public UserExtend dareaCounty(String dareaCounty) {
        this.dareaCounty = dareaCounty;
        return this;
    }

    public void setDareaCounty(String dareaCounty) {
        this.dareaCounty = dareaCounty;
    }

    public String getDcommunityStreet() {
        return dcommunityStreet;
    }

    public UserExtend dcommunityStreet(String dcommunityStreet) {
        this.dcommunityStreet = dcommunityStreet;
        return this;
    }

    public void setDcommunityStreet(String dcommunityStreet) {
        this.dcommunityStreet = dcommunityStreet;
    }

    public String getDaddress() {
        return daddress;
    }

    public UserExtend daddress(String daddress) {
        this.daddress = daddress;
        return this;
    }

    public void setDaddress(String daddress) {
        this.daddress = daddress;
    }

    public Long getDareaId() {
        return dareaId;
    }

    public UserExtend dareaId(Long dareaId) {
        this.dareaId = dareaId;
        return this;
    }

    public void setDareaId(Long dareaId) {
        this.dareaId = dareaId;
    }

    public String getCensusRegisterProvince() {
        return censusRegisterProvince;
    }

    public UserExtend censusRegisterProvince(String censusRegisterProvince) {
        this.censusRegisterProvince = censusRegisterProvince;
        return this;
    }

    public void setCensusRegisterProvince(String censusRegisterProvince) {
        this.censusRegisterProvince = censusRegisterProvince;
    }

    public String getCcityState() {
        return ccityState;
    }

    public UserExtend ccityState(String ccityState) {
        this.ccityState = ccityState;
        return this;
    }

    public void setCcityState(String ccityState) {
        this.ccityState = ccityState;
    }

    public String getCareaCounty() {
        return careaCounty;
    }

    public UserExtend careaCounty(String careaCounty) {
        this.careaCounty = careaCounty;
        return this;
    }

    public void setCareaCounty(String careaCounty) {
        this.careaCounty = careaCounty;
    }

    public String getCcommunityStreet() {
        return ccommunityStreet;
    }

    public UserExtend ccommunityStreet(String ccommunityStreet) {
        this.ccommunityStreet = ccommunityStreet;
        return this;
    }

    public void setCcommunityStreet(String ccommunityStreet) {
        this.ccommunityStreet = ccommunityStreet;
    }

    public Long getCareaId() {
        return careaId;
    }

    public UserExtend careaId(Long careaId) {
        this.careaId = careaId;
        return this;
    }

    public void setCareaId(Long careaId) {
        this.careaId = careaId;
    }

    public String getmLevel() {
        return mLevel;
    }

    public UserExtend mLevel(String mLevel) {
        this.mLevel = mLevel;
        return this;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmDegree() {
        return mDegree;
    }

    public UserExtend mDegree(String mDegree) {
        this.mDegree = mDegree;
        return this;
    }

    public void setmDegree(String mDegree) {
        this.mDegree = mDegree;
    }

    public String getmPoliticsStatus() {
        return mPoliticsStatus;
    }

    public UserExtend mPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
        return this;
    }

    public void setmPoliticsStatus(String mPoliticsStatus) {
        this.mPoliticsStatus = mPoliticsStatus;
    }

    public String getMduty() {
        return mduty;
    }

    public UserExtend mduty(String mduty) {
        this.mduty = mduty;
        return this;
    }

    public void setMduty(String mduty) {
        this.mduty = mduty;
    }

    public Long getUserId() {
        return userId;
    }

    public UserExtend userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserExtend userExtend = (UserExtend) o;
        if (userExtend.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtend.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtend{" +
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
