<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<script >
function boardWrite() {
	if(confirm("등록하시겠습니까?"))
		var form=document.getElementById("boardWrite");
	form.submit();
}

</script>


<link rel="stylesheet" type="text/css" href="/myboard/resources/css/default.css" />
</head>

<c:choose>
	<c:when test="${writeresult==true}">
		<script>alert("등록 완료");</script>
	</c:when>
	
	<c:when test="${writeresult==false}">
		<script>alert("등록 실패");</script>
	</c:when>
	
</c:choose>

<body>
<script>

function boardWrite() {
	if(confirm("등록하시겠습니까?"))
		{
			var writeForm=document.getElementById("writeForm");
			writeForm.submit();
		}
}

</script>

<h1>[ 글쓰기 ]</h1>
<form action="/web/board/boardWrite" id="writeForm" method="POST" enctype="multipart/form-data">
	<table>
		<tr>
			<th>작성자</th>
			<td>${sessionScope.userid}
			<input type="hidden" name="userid" value="${sessionScope.userid}">
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>첨부 파일</th>
			<td>
				첨부파일<input type="file" name="uploadFile">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea rows="3" name="content"></textarea>
			</td>
		</tr>
		<tr>
			<td class="right" colspan="2">
				<input type="button" value="확인" onclick="boardWrite()" >
				<a href="web/board/boardList"><input type="button" value="취소" ></a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>
