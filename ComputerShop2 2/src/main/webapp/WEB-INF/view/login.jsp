<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
    </head>
    <body>
        <form:form action="${pageContext.request.contextPath}/authenticate" method="POST">
            <p>
                Username: <input type="text" name="username">
            </p>
            <p>
                Password: <input type="password" name="password">
            </p>
            <input type="submit" value="Login">
        </form:form>
            
             
        
    </body>
</html>
