function checkUserType(){
	if(document.getElementById('manageUsersForm:userTypeInput').options[document.getElementById('manageUsersForm:userTypeInput').selectedIndex].value=='I'){
		document.getElementById('manageUsersForm:searchEmployee').style.display='block';
		document.getElementById('manageUsersForm:employeeInput').style.display='block';
		document.getElementById('manageUsersForm:employeeInput').value="";
		document.getElementById('manageUsersForm:userFullNameMessage').style.display='none';
		document.getElementById('manageUsersForm:userFullNameInput').style.display='none';
		document.getElementById('manageUsersForm:mailMessage').style.display='none';
		document.getElementById('manageUsersForm:mailInput').style.display='none';
		document.getElementById('manageUsersForm:mobil1Message').style.display='none';
		document.getElementById('manageUsersForm:mobil1Input').style.display='none';
		document.getElementById('manageUsersForm:mobil2Message').style.display='none';
		document.getElementById('manageUsersForm:mobil2Input').style.display='none';
	}else if(document.getElementById('manageUsersForm:userTypeInput').options[document.getElementById('manageUsersForm:userTypeInput').selectedIndex].value=='E'){
		document.getElementById('manageUsersForm:searchEmployee').style.display='none';
		document.getElementById('manageUsersForm:employeeInput').style.display='none';
		document.getElementById('manageUsersForm:employeeInput').value="0";
		document.getElementById('manageUsersForm:userFullNameMessage').style.display='block';
		document.getElementById('manageUsersForm:userFullNameInput').style.display='block';
		document.getElementById('manageUsersForm:mailMessage').style.display='block';
		document.getElementById('manageUsersForm:mailInput').style.display='block';
		document.getElementById('manageUsersForm:mobil1Message').style.display='block';
		document.getElementById('manageUsersForm:mobil1Input').style.display='block';
		document.getElementById('manageUsersForm:mobil2Message').style.display='block';
		document.getElementById('manageUsersForm:mobil2Input').style.display='block';
	}else{
		document.getElementById('manageUsersForm:searchEmployee').style.display='none';
		document.getElementById('manageUsersForm:employeeInput').style.display='none';
		document.getElementById('manageUsersForm:employeeInput').value="";
		document.getElementById('manageUsersForm:userFullNameMessage').style.display='none';
		document.getElementById('manageUsersForm:userFullNameInput').style.display='none';
		document.getElementById('manageUsersForm:mailMessage').style.display='none';
		document.getElementById('manageUsersForm:mailInput').style.display='none';
		document.getElementById('manageUsersForm:mobil1Message').style.display='none';
		document.getElementById('manageUsersForm:mobil1Input').style.display='none';
		document.getElementById('manageUsersForm:mobil2Message').style.display='none';
		document.getElementById('manageUsersForm:mobil2Input').style.display='none';
	}
}
function deleteRecord(confirmMessage){
	if(confirm(confirmMessage))
		return true;
	else
		return false;
}