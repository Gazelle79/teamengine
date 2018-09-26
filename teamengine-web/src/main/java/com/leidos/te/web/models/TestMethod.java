package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;


import com.fasterxml.jackson.dataformat.xml.annotation.*;


public class TestMethod 
{
	public enum Status {PASS, FAIL, SKIP}
	
	@JacksonXmlProperty(localName = "duration-ms", isAttribute = true)
	private int durationInMilliseconds = 0;
	@JacksonXmlProperty(localName = "started-at", isAttribute = true)
	private String startTime = "";
	@JacksonXmlProperty(localName = "finished-at", isAttribute = true)
	private String endTime = "";
	
	
	@JacksonXmlProperty(localName = "signature", isAttribute = true)
	private String signature = "";
	@JacksonXmlProperty(localName = "is-config", isAttribute = true)
	private boolean isConfig = false;
	
		
	@JacksonXmlProperty(localName = "status", isAttribute = true)
	private Status testStatus = Status.SKIP;
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name = "";
	@JacksonXmlProperty(localName = "description", isAttribute = true)
	private String description = "";
	@JacksonXmlProperty(localName = "depends-on-methods", isAttribute = true)
	private String dependsOnMethods = "";
	@JacksonXmlProperty(localName = "data-provider", isAttribute = true)
	private String dataProvider = "";


	@JacksonXmlProperty(localName = "reporter-output")
    @JacksonXmlElementWrapper(useWrapping = false)
	private String reporterOutput = "";

	@JacksonXmlProperty(localName = "exception")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<ExceptionType> exceptions = null;
	
	@JacksonXmlProperty(localName = "attributes")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<AttributeCollection> attributeCollections = new ArrayList<AttributeCollection>();
	
	@JacksonXmlProperty(localName = "params")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ParameterCollection parameterCollection = new ParameterCollection();

	
	
	public String getEndTime()
	{
		return this.endTime;
	}
	public String getStartTime()
	{
		return this.startTime;
	}
	public int getDuration()
	{
		return this.durationInMilliseconds;
	}
	public Status getTestStatus() 
	{
		return testStatus;
	}
	public String getSignature() 
	{
		return signature;
	}
	public boolean isConfig() 
	{
		return isConfig;
	}
	public ParameterCollection getParameterCollection() 
	{
		return parameterCollection;
	}
	public String getReporterOutput() 
	{
		reporterOutput = (reporterOutput != null ? reporterOutput.trim() : "");	
		return reporterOutput;
	}
	public ArrayList<ExceptionType> getExceptions() 
	{
		return exceptions;
	}
	public ArrayList<AttributeCollection> getAttributeCollections() 
	{
		return attributeCollections;
	}
	public String getName() 
	{
		return name;
	}
	public String getDescription() 
	{
		return description;
	}
	public String getDependsOnMethods() 
	{
		return dependsOnMethods;
	}
	public String getDataProvider() 
	{
		return dataProvider;
	}

	
}
