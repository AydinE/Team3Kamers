var table;

$(document).ready(function () {
    table = $("#dataTable").DataTable({searching: false});
    refreshTable();
    $("#addButton").click(function () {
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
    makeAjaxRequest("POST", "/addGuest", obj, function (guest) {
        if (guest) {
            console.log(true);
            console.log(guest);
            refreshTable();
        } else {
            console.log(false);
            alert("some of your input is not correct, please verify your input");
        }
    });
    $.alert({
        title: 'Added Guest!',
        content: '',
    });
}

function populateTable() {
    var endpoint = "/getGuestList";
    makeGetRequest(endpoint, function (guests) {
        $.each(guests, function (key, guest) {
            $("#dataTable tbody").append(                                                 // Hier wordt de MODAL aangeroepen # guestModal
                "<tr><td>" + "<a href=\"javascript:edit(" + guest.id + ")\" class=\"btn btn-danger\">Edit</a>" +
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
        $.ajax({url: "/api/removeGuest/" + id, type: "DELETE"}).done(function () {
            refreshTable();
        })
    }
}

// links nog kloppend maken
function edit(id) {
    $("#btnAddGuest").hide();
    $("#btnUpdateGuest").show();


    $.get({url: "/api/getGuest/?id=" + id, type: "GET"}).done(function (result) {
        console.log(result);
        console.log($("#editFirstName"));
        $("#id").val(result.id);
        $("#editFirstName").val(result.firstName);
        $("#editLastName").val(result.lastName);
        $("#editPhoneNumber").val(result.phoneNumber);
        $("#editEmail").val(result.email);
        $("#editAddress").val(result.address);
        $("#editPostalCode").val(result.postalCode);
        $("#editCity").val(result.city);
        $("#editCountry").val(result.country);
        $("#guestModal").modal("toggle");
    })
}


// de EDIT knop hier werkend krijgen, zie vorige apps
$("#btnUpdateGuest").click(function () {
    var obj = {
        id: $("#id").val(),
        firstName: $("#editFirstName").val(),
        lastName: $("#editLastName").val(),
        address: $("#editAddress").val(),
        postalCode: $("#editPostalCode").val(),
        city: $("#editCity").val(),
        country: $("#editCountry").val(),
        phoneNumber: $("#editPhoneNumber").val(),
        email: $("#editEmail").val()
    };

    console.log(obj);

    $.ajax({
        url: "/api/changeGuest/",
        method: "PUT",
        data: JSON.stringify(obj),
        contentType: "application/json; charset=utf-8"
    }).done(function () {
        $("#guestModal").modal("toggle");
        $("#guestModal input").val("");
        refreshTable();
    })


})


// When the user clicks on <div>, open the popup
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}

