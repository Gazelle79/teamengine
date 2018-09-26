package com.leidos.te.web.models;

import java.util.*;
import com.fasterxml.jackson.dataformat.xml.annotation.*;

import com.leidos.te.web.models.*;

public class AttributeCollection 
{

	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	@JacksonXmlProperty(localName = "attribute")
	@JacksonXmlElementWrapper(useWrapping = false)
	public ArrayList<Attribute> getAttributes() 
	{
		return attributes;
	} 
	
}
