package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import org.w3c.dom.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;
import com.leidos.te.web.models.*;

public class Attribute 
{
	public String getName() 
	{
		return name;
	}
	
	public String getData() 
	{
		return data;
	}

	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name = "";
	
	@JacksonXmlText
	@JacksonXmlCData
	private String data = ""; //Get data from CDATA
	
}
