package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class LostLuggageStatus extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean showInLuggage;
	private boolean showInComplaint;
	
	
	public boolean isShowInLuggage() {
		return showInLuggage;
	}
	public void setShowInLuggage(boolean showInLuggage) {
		this.showInLuggage = showInLuggage;
	}
	public boolean isShowInComplaint() {
		return showInComplaint;
	}
	public void setShowInComplaint(boolean showInComplaint) {
		this.showInComplaint = showInComplaint;
	}
	

}
