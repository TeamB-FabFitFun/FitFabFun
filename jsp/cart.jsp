<%@ include file="header.html" %>

<%@page import="java.util.ArrayList"%>
<%@page import="business.Activity"%>

<h2><font color="#990000">&nbsp;&nbsp;&nbsp;&nbsp;Shopping Cart</font></h2>
<hr>

<jsp:useBean id="member" scope="session" class="business.Member"/>
<jsp:useBean id="activityDS" scope="session" class="business.ActivityDataStore"/>

<% ArrayList<String> cart = member.getCart();%>

<% if (cart.isEmpty()) {%>
<div align="center">
    <b><font color="#990000">Your shopping cart is empty.</font></b>
    <br><br>
    <input type="button" value="View Activities" 
           onclick="form.action = '/fabfitfun/FFFServlet?action=class';
               form.submit();">
</div>

<% } else {%>

<input type="hidden" id="removedActivity" name="removedActivity" value="">
<table style="margin:0px auto; width:70%" rules="rows">

    <%
        int totalCost = 0;

        for (String actId : cart) {
            Activity activity = activityDS.getActivity(actId);
            totalCost += activity.getFeeInt();
    %>
    <tr>
        <td><%= activity.getName()%></td>        
        <td><%= activity.getDate()%></td>
        <td><%= activity.getCategory()%></td>
        <td align="center"><%= activity.getFee()%></td>        
        <td>
            <input type="button" value="Remove" 
                   onclick="form.action = '/fabfitfun/FFFServlet?action=removeFromCart';
               document.getElementById('removedActivity').value = <%= actId%>;
               form.submit();">
        </td>
    </tr>
    <% }%>
</table>
<hr>
<br>
<table style="margin:0px auto; width:75%">
    <tr>
        <td align="right"><b>Total Cost: $<%= totalCost%></b></td>
    </tr>
    <tr>
        <td align="right">           
            <input type="button" value="Checkout" 
                   onclick="form.action = '/fabfitfun/FFFServlet?action=checkout';
               form.submit();">
        </td>
    </tr>
</table>
<% }%>

<%@ include file="footer.html" %>
