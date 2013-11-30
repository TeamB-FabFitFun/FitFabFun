<%@ include file="header.html" %>

<table style="margin:0px auto; width:500px" cellspacing="10">
	<tr><td>
		First Name</br>
		<input type="text" name="firstName" size="20" value="">
	</td></tr>
	<tr><td>
		Last Name</br>
		<input type="text" name="lastName" size="20" value="">
	<td></tr>
	<tr><td>
		Email</br>
		<input type="text" name="email" size="20" value="">
	</td></tr>
	<tr><td>
		Phone</br>
		<input type="text" name="phone" size="20" value=""><br>
	</td></tr>
	<tr><td>
		General Customer Feedback</br>
		<textarea rows="4" cols="50" name="feedback"></textarea><br>
		<input type="button" value="Submit" onclick="submitFeedback(this.form);">
	</td></tr>
</table>

<script type="text/javascript">
	function submitFeedback(form) {
		var feedbackMsg = form.feedback.value;
		if (feedbackMsg == null || feedbackMsg == "") {
			alert("Please fill in your message. Thank you!");
		} else {
			form.action='/fabfitfun/FFFServlet?action=contact_submit'; 
			form.submit();
		}
	}
</script>

<%@ include file="footer.html" %>