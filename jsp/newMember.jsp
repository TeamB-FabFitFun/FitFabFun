<%@ include file="header.html" %>
	<hr>
		<table align="center" width="80%" cellpadding="5" cellspacing="0">
			<tr>
				<td align="right"><font color="#990000">First Name</font></td>
				<td><input type="text" style="background-color:#FFFFFF" name="firstName" size="20" value=""></td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Last Name</font></td>
				<td><input type="text" style="background-color:#FFFFFF" name="lastName" size="20" value=""></td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Email</font></td>
				<td><input type="text" style="background-color:#FFFFFF" name="email" size="20" value=""></td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Password</font></td>
				<td><input type="password" style="background-color:#FFFFFF" name="password1" size="20" value=""></td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Re-enter Password</font></td>
				<td><input type="password" style="background-color:#FFFFFF" name="password2" size="20" value=""></td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Gender</font></td>
				<td>
					<select name="gender">
						<option>Female
						<option>Male
					</select>
				</td>
			</tr>
			<tr>
				<td align="right"><font color="#990000">Age</font></td>
				<td><input type="text" style="background-color:#FFFFFF" name="age" size="20" value=""></td>
			</tr>
			<tr>
			    <td colspan="2" align="center"><input type="button" name="register" value="Register" onclick="submitRegistration(this.form);"></td>
			</tr>
		</table>

<script type="text/javascript">
	function submitRegistration(form) {
		// perform field check
		var fName = form.firstName.value;
		var lName = form.lastName.value;
		var email = form.email.value;
		var pwd1 = form.password1.value;
		var pwd2 = form.password2.value;
		var age = form.age.value;
		
		if (fName == null || fName == "")
			alert("Please enter your first name!");
		else if (lName == null || lName == "")
			alert("Please enter your last name!");
		else if (email == null || email == "")
			alert("Please enter your email!");
		else if (pwd1 == null || pwd1 == "")
			alert("please enter your password!");
		else if (pwd2 == null || pwd2 == "")
			alert("please reenter your password!");
		else if (pwd1 != pwd2)
			alert("Your passwords do not match. Please re-enter!");
		else if (age == null || age =="" || isNaN(age) || Number(age) < 1 || Number(age) > 100)
			alert("Please enter a valid age!");
		else {
			form.action='/fabfitfun/FFFServlet?action=register';
			form.submit();
		}
	}
</script>

<%@ include file="footer.html" %>