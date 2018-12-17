package com.thtf.app.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Dictionary entity.
 */
@ApiModel(description = "The Dictionary entity.")
@Entity
@Table(name = "sys_dictionary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dictionary extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * The dic_key attribute.
	 */
	@NotNull
	@Size(max = 50)
	@ApiModelProperty(value = "The dic_key attribute.", required = true)
	@Column(name = "dic_key", length = 50, nullable = false)
	private String dicKey;

	@NotNull
	@Size(max = 50)
	@Column(name = "dic_value", length = 50, nullable = false)
	private String dicValue;

	@Size(max = 2)
	@Column(name = "dic_flag", length = 2)
	private String dicFlag;

	@Size(max = 200)
	@Column(name = "dic_description", length = 200)
	private String dicDescription;

	@Column(name = "dic_level")
	private Integer dicLevel;

	@Column(name = "dic_order")
	private Float dicOrder;

	@Column(name = "leaf")
	private Boolean leaf;

	@Column(name = "dic_disabled")
	private Boolean dicDisabled;

	@ManyToOne
	private Dictionary upper;

	public Boolean getDicDisabled() {
		return dicDisabled;
	}

	public void setDicDisabled(Boolean dicDisabled) {
		this.dicDisabled = dicDisabled;
	}

	public Boolean isDicDisabled() {
		return dicDisabled;
	}

	public Dictionary dicDisabled(Boolean dicDisabled) {
		this.dicDisabled = dicDisabled;
		return this;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDicKey() {
		return dicKey;
	}

	public Dictionary dicKey(String dicKey) {
		this.dicKey = dicKey;
		return this;
	}

	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	public String getDicValue() {
		return dicValue;
	}

	public Dictionary dicValue(String dicValue) {
		this.dicValue = dicValue;
		return this;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getDicFlag() {
		return dicFlag;
	}

	public Dictionary dicFlag(String dicFlag) {
		this.dicFlag = dicFlag;
		return this;
	}

	public void setDicFlag(String dicFlag) {
		this.dicFlag = dicFlag;
	}

	public String getDicDescription() {
		return dicDescription;
	}

	public Dictionary dicDescription(String dicDescription) {
		this.dicDescription = dicDescription;
		return this;
	}

	public void setDicDescription(String dicDescription) {
		this.dicDescription = dicDescription;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public Integer getDicLevel() {
		return dicLevel;
	}

	public Dictionary dicLevel(Integer dicLevel) {
		this.dicLevel = dicLevel;
		return this;
	}

	public void setDicLevel(Integer dicLevel) {
		this.dicLevel = dicLevel;
	}

	public Float getDicOrder() {
		return dicOrder;
	}

	public Dictionary dicOrder(Float dicOrder) {
		this.dicOrder = dicOrder;
		return this;
	}

	public void setDicOrder(Float dicOrder) {
		this.dicOrder = dicOrder;
	}

	public Boolean isLeaf() {
		return leaf;
	}

	public Dictionary leaf(Boolean leaf) {
		this.leaf = leaf;
		return this;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Dictionary getUpper() {
		return upper;
	}

	public Dictionary upper(Dictionary dictionary) {
		this.upper = dictionary;
		return this;
	}

	public void setUpper(Dictionary dictionary) {
		this.upper = dictionary;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Dictionary dictionary = (Dictionary) o;
		if (dictionary.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), dictionary.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", dicKey=" + dicKey + ", dicValue=" + dicValue + ", dicFlag=" + dicFlag
				+ ", dicDescription=" + dicDescription + ", dicLevel=" + dicLevel + ", dicOrder=" + dicOrder + ", leaf="
				+ leaf + ", dicDisabled=" + dicDisabled + ", upper=" + upper + "]";
	}

}
