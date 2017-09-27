$("#createButton").click(function() {
    var guestNumber = $("#guestNumber").val();
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var address = $("#address").val();
    var postalCode = $("#postalCode").val();
    var city = $("#city").val();
    var country = $("#country").val();
    var phoneNumber = $("#phoneNumber").val();
    var email = $("#email").val();

    var url = "/api/addGuest?guestNumber=" + guestNumber + "&firstName=" + firstName + "&lastName" + lastName + "&address" + address
     + "&postalCode" + postalCode + "&city" + city + "&country" + country + "&phoneNumber" + phoneNumber + "&email" + email  ;
    $.get(url, function(data) {
        console.log(data);
        $("#outputField").html("Guest" + data.firstName + data.lastName " created")
    });
});