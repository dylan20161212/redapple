package com.thtf.deconfliction.domain;

import java.io.Serializable;
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
 * 人民调解员机关与小组
 */
@ApiModel(description = "人民调解员机关与小组")
@Entity
@Table(name = "de_office_group")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OfficeGroup extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）类型，考核小组或考核机关
     */
    @ApiModelProperty(value = "（来自于字典表）类型，考核小组或考核机关")
    @Column(name = "g_type")
    private String gType;

    /**
     * 小组负责人ID
     */
    @ApiModelProperty(value = "小组负责人ID")
    @Column(name = "user_id")
    private Long userId;

    /**
     * 小组负责人名称
     */
    @ApiModelProperty(value = "小组负责人名称")
    @Column(name = "user_name")
    private String userName;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    @Column(name = "org_id")
    private Long orgId;

    /**
     * 机构名
     */
    @ApiModelProperty(value = "机构名")
    @Column(name = "org_name")
    private String orgName;

    /**
     * 组名
     */
    @ApiModelProperty(value = "组名")
    @Column(name = "group_name")
    private String groupName;

    /**
     * 组描述
     */
    @ApiModelProperty(value = "组描述")
    @Column(name = "group_describe")
    private String groupDescribe;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    /**
     * 引用ID
     */
    @ApiModelProperty(value = "引用ID")
    @Column(name = "refer_id")
    private Long referId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgType() {
        return gType;
    }

    public OfficeGroup gType(String gType) {
        this.gType = gType;
        return this;
    }

    public void setgType(String gType) {
        this.gType = gType;
    }

    public Long getUserId() {
        return userId;
    }

    public OfficeGroup userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public OfficeGroup userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public OfficeGroup orgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public OfficeGroup orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGroupName() {
        return groupName;
    }

    public OfficeGroup groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescribe() {
        return groupDescribe;
    }

    public OfficeGroup groupDescribe(String groupDescribe) {
        this.groupDescribe = groupDescribe;
        return this;
    }

    public void setGroupDescribe(String groupDescribe) {
        this.groupDescribe = groupDescribe;
    }

    public String getRemarks() {
        return remarks;
    }

    public OfficeGroup remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getReferId() {
        return referId;
    }

    public OfficeGroup referId(Long referId) {
        this.referId = referId;
        return this;
    }

    public void setReferId(Long referId) {
        this.referId = referId;
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
        OfficeGroup officeGroup = (OfficeGroup) o;
        if (officeGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), officeGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OfficeGroup{" +
            "id=" + getId() +
            ", gType='" + getgType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", orgId=" + getOrgId() +
            ", orgName='" + getOrgName() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", groupDescribe='" + getGroupDescribe() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", referId=" + getReferId() +
            "}";
    }
}
