/**
 * 
 */
$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateEmployeeForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidEmployeeIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "EmployeeAPI",
 type : type,
 data : $("#formEmployee").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 
 onEmployeeSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidEmployeeIDSave").val($(this).data("id"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#nic").val($(this).closest("tr").find('td:eq(1)').text());
 $("#age").val($(this).closest("tr").find('td:eq(2)').text());
 $("#contact").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "EmployeeAPI",
 type : "DELETE",
 data : "id=" + $(this).data("Id"),
 dataType : "text",
 complete : function(response, status)
 {

  
 onItemDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateEmployeeForm()
{
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Employee name.";
 }
// NIC
if ($("#nic").val().trim() == "")
 {
 return "Insert Employee nic";
 } 

// AGE
if ($("#age").val().trim() == "")
 {
 return "Insert Employee Age.";
 }

// CONTACT
if ($("#contat").val().trim() == "")
 {
 return "Insert Employee contact.";
 }
return true;
}

function onEmployeeSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divEmployeesGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidEmployeeIDSave").val("");
 $("#formEmployee")[0].reset();
}

function onEmployeeDeleteComplete(response, status)
{
 if (status == "success")
 {
    var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divEmployeesGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 