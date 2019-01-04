package com.thtf.app.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Max;
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

    @Max(1)
    private Integer dicFlag;

    @Size(max = 255)
    private String dicDescription;

   

    private Float dicOrder;

    private Boolean isLeaf;

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

  

    public String getDicDescription() {
        return dicDescription;
    }

    public void setDicDescription(String dicDescription) {
        this.dicDescription = dicDescription;
    }

    public Float getDicOrder() {
        return dicOrder;
    }

    public void setDicOrder(Float dicOrder) {
        this.dicOrder = dicOrder;
    }

    public Long getUpperId() {
        return upperId;
    }

    public void setUpperId(Long dictionaryId) {
        this.upperId = dictionaryId;
    }
    
    

    public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
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

   

	public Integer getDicFlag() {
		return dicFlag;
	}

	public void setDicFlag(Integer dicFlag) {
		this.dicFlag = dicFlag;
	}
	
	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "DictionaryDTO [id=" + id + ", dicKey=" + dicKey + ", dicValue=" + dicValue + ", dicFlag=" + dicFlag
				+ ", dicDescription=" + dicDescription + ", dicOrder=" + dicOrder + ", isLeaf=" + isLeaf
				+ ", dicDisabled=" + dicDisabled + ", upperId=" + upperId + "]";
	}
	
	

}
