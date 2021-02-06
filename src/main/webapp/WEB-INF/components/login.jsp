<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>
<body>
<div >
    <form:form method="POST" modelAttribute="loginForm">
        <h2>Login</h2>
        <div>
            <form:input type="text" path="loginIdentifier" placeholder="Login*"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="password" path="loginPassword" placeholder="Password*"></form:input>
        </div>
        <button type="submit">submit</button>
        <div style="color: red">
                ${error}
        </div>
    </form:form>
    <h4><a href="/lk/api/auth/register">Registration</a></h4>
</div>
</body>
</html>
