<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
        <style>
            .error{
                color:red;
            }
        </style>
    </head>
    <body>
        <h1>Add new Product</h1>
        <form:form 
            action="${pageContext.request.contextPath}/form/category/${category}" 
            method="POST"
            modelAttribute="product"
            enctype="multipart/form-data">

            <p>
                <form:input path="name" placeholder="Enter Name"/>
                <!--                form:errors path="username" cssClass="error"/ -->
            </p>
            <p>
                <form:input path="pcode" placeholder="Enter Code"/>
                <!--                form:errors path="password" cssClass="error"/-->
            </p>
            <p>
                <form:input path="price" placeholder="Enter Price"/>

            </p>
            <p>
                <form:input path="quantity" placeholder="Enter quantity"/>

            </p>
            <p>
                <form:input path="description" placeholder="Enter description"/>

            </p>
            <p>
                <form:select path="category" items="${katigories}" multiple="false" itemValue="id" itemLabel="pcategory"/>
            </p>

            <p>
                select a file to upload:
                <input type="file" name="file"/>
            <br/>
        <c:if test="${category==1}">
            <input type="text" name="tower_type"/>
        </c:if>
        <c:if test="${category==2}">
            <input type="file" name="motherboard_manu"/>
        </c:if>
        
    </p>
    <input type="submit" value="Register">

    </form:form>


    <!-- <form:form action="${pageContext.request.contextPath}/form/upload" method="post" enctype="multipart/form-data">
         <table>
             <tr>
                 <td>Select a file to upload</td>
                 <td><input type="file" name="file" /></td>
             </tr>
             <tr>
                 <td><input type="submit" value="Submit" /></td>
             </tr>
         </table>
    </form:form>

    -->






</body>
</html>
