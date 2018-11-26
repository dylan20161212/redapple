package com.thtf.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the Dictionary entity.
 */
public class DictionaryDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    @NotNull
    @Size(max = 50)
    private String dicKey;

    @NotNull
    @Size(max = 50)
    private String dicValue;

    @Size(max = 255)
    private String dicFlag;

    @Size(max = 255)
    private String dicDescription;

    private Integer dicLft;

    private Integer dicRgt;

    private Integer dicLevel;

    private Float dicOrder;

    private Boolean leaf;

    private Boolean dicDisabled;
    
    private Long upperId;
    
    public Boolean getDicDisabled() {
		return dicDisabled;
	}

	public void setDicDisabled(Boolean dicDisabled) {
		this.dicDisabled = dicDisabled;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicKey() {
        return dicKey;
    }

    public void setDicKey(String dicKey) {
        this.dicKey = dicKey;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

    public String getDicFlag() {
        return dicFlag;
    }

    public void setDicFlag(String dicFlag) {
        this.dicFlag = dicFlag;
    }

    public String getDicDescription() {
        return dicDescription;
    }

    public void setDicDescription(String dicDescription) {
        this.dicDescription = dicDescription;
    }

    public Integer getDicLft() {
        return dicLft;
    }

    public void setDicLft(Integer dicLft) {
        this.dicLft = dicLft;
    }

    public Integer getDicRgt() {
        return dicRgt;
    }

    public void setDicRgt(Integer dicRgt) {
        this.dicRgt = dicRgt;
    }

    public Integer getDicLevel() {
        return dicLevel;
    }

    public void setDicLevel(Integer dicLevel) {
        this.dicLevel = dicLevel;
    }

    public Float getDicOrder() {
        return dicOrder;
    }

    public void setDicOrder(Float dicOrder) {
        this.dicOrder = dicOrder;
    }

    public Boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Long getUpperId() {
        return upperId;
    }

    public void setUpperId(Long dictionaryId) {
        this.upperId = dictionaryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DictionaryDTO dictionaryDTO = (DictionaryDTO) o;
        if(dictionaryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dictionaryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DictionaryDTO{" +
            "id=" + getId() +
            ", dicKey='" + getDicKey() + "'" +
            ", dicValue='" + getDicValue() + "'" +
            ", dicFlag='" + getDicFlag() + "'" +
            ", dicDescription='" + getDicDescription() + "'" +
            ", dicLft='" + getDicLft() + "'" +
            ", dicRgt='" + getDicRgt() + "'" +
            ", dicLevel='" + getDicLevel() + "'" +
            ", dicOrder='" + getDicOrder() + "'" +
            ", leaf='" + isLeaf() + "'" +
            "}";
    }
}
