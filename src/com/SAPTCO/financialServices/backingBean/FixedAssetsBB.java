package com.SAPTCO.financialServices.backingBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.ibatis.mapperBeans.FixedAssets;
import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.financialServices.bao.FABao;
import com.SAPTCO.financialServices.dto.FADto;

/**
*
* @author Shady
*/

@ManagedBean(name="fABB")
@ViewScoped
public class FixedAssetsBB extends ReportsBB{

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{fABao}")
	private FABao fABao;
	private FADto fADto = new FADto();
	private List<FixedAssets> fixedAssets = new ArrayList<FixedAssets>();
	private Boolean isNewReport = false;
	private String receiptNo;
	
	public void searchFixedAssets(){
		try{
			fixedAssets = fABao.getFixedAssets(fADto);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void viewReport(){
		try{
			isNewReport = false;
			generateReport(false,0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void viewReportReceipt(){
		try{
			isNewReport = true;
			fABao.excuteReceipts(receiptNo);
			generateReport(false,0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected ReportInfo getReportInfo() {
		if(isNewReport)
			return ReportInfo.Receipts;
		else
			return ReportInfo.FA;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		if(isNewReport)
			map.put("receiptNo", receiptNo);
		else{
			map.put("tagNum",fADto.getTagNum());
			map.put("region",fADto.getRegion());
			map.put("assetNum",fADto.getAssetNum());
			map.put("empNum",fADto.getEmpNum());
			map.put("fromDept",fADto.getFromDept());
			map.put("toDept",fADto.getToDept());
			map.put("parentAsset",fADto.getParentAsset());
			map.put("parentTag",fADto.getParentTag());
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		
		return null;
	}

	public List<FixedAssets> getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(List<FixedAssets> fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public Boolean getIsNewReport() {
		return isNewReport;
	}

	public void setIsNewReport(Boolean isNewReport) {
		this.isNewReport = isNewReport;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public FADto getfADto() {
		return fADto;
	}

	public void setfADto(FADto fADto) {
		this.fADto = fADto;
	}

	@Override
	protected Connection getConnection() throws Exception {
		return connectionFA();
	}

	public FABao getfABao() {
		return fABao;
	}

	public void setfABao(FABao fABao) {
		this.fABao = fABao;
	}
}
