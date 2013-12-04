<%@ include file="header_noform.html" %>

<form action="j_security_check" method="get">
	<table style="margin:0px auto; width:20%" cellspacing="10">
		<tr><td>
			User ID</br>
			<input type="text" size="20" name="j_username" value="">
		</td></tr>
		<tr><td>
			Password</br>
			<input type="password" size="20" name="j_password" value="">
		<td></tr>
		<tr><td><input type="submit" name="login" value="Login"></td></tr>
	</table>
</form>

<%@ include file="footer_noform.html" %>