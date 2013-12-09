<%@ include file="header.html" %>

<jsp:useBean id="member" scope="session" class="business.Member"/>

<%
    String fName = member.getFirstName();
    String lName = member.getLastName();
    String email = member.getEmail();
    String password = member.getPassword();
    String gender = member.getGender();
    String age = "" + member.getAge();
    
    String isMale = "";
    String isFemale = "";
    if ("male".equalsIgnoreCase(gender)) {
        isMale = "selected";
    } else {
        isFemale = "selected";
    }
%>

<hr>
<div align="center"><b>Member Profile</b></div>
<br>
<input type="hidden" name="oldEmail" value="<%= email %>">
<table align="center" width="80%" cellpadding="5" cellspacing="0">
    <tr>
        <td align="right"><font color="#990000">First Name</font></td>
        <td><input type="text" style="background-color:#FFFFFF" name="firstName" size="20" value="<%= fName %>"></td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Last Name</font></td>
        <td><input type="text" style="background-color:#FFFFFF" name="lastName" size="20" value="<%= lName %>"></td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Email</font></td>
        <td><input type="text" style="background-color:#FFFFFF" name="email" size="20" value="<%= email %>"></td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Password</font></td>
        <td><input type="password" style="background-color:#FFFFFF" name="password1" size="20" value="<%= password %>"></td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Re-enter Password</font></td>
        <td><input type="password" style="background-color:#FFFFFF" name="password2" size="20" value="<%= password %>"></td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Gender</font></td>
        <td>
            <select name="gender">
                <option value="Female" <%= isFemale %>>Female</option>
                <option value="Male" <%= isMale %>>Male</option>
            </select>
        </td>
    </tr>
    <tr>
        <td align="right"><font color="#990000">Age</font></td>
        <td><input type="text" style="background-color:#FFFFFF" name="age" size="20" value="<%= age %>"></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="button" name="update" value="Update" onclick="submitUpdate(this.form);"></td>
    </tr>
</table>

<script type="text/javascript">
            function submitUpdate(form) {
                // perform field check
                var fName = form.firstName.value;
                var lName = form.lastName.value;
                var email = form.email.value;
                var pwd1 = form.password1.value;
                var pwd2 = form.password2.value;
                var age = form.age.value;

                if (fName == null || fName == "")
                    alert("Please enter your first name!");
                else if (lName == null || lName == "")
                    alert("Please enter your last name!");
                else if (email == null || email == "")
                    alert("Please enter your email!");
                else if (pwd1 == null || pwd1 == "")
                    alert("please enter your password!");
                else if (pwd2 == null || pwd2 == "")
                    alert("please reenter your password!");
                else if (pwd1 != pwd2)
                    alert("Your passwords do not match. Please re-enter!");
                else if (age == null || age == "" || isNaN(age) || Number(age) < 1 || Number(age) > 100)
                    alert("Please enter a valid age!");
                else {
                    form.action = '/fabfitfun/FFFServlet?action=updateProfile';
                    form.submit();
                }
            }
</script>

<%@ include file="shortcuts.jsp" %>

<%@ include file="footer.html" %>
