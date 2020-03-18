document.addEventListener('DOMContentLoaded', function () {
    if (document.querySelectorAll('#map').length > 0)
    {
        if (document.querySelector('html').lang)
            lang = document.querySelector('html').lang;
        else
            lang = 'en';

        var js_file = document.createElement('script');
        js_file.type = 'text/javascript';
        js_file.src = 'https://maps.googleapis.com/maps/api/js?&signed_in=true&language=' + lang;
        document.getElementsByTagName('head')[0].appendChild(js_file);
    }
});
var app = (function () {

    var map;
    var markers;
    var bounds;

    var _genTable = function(info){
        $("#table > tbody").empty();
        info.map(function(airport){
            $("#table > tbody").append(
                "<tr> <td>" +
                airport.code +
                "</td>" +
                "<td>" +
                airport.name +
                "</td>" +
                "<td>" +
                airport.city +
                "</td>" +
                "<td>" +
                airport.countryCode +
                "</td>" +
                "</tr>"
            );
        });
    };

    function plotMarkers(m) {
        _genTable(m);
        markers = [];
        bounds = new google.maps.LatLngBounds();

        m.map(function (marker) {
            var position = new google.maps.LatLng(marker.location.latitude, marker.location.longitude);

            markers.push(
                new google.maps.Marker({
                    position: position,
                    map: map,
                    animation: google.maps.Animation.DROP
                })
            );

            bounds.extend(position);
        });

        map.fitBounds(bounds);
    }

    function clearMap(){
        if (markers){
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
        }
    }

    function initMap(){
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 8
        });

    };

    function getAirpotsByCity(name) {
        clearMap();
        apiClient.getAirportsByName(name,plotMarkers);
    }
    
    return {
        getAirpotsByCity: getAirpotsByCity,
        initMap:initMap,
        plotMarkers:plotMarkers,
        _genTable:_genTable
    }
})();