package com.SAPTCO.hajLimo.endPoints;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.SAPTCO.hajLimo.dto.HajLimoWSDto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface LimoHajWS{

    @WebMethod
    public Long authorizeUser(String userName, String password); 

    @WebMethod
    public HajLimoWSDto dispatch(Long userId,String ticketNumber);
    
    @WebMethod
    public HajLimoWSDto arrival(Long userId, String ticketNumber);

}