package com.SAPTCO.manageAllowance.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;


/**
 * @author Shady Mohsen
 *
 */
public class FileUploadDto extends BaseDto{

	
	private static final long serialVersionUID = 1L;

	private List<AllowanceDetailBean> rejectedAllowance = new ArrayList<AllowanceDetailBean>();
	private List<AllowanceDetailBean> succedAllowance = new ArrayList<AllowanceDetailBean>();
	private Integer totalRows = 0;
	private Integer succedRows = 0;
	private Integer rejectedRows = 0;
	private Integer succedIntegreted = 0;
	private Integer rejectedIntegreted = 0;

	public List<AllowanceDetailBean> getRejectedAllowance() {
		return rejectedAllowance;
	}

	public void setRejectedAllowance(List<AllowanceDetailBean> rejectedAllowance) {
		this.rejectedAllowance = rejectedAllowance;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getSuccedRows() {
		return succedRows;
	}

	public void setSuccedRows(Integer succedRows) {
		this.succedRows = succedRows;
	}

	public List<AllowanceDetailBean> getSuccedAllowance() {
		return succedAllowance;
	}

	public void setSuccedAllowance(List<AllowanceDetailBean> succedAllowance) {
		this.succedAllowance = succedAllowance;
	}

	public Integer getRejectedRows() {
		return rejectedRows;
	}

	public void setRejectedRows(Integer rejectedRows) {
		this.rejectedRows = rejectedRows;
	}

	public Integer getSuccedIntegreted() {
		return succedIntegreted;
	}

	public void setSuccedIntegreted(Integer succedIntegreted) {
		this.succedIntegreted = succedIntegreted;
	}

	public Integer getRejectedIntegreted() {
		return rejectedIntegreted;
	}

	public void setRejectedIntegreted(Integer rejectedIntegreted) {
		this.rejectedIntegreted = rejectedIntegreted;
	} 
}