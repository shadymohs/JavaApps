package com.SAPTCO.common.dto;

/**
 * @author Shady
*/

public class GeneralDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private Long branchId;
	private Long userId;
	private String tableName;
	private String columnName;
	private String procResult;
	private Long customerId;
	private Long customerBranchId;
	
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getCustomerBranchId() {
		return customerBranchId;
	}
	public void setCustomerBranchId(Long customerBranchId) {
		this.customerBranchId = customerBranchId;
	}

}