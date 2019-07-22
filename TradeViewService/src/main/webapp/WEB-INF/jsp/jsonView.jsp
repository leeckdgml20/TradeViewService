<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%
	int statusCd = response.getStatus();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>
</head>
<body>
	<form action="/main" method="get">
		<input type="submit" value="뒤로"></input>
	</form>
	<textarea id="jsonArea" style="width: 100%" readonly="readonly" disabled></textarea>
	<script>
	document.getElementById('jsonArea').style.height = window.innerHeight + "px";

	 var receiveText = JSON.parse('${jsonText}');
	 var jsonText = JSON.stringify(receiveText,null,2);
	 
	 var errorCd = "<%=statusCd%>";
	 if(errorCd != '200') {
		 jsonText = "http status : " + errorCd + "\n" + jsonText;
	 }
	 document.getElementById('jsonArea').value = jsonText;
	</script>
</body>
</html>