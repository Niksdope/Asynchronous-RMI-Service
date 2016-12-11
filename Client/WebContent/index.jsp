<%@ include file="includes/header.jsp" %>

<div class="animated bounceInDown" style="font-size:48pt; font-family:arial; color:#990000; font-weight:bold">String Comparison Service</div>

</p>&nbsp;</p>&nbsp;</p>

<table width="600" cellspacing="0" cellpadding="7" border="0">
	<tr>
		<td valign="top">

			<form bgcolor="white" method="POST" action="doProcess">
				<fieldset>
					<legend><h3>Specify Details</h3></legend>
				
					<b>Select Algorithm:</b>
						
					<select name="cmbAlgorithm">
						<option>Damerau-Levenshtein Distance</option>
						<option>Jaro-Winkler Distance</option>
						<option selected>Levenshtein Distance</option>
						<option>Hamming Distance</option>
					</select>	
					<p/>

					<b>Enter String <i>s</i> :</b><br>
					<textarea name="txtS" rows="5" cols="100"  wrap="soft"></textarea>	
					<p/>

					<b>Enter String <i>t</i> :</b><br>
					<textarea name="txtT" rows="5" cols="100"  wrap="soft"></textarea>	
					<p/>

					<center><input type="submit" value="Compare"></center>
				</fieldset>							
			</form>	

		</td>
	</tr>
</table>
<%@ include file="includes/footer.jsp" %>

