<%@ include file="header.html" %>

<%
	String userId = (String)request.getAttribute("userId");
	if (userId == null) userId = "";
	String findUserId = (String)request.getAttribute("findUserId");
	String matchPassword = (String)request.getAttribute("matchPassword");
	
	String fontColorUser = "#000000";
	String fontColorPwd = "#000000";
	
	if (findUserId!=null && findUserId.equals("false")) {
		fontColorUser = "#FF0000";
	}
	if (matchPassword!=null && matchPassword.equals("false")) {
		fontColorPwd = "#FF0000";
	}
%>

	<table style="margin:0px auto; width:20%" cellspacing="10">
		<tr><td>
			<font color=<%= fontColorUser %>>User ID</font></br>
			<input type="text" size="20" name="username" value=<%= userId %>>
		</td></tr>
		<tr><td>
			<font color=<%= fontColorPwd %>>Password</font></br>
			<input type="password" size="20" name="password" value="">
		<td></tr>
		<tr><td><input type="button" name="login" value="Login" onclick="form.action = '/fabfitfun/FFFServlet?action=logon'; form.submit();"></td></tr>
	</table>

<%@ include file="footer.html" %>