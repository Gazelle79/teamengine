package com.leidos.te.web.models;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Group 
{

	@JacksonXmlProperty(localName = "group", isAttribute = false)
	public String getGroupName() 
	{
		return groupName;
	}
	private String groupName = "";
	
}
