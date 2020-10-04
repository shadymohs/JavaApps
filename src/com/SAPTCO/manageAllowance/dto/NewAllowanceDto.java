/**
 * 
 */
package com.SAPTCO.manageAllowance.dto;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Sanadhr.acs
 *
 */
public class NewAllowanceDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private Long oldDriverAllowance ;
	private Long driver1Number;
	private Long driver2Number;
	private String busNumber;
	private Long newDriver1Number;
	private Long newDriver2Number;
	private String newBusNumber;
	private String changeReason ;
	private String procResult;
	private String wsURL;
	
	/**
	 * @return the changeReason
	 */
	public String getChangeReason() {
		return changeReason;
	}
	/**
	 * @param changeReason the changeReason to set
	 */
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	/**
	 * @return the oldDriverAllowance
	 */
	public Long getOldDriverAllowance() {
		return oldDriverAllowance;
	}
	/**
	 * @param oldDriverAllowance the oldDriverAllowance to set
	 */
	public void setOldDriverAllowance(Long oldDriverAllowance) {
		this.oldDriverAllowance = oldDriverAllowance;
	}
	/**
	 * @return the driver1Number
	 */
	public Long getDriver1Number() {
		return driver1Number;
	}
	/**
	 * @param driver1Number the driver1Number to set
	 */
	public void setDriver1Number(Long driver1Number) {
		this.driver1Number = driver1Number;
	}
	/**
	 * @return the driver2Number
	 */
	public Long getDriver2Number() {
		return driver2Number;
	}
	/**
	 * @param driver2Number the driver2Number to set
	 */
	public void setDriver2Number(Long driver2Number) {
		this.driver2Number = driver2Number;
	}
	/**
	 * @return the newDriver1Number
	 */
	public Long getNewDriver1Number() {
		return newDriver1Number;
	}
	/**
	 * @param newDriver1Number the newDriver1Number to set
	 */
	public void setNewDriver1Number(Long newDriver1Number) {
		this.newDriver1Number = newDriver1Number;
	}
	/**
	 * @return the newDriver2Number
	 */
	public Long getNewDriver2Number() {
		return newDriver2Number;
	}
	/**
	 * @param newDriver2Number the newDriver2Number to set
	 */
	public void setNewDriver2Number(Long newDriver2Number) {
		this.newDriver2Number = newDriver2Number;
	}
	/**
	 * @return the procResult
	 */
	public String getProcResult() {
		return procResult;
	}
	/**
	 * @param procResult the procResult to set
	 */
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getNewBusNumber() {
		return newBusNumber;
	}
	public void setNewBusNumber(String newBusNumber) {
		this.newBusNumber = newBusNumber;
	}
	public String getWsURL() {
		return wsURL;
	}
	public void setWsURL(String wsURL) {
		this.wsURL = wsURL;
	}
}
