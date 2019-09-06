<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<!--해드태그 내무나 타이틀안에 작성하면 됨 -->
<script> 
	function guestbookWrite(){
		if(confirm("등록 하시겠습니까?"))	
			var form = document.getElementById("writeForm");
 		form.submit();   
}
	function guestbookDelete(){
		{
			return confirm("삭제 하시겠습니까?");	
		}
	}
	map = {
			  const svg = d3.create("svg")
			      .attr("viewBox", [0, 0, width, height]);

			  const tile = d3.tile()
			      .extent([[0, 0], [width, height]])
			      .tileSize(512)
			      .clampX(false);

			  const zoom = d3.zoom()
			      .scaleExtent([1 << 8, 1 << 22])
			      .extent([[0, 0], [width, height]])
			      .on("zoom", () => zoomed(d3.event.transform));

			  let image = svg.append("g")
			      .attr("pointer-events", "none")
			    .selectAll("image");

			  svg
			      .call(zoom)
			      .call(zoom.transform, d3.zoomIdentity
			        .translate(width >> 1, height >> 1)
			        .scale(1 << 12));

			  function zoomed(transform) {
			    const tiles = tile(transform);

			    image = image.data(tiles, d => d).join("image")
			        .attr("xlink:href", d => url(...d3.tileWrap(d)))
			        .attr("x", ([x]) => (x + tiles.translate[0]) * tiles.scale)
			        .attr("y", ([, y]) => (y + tiles.translate[1]) * tiles.scale)
			        .attr("width", tiles.scale)
			        .attr("height", tiles.scale);
			  }

			  return svg.node();
			}
</script>

</head>
<body>
<c:choose>
	<c:when test="${writeResult==true}">
		<script>alert("등록 완료");</script>
	</c:when>
	
	<c:when test="${writeResult==false}">
		<script>alert("등록 실패");</script>
	</c:when>
	
	<c:when test="${deleteResult==true}">
		<script>alert("삭제 완료");</script>
	</c:when>
	
	<c:when test="${deleteResult==false}">
		<script>alert("삭제 실패");</script>
	</c:when>

</c:choose>

<!-- 검색 -->
<form action="/web/guestbook/guestbookList"  method="get">
	<input type="hidden" name="searchItem" value="name">
	작성자<input type="text" name="searchKeyword">
	<input type="submit" value="검색">
	
</form>

<!-- 방명록출력 						id가왜있는거지?-->	
<form action="/web/guestbook/write"	id="writeForm" method="post" enctype="multipart/form-data">
<!-- 								    ㄴ 특정 html을 찾아가는 거기때문에 name의 이름과 겹쳐서는 안됨 -->
	<fieldset>
				<legend><input type="button" value="글쓰기" onclick="guestbookWrite()"/></legend>
				<p>작성자 <input type="text" name="name"/></p>
				첨부파일<input type="file" name="uploadFile">
				내용 <textarea rows="3" name="content"></textarea><br>
				비밀번호 : <input type="password" name="pwd"><br>
				<a href="#" >글삭제</a>
	</fieldset>
</form>

	<c:forEach items="${list}" var="guestbook">
		<fieldset>
			<legend>${guestbook.seq}</legend>
			<p>작성자 : ${guestbook.name}</p>
			<p>
			작성일 :   
			<fmt:parseDate value="${guestbook.regdate}" var="parsedRegdate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${parsedRegdate}" pattern="yyyy-MM-dd"/>
			</p>
			<pre>내용 :${guestbook.content}</pre>
			<p>
				첨부파일:						<!-- 시퀀스는 게시글번호 -->
				<a href="/web/guestbook/download?seq=${guestbook.seq}">${guestbook.originalFilename}</a>
			<form action="/web/guestbook/delete" method="post">
			비밀번호 : <input type="password" name="pwd"/><br>
			<input type="hidden" name="seq" value="${guestbook.seq}">
			<input type="submit" value="글삭제" onclick="return guestbookDelete()">
			</form>
			
		</fieldset>
	
	</c:forEach>
	
	url = ƒ(x, y, z)
	url = (x, y, z) => `https://api.mapbox.com/styles/v1/mapbox/streets-v11/tiles/${z}/${x}/${y}${devicePixelRatio > 1 ? "@2x" : ""}?access_token=pk.eyJ1IjoidG1jdyIsImEiOiJjamN0Z3ZiOXEwanZkMnh2dGFuemkzemE3In0.gibebYiJ5TEdXvwjpCY0jg`
</body>
</html>