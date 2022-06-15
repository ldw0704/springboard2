<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Admin Page</h1>
<p>principal : <sec:authentication property="principal"/></p>
<p>사용자이름 : <sec:authentication property="principal.member.uname"/></p>
<p>사용자아이디 : <sec:authentication property="principal.member.uid"/></p>
<p>사용자비번 : <sec:authentication property="principal.member.upw"/></p>
<p>사용자권한 : <sec:authentication property="principal.member.authList"/></p>
<form action="/customLogout" method="post">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
<button>로그아웃</button>
</form>
</body>
</html>