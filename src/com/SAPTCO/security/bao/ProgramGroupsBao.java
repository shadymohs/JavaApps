package com.SAPTCO.security.bao;

import com.SAPTCO.security.dto.ProgramGroupsDto;

/**
 * @author Shady
*/

public interface ProgramGroupsBao{
	
	public ProgramGroupsDto getProgramGroupsByCode(ProgramGroupsDto programGroupsDto) throws Exception;
	public void insertProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception;
	public void updateProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception;
	public void deleteProgramGroups(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto getProgramGroupPages(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto getProgramGroupUnselectedPages(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto insertProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto deleteProgramGroupMenu(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto getAllProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto getCurrentProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto) throws Exception;
	public ProgramGroupsDto updateProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto) throws Exception;
	
}