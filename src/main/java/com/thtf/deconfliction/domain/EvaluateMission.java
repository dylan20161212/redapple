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
 * 人民调解员考核工作任务
 */
@ApiModel(description = "人民调解员考核工作任务")
@Entity
@Table(name = "de_evaluate_mission")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateMission extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * （来自于字典表）类型
     */
    @ApiModelProperty(value = "（来自于字典表）类型")
    @Column(name = "g_type")
    private String gType;

    /**
     * 负责人ID
     */
    @ApiModelProperty(value = "负责人ID")
    @Column(name = "user_id")
    private Long userId;

    /**
     * 负责人名称
     */
    @ApiModelProperty(value = "负责人名称")
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
     * 名
     */
    @ApiModelProperty(value = "名")
    @Column(name = "the_name")
    private String theName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Column(name = "the_describe")
    private String theDescribe;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

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

    public EvaluateMission gType(String gType) {
        this.gType = gType;
        return this;
    }

    public void setgType(String gType) {
        this.gType = gType;
    }

    public Long getUserId() {
        return userId;
    }

    public EvaluateMission userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public EvaluateMission userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public EvaluateMission orgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public EvaluateMission orgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTheName() {
        return theName;
    }

    public EvaluateMission theName(String theName) {
        this.theName = theName;
        return this;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    public String getTheDescribe() {
        return theDescribe;
    }

    public EvaluateMission theDescribe(String theDescribe) {
        this.theDescribe = theDescribe;
        return this;
    }

    public void setTheDescribe(String theDescribe) {
        this.theDescribe = theDescribe;
    }

    public String getRemarks() {
        return remarks;
    }

    public EvaluateMission remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        EvaluateMission evaluateMission = (EvaluateMission) o;
        if (evaluateMission.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateMission.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateMission{" +
            "id=" + getId() +
            ", gType='" + getgType() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", orgId=" + getOrgId() +
            ", orgName='" + getOrgName() + "'" +
            ", theName='" + getTheName() + "'" +
            ", theDescribe='" + getTheDescribe() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
