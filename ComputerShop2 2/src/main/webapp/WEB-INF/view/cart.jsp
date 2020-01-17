<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    

</head>


<body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous">
        </script>

    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <h3 class="text-md-left">Shopping Cart</h3>
        <div class="divcart ">
            <div class="indivcart">
                <div class="col-sm-1 col-md-1"></div>
                <div class="col-sm-3 col-md-3"><b>Product Name</b></div>
                <div class="col-sm-3 col-md-3"><b>Product Code</b></div>
                <div class="col-sm-2 col-md-2"><b>Quantity</b></div>
                <div class="col-sm-2 col-md-2"><b>Total Price</b></div>
                <div class="col-sm-1 col-md-1"><b></b></div>
            </div>
            <div>
                <c:forEach items="${cart}" var="c">
                    <c:url value="/cart/cartItem/delete" var="deleteLink">
                        <c:param name="productId" value="${c.getProduct().getId()}" />
                    </c:url>
                    <div class="indivcart mb-2">
                        <div class="col-sm-1 col-md-1"><img src="http://localhost:8080/images/${c.getProduct().getId()}.jpg" alt=""></div>
                        <div class="col-sm-3 col-md-3">${c.getProduct().getName()}</div>
                        <div class="col-sm-3 col-md-3">${c.getProduct().getPcode()}</div>
                        <div class="col-sm-2 col-md-2">
                            <div class="input-number">
                                <input type="number" value="${c.getQuantity()}">
                                <span class="qty-up">+</span>
                                <span class="qty-down">-</span>
                            </div>
                        </div>
                        <!-- <div class="col-sm-2 col-md-2">${c.getQuantity()}</div> -->
                        <div class="col-sm-2 col-md-2">${c.getTotalPrice()}</div>
                        <div class="col-sm-1 col-md-1"><a href="${deleteLink}">Delete</a></div>

                    </div>
                </c:forEach>
            </div>
        </div>
        <br>
        <p class="totalPrice"><b>Final Price : ${finalPrice}€</b></p>

    </div>

    <div class="container mt-5">
        <button id="openform" type="button" class="">Billing Information</button>

        <form id="editform">
            
            <div class="insignup">
                <input type="text" class="form-control" value="" placeholder="Firstname" required>
                <input type="text" class="form-control" value="" placeholder="Lastname" required>
            </div>
            <div class="insignup">
                <input type="text" class="form-control" value="" placeholder="Address" required>
                <input type="text" class="form-control" value="" placeholder="City" required>

            </div>
            <div class="insignup">
                <input type="text" class="form-control" value="" placeholder="Country" required>
                <input type="text" class="form-control" value="" placeholder="Zip code" required>

            </div>
            <div class="insignup">
                <input type="text" class="form-control" value="" placeholder="Phone number" required>
                <input type="text" class="form-control" value="" placeholder="Birthday date dd/mm/yyyy" required>
            </div>

        </form>
    </div>

    </div>


    <jsp:include page="footer.jsp" />


    <!-- BootStrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>




    <script>
        "use strict";

        $(document).ready(function () {
            $('#openform').click(function () {
                $('#editform').toggle();
            });
        });


        $('.input-number').each(function () {
            var $this = $(this),
                $input = $this.find('input[type="number"]'),
                up = $this.find('.qty-up'),
                down = $this.find('.qty-down');

            down.on('click', function () {
                var value = parseInt($input.val()) - 1;
                value = value < 1 ? 1 : value;
                $input.val(value);
                $input.change();
                updatePriceSlider($this, value)
            })

            up.on('click', function () {
                var value = parseInt($input.val()) + 1;
                $input.val(value);
                $input.change();
                updatePriceSlider($this, value)
            })
        });

        var priceInputMax = document.getElementById('price-max'),
            priceInputMin = document.getElementById('price-min');

        priceInputMax.addEventListener('change', function () {
            updatePriceSlider($(this).parent(), this.value)
        });

        priceInputMin.addEventListener('change', function () {
            updatePriceSlider($(this).parent(), this.value)
        });

        function updatePriceSlider(elem, value) {
            if (elem.hasClass('price-min')) {
                console.log('min')
                priceSlider.noUiSlider.set([value, null]);
            } else if (elem.hasClass('price-max')) {
                console.log('max')
                priceSlider.noUiSlider.set([null, value]);
            }
        }



    </script>


</body>

</html>