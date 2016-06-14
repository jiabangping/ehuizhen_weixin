<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${snsUserInfo != null }">
${ snsUserInfo.openId}
<br/>
${ snsUserInfo.nickname}
</c:if>
<c:if test="${snsUserInfo != null }">
用户未授权
</c:if>
</body>
</html>