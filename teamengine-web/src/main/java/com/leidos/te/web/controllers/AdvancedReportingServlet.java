package com.leidos.te.web.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.leidos.te.web.models.*;
import com.occamlab.te.web.*;

public class AdvancedReportingServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	Config Conf;
	//String xmlToParse = "C:/Users/[*userName*]/Desktop/repo/TE_BASE/users/[*userName*]/s0004/testng/69ea5679-03e0-43d9-bff0-251eef09798f/testng-results.xml";	
	
	String sessionId = "";
	boolean allDirectoriesCreated = false;
	
	
	private File userLogDirectory = null;
	private File htmlReportDirectory = null;
	private File resultReportDirectory = null;
	private File testNgDirectory = null;
	private File testNgResultsDirectory = null;
	private String testNgResultsPath = ""; //path to Test Results NG.xml
	
	
	public void init() throws ServletException 
	{
		Conf = new Config();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Send info from the web browser to the server as a text string w/ parameters after a question mark.
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		sessionId = request.getParameter("sessionId");
		
		String userName = request.getRemoteUser();
		allDirectoriesCreated = this.getUserDirectories(userName);
		TestResults testResults = this.GetTestResults();
		boolean testResultsCleaned = this.SortTestResults(testResults);
				
		//Pass Beans
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("testResults", testResults);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/advancedReporting.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Send info from the web browser to the server. 
		//Much reliable & safer: send info from browser to server as a separate message, not a text string.
		this.doGet(request, response);
	}
		
	private boolean getUserDirectories(String userName)
	{
		boolean allDirectoriesCreated = false;
		
		this.userLogDirectory = new File(Conf.getUsersDir(), userName);
		this.htmlReportDirectory = new File(userLogDirectory, sessionId + System.getProperty("file.separator") + "html");
		this.resultReportDirectory = new File(userLogDirectory, sessionId + System.getProperty("file.separator") + "result");
		this.testNgDirectory = new File(userLogDirectory, sessionId + System.getProperty("file.separator") + "testng");
		
		
		if(testNgDirectory != null)
		{
			//Get folders in testNG
			String[] directories = testNgDirectory.list(new FilenameFilter() 
			{
				@Override
				public boolean accept(File current, String name) 
				{
					return new File(current, name).isDirectory();
				}
			});
			
			//Loop through folders in testNG looking for testng-results.xml
			for(String testFolder : directories) 
			{
				if(testFolder != null)
				{
					//Test for testing-results.xml
					testNgResultsDirectory = new File(testNgDirectory, testFolder + System.getProperty("file.separator") + "testng-results.xml");
					if(testNgResultsDirectory.exists()) 
			 		{ 
			 			this.testNgResultsPath = testNgResultsDirectory.getAbsolutePath();
			 		}
				}
			}
		}
		
		if( this.userLogDirectory.exists() && this.htmlReportDirectory.exists() && this.resultReportDirectory.exists() && this.testNgDirectory.exists()
				&& this.testNgResultsDirectory.exists())
		{ allDirectoriesCreated = true; }
		
		return allDirectoriesCreated;
	}
		
	public TestResults GetTestResults() throws IOException
	{
		XmlMapper xmlMapper = new XmlMapper();
		String xml = inputStreamToString(new FileInputStream(testNgResultsPath));
		TestResults testNgResults = xmlMapper.readValue(xml, TestResults.class);
		return testNgResults;
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
	
	
	/*private void getTestUrl(TestResults thisTestResults) throws IOException
	{
		File xmlLogFilepath = new File(userLogDirectory.getAbsolutePath() + System.getProperty("file.separator") + "log.xml");
		if (xmlLogFilepath.exists())
		{
			XmlMapper xmlMapper = new XmlMapper();
			String xml = inputStreamToString(new FileInputStream(xmlLogFilepath));
			
			
		}
	}*/
	
	/**
	 * Make sure all Test Method Results are set to enumeration: Pass, fail or Skip.
	 * Count the number of passed, failed & Skipped tests in each class (that contains each TestMethod).
	**/
	private boolean SortTestResults(TestResults testResults)
	{
		boolean testMethodAdded = false;
		int totalTestClassTests = 0;
		ArrayList<TestSuite> testSuiteCollection = testResults.getTestSuites();
		
		for(Test thisTest : testSuiteCollection.get(0).getTests())
		{
				
				for (TestClass testClass : thisTest.getTestClasses())
				{
					totalTestClassTests = 0;	
					for (TestMethod testMethod : testClass.getTestMethods())
					{	
						switch(testMethod.getTestStatus())
						{
						case PASS:
							{
								testMethodAdded = testClass.passedTests.add(testMethod);
								totalTestClassTests++;
								break;
							}
	
						case FAIL:
							{
								testMethodAdded = testClass.failedTests.add(testMethod);
								totalTestClassTests++;
								break;
							}
							
						case SKIP:
							{
								testMethodAdded = testClass.skippedTests.add(testMethod);
								totalTestClassTests++;
								break;
							}
							
						default:
							{
								break;
							}
						}
					}
					testClass.setTotalTestCount(totalTestClassTests);
				}
		}
		return testMethodAdded;
	}
	
}
