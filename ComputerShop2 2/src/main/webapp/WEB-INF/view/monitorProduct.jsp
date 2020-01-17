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
        <title>${product.id}</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />



    </head>


    <body>

        <jsp:include page="header.jsp"/>

        <div class="container mt-5">
            <div class="row col-12 col-sm-12 col-lg-12 mt-5">
                <div class="product col-4 col-sm-4 col-lg-4">
                    <img src="http://localhost:8080/images/${gpu.id}.jpg" alt="TODO" height="100%" width="100%">
                </div>
                <div class="col-8 col-sm-8 col-lg-8">
                    <div>
                        <h4>
                            ${product.name}
                        </h4>
                    </div>
                    <div>
                        <ul class="oneprodatributes">
                            <li>Code: ${product.pcode}</li>
                            <li>Size: ${monitorSize}"</li>
                            <li>Frequency: ${monitorFrequency}Hz</li>
                            <li>Manufacturer: ${monitorManufacturer}</li>
                            <li>Category:${product.category}</li>
                        </ul>
                        <div class="">
                            <div class="price">
                                Price: <c:if test="${product.sales>0}">
                                    <del>${product.price}€</del>
                                </c:if>
                                ${product.price-product.sales}€
                            </div>
                            <div>
                                <div class="btncart">
                                    <a class="btn btn-danger" href="${addToCart}" role="button">Add to Cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-sm-12 col-lg-12 mt-4 mb-5">
                <h5>Description</h5>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia possimus veniam, reiciendis eaque
                    expedita cum natus, consectetur nulla corrupti ratione atque laboriosam doloribus doloremque sunt nisi
                    assumenda distinctio! Debitis, rem?</p>
            </div>
        </div>
    
        <div class="container mt-5">
            <H3>RELATED PRODUCTS</H3>
            <c:forEach items="${listOfProducts}" var="q">
                <c:url var="addToCart" value="/product/addToCart">
                    <c:param name="productId" value="${q.id}" />
                </c:url>
                <div class="relatedproduct col-12 col-sm-12 col-lg-3">
    
                    <img src="http://localhost:8080/images/${q.id}.jpg" alt="TODO" height="100%" width="100%" />
                    
                    <div>
                        <a href="#">${q.name}</a>
                    </div>
    
                    <div>
                        Price: <c:if test="${p.sales>0}">
                            <del>${q.price}€</del>
                        </c:if>
                        ${q.price-q.sales}€
                    </div>
                    
                    <a class="btn btn-danger" href="${addToCart}" role="button">Add to Cart</a>
    
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
