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
<h1>All Page</h1>
<sec:authorize access="isAnonymous()"><!--isAnonymous(): 익명의 사용자라면  -->
<a href="/customLogin">로그인</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()"><!--isAuthenticated() : 인증된 사용라자면  -->
<form action="/customLogout" method="post">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
<button>로그아웃</button>
</form>
</sec:authorize>
</body>
</html>