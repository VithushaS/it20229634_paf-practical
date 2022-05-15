package model;

import java.sql.*;

public class Employee {
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employee", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	public String insertEmployee(String Name, String Nic, String Age, String Contact) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into employee (`id`,`name`,`nic`,`age`,'contact')  values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, Name); 
	 preparedStmt.setString(3, Nic); 
	 preparedStmt.setString(4, Age); 
	 preparedStmt.setString(5, Contact); 
	 // execute the statement
	 
	 preparedStmt.execute(); 
	 con.close(); 
	 String newEmployees = readEmployee();
	 output = "{\"status\":\"success\", \"data\": \"" +newEmployees + "\"}";
	 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the employee.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String readEmployee() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for reading."; 
	 } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Employee Name</th><th>Employee NIC</th> <th>Employee Age</th><th>Employee contact</th><th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from employee"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String nic = rs.getString("nic"); 
	 String age = rs.getString("age"); 
	 String contact = rs.getString("contact"); 
	
	 // Add into the html table
	 
	 output += "<tr><td>" + name + "</td>"; 
	 output += "<td>" + nic + "</td>"; 
	 output += "<td>" + age + "</td>"; 
	 output += "<td>" + contact + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary' data-employeeid='"+id+"'></td>"
	  +"<td><input name='btnRemove' type='button' value='Remove' class='btn btn-danger' data-employeeid='" + id +"'></td></tr>";
	 
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the employees."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String updateEmployee(String Id, String Name, String Nic, String Age, String Contact) 
	 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 
	 // create a prepared statement
	 String query = "UPDATE employees SET name=?,nic=?,age=?,contact=?  WHERE id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, Name); 
	 preparedStmt.setString(2, Nic); 
	 preparedStmt.setString(3,Age); 
	 preparedStmt.setString(4, Contact); 
	 preparedStmt.setInt(5, Integer.parseInt(Id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newEmployees = readEmployee();
	 output = "{\"status\":\"success\", \"data\": \"" +newEmployees + "\"}";
 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while updating the employee.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteEmployee(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from empoyee where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close();
	 String newEmployees = readEmployee();
	 output = "{\"status\":\"success\", \"data\": \"" +newEmployees + "\"}";
	 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\":\"Error while deleting the employee.\"}";
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

}
