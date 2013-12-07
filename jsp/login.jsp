<%@ include file="header.html" %>

	<table style="margin:0px auto; width:20%" cellspacing="10">
		<tr><td>
			User ID</br>
			<input type="text" size="20" name="username" value="">
		</td></tr>
		<tr><td>
			Password</br>
			<input type="password" size="20" name="password" value="">
		<td></tr>
		<tr><td><input type="button" name="login" value="Login" onclick="form.action = '/fabfitfun/FFFServlet?action=logon'; form.submit();"></td></tr>
	</table>

<%@ include file="footer.html" %>