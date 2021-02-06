<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
</head>

<body>
<div>
    <form:form method="POST" modelAttribute="regForm">
        <h2>Registration</h2>
        <div>
            <form:input type="text" path="userName" placeholder="Login*"
                        autofocus="true"></form:input>
        </div>
        <div>
            <form:input type="password" path="password" placeholder="Password*"></form:input>
        </div>

        <div>
            <form:input type="email" path="email" placeholder="Email*"></form:input>
        </div>
        <div>
            <form:input type="text" path="firstName" placeholder="First name"></form:input>
        </div>
        <div>
            <form:input type="text" path="lastName" placeholder="Last name"></form:input>
        </div>
        <div>
            <form:input type="text" path="patronymic" placeholder="Patronymic"></form:input>
        </div>
        <div>
            <form:input type="date" path="birthDay" placeholder="Birth day"></form:input>
        </div>
        <button type="submit">submit</button>
        <div style="color: red">
                ${error}
        </div>
    </form:form>
    <h4><a href="/lk/api/auth/login">Login</a></h4>
</div>
</body>
</html>