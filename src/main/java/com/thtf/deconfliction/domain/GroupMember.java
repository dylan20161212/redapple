package com.thtf.deconfliction.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 人民调解员机关与小组的组员
 */
@ApiModel(description = "人民调解员机关与小组的组员")
@Entity
@Table(name = "de_group_member")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GroupMember extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）类型，考核小组或考核机关
     */
    @ApiModelProperty(value = "（来自于字典表）类型，考核小组或考核机关")
    @Column(name = "m_type")
    private String mType;

    /**
     * 人ID
     */
    @ApiModelProperty(value = "人ID")
    @Column(name = "user_id")
    private Long userId;

    /**
     * 人名称
     */
    @ApiModelProperty(value = "人名称")
    @Column(name = "user_name")
    private String userName;

    /**
     * 小组中角色
     */
    @ApiModelProperty(value = "小组中角色")
    @Column(name = "g_role")
    private String gRole;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    @OneToOne
    @JoinColumn(unique = true)
    private UserExtend userExtend;

    @ManyToOne
    private OfficeGroup group;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getmType() {
        return mType;
    }

    public GroupMember mType(String mType) {
        this.mType = mType;
        return this;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Long getUserId() {
        return userId;
    }

    public GroupMember userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public GroupMember userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getgRole() {
        return gRole;
    }

    public GroupMember gRole(String gRole) {
        this.gRole = gRole;
        return this;
    }

    public void setgRole(String gRole) {
        this.gRole = gRole;
    }

    public String getRemarks() {
        return remarks;
    }

    public GroupMember remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public UserExtend getUserExtend() {
        return userExtend;
    }

    public GroupMember userExtend(UserExtend userExtend) {
        this.userExtend = userExtend;
        return this;
    }

    public void setUserExtend(UserExtend userExtend) {
        this.userExtend = userExtend;
    }

    public OfficeGroup getGroup() {
        return group;
    }

    public GroupMember group(OfficeGroup officeGroup) {
        this.group = officeGroup;
        return this;
    }

    public void setGroup(OfficeGroup officeGroup) {
        this.group = officeGroup;
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
        GroupMember groupMember = (GroupMember) o;
        if (groupMember.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), groupMember.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GroupMember{" +
            "id=" + getId() +
            ", mType='" + getmType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", gRole='" + getgRole() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
