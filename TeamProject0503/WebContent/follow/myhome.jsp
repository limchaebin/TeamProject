<%@page import="net.admin.manage.db.MovieBean"%>
<%@page import="net.favorite.db.FavoriteBean"%>
<%@page import="net.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>왓츄: My Watchu Page</title>

<!-- CSS -->
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/myhome.css" rel="stylesheet" type="text/css">

<!-- 웹 폰트 : 나눔고딕 -->
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic" rel="stylesheet">


</head>
<body>
<%
MemberBean getmember=(MemberBean)request.getAttribute("memberbean");
int followercount=((Integer)request.getAttribute("followercount"));
int followingcount= ((Integer)request.getAttribute("followingcount"));
int reviewcount= ((Integer)request.getAttribute("reviewcount"));
MovieBean favorite = (MovieBean)request.getAttribute("moviebean");
String grede="";
if(getmember.getM_grade()==1){
	grede="정회원";
}else if(getmember.getM_grade()==2){
	grede="VIP";
}
%>

<!-- 헤더 영역 -->
<jsp:include page="../inc/header.jsp"/>
<!-- 헤더 영역 -->


<article>

<div class="all">

<section class="sec myInfo">
	<div id="profile">
		<img src="./images/proflie1.png" width="200px" height="200px">
		<p><%=getmember.getM_id()%></p><p><%=grede%></p> <!-- 이름, 등급 가져오기 -->
	</div><!-- profile -->
	<div id="info">
		<table border="1">
			<tr>
				<th>Following</th><th>Follower</th> 
			</tr>
			<tr>
				<td><%=followingcount%></td><td><%=followercount%></td><!-- 팔로잉, 팔로워 수 가져오기 -->
			</tr>
			<tr>
				<th colspan="2">리뷰 수</th>
			</tr>
			<tr>
				<td><%=reviewcount%></td><!-- 리뷰 수 가져오기 -->
			</tr>
			<tr>
			<%System.out.println(favorite.getMv_genre()); %>
				<th colspan="2">선호장르</th>
			</tr>
			<tr>
				<td><%=favorite.getMv_genre()%></td><td><%="드라마"%></td> <!-- 선호장르 상위 2개 -->
			</tr>
		</table>
	</div><!-- info -->
</section><!-- myInfo -->


<section class="sec myReview">
	<div class="secInfo">
		<h2><%=getmember.getM_id()%>님 의 리뷰</h2>
		<a href=""><p>리뷰 더 보기 >></p></a><!-- reviewList.jsp로 가야함 -->
	</div>	
	
	<!-- ↓↓↓↓↓↓↓리뷰 리스트 5개. for문 으로 돌릴 수 있으면 for문 사용해도 무방↓↓↓↓↓↓↓↓↓↓↓-->	
	<!-- <div class="rvList"> -->
			<%for(int i=0;i<=4;i++){ %>
			<div id="rv"> 
				<p>영화 제목/ 리뷰 날짜/ 추천/ 신고</p>
				<p class="rvList">
				<%="Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has "%></p>
			</div> 
			<%} %>
	<!-- </div> -->	
</section>
	

<section class="sec myFavMovie">
	<div class="secInfo">
		<h2><%=getmember.getM_id()%>님 이 좋아한 영화</h2>
		<a href=""><p>영화 더 보기 >></p></a><!-- reviewList.jsp로 가야함 -->	
	</div>	
	
		<!-- ↓↓↓↓↓↓↓영화 리스트 5개. for문 으로 돌릴 수 있으면 for문 사용해도 무방↓↓↓↓↓↓↓↓↓↓↓-->	
	<!-- <div class="mvList" id="rv"> -->
		<div class="mvList"> 
			<%for(int i=0;i<=4;i++){%>
			<div>
				<img src="./images/animation/Zootopia_p.jpg" width="175px" height="260px">
				<p>영화 제목</p>
			</div>
		<%} %>
		</div>
	<!-- </div> -->	
</section>
</div><!-- all -->
	
</article>



<!-- 푸터 영역 -->
<jsp:include page="../inc/footer.jsp"/>
<!-- 푸터 영역 -->

</body>
</html>