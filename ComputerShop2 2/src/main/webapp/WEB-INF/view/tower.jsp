<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>TOWERS</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />

    </head>
    <body>

        <jsp:include page="header.jsp"/>
        
         <security:authorize access="hasRole('ADMIN')">

        <div id="form" >
            <h3>Add new Tower</h3>
            <form:form 
                action="${pageContext.request.contextPath}/tower/add" 
                method="POST"
                modelAttribute="product"
                enctype="multipart/form-data">

                <form:hidden path="id"/>

                <p>
                    <form:input path="name" placeholder="Enter Name"/><span style="color:red;"><c:out value="${msgName}"/></span>

                </p>
                <p>
                    <form:input path="pcode" placeholder="Enter Code"/><span style="color:red;"><c:out value="${msgCode}"/></span>
                    <!--                form:errors path="password" cssClass="error"/-->
                </p>
                <p>
                    <input type="text"placeholder ="Enter Price" value="${pricee}" name="pricee"/><span style="color:red;"><c:out value="${msgNumbersPrice}"/></span>
                </p>
                <p>
                    <input type="text"placeholder ="Enter discount" value="${discountt}" name="discountt"/><span style="color:red;"><c:out value="${msgNumbersDiscount}"/></span>


                </p>
                <p>
                    <input type="text" placeholder ="Enter ammount" value="${ammountt}" name="ammountt"/><span style="color:red;"><c:out value="${msgNumbersAmmount}"/></span>



                </p>
                <p>
                    <form:input path="description" placeholder="Enter description"/>



                </p>


                <select name = "type">
                    <optgroup label = "Select Type">
                        <c:forEach items="${types}" var="t"> 
                            <option value="${t.getId()}">${t.name}</option>
                        </c:forEach>
                    </optgroup>
                </select>
                <br>
                <br>
                <select name="manufacturer">
                    <optgroup label = "Select Manufacturer">
                        <c:forEach items="${manufacturers}" var="m"> 
                            <option  value="${m.getId()}">${m.name}</option>
                        </c:forEach>
                    </optgroup>
                </select>

                <br>
                <br>

                <p>
                    select a file to upload:
                    <input type="file" name="file"/> <span style="color:red;"><c:out value="${msgImg}"/></span>


                    <br/>
                </p>
                <span style="color:red;"><c:out value="${msgGeneral}"/></span>
                <input type="submit" value="Register">



            </form:form>
        </security:authorize>
        </div>

        <div class="container mt-5">
            <h3>Filters</h3>

            <div class="btn-group">
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Price
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/price/0/50">up to 50€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/price/50/100">50€ up to 100€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/price/100/150">100€ up to 150€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/price/150/200">150€ up to 200€</a>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Manufacturer
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${manufacturers}" var="p">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/manufacturer/${p.getId()}">${p.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Type
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${types}" var="p">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/tower/type/${p.getId()}">${p.name}</a>

                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>



        <div class="container mt-5">
            <h3 class="text-md-left">Towers</h3>
            <c:forEach items="${productList}" var="q" varStatus="status">
                <c:url var="addToCart" value="/cart/addToCart">
                    <c:param name="productId" value="${q.id}" />
                    <c:param name="jspName" value="tower"/>
                </c:url>
                    
                
                <c:url value="/tower/update" var="updateLink" >
                    <c:param name="productId" value="${q.id}" />
                    <c:param name="towerType" value="${types}" /> <!--*************************************** giati towerType??-->
                </c:url>
                

                <c:url value="/tower/delete" var="deleteLink">
                    <c:param name="productId" value="${q.id}" />
                </c:url>
                    
                    
                <!--more information*************************************************************************************-->
                <c:url value="/tower/information/${q.id}" var="informationLink">
                    <c:param name="productId" value="${q.id}" />
                </c:url>


                <div class="divproduct row col-12 col-md-12">

                    <img class="col-12 col-md-2 my-2" src="http://localhost:8080/images/${q.id}.jpg" alt="TODO" >

                    <div class="indivproduct col-12 col-md-6">
                        <div>
                            ${q.name}
                        </div>
                        <div class="row">
                            <div class="col-6">Code: ${q.pcode}</div>
                            <div class="col-6">Type: ${towerList[status.index].towerType}</div>

                        </div>
                        <div class="row">
                            <div class="col-6">Category: <a
                                    href="${pageContext.request.contextPath}/tower">
                                    ${q.category}</a></div>
                            <div class="col-6">Manufacturer: ${towerList[status.index].manufacturer}</div>

                        </div>
                    </div>
                    <div class="indivproduct col-12 col-md-2">
                        <div>

                        </div>
                        <div>

                        </div>
                        <div>

                        </div>
                    </div>
                    <div class="indivproduct col-12 col-md-2">

                        <div class="price">
                            Price: <c:if test="${q.sales>0}">
                                <del>${q.price}€</del>
                            </c:if>
                            ${q.price-q.sales}€
                        </div>
                        <security:authorize access="hasRole('ADMIN')">
                   
                        <div>
                            <button><a href="${updateLink}">Update</a></button>
                        </div>
                        <div>
                            <button><a href="${deleteLink}">Delete</a></button>
                        </div>
                        </security:authorize>
                        <!--more information******************************************************************************************-->
                        <div>
                            <button><a href="${informationLink}">See more</a></button>
                        </div>
                        <div class="btncart">
                            <a class="btn btn-danger" href="${addToCart}" role="button">Add to Cart</a>                        
                        </div>
                    </div>

                </div>
                
            </c:forEach>
        </div>

        <%--<jsp:include page="footer.jsp"/>--%>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    </body>
</html>
