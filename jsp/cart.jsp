<%@ include file="header.html" %>

<h2>&nbsp;&nbsp;&nbsp;&nbsp;Shopping Cart</h2>
<hr>

<jsp:useBean id="member" scope="session" class="business.Member"/>
<jsp:useBean id="activity" scope="session" class="business.Activity"/>

<% String [] cart = member.getCart(); %>

<table style="margin:0px auto; width:90%" cellspacing="10">
	<% if (cart.length == 0) {%>
		<tr><td>Your shopping cart is empty.</td></tr>
	<% } else { 
		for (int i=0; i<cart.length; i++) {
	%>
		<tr>
			<td><%= cart[i] %></td>
			<td align="center"><input type="button" name=<%= Integer.toString(i) %> value="Remove" onclick="form.action='/fabfitfun/FFFServlet?action=removeFromCart<%= Integer.toString(i) %>'; form.submit();"></td>
		</tr>
	<% } } %>
</table>
<br>
<table style="margin:0px auto; width:90%" cellspacing="10">
	<tr><td><a href="checkout.jsp" style="text-decoration:none"><input type="button" name="checkout" value="Check Out"></a></td></tr>
</table>

<%@ include file="footer.html" %>
