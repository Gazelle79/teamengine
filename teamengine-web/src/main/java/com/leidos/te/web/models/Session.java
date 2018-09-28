package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.dataformat.xml.annotation.*;
import com.occamlab.te.web.Config;

public class Session 
{
	private File logDirectory = null;
	private File htmlReportDirectory = null;
	private File resultReportDirectory = null;
	private File testNgDirectory = null;
	private File testNgResultsDirectory = null;
	private String testNgResultsPath = ""; //path to Test Results NG.xml
	
	public boolean createSession(String userName, String sessionId, Config conf)
	{
		boolean allDirectoriesCreated = false;
		this.logDirectory = new File(conf.getUsersDir(), userName);
		this.htmlReportDirectory = new File(logDirectory, sessionId + System.getProperty("file.separator") + "html");
		this.resultReportDirectory = new File(logDirectory, sessionId + System.getProperty("file.separator") + "result");
		this.testNgDirectory = new File(logDirectory, sessionId + System.getProperty("file.separator") + "testng");
		
		
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
		
		if( this.logDirectory.exists() && this.htmlReportDirectory.exists() && this.resultReportDirectory.exists() && this.testNgDirectory.exists()
				&& this.testNgResultsDirectory.exists())
		{ allDirectoriesCreated = true; }
		
		return allDirectoriesCreated;
	}


}
