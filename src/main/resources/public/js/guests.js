var table;

$(document).ready( function () {
    table = $("#dataTable").DataTable({searching: false});
    refreshTable();
    $("#addButton").click(function() {
        createGuest();


    });
});

function createGuest() {
    var obj = {
        firstName: $("#addFirstName").val(),
        lastName: $("#addLastName").val(),
        address: $("#addAddress").val(),
        postalCode: $("#addPostalCode").val(),
        city: $("#addCity").val(),
        country: $("#addCountry").val(),
        phoneNumber: $("#addPhoneNumber").val(),
        email: $("#addEmail").val()
    };
    makeAjaxRequest("POST", "/addGuest", obj, function(guest) {
        $("#dataTable tbody").append("<tr><td>" + guest.id +
            "</td><td>" + guest.firstName +
            "</td><td>" + guest.lastName +
            "</td><td>" + guest.address +
            "</td><td>" + guest.postalCode +
            "</td><td>" + guest.city +
            "</td><td>" + guest.country +
            "</td><td>" + guest.phoneNumber +
            "</td><td>" + guest.email +
            "</td><td>" + "<a href=\"javascript:del(" + guest.id + ")\" class=\"btn btn-danger\">Delete</a>" +
            "</td></tr>");
        refreshTable();
    });
    $.alert({
                title: 'Added Guest!',
                content: '',
            });
}

function populateTable() {
    var endpoint = "/getGuestList";
    makeGetRequest(endpoint, function(guests) {
        $.each(guests, function(key, guest) {
            $("#dataTable tbody").append("<tr><td>" + guest.id +                                                    // Hier wordt de MODAL aangeroepen # guestModal
                "</td><td>" + "<a href=\"javascript:edit(" + guest.id + ")\" class=\"btn btn-danger\"data-toggle=\"modal\" data-target=\"#guestModal\">Edit</a>" +
                "</td><td>" + guest.firstName +
                "</td><td>" + guest.lastName +
                "</td><td>" + guest.address +
                "</td><td>" + guest.postalCode +
                "</td><td>" + guest.city +
                "</td><td>" + guest.country +
                "</td><td>" + guest.phoneNumber +
                "</td><td>" + guest.email +
                "</td><td>" + "<a href=\"javascript:del(" + guest.id + ")\" class=\"btn btn-danger\">Delete</a>" +
                "</td></tr>");

        });
    });
}

function refreshTable() {
    table.clear();
    populateTable();
    table.draw();
}

function del(id) {
    var r = confirm("Weet je zeker dat je de informatie van \"naam\" wilt verwijderen?");
    if (r == true) {
        $.ajax({url: "/api/removeGuest/" + id, type: "DELETE"}).done( function() {
                refreshTable();
            })
        }
}

// links nog kloppend maken
function edit(id){
    $("#btnAddGuest").hide();
    $("#btnUpdateGuest").show();
    $.get({url:"/api/getGuest/?id="+id, type:"GET"}).done( function(result) {
        console.log(result);
        $("#id").val(result.guestId);
        $("#editFirstName").val(result.firstName);
        $("#editLastName").val(result.lastName);
        $("#editPhoneNumber").val(result.phonenumber);
        $("#editEmailAddress").val(result.email);
        $("#editAddress").val(result.adress);
        $("#editZipCode").val(result.postalCode);
        $("#editCity").val(result.city);
        $("#editCountry").val(result.country);
      //$("#guestModal").modal();
    })


}


// When the user clicks on <div>, open the popup
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}