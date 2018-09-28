package com.leidos.te.web.models;

import java.lang.*;
import java.io.*;
import java.util.*;
import com.occamlab.te.web.*;


public class User 
{
	private String name = "";
	ArrayList<Session> sessionInfo = new ArrayList<Session>();
	
	
	public String getName() 
	{
		return name;
	}
	
	public boolean CreateUser(String userName)
	{
		//create a user based on his username.
		//If he has a single session (session id), create it. Put all info for that session in a list.
		//If he has many sessions (session id's), create them all & put them in a list.
		
		return true;
	}
	
}
