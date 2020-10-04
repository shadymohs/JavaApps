package com.SAPTCO.security.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.ProgramGroupsDao;
import com.SAPTCO.security.dto.ProgramGroupsDto;

/**
 * @author Shady
 */

@Repository("programGroupsDao")
public class ProgramGroupsDaoImpl  implements ProgramGroupsDao{
	
	@Autowired
	SqlSession session;

	public ProgramGroupsDto getProgramGroupsByCode(ProgramGroupsDto programGroupsDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		programGroupsDto.setProgramGroupsList(mapper.searchProgramGroups(programGroupsDto));
		return programGroupsDto;
	}
	
	public void insertProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDto.getProgramGroupsObj().setCreatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.insertProgramGroups(programGroupsDto);
	}
	
	public void updateProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDto.getProgramGroupsObj().setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.updateProgramGroups(programGroupsDto);
	}

	public void deleteProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		Security mapper = session.getMapper(Security.class);
		mapper.deleteProgramGroups(programGroupsDto);
	}

	public ProgramGroupsDto getProgramGroupPages(ProgramGroupsDto programGroupsDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		programGroupsDto.setMenuProgramGroups(mapper.getProgramGroupPages(programGroupsDto));
		return programGroupsDto;
	}

	public ProgramGroupsDto getProgramGroupUnselectedPages(ProgramGroupsDto programGroupsDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		programGroupsDto.setSystemMenuList(mapper.getProgramGroupUnselectedPages(programGroupsDto));
		return programGroupsDto;
	}

	public ProgramGroupsDto insertProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDto.setCreatedBy(BaseBB.getUserDetails().getId());
		for(Long menuId:programGroupsDto.getSelectedPages()){
			if(programGroupsDto.getSelectedPagesString() != null && !programGroupsDto.getSelectedPagesString().equals(""))
				programGroupsDto.setSelectedPagesString(programGroupsDto.getSelectedPagesString() +"," + menuId.toString());
			else
				programGroupsDto.setSelectedPagesString(menuId.toString());
		}
		Security mapper = session.getMapper(Security.class);
		mapper.insertProgramGroupMenu(programGroupsDto);
		return programGroupsDto;
	}

	public ProgramGroupsDto deleteProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception {
		Security mapper = session.getMapper(Security.class);
		mapper.deleteProgramGroupMenu(programGroupsDto);
		return programGroupsDto;
	}

	public ProgramGroupsDto getAllProgramGroupPagePrivelege(
		ProgramGroupsDto programGroupsDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		programGroupsDto.setPrivilegesList(mapper.getAllProgramGroupPagePrivelege(programGroupsDto));
		return programGroupsDto;
	}

	public ProgramGroupsDto getCurrentProgramGroupPagePrivelege(
		ProgramGroupsDto programGroupsDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		List<PrivilegeBean> currentPrivileges = mapper.getCurrentProgramGroupPagePrivelege(programGroupsDto);
		Long[] privilegesList = new Long[currentPrivileges.size()];
		for(int i = 0;i < currentPrivileges.size();i++)
			privilegesList[i] = currentPrivileges.get(i).getId();
		programGroupsDto.setSelectedPrivilege(privilegesList);
		return programGroupsDto;
	}

	public ProgramGroupsDto updateProgramGroupPagePrivelege(
		ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDto.setCreatedBy(BaseBB.getUserDetails().getId());
		for(Long privilegeId:programGroupsDto.getSelectedPrivilege()){
			if(programGroupsDto.getSelectedPrivilegeString() != null && !programGroupsDto.getSelectedPrivilegeString().equals(""))
				programGroupsDto.setSelectedPrivilegeString(programGroupsDto.getSelectedPrivilegeString() +"," + privilegeId.toString());
			else
				programGroupsDto.setSelectedPrivilegeString(privilegeId.toString());
		}
		Security mapper = session.getMapper(Security.class);
		mapper.updateProgramGroupPagePrivelege(programGroupsDto);
		return programGroupsDto;
	}	
}
