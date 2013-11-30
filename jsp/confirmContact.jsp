<%@ include file="header.html" %>

<%
	String name = (String) session.getAttribute("feedbackFirstName");
	
%>
<table style="margin:0px auto; width:90%" cellspacing="10">
	<tr><td>
	<% if (name != null && name != "") { %>
		Dear <%= name %>,<br><br>
	<% } %>
		Thanks for your feedback!<br>
		We will continue to improve our customer satisfication.<br>
		If your concern is not addressed in the next 48 hours. Please give us a call at (410)348-6377.<br><br>
		Happy Fitness!
	</td></tr>
</table>


<%@ include file="footer.html" %>
