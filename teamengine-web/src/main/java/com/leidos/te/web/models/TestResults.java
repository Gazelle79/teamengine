package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;


@JacksonXmlRootElement(localName = "testng-results")
public class TestResults 
{
	@JacksonXmlProperty(localName = "skipped", isAttribute = true)
	private int skipped = 0;
	@JacksonXmlProperty(localName = "passed", isAttribute = true)
	private int passed = 0;
	@JacksonXmlProperty(localName = "failed", isAttribute = true)
	private int failed = 0;
	@JacksonXmlProperty(localName = "total", isAttribute = true)
	private int totalTests = 0;
	
	//Testing.
	@JacksonXmlProperty(localName = "reporter-output")
    @JacksonXmlElementWrapper(useWrapping = false)
	private String reporterOutput = "";
	
	@JacksonXmlProperty(localName = "suite")
    @JacksonXmlElementWrapper(useWrapping = false)
	private ArrayList<TestSuite> testSuites = null;
	
	
	public int getSkippedTests() 
	{
		return this.skipped;
	}

	public int getPassedTests() 
	{
		return this.passed;
	}
	
	public int getFailedTests() 
	{
		return this.failed;
	}
	
	public int getTotalTests() 
	{
		return this.totalTests;
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
