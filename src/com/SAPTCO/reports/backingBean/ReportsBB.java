package com.SAPTCO.reports.backingBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.reports.bao.ReportBao;

/**
 * @author Shady
 */

@ManagedBean(name="reportsBB")
@ViewScoped
public abstract class ReportsBB extends BaseBB{

	@ManagedProperty("#{reportBao}")
	private ReportBao reportBao;
	private static final long serialVersionUID = 1L;
	protected Connection conn = null;
	
	public ReportsBB() {
	}
	
	//call from other backingBeans
	public void generateReport(Boolean autoPrint,int printCount) throws Exception{
		conn = getConnection();
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_lang",request.getSession().getAttribute("locale"));
		
		map.put("p_branch_desc",getUserDetails().getLoggedInBranch().getLabel());
		map.put("p_userid",getUserDetails().getId());
		map.put("p_username",getUserDetails().getUserName());

		map.put("system_name",getUserDetails().getLoggedInSystem().getLabel());
		map.put("companyname",getFacesBundleString(SystemConstants.Report_Boundle,SystemConstants.Company_Name));
		map.put("logopath",context.getRealPath(SystemConstants.driverAllowance_IMAGE));
		
		map.put("title",getFacesBundleString(SystemConstants.Report_Boundle,getReportInfo().getReportTitle()));
		map.put("commonHeaderVerticalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Vertical.getReportName()+SystemConstants.Rerport_Extention));
		map.put("commonHeaderHorizontalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Horizontal.getReportName()+SystemConstants.Rerport_Extention));
		map.put(JRParameter.REPORT_LOCALE.toString(),FacesContext.getCurrentInstance().getViewRoot().getLocale());
		map = getReportParameterMap(map);
		
		FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fctx.getExternalContext().getResponse();
        
        //JasperDesign jasperDesign = JRXmlLoader.load(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //JasperPrint print = JasperFillManager.fillReportToFile(jasperReport, new HashMap<>()); 
        
        //jasperReport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        //Maybe this too, but not positive
        //jasperReport.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        
        //JasperPrint jspprnt = JasperFillManager.fillReport(jasperReport,map,conn);
        JasperPrint jspprnt;
        @SuppressWarnings("rawtypes")
		List result = getServiceResult();
        if(result != null && !result.isEmpty()){
        	JRDataSource dataSource= getJRDataSource(result);
        	jspprnt = JasperFillManager.fillReport(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention),map,dataSource);
        }else
        	jspprnt = JasperFillManager.fillReport(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention),map,conn);
        	// jspprnt.setProperty( "net.sf.jasperreports.query.executer.factory.plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        
        
        response.setContentType("appication/pdf");
        response.addHeader("Content-Disposition", "attachment; filename= report.pdf");
		OutputStream responseOutputStream = response.getOutputStream();
		JRPdfExporter exporterPDF = new JRPdfExporter();
		exporterPDF.setParameter(JRPdfExporterParameter.JASPER_PRINT, jspprnt); 
		exporterPDF.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, responseOutputStream);
		if(autoPrint){
			exporterPDF.setParameter(JRPdfExporterParameter.PDF_JAVASCRIPT, "this.print();");
        }
        //JasperExportManager.exportReportToPdfStream(jspprnt,responseOutputStream);
        exporterPDF.exportReport();
        fctx.responseComplete();
        try{
        	conn.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
      }
	
