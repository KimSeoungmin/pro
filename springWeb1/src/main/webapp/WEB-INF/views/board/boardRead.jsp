<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardVO.title}</title>

<link rel="stylesheet" type="text/css" href="/web/resources/css/default.css" />
<!-- 로케이션은 주소창 부분을 뜻함  겟방식으로 vo요청을 하는것 -->
<script>
	function boardDelete() {
		if(confirm("삭제하시겠습니까?")){
			location.href="/web/board/boardDelete?boardnum=${vo.boardnum}";
		}
	}
	
	function replyWrite() {
		var replytext = document.getElementById("replytext");
			if(replytext.value.length==0)
				{
					alert("글일 입력해주세요")
					return;	/*리턴이 없으면 아무것도 입력이 되지않을때 바로 서브밋이 된다*/
				}
			document.getElementById("replyWrite").submit();
		}
	
		function replymodify(replynum,text) {
			document.getElementById("replytext").value=text;
			document.getElementById("replysubmit").value="댓글 수정";
			
			document.getElementById("replysubmit").onclick=function(){
				var updatext = document.getElementById("replytext").value;
				location.href="/web/board/replyUpdate?replynum="+replynum
						+"&boardnum=${vo.boardnum}&replytext="+updatext;
			}
		}
		
		function replyDelete(replynum) {
			if(confirm("삭제하시겠습니까?")){
				location.href="/web/board/replyDelete?boardnum=${vo.boardnum}"
				+"&replynum="+replynum;
			}
		}		
		
		
</script>


</head>
<body>
<c:choose>

	<c:when test="${updateResult==true}">
		<script>alert("수정 완료");</script>
	</c:when>
	
	<c:when test="${updateResult==false}">
		<script>alert("수정 실패");</script>
	</c:when>

	<c:when test="${resuldelete==true}">
		<script>alert("삭제 완료");</script>
	</c:when>
	
	<c:when test="${resuldelete==false}">
		<script>alert("삭제 실패");</script>
	</c:when>
</c:choose>
<h1>[ 글 읽기 ]</h1>
<table>
	<tr>
		<td class="right" colspan="2">
			<c:if test="${vo.userid==sessionScope.userid}">
				<a href="/web/board/boardUpdateForm?boardnum=${vo.boardnum}"><input type="button" value="수정"></a>
				<input type="button" value="삭제" onclick="boardDelete()">
			</c:if>
			<a href="/web/board/boardList"><input type="button" value="목록"></a>
		</td>
	</tr>
	<tr>
		<th>번호</th>
		<td>${vo.boardnum }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${vo.userid }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>
		<fmt:parseDate value="${vo.inputdate}" var="parsedRegdate" pattern="yyyy-MM-dd HH:mm:ss"/>
		<fmt:formatDate value="${parsedRegdate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
		</td>
	</tr>
	<tr>
		<th>조회</th>
		<td>${vo.hit }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${vo.title }</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
		<a href="/web/board/download?boardnum=${vo.boardnum}">${vo.originalFileName}</a>
		
		</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea readonly="readonly">${vo.content}</textarea></td>
	</tr>
</table>
<form action="/web/board/replyWrite" id="replyWrite" method="post" >
	<table id="replyinput">
		<tr>
			<td>
				
				<input type="text" name="replytext" id="replytext">
				<input type="button" id="replysubmit" value="댓글입력"
				 onclick="replyWrite()">
			</td>
		</tr>
	</table>
<input type="hidden" name="boardnum" value="${vo.boardnum}">
<input type="hidden" name="savedFileName" value="${vo.savedFileName}">

</form>
<div>
	<table class="reply">
		<c:forEach items="${replylist}" var="reply">
			<tr>
				<td>
					${reply.replytext}
				</td>
				
				<td>
					${reply.userid}
				</td>
				
				<td>
					${reply.inputdate}
				</td>
			
			
			<c:if test="${sessionScope.userid == reply.userid}">
				<td>
					<input type="button" value="삭제" onclick="replyDelete('${reply.replynum}')">
					<input type="button" value="수정" onclick="replymodify('${reply.replynum}','${reply.replytext }')">
				</td>
			</c:if>
		</tr>
		</c:forEach>
	
	</table>


</div>


</body>
</html>
