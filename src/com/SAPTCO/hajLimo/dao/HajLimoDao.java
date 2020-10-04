package com.SAPTCO.hajLimo.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.LimoHajBean;
import com.SAPTCO.hajLimo.dto.HajLimoDto;
import com.SAPTCO.hajLimo.dto.HajLimoWSDto;

public interface HajLimoDao {
	
	public HajLimoDto getLookups() throws Exception;
	public HajLimoDto getToStations(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto getvehicls(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto calculatePrice(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto createTickets(HajLimoDto hajLimoDto) throws Exception;
	public Boolean validateEtimad(String etimadNumber) throws Exception;
	public HajLimoWSDto dispatchTicket(Long userId, String ticketNumber) throws Exception;
	public HajLimoWSDto arrivalTicket(Long userId, String ticketNumber) throws Exception;
	public void printTicket(String ticketNumber) throws Exception;
	public Long validateUser(String userName, String password) throws Exception;
	public String cancelTicket(String ticketNumber) throws Exception;
	public HajLimoWSDto generateBatchNumber() throws Exception;
	public List<LimoHajBean> getFinIntegrationList() throws Exception;
	public void updateTicketFinIntegration(String ticketNumber) throws Exception;

}
