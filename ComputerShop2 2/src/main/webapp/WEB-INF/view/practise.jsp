<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            type="text/css" rel="stylesheet"
            href="${pageContext.request.contextPath}/static/css/style.css">
    </head>


    <body>

        <a href="${pageContext.request.contextPath}/product/displayAllProducts">All Products</a>
        <a href="${pageContext.request.contextPath}/product/displayAllTowers">Tower</a>
        <a href="${pageContext.request.contextPath}/">Motherboard</a>
        <a href="${pageContext.request.contextPath}/">CPU</a>
        <a href="${pageContext.request.contextPath}/">GPU</a>
        <a href="${pageContext.request.contextPath}/">RAM</a>
        <a href="${pageContext.request.contextPath}/">Storage Device</a>
        <a href="${pageContext.request.contextPath}/">PSU</a>
        <a href="${pageContext.request.contextPath}/">Monitor</a>
        <a href="${pageContext.request.contextPath}/">Mouse</a>
        <a href="${pageContext.request.contextPath}/">Keyboard</a>

        <br/>
        <br/>

        <a href="${pageContext.request.contextPath}/product/cart">Shopping Cart </a> : ${countCart}

        <br/>
        <br/>

        <h2>Newest Products</h2>

        <table border="1">
            <tr>
                <td><b>id</b></td>
                <td><b>name</b></td>
                <td><b>Product Code</b></td>
                <td><b>Price</b></td>
                <td><b>Quantity<b></td>
                <td><b>Category</b></td>                            
                            </tr>



                            <c:forEach items="${latestProducts}" var="p">

                                <c:url var="updateLink" value="/product/addToCart">
                                    <c:param name="productId" value="${p.id}" />
                                </c:url>

                                <tr>
                                    <td>${p.id}</td>
                                    <td>${p.name}</td>
                                    <td>${p.pcode}</td>
                                    <td>${p.price}</td>
                                    <td>${p.quantity}</td>
                                    <td>${p.category}</td>
                                    <td><a href="${updateLink}">add To card</a></td>
                                </tr>
                            </c:forEach>

                            </table>

                            <h2>Products with sale</h2>
                            <table border="1">
                                <tr>
                                    <td><b>id</b></td>
                                    <td><b>name</b></td>
                                    <td><b>Product Code</b></td>
                                    <td><b>Price</b></td>
                                    <td><b>Quantity<b></td>
                                                <td><b>Category</b></td>
                                                </tr>
                                                <c:forEach items="${salesProducts}" var="p">
                                                    <c:url var="updateLink" value="/product/addToCart">
                                                        <c:param name="productId" value="${p.id}" />
                                                    </c:url>
                                                    <tr>
                                                        <td>${p.id}</td>
                                                        <td>${p.name}</td>
                                                        <td>${p.pcode}</td>
                                                        <td>${p.price}</td>
                                                        <td>${p.quantity}</td>
                                                        <td>${p.category}</td>
                                                        <td><a href="${updateLink}">add To card</a></td>
                                                    </tr>
                                                </c:forEach>

                                                </table>

                                                <br>
                                                <br>
                                                <br>





                                                <!--
                                                <colgroup>
                                                    <table border ="1" cellpadding = "0" cellspacing = "0" width ="100%">
                                                        <tbody>  
                                                <c:forEach items="${latestProducts}" var="p">
                                                
                                                <tr>
                                                    <td class="web-product-photo"><a href="https://www.e-shop.gr/goniakos-ilektrikos-troxos-einhell-tc-ag-125-850watt-4430619-p-TLS.051011"><img src="${pageContext.request.contextPath}/static/images/10.jpg" alt="goniakos ilektrikos troxos einhell tc ag 125 850watt 4430619 photo" title="goniakos ilektrikos troxos einhell tc ag 125 850watt 4430619 photo" width="110" height="110" border="0"></a></td>
                                                    <td>
                                                        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="web-product-container">
                                                            <tbody>
                                                                <tr>    
                                                                    <td class="web-product-title"><a href="https://www.e-shop.gr/goniakos-ilektrikos-troxos-einhell-tc-ag-125-850watt-4430619-p-TLS.051011" class="web-title-link"><h2>${p.name}</h2></a>&nbsp;&nbsp;<font style="font-size:11px;">(TLS.051011)</font>&nbsp;&nbsp;&nbsp;<font color="#FF0000">ΕΚΠΤΩΣΗ&nbsp;1.04 €</font></td>
                                                                    <td class="web-product-price">
                                                                        <b><s>${p.price}</s> <font style="color:#FF0000">${p.price}</font></b>&nbsp;€		
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="web-product-info"><b>Κατηγορία:</b>&nbsp; ${p.category}&nbsp;&nbsp;&nbsp; <b>Υποκατηγορία:</b>&nbsp; ΗΛΕΚΤΡΙΚΟΙ&nbsp;&nbsp;&nbsp; <b>Κατασκευαστής:</b>&nbsp; EINHELL</td>
                                                                    <td rowspan="2" class="web-product-buttons">
                                                                        <div style="display:block;width:auto;padding:0 5px 7px 0;"><img src="https://www.e-shop.gr/images/crazy-availability.png" style="margin-bottom:-2px;margin-right:4px;"><font class="web-avail-stock-a">Αμεσα&nbsp;διαθέσιμο</font></div><a href="javascript:;" onclick="getcontents('https://www.e-shop.gr/basket.phtml?itemid=TLS.051011&amp;itemquant=1&amp;r=basketshort', 'basketlist');"><img src="https://www.e-shop.gr/images/web-basket-button.png" style="height:38px;width:auto;" border="0"></a>
                                                                        <br><a class="web-add-wishlist" href="https://www.e-shop.gr/wishlist?ins=TLS.051011" rel="nofollow"><div class="wishlist-icon"></div>Wishlist<div class="wishlist-icon-plus">+</div></a>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td class="web-product-info"><b>Χαρακτηριστικά:</b>&nbsp;&nbsp; <spring18>850WATT &nbsp;•&nbsp; 125MM
                                                            </spring18></td></tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                            </table>
                                            </colgroup>--


            <a href="${pageContext.request.contextPath}/">Home Page</a>
    </body>
</html>