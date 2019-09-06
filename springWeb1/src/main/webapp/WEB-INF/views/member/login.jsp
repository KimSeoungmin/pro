<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>[ 로그인 ]</h1><br>
	<form action="login" method="post">
		ID <input type="text" name="userid" placeholder="아이디 입력"><br>
		PWD	<input type="password" name="userpwd"><br>
		<button type="submit">로그인</button>
		<button type="reset">취소</button><br>
		
		<c:if test="${result==false }">
			아이디 또는 패스워드가 일치하지 않습니다.
		</c:if>
		
	</form>
<%-- 	${fal} --%>
</body>
</html>