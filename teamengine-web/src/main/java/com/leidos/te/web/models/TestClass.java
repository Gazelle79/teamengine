package com.leidos.te.web.models;

import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.annotation.*;


public class TestClass
{
	@JacksonXmlProperty(localName = "name", isAttribute = true)
	private String name = "";
	
	@JacksonXmlProperty(localName = "test-method")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<TestMethod> testMethods = null;
	
	private int passedTestPercentage = 0;
	private int failedTestPercentage = 0;
	private int skippedTestPercentage = 0;
	private int totalTestCount = 0;

	public ArrayList<TestMethod> passedTests = new ArrayList<TestMethod>();
	public ArrayList<TestMethod> failedTests = new ArrayList<TestMethod>();
	public ArrayList<TestMethod> skippedTests = new ArrayList<TestMethod>();
	
	private String path = null;
	
	public String getName() 
    {
        return this.name;
    }
    
	public String getPath() 
	{
		if(name!=null && name.length() > 0)
		{
			path = name;
			path = path.replace('.', '/');
		}
		return this.path;
	}
	
    public ArrayList<TestMethod> getTestMethods() 
    {
        return testMethods;
    }
	
	public int getTotalTestCount() 
	{
		return totalTestCount;
	}

	public void setTotalTestCount(int totalTestCount) 
	{
		this.totalTestCount = totalTestCount;
	}
	
    public int getFailedTestPercentage() 
	{
    	failedTestPercentage = (failedTests.size() *100) / totalTestCount;
		return failedTestPercentage;
	}
    
    public int getPassedTestPercentage() 
	{
    	passedTestPercentage = (passedTests.size() *100) / totalTestCount;
		return passedTestPercentage;
	}

	public int getSkippedTestPercentage() 
	{
		skippedTestPercentage = (skippedTests.size() *100) / totalTestCount;
		return skippedTestPercentage;
	}
		
}
