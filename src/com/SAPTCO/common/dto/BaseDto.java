package com.SAPTCO.common.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.faces.model.SelectItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.SAPTCO.common.backingBean.BaseBB;

public class BaseDto extends SelectItem implements Serializable,Comparable<BaseDto>{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String code;
	private String localeName;
	private String foreignName;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp updatedDate;
	private Long updatedBy;
	private Boolean isActive;
	private Boolean isManageMode = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocaleName() {
		return localeName;
	}
	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}
	public String getForeignName() {
		return foreignName;
	}
	public void setForeignName(String foreignName) {
		this.foreignName = foreignName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Boolean getIsActive() {
		if(isActive == null)
			isActive = false;
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String getLabel() {
		if (BaseBB.isARLang())
			return  localeName;
		else
			return  (foreignName== null  ||  foreignName.equals("")) ? localeName : foreignName;
	}
	
	@Override
	public Object getValue() {
		return id;
	}

	@Override
	 public String toString() { 
	  return ToStringBuilder.reflectionToString(this,
	    ToStringStyle.SIMPLE_STYLE);
	 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;  
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseDto other = (BaseDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * Returns a Deep Copy from this object , using Serialization .
	 * @return
	 */
	public Object getDeepCopy (){
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object deepCopy = ois.readObject();
			return deepCopy;
		}catch (NotSerializableException e) {
			return new Object();
		}catch (Exception e) {
			return new Object();
		}
	}

	public int compareTo(BaseDto o) {
		if (this.localeName ==null && o.localeName == null)
			return 0;
		if (this.localeName == null) //means that o.localeName !=null
			return -1;
		return this.localeName.compareTo(o.localeName);
	}
	public Boolean getIsManageMode() {
		return isManageMode;
	}
	public void setIsManageMode(Boolean isManageMode) {
		this.isManageMode = isManageMode;
	}
}
