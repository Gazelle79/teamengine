package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.*;

import org.junit.Test;
import org.junit.*;


import com.leidos.te.web.models.*;

public class ModelsTest 
{
	
	private TestResults testNgResults = null;
	private ArrayList<TestSuite> testSuiteList = null;
	
	private String testNgXml = "C:/Users/boydjrf/Desktop/repo/TE_BASE/users/boydjrf/s0004/testng/69ea5679-03e0-43d9-bff0-251eef09798f/testng-results.xml";	
	

	
	@Test
	public void TestResults_Test() throws IOException
	{
		/*
		 * Create a test results object.
		 * Read in the XML from the file.
		 * Assert for specific values.  
		*/
		//XmlMapper xmlMapper = (XmlMapper) new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		XmlMapper xmlMapper = new XmlMapper();
		String xml = inputStreamToString(new FileInputStream(new File(testNgXml)));
		testNgResults = xmlMapper.readValue(xml, TestResults.class);
	    
		Assert.assertTrue(testNgResults != null);
	    Assert.assertTrue(testNgResults.getReporterOutput().equals(""));
	    
	    testSuiteList = testNgResults.getTestSuites();
	}
	
	private static String inputStreamToString(InputStream is) throws IOException 
	{
	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    while ((line = br.readLine()) != null) 
	    {
	        sb.append(line);
	    }
	    br.close();
	    return sb.toString();
	}
	
	
}
