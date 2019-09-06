<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>

<script> 
	function loginp(){
		if(confirm("로그인해주세요"))	
			var form = document.getElementById("writeForm");
 		form.submit();   
	}
</script>

<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" />

<script>
	function pageProc(currentPage, searchItem, searchKeyword) {
		location.href="/web/board/boardList?currentPage=" + currentPage + "&searchItem=" + searchItem + "&searchKeyword=" + searchKeyword;
	}
</script>


<!-- 710선생님 -->
<!-- <link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" /> 절대 경로로 지정-->
<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/default.css"/>"/> ul태그 경로설정--%>

</head>
<c:choose>
	<c:when test="${deleteResult==true }">
		<script>alert("삭제 완료")</script>
	</c:when>
	
	<c:when test="${deleteResult==false }">
		<script>alert("삭제 실패")</script>
	</c:when>
</c:choose>

<c:choose>
	<c:when test="${writeResult==true }">
		<script>alert("등록 완료")</script>
	</c:when>
	
	<c:when test="${writeResult==false }">
		<script>alert("등록 실패")</script>
	</c:when>
</c:choose>


<body>
	<h1>[ 게시판 ]</h1>
	
	<form action="/web/board/boardSelect" method="post">
	<select name="searchItem">
	<option value="title"<c:if test="${searchItem eq 'title'}"> selected</c:if>> 제목</option>
	<option value="userid"<c:if test="${searchItem eq 'userid'}"> selected</c:if>> 아이디</option>
	<option value="content"<c:if test="${searchItem eq 'content'}"> selected</c:if>>내용</option>
	</select>
	
	<input type="text" name="searchKeyword">
	<input type="hidden" value="searchItme" name="name">
	<button type="submit">검색</button>
	</form>
	<table>
	<c:if test="${sessionScope.userid==null}">
		<tr>
			<td class="right" colspan="5">
				<a href="/web/main" id="loginp"><img src="/web/resources/img/write_64.png"></a>
			</td>
		</tr>
		</c:if>
		<c:if test="${sessionScope.userid!=null}">
		<tr>
			<td class="right" colspan="5">
				<a href="/web/board/boardWriteForm"><img src="/web/resources/img/write_64.png"></a>
			</td>
		</tr>
		</c:if>
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${list}" var="board">
			<tr>
				<td class="center">${board.boardnum}</td>
				<td class="center">${board.userid}</td>
				<td id="title">
				<a href="/web/board/boardRead?boardnum=${board.boardnum}">${board.title}</a>
				</td>
				<td class="center">${board.hit}</td>
				<td id="inputdate">
				
		<fmt:parseDate value="${board.inputdate}" var="parsedRegdate" pattern="yyyy-MM-dd HH:mm:ss"/>
		<fmt:formatDate value="${parsedRegdate}" pattern="yyyy년 MM월 dd일 "/>
				
				</td>
			</tr>
		</c:forEach>
		
		<!-- 페이징 -->
<tr>
	<td id="navigator" colspan="5">
<!--	 		펑션을 만들어서: 다음부터 메서드실행부를 만들면 됨 -->
		<a href="javascript:pageProc(${navi.currentPage - navi.pagePerGroup}, '${searchItem}', '${searchKeyword}')">◁◁ </a> &nbsp;&nbsp;
		<a href="javascript:pageProc(${navi.currentPage - 1}, '${searchItem}', '${searchKeyword}')">◀</a> &nbsp;&nbsp;
		<c:forEach var="counter" begin="${navi.startPageGroup}" end="${navi.endPageGroup}"> 
			<c:if test="${counter == navi.currentPage}"><b></c:if>
				<a href="javascript:pageProc(${counter}, '${searchItem}', '${searchKeyword}')">${counter}</a>&nbsp;
			<c:if test="${counter == navi.currentPage}"></b></c:if>
		</c:forEach>
		&nbsp;&nbsp;
		<a href="javascript:pageProc(${navi.currentPage + 1}, '${searchItem}', '${searchKeyword}')">▶</a> &nbsp;&nbsp;
		<a href="javascript:pageProc(${navi.currentPage + navi.pagePerGroup}, '${searchItem}', '${searchKeyword}')">▷▷</a>
	</td>
</tr>
		
<!-- 		0710 선생님 -->
<%-- 		<c:forEach items="${list}" var="board"> --%>
<!-- 			<tr> -->
<%-- 				<td class="center">${board.boardnum}</td> --%>
<%-- 				<td class="center">${board.userid}</td> --%>
<%-- 				<td id="title">${board.title}</td> --%>
<%-- 				<td class="center">${board.hit}</td> --%>
<%-- 				<td id="inputdate">${board.inputdate}</td> --%>
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>

	</table>
</body>
</html>
