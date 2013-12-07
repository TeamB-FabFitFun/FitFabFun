<%@ include file="header.html" %>

<jsp:useBean id="member" scope="session" class="business.Member"/>

<table style="margin:0px auto; width:90%" cellspacing="10">
	<tr><td>
		Thanks for shopping with us!<br>
		A confirmation email has been sent to <font color="#0000FF"><%= member.getEmail() %></font>.
	</td></tr>
</table>


<%@ include file="footer.html" %>
