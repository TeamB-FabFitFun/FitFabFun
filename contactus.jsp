<%@ include file="header.html" %>

<form action="RegisterServlet" method="post">
	<table style="margin:0px auto; width:500px" cellspacing="10">
		<tr><td>
			First Name</br>
			<input type="text" size="20" value="">
		</td></tr>
		<tr><td>
			Last Name</br>
			<input type="text" size="20" value="">
		<td></tr>
		<tr><td>
			Email</br>
			<input type="text" size="20" value="">
		</td></tr>
		<tr><td>
			Phone</br>
			<input type="text" size="20" value=""><br>
		</td></tr>
		<tr><td>
			General Customer Feedback</br>
			<textarea rows="4" cols="50"></textarea><br>
			<input type="submit" value="Submit">
		</td></tr>
	</table>
</form>

<%@ include file="footer.html" %>
