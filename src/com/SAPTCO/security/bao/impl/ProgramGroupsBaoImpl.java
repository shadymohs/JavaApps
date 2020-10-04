package com.SAPTCO.security.bao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.security.bao.ProgramGroupsBao;
import com.SAPTCO.security.dao.ProgramGroupsDao;
import com.SAPTCO.security.dto.ProgramGroupsDto;

/**
 * @author Shady
*/

@Service("programGroupsBao")
public class ProgramGroupsBaoImpl implements ProgramGroupsBao {

	@Autowired
	private ProgramGroupsDao programGroupsDao;

	public ProgramGroupsDao getProgramGroupsDao() {
		return programGroupsDao;
	}

	public void setProgramGroupsDao(ProgramGroupsDao programGroupsDao) {
		this.programGroupsDao = programGroupsDao;
	}

	public ProgramGroupsDto getProgramGroupsByCode(ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.getProgramGroupsByCode(programGroupsDto);
	}

	public void insertProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDao.insertProgramGroups(programGroupsDto);		
	}

	public void updateProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDao.updateProgramGroups(programGroupsDto);
	}

	public void deleteProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception {
		programGroupsDao.deleteProgramGroups(programGroupsDto);
	}

	public ProgramGroupsDto getProgramGroupPages(ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.getProgramGroupPages(programGroupsDto);
	}

	public ProgramGroupsDto getProgramGroupUnselectedPages(ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.getProgramGroupUnselectedPages(programGroupsDto);
	}

	public ProgramGroupsDto getAllProgramGroupPagePrivelege(
			ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.getAllProgramGroupPagePrivelege(programGroupsDto);
	}

	public ProgramGroupsDto getCurrentProgramGroupPagePrivelege(
			ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.getCurrentProgramGroupPagePrivelege(programGroupsDto);
	}

	public ProgramGroupsDto updateProgramGroupPagePrivelege(
			ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.updateProgramGroupPagePrivelege(programGroupsDto);
	}

	public ProgramGroupsDto insertProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.insertProgramGroupMenu(programGroupsDto);		
	}

	public ProgramGroupsDto deleteProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception {
		return programGroupsDao.deleteProgramGroupMenu(programGroupsDto);
	}



}