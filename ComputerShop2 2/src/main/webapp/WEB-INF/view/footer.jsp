<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Footer</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
    <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.1.1/css/ol.css" type="text/css">

    <script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.1.1/build/ol.js"></script>


</head>

<body>

    <footer class="container-fluid mt-5 p-4">
        <div class="rowmap">
            <div class="row">
                <div>
                    <h3 class="footer-title ml-5">Information</h3>
                    <ul class="footer-links ml-2">
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Contact Us</a></li>
                        <li><a href="#">Orders and Returns</a></li>
                        <li><a href="#">Terms & Conditions</a></li>
                    </ul>
                </div>
                <div>
                    <h3 class="footer-title ml-5">About Us</h3>
                    <ul class="footer-links">
                        <li><a href="#"><i class="fa fa-map-marker"></i>39 Panepistimiou street, 10564, Athens</a></li>
                        <li><a href="#"><i class="fa fa-phone"></i>+210-25-25-255</a></li>
                        <li><a href="#"><i class="fa fa-envelope-o"></i>info@geekshop.com</a></li>
                    </ul>
                </div>

            </div>
            <div class="row">
                <div id="map" class="map"></div>
            </div>
        </div>

        <div id="footfooter">
            <div>
                <i class="fa fa-youtube"></i>
                <i class="fa fa-twitter-square"></i>
                <i class="fa fa-facebook-square"></i>
            </div>
            <div>
                Copyright &copy;
                <script>document.write(new Date().getFullYear());</script>
            </div>
        </div>
    </footer>

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
        // 1) Declare success function
        function success(position) {
            console.log(position);
            // position.coords.latitude, position.coords.longitude
            const map = new ol.Map({
                target: 'map',
                layers: [
                    new ol.layer.Tile({
                        source: new ol.source.OSM()
                    })
                ],
                view: new ol.View({
                    center: ol.proj.fromLonLat([
                        //position.coords.longitude, position.coords.latitude
                        23.732193, 37.980813
                    ]),
                    zoom: 17
                })
            });
        }


        // 2) Declare error function
        function error(err) {
            console.log(err);
        }
        // 3) Check if geolocation API is available
        if (navigator.geolocation) {
            // 4) Use navigator.geolocation.getCurrentPosition( SUCCESS, ERROR )
            navigator.geolocation.getCurrentPosition(success, error);
            // async/await

        } else {
            // Inform the user about unavailable service
        }
    </script>

</body>

</html>