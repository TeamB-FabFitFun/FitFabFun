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

        <%
            Member member = (Member) session.getAttribute("member");
            // Only display add buttons for members that are logged in
            if (member != null) {
        %>
        <td align="center">
            <% if (member.inActivities(strActId)) {%>
            Registered
            <% } else if (member.inCart(strActId)) {%>
            In Cart
            <% } else {%>
            <input type="button" value="Add to Cart" 
                   onclick="form.action = '/fabfitfun/FFFServlet?action=addToCart';
                           document.getElementById('addedActivity').value = <%= strActId%>;
                           form.submit();">
            <% }%>
        </td>
        <% }%>

    </tr>
    <%
        }
    %>

</table>

<%@ include file="shortcuts.jsp" %>

<%@ include file="footer.html" %>
