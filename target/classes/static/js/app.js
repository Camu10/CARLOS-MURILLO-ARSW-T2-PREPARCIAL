var app = (function () {

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

    function getAirpotsByCity(name) {
        apiClient.getAirportsByName(name,_genTable);
    }
    
    return {
        getAirpotsByCity: getAirpotsByCity,
    }
})();