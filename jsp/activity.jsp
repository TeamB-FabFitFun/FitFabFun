<%@ include file="header.html" %>

<%@ page import="business.Activity" %>


<% Activity activity = new Activity("/activities.txt"); %>

<table align="center" width="80%">
	<tr>
		<th bgcolor="#FFCC88">Activity</th>
		<th bgcolor="#FFCC88">Date</th>
		<th bgcolor="#FFCC88">Age</th>
		<th bgcolor="#FFCC88">Openings</th>
		<th bgcolor="#FFCC88">Fee</th>
		<th bgcolor="#FFCC88">&nbsp;</th>
	</tr>
<%
	for (int i=0; i<activity.getNumber(); i++) {
		String strName = activity.getName(i);
		String strDate = activity.getDate(i);
		String strAge = activity.getAge(i);
		String strOpening = activity.getOpening(i);
		String strFee = activity.getFee(i);
%>
	<tr>
		<td align="center"><%= strName %></td>
		<td align="center"><%= strDate %></td>
		<td align="center"><%= strAge %></td>
		<td align="center"><%= strOpening %></td>
		<td align="center"><%= strFee %></td>
		<td align="center"><input type="button" name=<%= Integer.toString(i) %> value="Add to Cart" onclick="form.action='/fabfitfun/FFFServlet?action=addToCart<%= Integer.toString(i) %>'; form.submit();"></td>
	</tr>
<%
	}
%>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td colspan="5">&nbsp;</td>
		<td align="center"><input type="image" src="images/cart.bmp" name="cart" width="90" height="30" onclick="form.action='/fabfitfun/FFFServlet?action=goToCart'; form.submit();"></td>
	</tr>
</table>

<%@ include file="footer.html" %>