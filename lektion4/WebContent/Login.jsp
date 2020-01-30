<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="login" method="post">
<!-- action="login" betyder at det der sker her bliver sent til LginServler, hvis url er /login -->
	<br>UserID: <input type="text" name="userID"/>
	<br>Password: <input type="password" name="password"/>
	<br><input type="submit" />

</form>

</body>
</html>