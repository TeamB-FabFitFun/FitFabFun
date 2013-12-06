<!-- This file should be included in other JSPs.a -->
<!-- It is not a complete JSP own its own .-->

<%@ page import="business.Member" %>

<div>
    <hr>
    <table align="center" width="75%" border="0">

        <%
            Member myMember = (Member) session.getAttribute("member");

            if (myMember == null || myMember.isEmpty()) {
                // Not logged in
%>
        <tr>
            <td align="left">
                <input type="button" value="Login" 
                       onclick="form.action = '/fabfitfun/FFFServlet?action=login';
                               form.submit();">
            </td>
            <td></td>
        </tr>

        <% } else {%>
        <tr>
            <td align="left">
                <input type="button" value="Logout" 
                       onclick="form.action = '/fabfitfun/FFFServlet?action=logout';
                               form.submit();">
            </td>
            <td align="right">
                <input type="image" src="/fabfitfun/images/cart.bmp" name="cart" alt="My Cart" width="90" height="30" 
                       onclick="form.action = '/fabfitfun/FFFServlet?action=goToCart';
                               form.submit();">
            </td>
        </tr>

        <% }%>

    </table>


</div>