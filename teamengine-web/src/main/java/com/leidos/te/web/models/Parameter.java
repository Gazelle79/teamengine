package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;


public class Parameter 
{
	public int getIndex() 
	{
		return index;
	}
	
	public ArrayList<String> getValues() 
	{
		return values;
	}
	
	@JacksonXmlProperty(localName = "index", isAttribute = true)
	private int index = 0;
	
	@JacksonXmlProperty(localName = "value")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<String> values = null;
	
}
