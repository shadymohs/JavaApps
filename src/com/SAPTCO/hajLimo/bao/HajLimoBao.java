package com.SAPTCO.hajLimo.bao;

import com.SAPTCO.hajLimo.dto.HajLimoDto;

public interface HajLimoBao {
	
	public HajLimoDto getLookups() throws Exception;
	public HajLimoDto getToStations(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto getvehicls(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto calculatePrice(HajLimoDto hajLimoDto) throws Exception;
	public HajLimoDto createTickets(HajLimoDto hajLimoDto) throws Exception;
	public Boolean validateEtimad(String etimadNumber) throws Exception;
	public void printTicket(String ticketNumber) throws Exception;
	public String cancelTicket(String ticketNumber) throws Exception;
	
}
