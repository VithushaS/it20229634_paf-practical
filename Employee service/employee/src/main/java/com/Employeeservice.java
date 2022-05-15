package com;


import model.Employee;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/employee")

public class Employeeservice {
	
	Employee employeeObj = new Employee();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee()
	 {
	 return employeeObj.readEmployee();
	 }


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("name") String name,
	 @FormParam("nic") String nic,
	 @FormParam("age") String age,
	 @FormParam("contact") String contact)
	{
	 String output = employeeObj.insertEmployee(name, nic, age, contact);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData)
	{
	//Convert the input string to a JSON object
	 JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
	//Read the values from the JSON object
	 String id = employeeObject.get("id").getAsString();
	 String name = employeeObject.get("name").getAsString();
	 String nic = employeeObject.get("nic").getAsString();
	 String age = employeeObject.get("age").getAsString();
	 String contact = employeeObject.get("contact").getAsString();
	 String output = employeeObj.updateEmployee(id, name, nic, age, contact);
	return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

	//Read the value from the element <customerID>
	 String id = doc.select("id").text();
	 String output = employeeObj.deleteEmployee(id);
	return output;
	}

}
