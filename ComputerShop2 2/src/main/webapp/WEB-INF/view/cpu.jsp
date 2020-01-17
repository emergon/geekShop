<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>CPU</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
    </head>


    <body>

        <jsp:include page="header.jsp"/>

        <div id="form" class="container" >
            <h3>Add new Cpu</h3>
            <form:form 
                action="${pageContext.request.contextPath}/cpu/add" 
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
                <select name="manufacturer">
                    <optgroup label = "Select Manufacturer">
                        <c:forEach items="${manufacturers}" var="m"> 
                            <option  value="${m.getId()}">${m.name}</option>
                        </c:forEach>
                    </optgroup>
                </select>
                <br>
                <br>
                <select name = "chip">
                    <optgroup label = "Select Chip">
                        <c:forEach items="${chips}" var="c"> 
                            <option value="${c.getId()}">${c.name}</option>
                        </c:forEach>
                    </optgroup>
                </select>
                <br>
                <br>
                <select name = "cores">
                    <optgroup label = "Select Cores">
                        <c:forEach items="${cores}" var="c"> 
                            <option value="${c}">${c}</option>
                        </c:forEach>
                    </optgroup>
                </select>
                <br>
                <br>
                <p>
                    select a file to upload:
                    <input type="file" name="file"/>
                    <br/>
                </p>

                <input type="submit" value="Register">

            </form:form>
        </div>

        <div class="container mt-5">
            <h3>Filters:</h3>

            <div class="btn-group">
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Price
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/cpu/price/0/100">up to 100€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/cpu/price/100/200">100€ up to 200€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/cpu/price/200/300">200€ up to 300€</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/cpu/price/300/400">300€ up to 400€</a>
                    </div>
                </div>
                <!--MANUFACTURER-->
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Manufacturer
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${manufacturers}" var="p">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/cpu/manufacturer/${p.getId()}">${p.name}</a>
                        </c:forEach>
                    </div>
                </div>
                <!--CHIP-->
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Chip
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${chips}" var="p">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/cpu/chip/${p.getId()}">${p.name}</a>

                        </c:forEach>
                    </div>
                </div>


                <!--CORES-->
                <div class="btn-group">
                    <button type="button" class="btn bg-dark dropdown-toggle" data-toggle="dropdown">
                        Number of Cores
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <c:forEach items="${cores}" var="p">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/cpu/cores/${p}">${p}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>


        <div class="container mt-5">
            <h3 class="text-md-left">CPU</h3>
            <c:forEach items="${productList}" var="q" varStatus="status">
                <c:url var="addToCart" value="/cart/addToCart">
                    <c:param name="productId" value="${q.id}" />
                    <c:param name="jspName" value="cpu"/>
                </c:url>
                <div class="divproduct row col-12 col-md-12">

                    <img class="col-12 col-md-2 my-2" src="http://localhost:8080/images/${q.id}.jpg" alt="TODO">

                    <div class="indivproduct col-12 col-md-6">
                        <div>
                            ${q.name}
                        </div>
                        <div class="row">
                            <div class="col-6">Code: ${q.pcode}</div>
                            <div class="col-6">Cores: ${cpuList[status.index].getCoresNumber()}</div>

                        </div>
                        <div class="row">
                            <div class="col-6">Category: <a
                                    href="${pageContext.request.contextPath}/cpu">
                                    ${q.category}</a></div>
                            <div class="col-6">  Manufacturer: ${cpuList[status.index].cpuManufacturer}</div>

                        </div>
                    </div>
                    <div class="indivproduct col-12 col-md-2">
                        <div>

                        </div>
                        <div>

                        </div>
                        <div>
                            Chip: ${cpuList[status.index].cpuChip}<br/>
                        </div>
                    </div>
                    <div class="indivproduct col-12 col-md-2">
                        <div class="price">
                            Price: <c:if test="${q.sales>0}">
                                <del>${q.price}€</del>
                            </c:if>
                            ${q.price-q.sales}€
                        </div>

                        <div class="btncart">
                            <a class="btn btn-danger" href="${addToCart}" role="button">Add to Cart</a>
                        </div>
                    </div>

                </div>

            </c:forEach>
        </div>



        <jsp:include page="footer.jsp"/>

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
