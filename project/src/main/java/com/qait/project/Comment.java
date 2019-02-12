package com.qait.project;
import java.sql.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.json.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("comment")
public class Comment {
	
	@POST
	@Path("addcomment")
	@Produces(MediaType.TEXT_PLAIN)
	public Response AddComment(@FormParam("comment") String comment,@FormParam("email") String email) throws URISyntaxException{
		try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/fakebook","root","root");  
    		  
    		PreparedStatement ps=con.prepareStatement("insert into comment values(?,?)");  
    		
    		ps.setString(2,comment);  
    		ps.setString(1,email );
    		
    		ps.executeUpdate();
    		
    		System.out.println("Comment Added");
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    		  
    	URI location = new URI("http://localhost:8059/project/home.html");
    	return Response.seeOther(location).build(); 

}
	
	@GET
	@Path("getcomment")	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getComment(@QueryParam("email") String email) throws URISyntaxException{				
		JSONArray jarray=new JSONArray();		
		JSONObject usercomment = new JSONObject();		
		String output="";		
		String comment="";				
		try{      		
			Class.forName("com.mysql.jdbc.Driver");      		
			Connection con=DriverManager.getConnection(      		
					"jdbc:mysql://localhost:3306/fakebook","root","root");     
			
			Statement stmt=con.createStatement();      		
			ResultSet rs=stmt.executeQuery("select email, comment from comment where email='"+email+"'");    		
			
			while(rs.next())      		
			{    
				JSONObject jobj=new JSONObject(); 
				email= rs.getString(1);     			
				comment= rs.getString(2); 
				jobj.put("email",email);    		
				jobj.put("comment",comment); 
				jarray.put(jobj);
			}   
			
			usercomment.put("comment", jarray);				
			}		
		catch(Exception e) {		
			System.out.println(e);			
			}				
		return Response.status(200).entity(usercomment.toString()).build();			
		}
	
	@GET
	@Path("getallcomments")	
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)	
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getAllComments() throws URISyntaxException{		
		JSONArray jarray=new JSONArray();		
		JSONObject jsonObject=new JSONObject();		
		JSONObject usercomment = new JSONObject();		
		String comment="";		
		String email="";					
		try{      		
			Class.forName("com.mysql.jdbc.Driver");      		
			Connection con=DriverManager.getConnection(      		
					"jdbc:mysql://localhost:3306/fakebook","root","root");      		
			Statement stmt=con.createStatement();      		
			ResultSet rs=stmt.executeQuery("select email, comment from comment");     		
			while(rs.next()) {    			    			
				JSONObject jobj=new JSONObject();    			
				email=rs.getString(1);    			
				comment=rs.getString(2);    			
				jobj.put("email", email);    			
				jobj.put("comment",comment);    			
				jarray.put(jobj);    			    		
				}    		
			usercomment.put("comments", jarray);    				
			}catch(Exception e){ System.out.println(e);}		
		return Response.status(200).entity(usercomment.toString()).build();		
		}
}
	
	
	
//	@GET
//    @Path("getallcomments")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response getAllComments() throws URISyntaxException {
//    	String output = "";
//    	try{  
//    		Class.forName("com.mysql.jdbc.Driver");  
//    		Connection con=DriverManager.getConnection(  
//    		"jdbc:mysql://localhost:3306/fakebook","root","root");  
//    		Statement stmt=con.createStatement();  
//    		ResultSet rs=stmt.executeQuery("select fname,comment from information");  
//    		while(rs.next())  
//    		{
//    			output += rs.getString(1) + " : " + rs.getString(2) + "\n";
//    		}
//    		con.close();
//    		return Response.status(200).entity(output).build();	
//    		}
//    	catch(Exception e){ System.out.println(e);
//    	}
//    	URI location = new URI("http://localhost:8080/fakebook/home.html");
//    	return Response.seeOther(location).build(); 
//    	}
//}
