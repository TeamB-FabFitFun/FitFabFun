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
			    <td colspan="2" align="center"><input type="button" name="register" value="Register" onclick="form.action='/fabfitfun/FFFServlet?action=register'; form.submit();"></td>
			</tr>
		</table>

<%@ include file="footer.html" %>