package com.leidos.te.web.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ExceptionType 
{
	
	@JacksonXmlProperty(localName = "class", isAttribute = true)
	private String exceptionClass = "";
	
	@JacksonXmlProperty(localName = "message")
    @JacksonXmlElementWrapper(useWrapping = false)
	private String message = "";
	
	
	public String getExceptionClass() 
	{
		return exceptionClass;
	}
	
	public String getMessage() 
	{
		return message;
	}
	
	

	
}
