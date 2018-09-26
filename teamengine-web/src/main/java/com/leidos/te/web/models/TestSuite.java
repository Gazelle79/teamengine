package com.leidos.te.web.models;

import java.lang.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;

public class TestSuite
{
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name = "";
	@JacksonXmlProperty(localName = "duration-ms", isAttribute = true)
	private int durationInMilliseconds = 0;
	@JacksonXmlProperty(localName = "started-at", isAttribute = true)
	private String startDateTime = "";
	@JacksonXmlProperty(localName = "finished-at", isAttribute = true)
	private String endDateTime = "";
	
	private String startTime = "";
	private String endTime = "";
	private String startDate = "";
	private String endDate = "";
	
	private String testUrl = "";

	@JacksonXmlProperty(localName = "groups")
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<Group> groups = null;
	
	@JacksonXmlProperty(localName = "test")
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<Test> tests = null;

	
	
    public String getName() 
    {
        return this.name;
    }
	
    public int getDurationInMilliseconds() 
    {
        return this.durationInMilliseconds;
    }
	
    public String getStartTime() 
    {
    	startTime = startDateTime;
    	String[] dateAndTime = startTime.split("T");
    	startTime = dateAndTime[1].replace("Z", "");
        
    	return this.startTime;
    }
    
    public String getEndTime() 
    {
    	endTime = endDateTime;
    	String[] dateAndTime = endTime.split("T");
    	endTime = dateAndTime[1].replace("Z", "");
    	
        return this.endTime;
    }
    

	public String getStartDate() 
	{
		startDate = startDateTime;
		String[] dateAndTime = startDate.split("T");
    	startDate = dateAndTime[0].replace("Z", "");
		
		return startDate;
	}

	public String getEndDate() 
	{
		endDate = endDateTime;
		String[] dateAndTime = endDate.split("T");
    	endDate = dateAndTime[0].replace("Z", "");
		
		return endDate;
	}
	
	public String getTestUrl() 
	{
		return testUrl;
	}

	public void setTestUrl(String url) 
	{
		this.testUrl = url;
	}
    
    public ArrayList<Test> getTests() 
    {
        return this.tests;
    }
	
    public ArrayList<Group> getGroups() 
    {	
		return this.groups;
    } 
    
}
