package com.SAPTCO.common.ibatis.mapperInterface;

import java.util.List;
import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.MenuBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;

/**
 * @author Shady
 */

public interface Common {

	public List<BranchesBean> findUserbranchesByName(CommonDto loginDto);
	public List<SystemBean> findUserSystemsByName(CommonDto loginDto);
	public CommonDto authenticationUser(CommonDto loginDto);
	public List<MenuBean> retrieveAuthorizedPrograms(CommonDto loginDto);
	
}
