package com.leidos.te.web.models;

import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ParameterCollection 
{

	@JacksonXmlProperty(localName = "param")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

	public ArrayList<Parameter> getParameters() 
	{
		return parameters;
	}
	
}
