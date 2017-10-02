$("#createButton").click(function() {
    var obj = {
        guestNumber: $("#guestNumber").val(),
        firstName: $("#firstName").val(),
        lastName: $("#lastName").val(),
        address: $("#address").val(),
        postalCode: $("#postalCode").val(),
        city: $("#city").val(),
        country: $("#country").val(),
        phoneNumber: $("#phoneNumber").val(),
        email: $("#email").val(),
    }

    console.log(obj);

   $.ajax({
       url: "/api/addGuest",                            // deze zoekt hij mee in de restcontroller naar een POST methode met die url.
       type: "POST",
       data: JSON.stringify(obj),                      // zet het object om in Json String.
       contentType: "application/json; charset=utf-8", // standaard waarde
       success: function(result) {
       console.log(result);
       $("#outputField1").html("Guest added "); // resultaat in gedefineerd HTML veld.
        $("#outputField2").html(result.guestNumber);
         $("#outputField3").html(result.firstName);
          $("#outputField4").html(result.lastName);
           $("#outputField5").html(result.address);
            $("#outputField6").html(result.postalCode);
             $("#outputField7").html(result.city);
              $("#outputField8").html(result.country);
               $("#outputField9").html(result.phoneNumber);
                $("#outputField10").html(result.email);
       },
       error: function(err) {
       console.log(err);
       }

   });


});

$("#getButton").click (function(){
    var firstName = $("#firstName2").val();
    var lastName = $("#lastName2").val();
    var url = "/api/getGuest?firstName=" + firstName +"&lastName=" + lastName;

    $.get(url, function(result){
        console.log(result);
               $("#outputField11").html("Information Guest: "); // resultaat in gedefineerd HTML veld.
                $("#outputField12").html(result.guestNumber);
                 $("#outputField13").html(result.firstName);
                  $("#outputField14").html(result.lastName);
                   $("#outputField15").html(result.address);
                    $("#outputField16").html(result.postalCode);
                     $("#outputField17").html(result.city);
                      $("#outputField18").html(result.country);
                       $("#outputField19").html(result.phoneNumber);
                        $("#outputField20").html(result.email);

    });

});

