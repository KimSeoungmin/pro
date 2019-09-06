<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/default.css" />" />
<script>
	function boardUpdate() {
		if (confirm("수정하시겠습니까?")) {
			var updateForm=document.getElementById("updateForm")
			updateForm.submit();
		}
	}
</script>


</head>
<body>
	<script></script>
	<h1>[ 글 수정 ]</h1>
	<form action="/web/board/boardUpdate" id="updateForm" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<th>번호</th>
				<td>${vo.boardnum}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${sessionScope.userid}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>
				
				<fmt:parseDate value="${vo.inputdate}" var="parsedate" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${parsedate}" pattern="yyyy년 MM월 dd일 HH초 mm분 ss초"/>
				
				</td>
			</tr>
			<tr>
				<th>조회</th>
				<td>${vo.hit }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${vo.title}"></td>
			</tr>
			<tr>
				<th>첨부 파일</th>
				<td>
				
				<c:if test="${vo.originalFileName!=null}">
					${vo.originalFileName}
					<a href="/web/board/FileUpdate?boardnum=${vo.boardnum}" ><input type="button" value="파일수정"> </a>
				</c:if>
				<c:if test="${vo.originalFileName==null}">
					<input type="file" name="uploadFile">
				</c:if>
				 
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="3" name="content">${vo.content}</textarea>
				</td>
			</tr>
			<tr>
				<td class="right" colspan="2"><input type="button" value="수정" onclick="boardUpdate()">
					<a href="/web/board/boardList"><input type="button" value="취소"></a></td>
			</tr>
		</table>
	<input type="hidden" name="boardnum" value="${vo.boardnum}">
	</form>
</body>
</html>
