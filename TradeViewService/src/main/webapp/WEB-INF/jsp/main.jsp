<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	 <script src="//code.jquery.com/jquery.js"></script>
<title></title>
</head>
<body>
	<div>
		<form action="/no1" method="get">
			<input type="submit" value="1.각 연도별 합계 금액"></input>
		</form>
		</br>
		<form action="/no2" method="get">
			<input type="submit" value="2.각 연도별 무거래 고객"></input>
		</form>
		</br>
		<form action="/no3" method="get">
			<input type="submit" value="3.연도별 관리점별 거래금액 "></input>
		</form>
		</br>
		<form action="/no4" method="get">
			<input type="text" id="brName" name="brName" size="5"></input>
			<input type="submit" value="4.해당 지점 거래금액 합계"></input>
		</form>
	</div>
</body>
</html>