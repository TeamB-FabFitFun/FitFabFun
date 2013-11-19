<%@ include file="header.html" %>

<form action="" method="get">
	<table style="margin:0px auto; width:90%" cellspacing="10">
	<tr>
		<td colspan="3" style="padding-top:20px;">
			<fieldset style="padding:10px">
				<legend style="color:black; font-weight:bold">Payment Details</legend>
				<table align="left">
					<tr>
						<td>Credit Card Type</td>
						<td style="padding-left:10px;">
							<input type="radio" name="creditCardType" value="VISA">VISA
							<input type="radio" name="creditCardType" value="Master Card">Master Card
							<input type="radio" name="creditCardType" value="Discover">Discover
						</td>
					</tr>
					<tr>
						<td>Credit Card Number</td>
						<td style="padding-left:10px;">
							<input type="text" name="creditCardNo" size="30">
						</td>
					</tr>
					<tr>
						<td>Credit Card Expiration Date</td>
						<td style="padding-left:10px;">
							<input type="text" name="creditCardExpDate" size="30">
							(MM/YYYY)
						</td>
					</tr>
				</table>
			</fieldset>
		</td>
	</tr>
    <tr align="center">
        <td colspan="3" style="padding-top:15px">
            <a href="activity.jsp" style="text-decoration:none"><input type="button" style=" width:10%" name="cancel" value="Cancel"></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <input type="button" style=" width:10%" name="confirm" value="Confirm" onclick="confirm(this.form);">
		</td>
    </tr>
	</table>
</form>

<script type="text/javascript">
	function confirm(form) {
		var ccTypeRadioVisa = form.creditCardType[0].checked;
		var ccTypeRadioMaster = form.creditCardType[1].checked;
		var ccTypeRadioDiscover = form.creditCardType[2].checked;
		var ccNumText = form.creditCardNo.value;
		var ccExpText = form.creditCardExpDate.value;
		// validate the payment fields
		if ((!ccTypeRadioVisa && !ccTypeRadioMaster && !ccTypeRadioDiscover) || 
		    ccNumText == null || ccNumText == "" || 
			ccExpText == null || ccExpText == "") {
			alert("Please complete the payment details!");
		} else if (!isNormalInteger(ccNumText)) {
			alert("Credit Card Number is NOT a valid number!");
		} else if (!isValidExpDate(ccExpText)){
			alert("Credit Card Expiration Date is NOT valid!");
		} else {
			form.action='ResultsServlet?action=confirm';
			// submit
			form.submit();
		}
	}
	
	function isNormalInteger(str) {
		// ~~: Truncate the number (chops off any fractional portion).
		var n = ~~Number(str);
		return String(n) === str && n > 0;
	}
	
	function isValidExpDate(str) {
		if (str.length != 7)
			return false;
		
		var month = Number(str.substring(0, 2));
		var year = Number(str.substring(3));
		var slash = str.substring(2, 3);

		if (isNaN(month) || isNaN(year) || slash != "/")
			return false;
		
		var currYear = new Date().getFullYear();
		var currMonth = new Date().getMonth() + 1;	// from 0 - 11

		if (month< 1 || month>12 || year<currYear || (year == currYear && month<currMonth))
			return false;
		
		return true;
	}
</script>

<%@ include file="footer.html" %>
