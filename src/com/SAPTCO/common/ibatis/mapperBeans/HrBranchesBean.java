/**
 * 
 */
package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Sanadhr.acs
 *
 */
public class HrBranchesBean  extends BaseDto{

	
	private static final long serialVersionUID = 1L;
	
	private Long hrBranchCode;
	private String hrBranchDes ;
	
	@Override
	public void setId(Long id) 
	{
		super.setId(id);
		hrBranchCode=id;
	}
	
	@Override
	public String getLabel() {
		if (BaseBB.isARLang())
			return  hrBranchDes +" - "+hrBranchCode;
		else
			return hrBranchCode + " - "+hrBranchDes;
	}
	
	/**
	 * @return the hrBranchCode
	 */
	public Long getHrBranchCode() {
		return hrBranchCode;
	}
	/**
	 * @param hrBranchCode the hrBranchCode to set
	 */
	public void setHrBranchCode(Long hrBranchCode) {
		this.hrBranchCode = hrBranchCode;
	}
	/**
	 * @return the hrBranchDes
	 */
	public String getHrBranchDes() {
		return hrBranchDes;
	}
	/**
	 * @param hrBranchDes the hrBranchDes to set
	 */
	public void setHrBranchDes(String hrBranchDes) {
		this.hrBranchDes = hrBranchDes;
	}
	
	
	
	
}
