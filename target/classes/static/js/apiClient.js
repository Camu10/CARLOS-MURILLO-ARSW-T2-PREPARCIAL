const url = "http://localhost:8080/api";
var apiClient = (function () {
    return {
        getAirportsByName: function (name,callback) {
            var urlApp = url + "/airports/"+name;
            $.ajax({
                url: urlApp,
                type: "GET",
                success:function(res){
                    callback(res);
                },
                error:function (err) {
                    alert(err + " fetch error");
                }
            });
        }

    };
})();