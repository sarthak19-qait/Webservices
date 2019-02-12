package com.qait.project;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

@Path("login")
public class Login {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response login(@FormParam("email") String email,@FormParam("password") String password) throws URISyntaxException{
		try{  
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("connecting to database.....");
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fakebook","root","root");    
    		Statement stmt=con.createStatement();  
    		ResultSet rs=stmt.executeQuery("select email,password from information");  
    		while(rs.next())  
    		{
    			if(email.equals(rs.getString(1)) && password.equals(rs.getString(2)))
    			{
    				System.out.println("connected....");
    				URI location = new URI("http://localhost:8059/project/home.html");
    				
    		    	return Response.seeOther(location).build(); 
    		    	
    			}
    			
    			
    		}
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	
		URI location = new URI("http://localhost:8059/fakebook/login.html");
    	return Response.seeOther(location).build();
    	}
	

}
