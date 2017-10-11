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
        if (guest) {
            console.log(true);
        console.log(guest);
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
        } else {
            console.log(false);
             alert("some of your input is not correct, please verify your input");
        }
    });
}

function populateTable() {
    var endpoint = "/getGuestList";
    makeGetRequest(endpoint, function(guests) {
        $.each(guests, function(key, guest) {
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