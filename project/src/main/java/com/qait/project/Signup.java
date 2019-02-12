package com.qait.project;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("signup")
public class Signup {
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response signup(@FormParam("fname") String fname,@FormParam("lname") String lname,@FormParam("email") String email, @FormParam("password") String password) throws URISyntaxException{
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/fakebook","root","root");  
    		  
    		PreparedStatement ps=con.prepareStatement("insert into information values(?,?,?,?)");  
    		
    		ps.setString(1,fname);  
    		ps.setString(2,lname);  
    		ps.setString(3,password);  
    		ps.setString(4,email);
    	
    		
    		ps.executeUpdate();
    		
    		System.out.println("Registered");
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		  
    	URI location = new URI("http://localhost:8059/project/index.html");
    	return Response.seeOther(location).build(); 
    	}
	
	
	
	

}
