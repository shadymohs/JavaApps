package com.SAPTCO.common.dto;
		
/**
 * @author Shady
*/

public enum ProgramPrivelegeEnum {

	VIEW(new Long(1L)),
	ADD(new Long(2L)),
	UPDATE(new Long(3L)),
	DELETE(new Long(4L)),
	SEARCH_ALL(new Long(5L)),
	PRINT(new Long(6L)),
	Detail(new Long(7L)),
	Send_to_HR(new Long(8L)),
	Change_price(new Long(9L)),
	REMOVE_LINK(new Long(10L))
	;
	
	private final Long id;
	
	ProgramPrivelegeEnum(Long id ){
		this.id=id;
	}
	
	public Long getId(){
		return id;
	}
	
	public static ProgramPrivelegeEnum getById(String id)throws Exception{
		for (ProgramPrivelegeEnum c: ProgramPrivelegeEnum.values())
			if (c.getId().equals(id))
				return c;
		throw new Exception();
	}
}
