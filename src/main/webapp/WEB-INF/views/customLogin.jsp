<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Custom Login Page</h1>
<h2><c:out value="${error}"/></h2>
<h2><c:out value="${logout}"/></h2>
<form method='POST' action="/login">
<div>
<input type='text' name='username' value='admin'>
<!-- username같은 경우엔 무조건 소문자로만 사용해야함 -->
</div>
<div>
<input type='password' name='password' value='admin'>
<!-- username같은 경우엔 무조건 소문자로만 사용해야함 -->
<div>
<input type="checkbox" name="remember-me" >
remember-me</div>
</div>

<input type='submit'>



<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>
<button><a href="../board/list">board/list</a></button>
<button><a href="../customLogout">로그아웃</a></button>
</body>
</html>