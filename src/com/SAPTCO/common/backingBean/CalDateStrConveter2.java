package com.SAPTCO.common.backingBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import com.SAPTCO.common.config.SystemConstants;

public class CalDateStrConveter2 implements Converter {
    private String pattern = SystemConstants.Date_Format2;
                //eg 02/02/2012 12:00  (note Rich:calendar has no support for seconds)

    public Object getAsObject(FacesContext context, UIComponent component, String value)
            throws ConverterException {

        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
   
        if(value!= null && value.length() > 0) {
            try {
                Date date = sdf.parse(value);
                result = sdf.format(date);
            } catch (Exception e) {
            }
        }

        return result;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value)
            throws ConverterException {

        String result = "";
        String valueStr = (String) value;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
       
        if (valueStr!= null && valueStr.length() > 0) {
            try {
                Date date = sdf.parse(valueStr);
                result = sdf.format(date);
            } catch (Exception e) {
            	//error
                }
        }
        return result;
    }

}