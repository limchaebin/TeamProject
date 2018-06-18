<%@page import="net.admin.notice.db.NoticeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>왓츄 : 공지사항 </title>

<!-- css -->
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/admin.css" rel="stylesheet" type="text/css">

<!-- 웹 폰트 : 나눔고딕 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">

</head>
<body>
	<%
		NoticeBean noticebean = (NoticeBean) request.getAttribute("noticebean");
	%>

	<!-- 헤더영역 -->
	<jsp:include page="../../inc/header.jsp" />
	<!-- 헤더영역 -->

	<!-- 어드민 서브메뉴 -->
	<jsp:include page="../../inc/admin_sub.jsp" />
	<!-- 어드민 서브메뉴 -->
<article class="content">
	<div id="content">
		<!-- <h1>notice content</h1> -->
		<table class="db_list adminCont">
			<tr>
				<th class="th1">No</th>
				<th class="th2">제목</th>
				<th class="th3">작성자</th>
				<th class="th5" colspan="2">작성일</th>
			</tr>
			<tr>
				<td><%=noticebean.getN_num()%></td>
				<td><%=noticebean.getN_subject()%></td>
				<td>관리자</td>
				<td colspan="2"><%=noticebean.getN_date()%></td>
			</tr>
			<tr>
				<th colspan="5" class="th5">내용</th>
			</tr>
			<tr>
				<td colspan="5" id="ad_content"><%=noticebean.getN_content()%></td>
			</tr>
			<tr>
				<th colspan="3" class="th5">이미지</th>
				<th colspan="2" class="th5">파일</th>
			</tr>
			<tr>
				<td colspan="3"><input type="image" src="<%=noticebean.getN_image()%>"></td>
				<td colspan="2"><%=noticebean.getN_file()%></td>
			</tr>

		</table>
		
		<div class="c_admin-notice-write">
			<button type="submit" class="wirtebtn" onclick="location.href='./AdminNoticeList.an'">글목록</button>
			<button type="button" class="wirtebtn" onclick="location.href='./AdminNoticeUpdate.an?n_num=<%=noticebean.getN_num() %>'">글수정</button>
			<button type="button" class="wirtebtn" onclick="location.href='./AdminNoticeDelete.an?n_num=<%=noticebean.getN_num() %>'">글삭제</button>
		</div>
		<div class="admin-notice-write"></div>
	</div>
</article>
	<!-- 푸터 영역 -->
	<jsp:include page="../../inc/footer.jsp" />
	<!-- 푸터 영역 -->

</body>
</html>