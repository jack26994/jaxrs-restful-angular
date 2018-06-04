package com.login.ang;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.login.ang.model.*;

@Path("/actors")
public class RestfulWS {
	static final String api_version = "1.01A rev.18729";
	static Logger logger = Logger.getLogger(RestfulWS.class);
	static String xmlString = null;
	static Map<String, Model> model = new HashMap<String, Model>();
	
	static { 
		System.out.println("Initializing Internal DataStore...");
		model.put("1", new Model(1, "admin01@mail.com", "123456", "Admin"));
		model.put("2", new Model(2, "admin02@mail.com", "123456", "Admin"));
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: " + api_version + "</p>";
	}
	
	//Login
	@Path("/login")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Model login(Model user) { 
		if (user != null) {
			for (Model i : model.values()) {
				if ((i.getEmail().toUpperCase().equals(user.getEmail().toUpperCase())) && i.getPassword().equals(user.getPassword())) {
					user.setId(i.getId());
					user.setName(i.getName());
				}
			}
		}
		return user;
	}
	
	//Default
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Model> getAllUsers() {
		ArrayList<Model> modelList = new ArrayList<Model>(model.values());
		return modelList;
	}
}
