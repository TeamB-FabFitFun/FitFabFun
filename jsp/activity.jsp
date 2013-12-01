<%@ include file="header.html" %>

<%@ page import="business.Activity" %>
<%@ page import="business.ActivityDataStore" %>

<jsp:useBean id="activityDS" scope="session" class="business.ActivityDataStore"/>

<input type="hidden" id="addedActivity" name="addedActivity" value="">
<table align="center" width="80%" rules="rows">
    <tr>
        <th bgcolor="#FFCC88">Activity</th>
        <th bgcolor="#FFCC88">Date</th>
        <th bgcolor="#FFCC88">Category</th>
        <th bgcolor="#FFCC88">Openings</th>
        <th bgcolor="#FFCC88">Fee</th>
        <th bgcolor="#FFCC88">&nbsp;</th>
    </tr>

    <%
        for (Activity activity : activityDS.getActivityDS()) {
            String strActId = activity.getActId();
            String strName = activity.getName();
            String strDate = activity.getDate();
            String strCategory = activity.getCategory();
            String strOpenings = activity.getOpenings();
            String strFee = activity.getFee();
    %>
    <tr>
        <td align="center"><%= strName%></td>
        <td align="center"><%= strDate%></td>
        <td align="center"><%= strCategory%></td>
        <td align="center"><%= strOpenings%></td>
        <td align="center"><%= strFee%></td>
        <td align="center">
            <input type="button" value="Add to Cart" 
                   onclick="form.action = '/fabfitfun/FFFServlet?action=addToCart';
                           document.getElementById('addedActivity').value = <%= strActId%>;
                           form.submit();">
        </td>
    </tr>
    <%
        }
    %>
    <tr><td>&nbsp;</td></tr>
    <tr>
        <td colspan="5">&nbsp;</td>
        <td align="center">
            <input type="image" src="images/cart.bmp" name="cart" alt="Cart" width="90" height="30" 
                   onclick="form.action = '/fabfitfun/FFFServlet?action=goToCart';
                           form.submit();">
        </td>
    </tr>
</table>

<%@ include file="footer.html" %>
