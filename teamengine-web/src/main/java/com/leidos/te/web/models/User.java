package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;
import com.occamlab.te.web.*;


public class User 
{
	private final String allSessionIds = "all";
	private String name = "";
	private ArrayList<Session> sessions = new ArrayList<Session>();
	
	
	public ArrayList<Session> getSessions() 
	{
		return sessions;
	}

	public String getName() 
	{
		return name;
	}
	
	public boolean CreateUser(String userName, String sessionId, Config Conf)
	{
		//create a user based on his username.
		//If he has a single session (session id), create it. Put all info for that session in a list.
		//If he has many sessions (session id's), create them all & put them in a list.
		//In every single session, account for TestResults.
		
		this.name = userName;
		
		if (!sessionId.equals(allSessionIds) )
		{ 
			Session userSession = new Session();
			userSession.createSession(userName, sessionId, Conf);
			this.sessions.add(userSession);
		}
		else
		{
			File Fred = new File(Conf.getUsersDir(), userName);
			
			//Get folders in testNG
			String[] directories = Fred.list(new FilenameFilter() 
			{
				@Override
				public boolean accept(File current, String name) 
				{
					return new File(current, name).isDirectory();
				}
			});
			
			for(String directory : directories)
			{
				Session userSession = new Session();
				sessionId = directory.toString();
				userSession.createSession(userName, sessionId, Conf);
				this.sessions.add(userSession);
			}
		}
		
		return true;
	}
	
}
