
var apiClient = (function () {
    var getAirportsByName = (function (name,callback) {

        axios({
            method: 'GET',
            url: '/api/airports/'+name,

        })
            .then(response => callback(response.data))
            .catch(error => console.log(error));
    });

    return{
        getAirportsByName:getAirportsByName,
    }
})();