	public InputStream getInputStream(ReportInfo reportInfo){
		InputStream reportStream=null;
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		File path = new File(context.getRealPath(SystemConstants.Report_Path+reportInfo.getReportName()+SystemConstants.Rerport_Extention));
		
		try{
			reportStream = new FileInputStream(path);					
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reportStream;
	}
	
	public void generateExcelReport() throws Exception{
		conn = getConnection();
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_lang",request.getSession().getAttribute("locale"));
		map.put("p_branch_desc",getUserDetails().getLoggedInBranch().getLabel());
		map.put("p_userid",getUserDetails().getId());
		map.put("p_username",getUserDetails().getUserName());
		map.put("system_name",getUserDetails().getLoggedInSystem().getLabel());
		map.put("companyname",getFacesBundleString(SystemConstants.Report_Boundle,SystemConstants.Company_Name));
		map.put("logopath",context.getRealPath(SystemConstants.driverAllowance_IMAGE));
		map.put("title",getFacesBundleString(SystemConstants.Report_Boundle,getReportInfo().getReportTitle()));
		map.put("commonHeaderVerticalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Vertical.getReportName()+SystemConstants.Rerport_Extention));
		map.put("commonHeaderHorizontalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Horizontal.getReportName()+SystemConstants.Rerport_Extention));
		map.put(JRParameter.REPORT_LOCALE.toString(),FacesContext.getCurrentInstance().getViewRoot().getLocale());
		map.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		map = getReportParameterMap(map);
		FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fctx.getExternalContext().getResponse();
        JasperPrint jspprnt;
        @SuppressWarnings("rawtypes")
		List result = getServiceResult();
        if(result != null && !result.isEmpty()){
        	JRDataSource dataSource= getJRDataSource(result);
        	jspprnt = JasperFillManager.fillReport(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention),map,dataSource);
        }else
        	jspprnt = JasperFillManager.fillReport(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention),map,conn);
        response.setContentType("application/xls");
        response.addHeader("Content-Disposition", "attachment; filename= report.xls");
        
        OutputStream responseOutputStream = response.getOutputStream();
        JRXlsExporter exporterXLS = new JRXlsExporter(); 
        exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jspprnt); 
        exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, responseOutputStream); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); 
        exporterXLS.setParameter(JRXlsExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BACKGROUND, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE); 
        exporterXLS.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET,0);
        
        exporterXLS.exportReport(); 
      
        fctx.responseComplete();
        try{
        	conn.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}
	
	
	public void generateReportFromSP() throws Exception{
		conn = getConnection();
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("p_lang",request.getSession().getAttribute("locale"));
		
		map.put("p_branch_desc",getUserDetails().getLoggedInBranch().getLabel());
		map.put("p_userid",getUserDetails().getId());
		map.put("p_username",getUserDetails().getUserName());

		map.put("system_name",getUserDetails().getLoggedInSystem().getLabel());
		map.put("companyname",getFacesBundleString(SystemConstants.Report_Boundle,SystemConstants.Company_Name));
		map.put("logopath",context.getRealPath(SystemConstants.driverAllowance_IMAGE));
		
		map.put("title",getFacesBundleString(SystemConstants.Report_Boundle,getReportInfo().getReportTitle()));
		map.put("commonHeaderVerticalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Vertical.getReportName()+SystemConstants.Rerport_Extention));
		map.put("commonHeaderHorizontalReport",context.getRealPath(SystemConstants.Report_Path+ReportInfo.Header_Horizontal.getReportName()+SystemConstants.Rerport_Extention));
		map.put(JRParameter.REPORT_LOCALE.toString(),FacesContext.getCurrentInstance().getViewRoot().getLocale());
		map = getReportParameterMap(map);
		
		FacesContext fctx = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fctx.getExternalContext().getResponse();
        
        //JasperDesign jasperDesign = JRXmlLoader.load(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //JasperPrint print = JasperFillManager.fillReportToFile(jasperReport, new HashMap<>()); 
        
        //jasperReport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        //Maybe this too, but not positive
        //jasperReport.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        
        //JasperPrint jspprnt = JasperFillManager.fillReport(jasperReport,map,conn);
        
        //JasperPrint jspprnt = JasperFillManager.fillReport(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention),map,conn);
        // jspprnt.setProperty( "net.sf.jasperreports.query.executer.factory.plsql","com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
        
        //////////////////////////////////////////////////////////////////
        @SuppressWarnings("deprecation")
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(context.getRealPath(SystemConstants.Report_Path+getReportInfo().getReportName()+SystemConstants.Rerport_Extention));
        
        /* This line sets correct factory to parse and process PLSQL procedure calls.
           The PlSqlQueryExecuterFactory class is located in jasperreports-extensions-3.5.3.jar */
      
        jasperReport.setProperty( "net.sf.jasperreports.query.executer.factory.plsql"
                                 ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
      
        /* You may need to execute following line just once in your application.
          You can try to move it into initialization or static code block.
          Also you may notice that line below is marked as deprecated. 
          However in my case the export to pdf fails without this line being executed. */
      
        JRProperties.setProperty( JRQueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX+"plsql"
                                ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
      
        /* Prepare Jasper print and exporter objects in lines below */
      
        JasperPrint print = JasperFillManager.fillReport(jasperReport, map, conn);
        
        
        response.setContentType("appication/pdf");
        response.addHeader("Content-Disposition", "attachment; filename= report.pdf");
		OutputStream responseOutputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print,responseOutputStream);
        fctx.responseComplete();
        try{
        	conn.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
      }
	
	//make connection
	abstract protected Connection getConnection() throws Exception;
	
	public Connection connectionDA() throws Exception{
		return reportBao.getConnectionDetails(1);
	}
	
	public Connection connectionCostAnalysis() throws Exception{
		return reportBao.getConnectionDetails(2);
	}
	
	public Connection connectionTR() throws Exception{
		return reportBao.getConnectionDetails(3);
	}
	
	public Connection connectionLostLuggage() throws Exception{
		return reportBao.getConnectionDetails(4);
	}
	
	public Connection connectionFA() throws Exception{
		return reportBao.getConnectionDetails(5);
	}
	
	public Connection connectionUrbanfest() throws Exception{
		return reportBao.getConnectionDetails(6);
	}
	
	public Connection connectionHajLimo() throws Exception{
		return reportBao.getConnectionDetails(7);
	}
	
	abstract protected ReportInfo getReportInfo();

	abstract protected Map<String, Object> getReportParameterMap(Map<String, Object> map) throws Exception;

	@SuppressWarnings({ "rawtypes" })
	abstract protected List getServiceResult();
	
	@SuppressWarnings({ "rawtypes" })
	protected JRDataSource getJRDataSource(List dataResultList){
		if (dataResultList == null) {
			return new JRBeanArrayDataSource(Collections.emptyList().toArray());
		} else {
			return new JRBeanArrayDataSource(dataResultList.toArray());
		}
	}

	@SuppressWarnings({ "rawtypes" })
	protected JRBeanCollectionDataSource getJRBeanCollectionDataSource(
			List dataResultList) {
		if (dataResultList == null) {
			return new JRBeanCollectionDataSource(Collections.EMPTY_LIST);
		} else {		
			return new JRBeanCollectionDataSource(dataResultList);
		}
	}

	public ReportBao getReportBao() {
		return reportBao;
	}

	public void setReportBao(ReportBao reportBao) {
		this.reportBao = reportBao;
	}
	
}
