package com.SAPTCO.hajLimo.bao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.hajLimo.bao.HajLimoBao;
import com.SAPTCO.hajLimo.dao.HajLimoDao;
import com.SAPTCO.hajLimo.dto.HajLimoDto;

@Service("hajLimoBao")
public class HajLimoBaoImpl implements HajLimoBao{

	
	@Autowired
	private HajLimoDao hajLimoDao;

	public HajLimoDao getHajLimoDao() {
		return hajLimoDao;
	}


	public void setHajLimoDao(HajLimoDao hajLimoDao) {
		this.hajLimoDao = hajLimoDao;
	}


	@Override
	public HajLimoDto getLookups() throws Exception {
		return hajLimoDao.getLookups();
	}


	@Override
	public HajLimoDto getToStations(HajLimoDto hajLimoDto) throws Exception {
		return hajLimoDao.getToStations(hajLimoDto);
	}


	@Override
	public HajLimoDto getvehicls(HajLimoDto hajLimoDto) throws Exception {
		return hajLimoDao.getvehicls(hajLimoDto);
	}


	@Override
	public HajLimoDto calculatePrice(HajLimoDto hajLimoDto) throws Exception {
		return hajLimoDao.calculatePrice(hajLimoDto);
	}


	@Override
	public HajLimoDto createTickets(HajLimoDto hajLimoDto) throws Exception {
		return hajLimoDao.createTickets(hajLimoDto);
	}


	@Override
	public Boolean validateEtimad(String etimadNumber) throws Exception {
		return hajLimoDao.validateEtimad(etimadNumber);
	}


	@Override
	public void printTicket(String ticketNumber) throws Exception {
		hajLimoDao.printTicket(ticketNumber);
	}


	@Override
	public String cancelTicket(String ticketNumber) throws Exception {
		return hajLimoDao.cancelTicket(ticketNumber);
	}

}
