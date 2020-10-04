package com.SAPTCO.security.backingBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserInfBean;
import com.SAPTCO.security.bao.UsersBao;
import com.SAPTCO.security.dto.UsersDto;

/**
*
* @author Shady
*/

@ManagedBean(name="usersBB")
@ViewScoped
public class UsersBB extends BaseBB{
	
	@ManagedProperty("#{usersBao}")
	private UsersBao usersBao;
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UsersDto usersDto = new UsersDto();
	private List<UserBean> usersTable = new ArrayList<UserBean>();
	private List<UserBranchesBean> usersBranchesTable = new ArrayList<UserBranchesBean>();
	private List<BranchesBean> branchList = new ArrayList<BranchesBean>();
	private List<UserInfBean> employeesTable = new ArrayList<UserInfBean>();
	private List<ProgramGroupsBean> programGroupsList = new ArrayList<ProgramGroupsBean>();
	private Boolean manageMode = false;
	
	@PostConstruct
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,Object> params = fc.getExternalContext().getRequestMap();
		if(params.get("usersDto") != null){
			usersDto = (UsersDto) params.get("usersDto");
			try{
				searchUserBranches();
				branchList = usersBao.getBranchesUser(usersDto).getBranchesList();
				programGroupsList = usersBao.getProgramsUser(usersDto).getProgramGroupsList();
			}catch (Exception e) {
				logger.error(e.getMessage(), e);
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Process_Exception,
					FacesMessage.SEVERITY_ERROR);
			}
		}else
			usersSearchListner();
			
	}
	
	public String openUserBranches(){
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("usersDto", usersDto);
		return "userBranches.xhtml";
	}

	public void usersSearchListner() {
		try {
			//return codes list
			usersTable = new ArrayList<UserBean>((usersBao.getUsersByName(usersDto)).getUsersList());
			if(usersTable == null && usersTable.isEmpty())
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Load_Data_Faild,
					FacesMessage.SEVERITY_ERROR);
			usersDto.setUserName(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addListner() {
		try {
			manageMode = true;
			usersDto.setUserObj(new UserBean());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void editListner() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("ur") != null){
				usersDto.setUserObj((UserBean)params.get("ur"));
				manageMode = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void employeeSearchByName(){
		try {
			List<UserInfBean> empList = new ArrayList<UserInfBean>(usersBao.getEmployees(usersDto).getEmployeesList());
			if(empList != null && !empList.isEmpty())
				setEmployeesTable(empList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void saveListner(){
		try{
			if(usersDto.getUserObj().getExpireDate() != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				usersDto.getUserObj().setExpireDateString(df.format(usersDto.getUserObj().getExpireDate()));
			}else
				usersDto.getUserObj().setExpireDateString(null);
			if(usersDto.getUserObj().getId() == null || usersDto.getUserObj().getId().equals(0L)){
				//add code
				List<UserBean> usersList = new ArrayList<UserBean>((usersBao.getUsersByName(usersDto)).getUsersList());
				if(usersList != null && !usersList.isEmpty()){
					usersDto.getUserObj().setUserName(null);
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Ununique_Faild,
						FacesMessage.SEVERITY_ERROR);
				}else if(usersDto.getUserObj().getUserInf().getUserType().equals("I") && usersDto.getUserObj().getUserInf().getId() == null){
					addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Users.required_employeeNum",
							FacesMessage.SEVERITY_ERROR);
				}else if(usersDto.getUserObj().getUserInf().getUserType().equals("E") && usersDto.getUserObj().getUserInf().getUserFullName() == null){
					addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Users.required_userFullName",
							FacesMessage.SEVERITY_ERROR);
				}else{
					usersDto = usersBao.insertUser(usersDto);
					if(usersDto.getProcResult() != null && !usersDto.getProcResult().equals("") && !usersDto.getProcResult().equals("Y")){
						addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							usersDto.getProcResult(),
							FacesMessage.SEVERITY_ERROR);
					}else{
						manageMode = false;
						usersDto.setUserObj(new UserBean());
						addMessage(
								SystemConstants.Error_RESOURCE_BUNDLE,
								SystemConstants.Add_Success);
					}
				}
			}else{
				//edit code
				usersBao.updateUser(usersDto);
				manageMode = false;
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Edit_Success);
			}
			usersSearchListner();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deleteListner(){
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("ur") != null){
				usersDto.setUserObj((UserBean)params.get("ur"));
				usersBao.deleteUser(usersDto);
				usersSearchListner();
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Delete_Success);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void searchUserBranches(){
		try {
			usersBranchesTable = new ArrayList<UserBranchesBean>(usersBao.getUserBranches(usersDto).getUserBranches());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addUserBranch(){
		manageMode = true;
		usersDto.setUserBranchObj(new UserBranchesBean());
	}
	
	public void editUserBranch(){
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("ub") != null){
				usersDto.setUserBranchObj((UserBranchesBean)params.get("ub"));
				manageMode = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void saveBranchListner(){
		try{
			SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
			usersDto.getUserBranchObj().setFromDateString(df.format(usersDto.getUserBranchObj().getFromDate()));
			if(usersDto.getUserBranchObj().getToDate() != null)
				usersDto.getUserBranchObj().setToDateString(df.format(usersDto.getUserBranchObj().getToDate()));
			else
				usersDto.getUserBranchObj().setToDateString(null);
			if(usersDto.getUserBranchObj().getId() == null || usersDto.getUserBranchObj().getId().equals(0L)){
				if(usersBao.validateUserBranch(usersDto)){
					usersDto.setFromDate(null);
					usersDto.setToDate(null);
					usersDto.getUserBranchObj().setFromDate(null);
					usersDto.getUserBranchObj().setToDate(null);
					usersDto.getUserBranchObj().getBranchId().setId(null);
					addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"duplicate_data",
							FacesMessage.SEVERITY_ERROR);
				}else{
					usersBao.insertUserBranch(usersDto);
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
				}
			}else{
				usersBao.editUserBranch(usersDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Edit_Success);
			}
			usersDto.setUserBranchObj(new UserBranchesBean());
			searchUserBranches();
			manageMode = false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void resetPassword(){
		try{
			usersBao.resetPassword(usersDto);
			manageMode = false;
			addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Edit_Success);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cancelManage(){
		manageMode = false;
	}

	public UsersBao getUsersBao() {
		return usersBao;
	}

	public void setUsersBao(UsersBao usersBao) {
		this.usersBao = usersBao;
	}

	public UsersDto getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(UsersDto usersDto) {
		this.usersDto = usersDto;
	}

	public Boolean getManageMode() {
		return manageMode;
	}

	public void setManageMode(Boolean manageMode) {
		this.manageMode = manageMode;
	}

	public List<UserBranchesBean> getUsersBranchesTable() {
		return usersBranchesTable;
	}

	public void setUsersBranchesTable(List<UserBranchesBean> usersBranchesTable) {
		this.usersBranchesTable = usersBranchesTable;
	}

	public List<BranchesBean> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BranchesBean> branchList) {
		this.branchList = branchList;
	}

	public List<UserInfBean> getEmployeesTable() {
		return employeesTable;
	}

	public void setEmployeesTable(List<UserInfBean> employeesTable) {
		this.employeesTable = employeesTable;
	}
	public List<UserBean> getUsersTable() {
		return usersTable;
	}

	public void setUsersTable(List<UserBean> usersTable) {
		this.usersTable = usersTable;
	}

	public List<ProgramGroupsBean> getProgramGroupsList() {
		return programGroupsList;
	}

	public void setProgramGroupsList(List<ProgramGroupsBean> programGroupsList) {
		this.programGroupsList = programGroupsList;
	}
}
