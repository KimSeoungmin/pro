<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>parameter 전송</title>
</head>
<body>


	<a href="send7">Model 객체 실습</a><br>
	
	<c:if test="${data !=null && vo!=null}">
	test : ${data}<br>
	vo.a : ${vo.a}<br>
	vo.b : ${vo.b}<br>
	
	requestSceop.data : ${requestScope.data}<br>
	requestSceop.a :    ${requestScope.vo.a}<br>
	requestSceop.b :    ${requestScope.vo.b}<br>
	</c:if>



	<img src="resources/image.jpg"/>	
	<img src="/web/resources/image.jpg"/>   <!-- 절대경로 지정 -->
	<img src='<c:url value="/resources/image.jpg"/> '/>	<!-- 코어를 사용 하는 방법 -->
	<img src="abcdef/image.jpg"/>   <!-- servlet-context.xml에   -->
									<!-- <resources mapping="/abcdef/**"--> 
									<!-- location="/resources/" />  -->
									<!-- 에 mapping이름을 설정 해주면   -->
									<!-- location 의 경로를 호출 해서 가짐   -->
	<ul>
		<li>
			<h2><a href="send1?a=테스트&b=1234">a 태그로 전송</a></h2>
		</li>
		<li>
			<h2>form 태그로 전송(get)</h2>
			<form action="send2" method="get">
				<input type="text" name="a">
				<input type="text" name="b">
				<input type="submit" value="get으로 전송!!">
			</form> 
		</li>
			<li>
			<h2>form 태그로 전송(post)</h2>
			<form action="send2" method="post">
				<input type="text" name="a">
				<input type="text" name="b">
				<input type="submit" value="post로 전송!!">
			</form> 
		</li>
		<li>
			<h2><a href="send4?a=테스트&b=1231">a 태그(VO)</a></h2>
		
		</li>
		<li>
			<h2>form 태그로 전송(vo get)</h2>
			<form action="send5" method="get">
				<input type="text" name="a">
				<input type="text" name="b">
				<input type="submit" value="get VO 전송!!">
			</form> 
		</li>
		
		<li>
			<h2>form 태그로 전송(vo post)</h2>
			<form action="send6" method="post">
				<input type="text" name="a">
				<input type="text" name="b">
				<input type="submit" value="post VO 전송!!">
			</form> 
		</li>
		
	</ul>
</body>
</html>