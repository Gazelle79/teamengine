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
	private XmlMapper xmlMapper = new XmlMapper();
	
	
	
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
		
		//Create a user based on a username.
		//Create all of this user's sessions.
		User thisUser = new User();
		thisUser.CreateUser(userName, sessionId, Conf);
		TestResults testResults = new TestResults();
		
		
		for(Session thisSession : thisUser.getSessions())
		{
			String testNgResultsFilepath = thisSession.getTestNgResultsPath();
			if(!testNgResultsFilepath.equals("")) 
			{
			testResults = this.GetTestResults(testNgResultsFilepath);
			thisSession.getTestResults().add(testResults);
			}
		}
				
		//Pass Beans
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("testResults", testResults);
		request.setAttribute("user", thisUser);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/advancedReporting.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Send info from the web browser to the server. 
		//Much reliable & safer: send info from browser to server as a separate message, not a text string.
		this.doGet(request, response);
	}
		
		
	public TestResults GetTestResults(String testNgResultsFilepath) throws IOException
	{
		xmlMapper = new XmlMapper();
		String xml = inputStreamToString(new FileInputStream(testNgResultsFilepath));
		TestResults testNgResults = xmlMapper.readValue(xml, TestResults.class);
		if(testNgResults.getTotalTestCount() > 0)
		{
			this.SortTestResults(testNgResults);
		}	
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
