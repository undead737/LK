<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>
<body>
<div>
    <div>
        User: ${user.userName}
    </div>
    <div>
        Balance: ${balance}
    </div>
    <div>
        <form method="post">
            <div>
                <input type="number" id="balance" name="balance" class="form-control" placeholder="Balance*">
            </div>
            <button type="submit">edit</button>
        </form>
        <div style="color: red">
            ${error}
        </div>
    </div>
    <h4><a href="/lk/api/auth/logout">Logout</a></h4>
</div>
</body>
</html>