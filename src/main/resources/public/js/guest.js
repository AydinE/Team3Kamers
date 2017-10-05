
$(".createButton").on('click', function(event){
    var obj = {
        firstName: $("#guest-firstName").val(),
        lastName: $("#guest-lastName").val(),
        address: $("#guest-address").val(),
        postalCode: $("#guest-postalCode").val(),
        city: $("#guest-city").val(),
        country: $("#guest-country").val(),
        phoneNumber: $("#guest-phoneNumber").val(),
        email: $("#guest-email").val(),
    }

    console.log(obj);

   $.ajax({
       url: "/api/addGuest",                            // deze zoekt hij mee in de restcontroller naar een POST methode met die url.
       type: "POST",
       data: JSON.stringify(obj),                      // zet het object om in Json String.
       contentType: "application/json; charset=utf-8", // standaard waarde
       success: function(result) {
       console.log(result);
        $("#guest-outputField1").html(result.guestNumber); // resultaat in gedefineerd HTML veld.
         $("#guest-outputField2").html(result.firstName);
          $("#guest-outputField3").html(result.lastName);
           $("#guest-outputField4").html(result.address);
            $("#guest-outputField5").html(result.postalCode);
             $("#guest-outputField6").html(result.city);
              $("#guest-outputField7").html(result.country);
               $("#guest-outputField8").html(result.phoneNumber);
                $("#guest-outputField9").html(result.email);
       },
       error: function(err) {
       console.log(err);
       }

   });


});

    // remove guest api, nog de REMOVE button in HTML tabel aanmaken.
$(".removeButton").on('click', function(event){

    var guestNumber = $("#....").val();
    var url = "/api/getGuest?guestNumber=" + guestNumber;
    $.get(url, function(data){
        console.log(data);

        // POST in get methode, omdat eerst de Getter moet worden uitgevoerd. De uitvoering verloopt asynchroon.
        $.ajax({
           url: "/api/removeGuest",                            // deze zoekt hij mee in de restcontroller naar een POST methode met die url.
           type: "POST",
           data: JSON.stringify(obj),                      // zet het object om in Json String.
           contentType: "application/json; charset=utf-8", // standaard waarde
           success: function(result) {
                console.log(result);
               },
           error: function(err) {
                console.log(err);
           }

       });
     }





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

