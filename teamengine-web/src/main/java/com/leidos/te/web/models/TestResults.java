package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;


@JacksonXmlRootElement(localName = "testng-results")
public class TestResults 
{
	@JacksonXmlProperty(localName = "skipped", isAttribute = true)
	private int skippedTestCount = 0;
	@JacksonXmlProperty(localName = "passed", isAttribute = true)
	private int passedTestCount = 0;
	@JacksonXmlProperty(localName = "failed", isAttribute = true)
	private int failedTestCount = 0;
	@JacksonXmlProperty(localName = "total", isAttribute = true)
	private int totalTestCount = 0;
	
	
	@JacksonXmlProperty(localName = "reporter-output")
    @JacksonXmlElementWrapper(useWrapping = false)
	private String reporterOutput = "";
	
	@JacksonXmlProperty(localName = "suite")
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<TestSuite> testSuites = null;
	
	
	public int getSkippedTestCount() 
	{
		return this.skippedTestCount;
	}

	public int getPassedTestCount() 
	{
		return this.passedTestCount;
	}
	
	public int getFailedTestCount() 
	{
		return this.failedTestCount;
	}
	
	public int getTotalTestCount() 
	{
		return this.totalTestCount;
	}
	
	public ArrayList<TestSuite> getTestSuites() 
	{
		return this.testSuites;
	}

	public String getReporterOutput() 
	{
		reporterOutput = (reporterOutput != null ? reporterOutput.trim() : "");	
		return reporterOutput;
	}

	
	
}
